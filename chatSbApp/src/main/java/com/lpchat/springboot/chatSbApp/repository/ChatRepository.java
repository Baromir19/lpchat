package com.lpchat.springboot.chatSbApp.repository;

import com.lpchat.springboot.chatSbApp.model.ChatModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<ChatModel, Long> {

//    @Query(value = "SELECT c.chat_id FROM ChatModel c WHERE :userId MEMBER OF c.user_ids")
//    List<Long> findByUserId(Long userId);
	
}