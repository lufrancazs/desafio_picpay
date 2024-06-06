package com.lucasfranca.picpay.services;

import java.util.concurrent.CompletableFuture;

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

import jakarta.transaction.Transactional;

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


	@Transactional
	public Transfer transfer(TransferDto transferDto) {
		
		var sender = walletRepository.findById(transferDto.payer())
				.orElseThrow(() -> new WalletNotFoundException(transferDto.payer()));
		
		var receiver = walletRepository.findById(transferDto.payee())
				.orElseThrow(() -> new WalletNotFoundException(transferDto.payee()));
		
		validateTransfer(transferDto, sender);
		
		sender.debit(transferDto.value());
		receiver.credit(transferDto.value());
		
		var transfer = new Transfer(sender, receiver, transferDto.value());
		
		walletRepository.save(sender);
		walletRepository.save(receiver);
		var transferResult = transferRepository.save(transfer);

		CompletableFuture.runAsync(() -> notificationService.sendNotification(transferResult));
		
		return transferResult;
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
