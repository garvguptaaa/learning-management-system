package com.elearning.common.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.elearning.common.enums.RoleType;
import com.elearning.role.entity.Role;
import com.elearning.role.repository.RoleRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {

        createRole(RoleType.ADMIN, "System Administrator");
        createRole(RoleType.INSTRUCTOR, "Course Instructor");
        createRole(RoleType.STUDENT, "Platform Student");

        System.out.println("Default roles initialized.");
    }

    private void createRole(RoleType roleType, String description) {

        if (!roleRepository.existsByName(roleType)) {

            Role role = new Role();
            role.setName(roleType);
            role.setDescription(description);

            roleRepository.save(role);

            System.out.println(roleType + " role created.");
        }
    }
}