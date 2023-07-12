package com.lpchat.springboot.chatSbApp.repository;

import com.lpchat.springboot.chatSbApp.model.ChatMessage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
	
}