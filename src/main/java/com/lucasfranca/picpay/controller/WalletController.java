package com.lucasfranca.picpay.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lucasfranca.picpay.dto.CreateWalletDto;
import com.lucasfranca.picpay.entity.Wallet;
import com.lucasfranca.picpay.services.WalletService;

import jakarta.validation.Valid;

@RestController
public class WalletController {
	
	private final WalletService walletService;

	public WalletController(WalletService walletService) {
		this.walletService = walletService;
	}
	
	@PostMapping("/wallets")
	public ResponseEntity<Wallet> createWallet(@RequestBody @Valid CreateWalletDto dto){
		
		var wallet = walletService.createWallet(dto);
		
		return ResponseEntity.ok(wallet);
	}

}
