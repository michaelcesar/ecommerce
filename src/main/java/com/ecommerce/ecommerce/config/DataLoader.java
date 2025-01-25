package com.ecommerce.ecommerce.config;

import com.ecommerce.ecommerce.domain.Role;
import com.ecommerce.ecommerce.domain.User;
import com.ecommerce.ecommerce.repository.RoleRepository;
import com.ecommerce.ecommerce.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        return args -> {
            if (roleRepository.count() == 0) {
                Role adminRole = new Role(null, "ROLE_ADMIN");
                Role managerRole = new Role(null, "ROLE_MANAGER");
                Role userRole = new Role(null, "ROLE_USER");
                roleRepository.saveAll(Set.of(adminRole, managerRole, userRole));
            }

            if (userRepository.count() == 0) {
                Role adminRole = roleRepository.findByName("ROLE_ADMIN");
                Role userRole = roleRepository.findByName("ROLE_USER");

                User adminUser = new User();
                adminUser.setUsername("admin");
                adminUser.setPassword(encoder.encode("123456"));
                adminUser.setRoles(Set.of(adminRole, userRole));
                userRepository.save(adminUser);
            }
        };
    }
}