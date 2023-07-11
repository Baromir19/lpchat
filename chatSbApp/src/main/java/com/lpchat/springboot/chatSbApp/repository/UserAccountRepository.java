package com.lpchat.springboot.chatSbApp.repository;

import com.lpchat.springboot.chatSbApp.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    boolean existsByUsername(String username);
    
    boolean existsByPhone(String phone);
    
    UserAccount findByPhone(String phone);
}