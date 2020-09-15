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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.pattern.PathPatternParser;

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
                //login/register/landing-age  Public routes
                .antMatchers("/authorities/**").permitAll()
                .antMatchers("/users/register").anonymous()
                .antMatchers("/users/manager/register").hasAnyRole("PROJECT_MANAGER", "ADMIN")
                .antMatchers("/users/authenticate").anonymous()
                //admins-only change user password, lock users account
                .antMatchers("/admins/*").hasAnyRole("ADMIN")

                .antMatchers(HttpMethod.GET, "/users*").authenticated()
                .antMatchers(HttpMethod.GET, "/projects*").authenticated()//todo adjust security
                .antMatchers(HttpMethod.GET, "/tickets*").authenticated() //todo adjust security
                .antMatchers(HttpMethod.POST, "/projects/{userId:\\d+}").hasAnyRole("PROJECT_MANAGER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/projects/{userId:\\d+}").hasAnyRole("PROJECT_MANAGER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/projects/qa").hasAnyRole("ADMIN")
                .antMatchers("/comments/**").permitAll() //todo adjust security
                .antMatchers("/admins/**").permitAll()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/actuator/shutdown").hasAnyRole("ADMIN")
                .antMatchers("/**").authenticated()
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
