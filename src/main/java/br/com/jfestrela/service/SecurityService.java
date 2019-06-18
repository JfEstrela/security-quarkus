package br.com.jfestrela.service;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class SecurityService {
	
	@ConfigProperty(name = "security.account.name")
	private String nameAccount;
	
	@ConfigProperty(name = "security.account")
	private String account;
	
	@ConfigProperty(name = "security.account.agency")
	private String agency;
	
	@ConfigProperty(name = "security.account.password")
	private String password;

	
	public AccountDetails account() {
        return new AccountDetails(account);
    }
	
	public AccountDetails accountDetails() {
		return   new AccountDetails(account,agency,nameAccount);
	}
	
	public AccountDetails accountDetailsFull() {
		return new AccountDetails(account,agency,password,nameAccount);
	}
	
	

}
