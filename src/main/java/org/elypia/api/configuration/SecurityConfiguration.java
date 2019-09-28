/*
 * Elypia API - Backend for Elypia website and core services.
 * Copyright (C) 2019-2019  Elypia CIC
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.elypia.api.configuration;

import org.elypia.api.services.AccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.*;

import java.util.List;

/**
 * @author seth@elypia.org (Syed Shah)
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final List<String> CORS = List.of(
        "https://elypia.org", "https://elypia.com", // Live Website
        "https://localhost:4200", "http://localhost:4200", // Angular Development
        "https://localhost:3000", "http://localhost:3000" // Angular Light Webserver
    );

    private static final List<String> METHODS = List.of(
        "HEAD", "GET", "POST", "PATCH", "DELETE", "OPTIONS"
    );

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfiguration(final AccountDetailsService accountDetailsService) {
        this.userDetailsService = accountDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
            .configurationSource(getCorsConfigurationSource());
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource getCorsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(CORS);
        configuration.setAllowedMethods(METHODS);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public AuthenticationManager getAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    public void configureAuthenticationManagerBuilder(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(getBCryptPasswordEncoder());
    }
}
