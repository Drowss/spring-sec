package com.drowsy;

import com.drowsy.models.ERole;
import com.drowsy.models.RoleEntity;
import com.drowsy.models.UserEntity;
import com.drowsy.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class CodeJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeJwtApplication.class, args);
	}

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	IUserRepository iUserRepository;

	@Bean
	CommandLineRunner init() {
		return args -> {
			UserEntity userEntity = UserEntity.builder()
					.email("Drow@Email.com")
					.username("drow")
					.password(passwordEncoder.encode("1234"))
					.roles(Set.of(RoleEntity.builder()
							.name(ERole.valueOf(ERole.ADMIN.name()))
							.build()))
					.build();

			UserEntity userEntity2 = UserEntity.builder()
					.email("Drof@Email.com")
					.username("drowa")
					.password(passwordEncoder.encode("4321"))
					.roles(Set.of(RoleEntity.builder()
							.name(ERole.valueOf(ERole.USER.name()))
							.build()))
					.build();

			UserEntity userEntity3 = UserEntity.builder()
					.email("Droas@Email.com")
					.username("drowase")
					.password(passwordEncoder.encode("1212"))
					.roles(Set.of(RoleEntity.builder()
							.name(ERole.valueOf(ERole.INVITED.name()))
							.build()))
					.build();

			iUserRepository.save(userEntity);
			iUserRepository.save(userEntity2);
			iUserRepository.save(userEntity3);
		};
	}
}
