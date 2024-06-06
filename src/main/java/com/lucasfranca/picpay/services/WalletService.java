package com.lucasfranca.picpay.services;

import org.springframework.stereotype.Service;

import com.lucasfranca.picpay.dto.CreateWalletDto;
import com.lucasfranca.picpay.entity.Wallet;
import com.lucasfranca.picpay.exceptions.WalletDataAlreadyExistsException;
import com.lucasfranca.picpay.repository.WalletRepository;


@Service
public class WalletService {
	
	private final WalletRepository walletRepository;
	
	public WalletService(WalletRepository walletRepository) {
		this.walletRepository = walletRepository;
	}

	public Wallet createWallet(CreateWalletDto dto) {
		
		var walletDb = walletRepository.findByCpfCnpjOrEmail(dto.cpfCnpj(), dto.email());
		
		if(walletDb.isPresent()) {
			throw new WalletDataAlreadyExistsException("CpfCnpj or Email already exists");
		}
		
		return walletRepository.save(dto.toWallet());
		
	}

}
