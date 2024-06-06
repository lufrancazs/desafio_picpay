package com.lucasfranca.picpay.services;

import org.springframework.stereotype.Service;

import com.lucasfranca.picpay.client.AuthorizationClient;
import com.lucasfranca.picpay.entity.Transfer;
import com.lucasfranca.picpay.exceptions.PicPayException;

@Service
public class AuthorizationService {
	
	private final AuthorizationClient authorizationClient;

	public AuthorizationService(AuthorizationClient authorizationClient) {
		this.authorizationClient = authorizationClient;
	}
	
	public boolean isAuthorized(Transfer transfer) {
		
		
		var resp = authorizationClient.isAuthorized();
		
		if(resp.getStatusCode().isError()) {
			throw new PicPayException();
		}
		
		return resp.getBody().authorized();
	}
	
	

}
