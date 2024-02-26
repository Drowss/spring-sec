package com.drowsy.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRolesController {

    @GetMapping("/accesAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public String accesAdmin() {
        return "Hi has accedido como admin";
    }

    @GetMapping("/accesUser")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String accesUser() {
        return "Hi has accedido como User";
    }

    @GetMapping("/accesInvited")
    @PreAuthorize("hasRole('INVITED')")
    public String accesInvited() {
        return "Hi has accedido como invited";
    }
}
