package com.secure.secure_back_end.configuration.security;

import com.secure.secure_back_end.configuration.security.jwt.JWTConfigurer;
import com.secure.secure_back_end.configuration.security.jwt.TokenProvider;
import com.secure.secure_back_end.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{


    private final UserService userDetailsService;
    private final TokenProvider tokenProvider;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfiguration(UserService userDetailsService, TokenProvider tokenProvider, BCryptPasswordEncoder passwordEncoder)
    {
        this.userDetailsService = userDetailsService;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.inMemoryAuthentication().withUser("in_momry_admin")
                .password(this.passwordEncoder.encode("123456a"))
                .roles("ADMIN");
        auth.userDetailsService(this.userDetailsService)
                .passwordEncoder(this.passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {

        http
                .exceptionHandling().accessDeniedPage("/no-access")
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //account management
                .antMatchers("/authorities/**").permitAll() //we use authorities to create new accounts
                .antMatchers("/users/register").anonymous() //for creating a new account
                .antMatchers("/users/register/manager/{managerId:\\d+}").hasAnyRole("PROJECT_MANAGER", "ADMIN") //for creating acc-s with manager_id
                .antMatchers("/users/authenticate").anonymous() //for getting a JWT
                .antMatchers("/admins/*").hasAnyRole("ADMIN") //this for admins to ban users or change their authority
                .antMatchers(HttpMethod.GET, "/users*").authenticated() // this refers "get user details"
                .antMatchers(HttpMethod.PUT, "/users/**").authenticated() // this refers to delete account and change password
                //projects and tickets
                .antMatchers(HttpMethod.GET, "/projects*").authenticated() //read only data for projects
                .antMatchers(HttpMethod.GET, "/projects/**").authenticated() // read only data for projects (this routes are used to display project personal and project tickets)
                .antMatchers(HttpMethod.GET, "/tickets*").authenticated() //read only data for tickets
                .antMatchers(HttpMethod.POST, "/projects/{userId:\\d+}").hasAnyRole("PROJECT_MANAGER", "ADMIN") //create new projects
                .antMatchers(HttpMethod.PUT, "/projects/{userId:\\d+}").hasAnyRole("PROJECT_MANAGER", "ADMIN") //edit projects
                .antMatchers(HttpMethod.PUT, "/projects/qa").hasAnyRole("PROJECT_MANAGER", "ADMIN") //assign-remove qa-s from projects
                .antMatchers(HttpMethod.PUT, "/tickets/{ticketId:\\d+}/qa").hasAnyRole("QA") //qa edit ticket
                .antMatchers(HttpMethod.PUT, "/tickets/{ticketId:\\d+}/developer").hasAnyRole("DEVELOPER") //developer edit ticket
                .antMatchers(HttpMethod.PUT, "/tickets/{ticketId:\\d+}/manager").hasAnyRole("PROJECT_MANAGER", "ADMIN") //project_manager,admin edit ticket
//               //comments
                .antMatchers(HttpMethod.GET, "/comments*").authenticated()
                .antMatchers("/comments/*").authenticated()
                //actuator
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/actuator/shutdown").hasAnyRole()
                .antMatchers("/**").authenticated()//make every other request authenticated
                .and()
                .apply(securityConfigurerAdapter());
        http.cors(); //use the my CORS configuration
    }

    private JWTConfigurer securityConfigurerAdapter()
    {
        return new JWTConfigurer(tokenProvider);
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception
    {

        return super.authenticationManager();
    }

    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }
}
