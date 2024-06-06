package com.lucasfranca.picpay.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class WalletNotFoundException extends PicPayException{

	private static final long serialVersionUID = 1L;
	
	private Long walletId;

	public WalletNotFoundException(Long walletId) {
		this.walletId = walletId;
	}


	@Override
	public ProblemDetail toProblemDetail() {
		var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
		
		pb.setTitle("Wallet not found");
		pb.setDetail("There is no wallet whith id " + walletId + ".");
		
		return pb;
	}
	
	

}
