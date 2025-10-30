package com.chrishield.sec_sample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//Securing Passwords Using BCrypt
//Password hashing is critical, when saving password, saving them in plain text is extremely dangerous.
//Hashing a password transforms it into a fixed length, irreversible string of characters. Best practice is to store only hashed passwords.
// We use BCrypt hashing function specifically designed for spring security.
// While storing the password on first login, we shouldn’t store the raw password. Instead, use a password encoder to hash the password first.
// This way, the database will store only the hashed version.
// When verifying passwords during login, the password they enter is hashed and compared to the stored hashed password.
// You never decode the stored password - you always encode the input and then compare with the hashed version that was stored during first login.
@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // The PasswordEncoder is responsible for hashing, encoding,
        // and verifying passwords securely.
        return new BCryptPasswordEncoder();
    }

    /////////////////////////////////////////////
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(User.withUsername("user")
                .password(passwordEncoder.encode("user123")) // or pre-encoded
                .roles("USER")
                .build());

        manager.createUser(User.withUsername("admin")
                .password(passwordEncoder.encode("admin123")) // or pre-encoded
                .roles("ADMIN")
                .build());

        UserDetails userDetails = manager.loadUserByUsername("user");
        System.out.println("\nUser Details:\nUsername: " + userDetails.getUsername() + " Password: " + userDetails.getPassword());

        UserDetails adminDetails = manager.loadUserByUsername("admin");
        System.out.println("Admin Details:\nUsername: " + adminDetails.getUsername() + " Password: " + adminDetails.getPassword());
        System.out.println();

        return manager;
    }

}
