package com.lpchat.springboot.chatSbApp.service;

import com.lpchat.springboot.chatSbApp.model.UserAccount;
import com.lpchat.springboot.chatSbApp.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    @Autowired
    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    public void saveUserAccount(UserAccount userAccount) {
        userAccountRepository.save(userAccount);
    }
    
    public boolean isPhoneExists(String phone) {
        return userAccountRepository.existsByPhone(phone);
    }

    public boolean isUsernameExists(String username) {
        return userAccountRepository.existsByUsername(username);
    }
    
    public boolean authenticate(String phone, String password) {
        UserAccount userAccount = userAccountRepository.findByPhone(phone);
        if (userAccount == null) {
            return false;
        }

        return userAccount.getPassword().equals(password);
    }
    
    public UserAccount getUserAccountByPhone(String phone) {
        return userAccountRepository.findByPhone(phone);
    }
}