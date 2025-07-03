package it.unisa.model.beans;

import java.io.Serializable;

public class CarrelloBean implements BeanMarker, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String  Account_username;

	public String getAccount_username() {
		return Account_username;
	}

	public void setAccount_username(String account_username) {
		Account_username = account_username;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CarrelloBean [Account_username=" + Account_username + "]";
	}
	
}