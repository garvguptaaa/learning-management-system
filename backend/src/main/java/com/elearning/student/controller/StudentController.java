package com.elearning.student.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('STUDENT')")
    public String dashboard() {

        return "Welcome Student";

    }

}