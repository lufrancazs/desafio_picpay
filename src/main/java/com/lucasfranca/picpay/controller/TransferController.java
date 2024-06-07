package com.lucasfranca.picpay.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lucasfranca.picpay.dto.TransferDto;
import com.lucasfranca.picpay.entity.Transfer;
import com.lucasfranca.picpay.services.TransferService;

import jakarta.validation.Valid;

@RestController
public class TransferController {
	
	private final TransferService transferService;
	
	public TransferController(TransferService transferService) {
		this.transferService = transferService;
	}
	
	@PostMapping("/transfer")
	public ResponseEntity<Transfer> transfer(@RequestBody @Valid TransferDto dto){
		
		var resp = transferService.transfer(dto);
		
		return ResponseEntity.ok(resp);
	}

}
