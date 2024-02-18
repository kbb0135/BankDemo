package com.expense.springsecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
//Spring configuration is used in class level and is managed by spring
//will overide the default method of defaultSecurityFilterChain
@Configuration
public class ProjectSecurityConfiguration {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        //creating a path where users have the ability to access public sites or authorized sites
        /*
            in the path use use Security filter chain to provide or make path accessible for users,
            we assume that the user is abel to access home and the users path such as addexpense and other
            is only allowed when user provides valid credentials
         */
        http.authorizeHttpRequests((requests) -> requests
                        //check if the site is needed authentication
                        .requestMatchers("/myaccount", "mybalance").authenticated()
                        //else permit all provides path for all the users without being authenticated
                        .requestMatchers("/welcome").permitAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return (SecurityFilterChain) http.build();
    }
    /*
    Instead of creating multiple users inside spring, we can create multiple users using userDetails Services
    using this method from spring security
     */
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        //withDefaultPassword encoder is depriciated and shouldn't be used in the production environment
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("Nepal1234")
                .authorities("admin")
                .build();
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("Nepal1234")
                .authorities("read")
                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }

}
