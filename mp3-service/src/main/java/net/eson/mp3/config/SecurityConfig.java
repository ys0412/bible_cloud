package net.eson.mp3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Eson
 * @date 2025年03月20日 10:25
 */
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        security.authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .httpBasic().disable()
                .formLogin().disable()
                .csrf().disable();
        return security.build();
    }
}
    