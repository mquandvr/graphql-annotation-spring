package fjs.co.graphql.security.config;

import javax.annotation.Resource;

import fjs.co.graphql.security.jwt.JwtFilter;
import fjs.co.graphql.security.jwt.JwtTokenUtil;
import fjs.co.graphql.security.jwt.JwtUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "userService")
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(jwtUserDetailsService)
                .passwordEncoder(passwordEncoderBean());
    }

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().exceptionHandling().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                    .antMatchers("/graphiql/**").permitAll()
                    .antMatchers("/h2-console/**").permitAll()
                    .antMatchers("/graphql/**").permitAll()
                .anyRequest().authenticated().and()
                .formLogin().and().httpBasic();

        httpSecurity.headers().frameOptions().disable();

        // check jwt token.
        JwtFilter authenticationFilter = new JwtFilter(userDetailsService(),
                jwtTokenUtil);
        httpSecurity.addFilterBefore(authenticationFilter,
                UsernamePasswordAuthenticationFilter.class);
    }

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder())
                .withUser("mi3o").password("12345").roles("USER")
                .and()
                .withUser("admin").password("12345").roles("USER", "ADMIN");
    }

    //
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        /*
         * CorsConfiguration config = new CorsConfiguration();
         * config.setAllowCredentials(true); //config.addAllowedOrigin("*");
         * //config.addAllowedOrigin("http://dev.maxb.vn");
         * config.addAllowedHeader("*"); config.addAllowedMethod("*");
         * source.registerCorsConfiguration("/**", config);
         * FilterRegistrationBean bean = new FilterRegistrationBean(new
         * CorsFilter(source)); bean.setOrder(0);
         */
        CorsConfiguration config = new CorsConfiguration()
                .applyPermitDefaultValues();
        config.addAllowedOrigin("*");
        config.addAllowedMethod(HttpMethod.POST);
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(
                new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
