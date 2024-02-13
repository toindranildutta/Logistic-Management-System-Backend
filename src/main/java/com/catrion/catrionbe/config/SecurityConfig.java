package com.catrion.catrionbe.config;

 import com.catrion.catrionbe.filter.CustomAuthenticationFilter;
import com.catrion.catrionbe.filter.CustomAuthorizationFilter;
import com.catrion.catrionbe.repository.ActiveTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebMvc
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String[] OPEN_ENDPOINTS = {
            "/v2/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/configuration/**",
            "/webjars/**",
            "/public",
            "/swagger-ui/**",
            "/storage/download/**"
    };
 //   private final UserDetailsService userDetailsService;
    //private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Value("${system.jwt.secret}")
    private String jwtSecret;
    @Value("${system.jwt.expiration.days}")
    private String jwtExpirationDays;
    @Value("${system.login.path}")
    private String loginPath;
    @Autowired
    private ActiveTokenRepository activeTokenRepository;

    
	/*
	 * public SecurityConfig(UserDetailsService userDetailsService,
	 * BCryptPasswordEncoder bCryptPasswordEncoder) {
	 * System.out.println(" Step 4.1 "); this.userDetailsService =
	 * userDetailsService; this.bCryptPasswordEncoder = bCryptPasswordEncoder; }
	 */

	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { auth.userDetailsService(userDetailsService).passwordEncoder(
	 * bCryptPasswordEncoder); }
	 */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	System.out.println(" Step 4 ");
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean(), jwtSecret, jwtExpirationDays, activeTokenRepository);
        customAuthenticationFilter.setFilterProcessesUrl(loginPath);
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers(loginPath + "/**").permitAll();
        http.authorizeRequests().antMatchers(OPEN_ENDPOINTS).permitAll();
//        http.authorizeRequests().antMatchers(GET, "/user/**").hasAnyAuthority(Role.MANAGE_USER.name());
//        http.authorizeRequests().antMatchers(GET, "/export/**", "/key-list/**").hasAnyAuthority(Role.DATA_EXPORT.name());
        //http.authorizeRequests().anyRequest().authenticated();
        //http.addFilter(customAuthenticationFilter);
    //    http.addFilterBefore(new CustomAuthorizationFilter(jwtSecret, loginPath, activeTokenRepository), UsernamePasswordAuthenticationFilter.class);
         http.cors();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
    	System.out.println(" Step 5 ");
        return super.authenticationManagerBean();
    }

}
