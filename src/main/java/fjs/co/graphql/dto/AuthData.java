package fjs.co.graphql.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AuthData implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;
}
