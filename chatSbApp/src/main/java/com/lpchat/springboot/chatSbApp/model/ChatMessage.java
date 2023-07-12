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
//@Access(AccessType.FIELD)
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//  @Access(AccessType.PROPERTY)
    @Column(name = "message_id")
    private Long messageId;
	private Long chat_id;
	private Long sender_id;
    private String message_text;
    private Timestamp timestamp;

    public ChatMessage() {

    }

    public ChatMessage(Long id, Long sender, String text, Timestamp timestamp) {
        this.messageId = id;
        this.sender_id = sender;
        this.message_text = text;
        this.timestamp = timestamp;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }
    
    public Long getChat_id() {
		return chat_id;
	}

	public void setChat_id(Long chat_id) {
		this.chat_id = chat_id;
	}

    public Long getSender_id() {
        return sender_id;
    }

    public void setSender_id(Long sender_id) {
        this.sender_id = sender_id;
    }

    public String getMessage_text() {
        return message_text;
    }

    public void setMessage_text(String message_text) {
        this.message_text = message_text;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    
    @Override
	public String toString() {
		return "ChatMessage [messageId=" + messageId 
				+ ", chat_id=" + chat_id 
				+ ", sender_id=" + sender_id
				+ ", message_text=" + message_text 
				+ ", timestamp=" + timestamp + "]";
	}
}