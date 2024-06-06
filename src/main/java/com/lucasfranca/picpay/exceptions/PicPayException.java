package com.lucasfranca.picpay.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class PicPayException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ProblemDetail toProblemDetail() {
		var pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		
		pb.setTitle("PicPay internal server error");
		
		return pb;
	}

}
