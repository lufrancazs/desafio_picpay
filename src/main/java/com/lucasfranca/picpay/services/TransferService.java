package com.lucasfranca.picpay.services;

import org.springframework.stereotype.Service;

import com.lucasfranca.picpay.dto.TransferDto;
import com.lucasfranca.picpay.entity.Transfer;
import com.lucasfranca.picpay.entity.Wallet;
import com.lucasfranca.picpay.exceptions.InsufficientBalanceException;
import com.lucasfranca.picpay.exceptions.TransferNotAllowedForWalletTypeException;
import com.lucasfranca.picpay.exceptions.TransferNotAuthorizedException;
import com.lucasfranca.picpay.exceptions.WalletNotFoundException;
import com.lucasfranca.picpay.repository.TransferRepository;
import com.lucasfranca.picpay.repository.WalletRepository;

@Service
public class TransferService {
	
	private final NotificationService notificationService;
	private final AuthorizationService authorizationService;
	private final TransferRepository transferRepository;
	private WalletRepository walletRepository;
	
	

	public TransferService(NotificationService notificationService, 
			AuthorizationService authorizationService,
			TransferRepository transferRepository,
			WalletRepository walletRepository) {
		this.notificationService = notificationService;
		this.authorizationService = authorizationService;
		this.transferRepository = transferRepository;
		this.walletRepository = walletRepository;
	}



	public Transfer transfer(TransferDto transferDto) {
		
		var sender = walletRepository.findById(transferDto.payer())
				.orElseThrow(() -> new WalletNotFoundException(transferDto.payer()));
		
		var receiver = walletRepository.findById(transferDto.payee())
				.orElseThrow(() -> new WalletNotFoundException(transferDto.payee()));
		
		}
	
	private void validateTransfer(TransferDto transferDto, Wallet sender) {
		
		if(sender.isTransferAllowedForWalletType()) {
			throw new TransferNotAllowedForWalletTypeException();
	}
		
		if(!sender.isBalancerEqualOrGreatherThan(transferDto.value())) {
			throw new InsufficientBalanceException();
		}
		
		if(!authorizationService.isAuthorized(transferDto)) {
			throw new TransferNotAuthorizedException();
		}
		
	}

}
