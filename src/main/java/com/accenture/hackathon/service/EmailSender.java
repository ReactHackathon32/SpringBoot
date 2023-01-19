package com.accenture.hackathon.service;

public interface EmailSender {
	void send(String to, String email, String subject);
	
	String buildConfirmationEmail(String name, String link);
	
	String buildPasswordResetEmail(String name, String link);
}
