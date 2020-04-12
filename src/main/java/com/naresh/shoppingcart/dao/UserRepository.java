package com.naresh.shoppingcart.dao;

import com.naresh.shoppingcart.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUserName(String userName);
}
