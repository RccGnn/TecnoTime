package it.unisa.model.beans;

import java.io.Serializable;

public class CarrelloBean implements BeanMarker, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String  Account_username;
	private int  Carrello_id;

	public int getCarrello_id() {
		return Carrello_id;
	}
	
	public void setCarrello_id(int carrelloid) {
		Carrello_id=carrelloid;
	}
	
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
		return "CarrelloBean [Account_username=" + Account_username + ",Carrello_id=" + Carrello_id + "]";
	}
	
}