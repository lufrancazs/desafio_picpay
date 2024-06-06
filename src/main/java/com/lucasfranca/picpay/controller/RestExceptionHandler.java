package com.lucasfranca.picpay.controller;

import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lucasfranca.picpay.exceptions.PicPayException;

@RestControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(PicPayException.class)
	public ProblemDetail handlePicPayException(PicPayException e) {
		return e.toProblemDetail();
	}

}
