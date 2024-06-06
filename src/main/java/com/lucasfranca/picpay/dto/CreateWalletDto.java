package com.lucasfranca.picpay.dto;

import com.lucasfranca.picpay.entity.Wallet;
import com.lucasfranca.picpay.entity.WalletType;

public record CreateWalletDto(String fullName, 
		String cpfCnpj, 
		String email, 
		String password, 
		WalletType.Enum walletType) {
	
	public Wallet toWallet() {
		return new Wallet(fullName, cpfCnpj, email, password, walletType.get());
	}

}
