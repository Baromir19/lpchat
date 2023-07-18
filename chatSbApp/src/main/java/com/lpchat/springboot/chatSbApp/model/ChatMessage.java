package com.lpchat.springboot.chatSbApp.model;

import java.sql.Timestamp;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "messages_table")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long messageId;
    
    @Column(name = "chat_id")
	private Long chatId;
    
    @Column(name = "sender_id")
	private Long senderId;
    
    @Column(name = "message_text")
    private String message_text;
    
    @Column(name = "message_timestamp")
    private Timestamp messageTimestamp;

    public ChatMessage() {

    }

    public ChatMessage(Long id, Long sender, String text, Timestamp timestamp) {
        this.messageId = id;
        this.senderId = sender;
        this.message_text = text;
        this.messageTimestamp = timestamp;
    }

    public Long getId() {
        return messageId;
    }

    public void setId(Long id) {
        this.messageId = id;
    }
    
    public Long getChat_id() {
		return chatId;
	}

	public void setChat_id(Long chat_id) {
		this.chatId = chat_id;
	}

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getMessage_text() {
        return message_text;
    }

    public void setMessage_text(String message_text) {
        this.message_text = message_text;
    }

    public Timestamp getTimestamp() {
        return messageTimestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.messageTimestamp = timestamp;
    }
    
    @Override
	public String toString() {
		return "ChatMessage [id=" + messageId 
				+ ", chat_id=" + chatId 
				+ ", sender_id=" + senderId
				+ ", message_text=" + message_text 
				+ ", timestamp=" + messageTimestamp + "]";
	}
}