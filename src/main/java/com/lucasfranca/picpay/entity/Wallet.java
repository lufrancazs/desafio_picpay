package com.lucasfranca.picpay.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_wallet")
public class Wallet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "fullName", unique = true)
	private String fullName;
	
	@Column(name = "cpfCnpj", unique = true)
	private String cpfCnpj;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "balance")
	private BigDecimal balance = BigDecimal.ZERO;
	
	@JoinColumn(name = "wallet_type_id")
	@ManyToOne
	private WalletType walletType;
	
	
	public Wallet() {
	}
	

	public Wallet(String fullName, String cpfCnpj, String email, String password, WalletType walletType) {
		this.fullName = fullName;
		this.cpfCnpj = cpfCnpj;
		this.email = email;
		this.password = password;
		this.walletType = walletType;
	}
	
	public boolean isTransferAllowedForWalletType() {
		return this.walletType.equals(WalletType.Enum.USER.get());
	}	
	
	public boolean isBalancerEqualOrGreatherThan(BigDecimal value) {
		return this.balance.doubleValue() >= value.doubleValue();
	}
	
	public void debit(BigDecimal value) {
		this.balance = this.balance.min(value);
	}
	
	public void credit(BigDecimal value) {
		this.balance = this.balance.add(value);
	}




	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getCpfCnpj() {
		return cpfCnpj;
	}


	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public BigDecimal getBalance() {
		return balance;
	}


	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}


	public WalletType getWalletType() {
		return walletType;
	}


	public void setWalletType(WalletType walletType) {
		this.walletType = walletType;
	}

}
