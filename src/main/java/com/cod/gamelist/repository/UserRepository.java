package com.cod.gamelist.repository;

import com.cod.gamelist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
