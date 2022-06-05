/*
 *
 * Disable CSRF, perfectly fine since we're building an API
 *
 */

package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/*
// Deprecated
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }
}
*/

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http.csrf().disable();
        return http.build();
    }
}