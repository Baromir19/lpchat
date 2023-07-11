package com.lpchat.springboot.chatSbApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.sql.Timestamp;

@Entity
@Table(name = "chats_table")
public class ChatModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chat_id;
	private Long[] user_ids;
    private String type;
    private Timestamp timestamp;

    public ChatModel() {

    }

    public ChatModel(Long chat_id, Long[] user_ids, String type) {
        this.chat_id = chat_id;
        this.user_ids = user_ids;
        this.type = type;
    }
    
    public Long getChat_id() {
		return chat_id;
	}

	public void setChat_id(Long chat_id) {
		this.chat_id = chat_id;
	}

	public Long[] getUser_ids() {
		return user_ids;
	}

	public void setUser_ids(Long[] user_ids) {
		this.user_ids = user_ids;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
    
}