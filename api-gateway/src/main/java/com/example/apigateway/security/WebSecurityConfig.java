package com.example.apigateway.security;

import com.example.apigateway.jwt.JwtAuthEntryPoint;
import com.example.apigateway.jwt.JwtAuthTokenFilter;
import com.example.apigateway.service.implement.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtAuthEntryPoint unauthorizedHandler;

    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().
                authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers(HttpMethod.GET, "/auth"+"/auth/**").permitAll()
                .antMatchers(HttpMethod.POST, "/auth"+"/auth/**").permitAll()
                .antMatchers(HttpMethod.GET, "/jobs"+"/jobs/**").hasAnyRole("ADMIN","PM")
                .antMatchers(HttpMethod.POST, "/jobs"+"/jobs/**").hasAnyRole("ADMIN","PM")
                .antMatchers(HttpMethod.GET, "/jobs"+"/jobs/**").hasAnyRole("ADMIN","PM","USER")
                .antMatchers(HttpMethod.GET, "/user"+"/user/**").hasAnyRole("ADMIN","PM","USER")
                .antMatchers(HttpMethod.POST, "/user"+"/user/**").hasAnyRole("ADMIN","PM","USER")
                .antMatchers(HttpMethod.DELETE, "/jobs"+"/jobs/**").hasAnyRole("ADMIN","PM")
                .antMatchers(HttpMethod.POST,"/cv"+"/cv/**").hasAnyRole("ADMIN","PM","USER")
                .antMatchers(HttpMethod.GET,"/cv"+"/cv/**").hasAnyRole("ADMIN","PM","USER")
                .antMatchers(HttpMethod.POST,"/rcm"+"/cv/**").hasAnyRole("ADMIN","PM","USER")
                .antMatchers(HttpMethod.GET,"/rcm"+"/cv/**").hasAnyRole("ADMIN","PM","USER")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
