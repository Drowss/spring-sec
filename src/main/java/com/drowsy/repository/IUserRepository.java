package com.drowsy.repository;

import com.drowsy.models.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends CrudRepository<UserEntity, Long> {
    @Query("select u from UserEntity u where u.username = ?1") //findByUsername() es otra forma de aplicarlo
    Optional<UserEntity> getName(String username);

}
