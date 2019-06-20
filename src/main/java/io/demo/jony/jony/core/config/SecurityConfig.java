package io.demo.jony.jony.core.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.demo.jony.jony.core.security.AuthenticationFilter;
import io.demo.jony.jony.core.security.CORSOptionsFilter;
import io.demo.jony.jony.core.security.JWTRefreshAuthenticationFilter;
import io.demo.jony.jony.core.security.LoginFilter;

/**
 * Security Configuration.
 *
 * @author Virtus
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * (non-Javadoc)
     *
     * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/*/login").permitAll()
                .antMatchers(HttpMethod.POST, "/*/refresh").permitAll()
                .antMatchers(HttpMethod.GET, "/v2/api-docs").permitAll()
                .antMatchers(HttpMethod.GET, "/configuration/ui/**").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-resources/**").permitAll()
                .antMatchers(HttpMethod.GET, "/configuration/security/**").permitAll()
                .antMatchers(HttpMethod.GET, "/webjars/**").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-ui.html/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new CORSOptionsFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new LoginFilter("/*/login"), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTRefreshAuthenticationFilter("/*/refresh"), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new AuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}