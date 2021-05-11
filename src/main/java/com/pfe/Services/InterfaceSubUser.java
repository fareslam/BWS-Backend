package com.pfe.Services;

import javax.mail.MessagingException;

import org.springframework.http.ResponseEntity;

import com.pfe.Email.MailResponse;
import com.pfe.Entity.Device;
import com.pfe.Entity.SubUser;
import com.pfe.exception.ResourceNotFoundException;

public interface InterfaceSubUser {

	public ResponseEntity<?> updateProfile (Long cin,SubUser su) throws ResourceNotFoundException;

	public MailResponse resetPassword(String email) throws MessagingException,Exception;

}
