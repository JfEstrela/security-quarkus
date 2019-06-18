package br.com.jfestrela.service;

import java.io.Serializable;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class AccountDetails implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String account;
	private String agency;
	private String password;
	private String  nameAccount;
	
	public  AccountDetails(String account) {
		this.account = account;
	}
	
	public  AccountDetails(String account,String agency,String nameAccount) {
		this(account);
		this.agency = agency;
		this.nameAccount = nameAccount;
	}
	
	public  AccountDetails(String account,String agency, String password,String nameAccount) {
		this(account, agency, nameAccount);
		this.nameAccount = nameAccount;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNameAccount() {
		return nameAccount;
	}

	public void setNameAccount(String nameAccount) {
		this.nameAccount = nameAccount;
	}
	
	

}
