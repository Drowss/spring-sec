package com.drowsy.controller;

import com.drowsy.controller.request.CreateUserDTO;
import com.drowsy.models.ERole;
import com.drowsy.models.RoleEntity;
import com.drowsy.models.UserEntity;
import com.drowsy.repository.IUserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class PrincipalController {

    @Autowired
    private IUserRepository iUserRepository;

    @GetMapping("/hello")
    public String hello() {
        return "Hi bitches not secured";
    }

    @GetMapping("/helloSec")
    public String helloSec() {
        return "Hi bitches secured";
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO createUserDTO) {

        Set<RoleEntity> roles = createUserDTO.getRoles().stream()
                .map(role -> RoleEntity.builder()
                        .name(ERole.valueOf(role))
                        .build())//Construimos objetos RoleEntity segun la cantidad de roles que se hayan pasado
                .collect(Collectors.toSet());

        UserEntity userEntity = UserEntity.builder()
                .username(createUserDTO.getUsername())
                .password(createUserDTO.getPassword())
                .email(createUserDTO.getEmail())
                .roles(roles)
                .build();

        iUserRepository.save(userEntity);
        return ResponseEntity.ok(userEntity);
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam String id) {
        iUserRepository.deleteById(Long.parseLong(id));
        return "Se ha borrado el user con id".concat(id);
    }
}
