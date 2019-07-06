ongnuoc=(state)=>{
            num = 0;
            interupt = 20000;
            wired = []
            sources = GetAllSources(state);
            for(s of sources)
            {
                var con = GetConnections(state, s);
                for (c of con)
                {
                    seq = []
                    seq.push(s);
                    seq.push(c);
                    prev = [ s[0], s[1] ];
                    cur = [ c[0], c[1] ];


                    while (true)
                    {
                        next = GetNext(state, state[cur[0]][cur[1]], cur, prev);     
					
                        if (next == null || (upper(state[next[0]][next[1]]) &&
                            state[next[0]][next[1]] != (state[seq[0][0]][seq[0][1]]).toUpperCase()))
                        {
                            interupt = interupt > seq.length - 1 ? seq.length - 1 : interupt;
                            break;
                        }
                        if (upper(state[next[0]][next[1]])) break;
                        prev = cur;
                        cur = next;
                        seq.push(next);
                    }

                    wired.push(seq.filter(x => /^[-+]?\d*$/.test(state[x[0]][x[1]])));

                }
            }
					
            filled=[];
            if (interupt == 20000)
                for(s of wired)
                    filled=filled.concat(s)
            else
                for (s of wired)
                    for (i = 0; i < s.length && i < interupt; i++)
                        filled.push(s[i]);
			filled=filled.reduce((x, y) => x.find(a=> a[0]==y[0] && a[1]==y[1]) != null ? x : [...x, y], [])
			
            return filled.length * (interupt == 20000 ? 1 : -1);
        }

GetNext=(state, pipe, coord, prev) => {
            neighb = [];
            i = coord[0];
            j = coord[1];
            pi = prev[0];
            pj = prev[1];

            switch (pipe)
            {
                case '1':
                    neighb = (pi == i - 1 && pj == j) ? [ i + 1, j ] : [ i - 1, j ];
                    break;
                case '2':
                    neighb = (pi == i && pj == j - 1) ?
                        [ i, j + 1 ]:
                        [ i, j - 1 ]
                    break;
                case '3':
                    neighb = (pi == i + 1 && pj == j) ?
                        [ i, j + 1 ]:
                        [ i + 1, j ]
                    break;
                case '4':
                    neighb = (pi == i + 1 && pj == j) ?
                        [ i, j - 1 ] :
                        [ i + 1, j ];
                    break;
                case '5':
                    neighb = (pi == i && pj == j - 1) ?
                        [ i - 1, j ] :
                        [ i, j - 1 ];
                    break;
                case '6':
                    neighb = (pi == i - 1 && pj == j) ?
                        [ i, j + 1 ] :
                        [ i - 1, j ];
                    break;
                case '7':
                    neighb = (pi == i) ?
                        GetNext(state, '2', coord, prev) :
                        GetNext(state, '1', coord, prev);
                    break;
            }
			
            return (!IsCoordNormal(state, neighb) ||
                !AreMerging(state[coord[0]][coord[1]], state[neighb[0]][neighb[1]], coord, neighb) ||
                !AreMerging(state[neighb[0]][neighb[1]], state[coord[0]][coord[1]], neighb, coord)) ? null : neighb;
        }

GetConnections=(state, coord)=>{
            i = coord[0];
            j = coord[1];
            res = [];
            res.push([ i, j - 1 ]);
            res.push([ i, j + 1 ]);
            res.push([ i - 1, j ]);
            res.push([ i + 1, j ]);

            res = res.filter(x => IsCoordNormal(state, x))
								 
			 res = res.filter(x => (x[0] == i + 1 && regex("1567",(state[x[0]][x[1]]))) ||
			 (x[0] == i - 1 && regex("1347",(state[x[0]][x[1]])) )||
			 (x[1] == j - 1 && regex("2367",(state[x[0]][x[1]]))) ||
			 (x[1] == j + 1 && regex("2457",(state[x[0]][x[1]])))
			 )

            return res;
        }

GetAllSources=(state) => {
            sr = []
            for (i = 0; i < length(state); i++)
                for (j = 0; j < length(state[0]); j++)
                    if (lower(state[i][j])) sr.push([ i, j ]);

            return sr;
        }

IsCoordNormal=(state, coord) => {
            if (!coord) return false;

            yOK = coord[0] >= 0 && coord[0] < state.length;
            xOK = coord[1] >= 0 && coord[1] < state[0].length;
            return xOK && yOK;
        }

AreMerging = ((e, r, a, t) => {
    switch (e) {
        case "1":
            return a[1] == t[1] && 1 == Math.abs(a[0] - t[0]);
        case "2":
            return a[0] == t[0] && 1 == Math.abs(a[1] - t[1]);
        case "3":
            return a[0] == t[0] && t[1] - a[1] == 1 || a[1] == t[1] && t[0] - a[0] == 1;
        case "4":
            return a[0] == t[0] && t[1] - a[1] == -1 || a[1] == t[1] && t[0] - a[0] == 1;
        case "5":
            return a[0] == t[0] && t[1] - a[1] == -1 || a[1] == t[1] && t[0] - a[0] == -1;
        case "6":
            return a[0] == t[0] && t[1] - a[1] == 1 || a[1] == t[1] && t[0] - a[0] == -1;
        case "7":
            return a[1] == t[1] && 1 == Math.abs(a[0] - t[0]) || a[0] == t[0] && 1 == Math.abs(a[1] - t[1]);
        case "0":
            return !1;
        default:
            return !lower(e) && !lower(r)
    }
});
length=(x)=>x.length
lower=(x)=>/^[a-z]*$/.test(x)
upper=(x)=>/^[A-Z]*$/.test(x)
regex=(x, y)=>new RegExp(`^[${x}]*$`).test(y)
