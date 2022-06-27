package com.bm.lpchat.repository;

import com.bm.lpchat.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
