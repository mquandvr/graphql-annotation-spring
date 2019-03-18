//package com.mqdvr.graphql.config;
//
//import javax.annotation.Resource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.oauth2.provider.ClientDetailsService;
//import org.springframework.security.oauth2.provider.approval.ApprovalStore;
//import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
//import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
//import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
////@Configuration
////@EnableWebSecurity
////@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
//
////    @Resource(name = "userService")
////    private UserDetailsService userDetailsService;
////
////    @Autowired
////    private ClientDetailsService clientDetailsService;
////
////    @Override
////    @Bean
////    public AuthenticationManager authenticationManagerBean() throws Exception {
////        return super.authenticationManagerBean();
////    }
////
////    @Autowired
////    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
////    }
////
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http
////            .csrf().disable()
////            .anonymous().disable()
////            .authorizeRequests()
////            .antMatchers("/graphql").permitAll();
////    }
////
////    @Bean
////    public TokenStore tokenStore() {
////        return new InMemoryTokenStore();
////    }
////
////    @Bean
////    @Autowired
////    public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore) {
////        TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
////        handler.setTokenStore(tokenStore);
////        handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
////        handler.setClientDetailsService(clientDetailsService);
////        return handler;
////    }
////
////    @Bean
////    @Autowired
////    public ApprovalStore approvalStore(TokenStore tokenStore) {
////        TokenApprovalStore store = new TokenApprovalStore();
////        store.setTokenStore(tokenStore);
////        return store;
////    }
////
////    @Bean
////    public BCryptPasswordEncoder encoder() {
////        return new BCryptPasswordEncoder();
////    }
////
////    @Bean
////    public FilterRegistrationBean<CorsFilter> corsFilter() {
////        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        /*
////         * CorsConfiguration config = new CorsConfiguration();
////         * config.setAllowCredentials(true); //config.addAllowedOrigin("*");
////         * //config.addAllowedOrigin("http://dev.maxb.vn");
////         * config.addAllowedHeader("*"); config.addAllowedMethod("*");
////         * source.registerCorsConfiguration("/**", config); FilterRegistrationBean bean
////         * = new FilterRegistrationBean(new CorsFilter(source)); bean.setOrder(0);
////         */
////        CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
////        config.addAllowedOrigin("*");
////        config.addAllowedMethod(HttpMethod.POST);
////        source.registerCorsConfiguration("/**", config);
////        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(source));
////        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
////        return bean;
////    }
//
//}
