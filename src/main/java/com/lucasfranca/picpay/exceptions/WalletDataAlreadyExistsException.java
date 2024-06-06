package com.lucasfranca.picpay.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class WalletDataAlreadyExistsException extends PicPayException{
	
	private static final long serialVersionUID = 1L;
	
	
	private String detail;
	
	
	public WalletDataAlreadyExistsException(String detail) {
		this.detail = detail;
	}



	public ProblemDetail toProblemDetail() {
		var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
		
		pb.setTitle("Wallet data already exists");
		pb.setDetail(detail);
		
		return pb;
	}

}
