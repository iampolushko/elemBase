package com.example.elembase.repository;

import com.example.elembase.Entitity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByName(String username);

    @Modifying
    @Query(value = "INSERT INTO `diplom`.`user` (`name`, `password`, `role`) VALUES (:name, :password, :role);", nativeQuery = true)
    @Transactional
    void createNewUser(@Param("name") String name, @Param("password") String password, @Param("role") String role);


}
