package it.unisa.model.beans;

import java.io.Serializable;

public class CarrelloBean implements BeanMarker, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String  Account_username;
	private String Carrello_Id;

	public String getCarrello_Id() {
		return Carrello_Id;
	}
	
	public void setCarrello_Id(String carrelloid) {
		Carrello_Id=carrelloid;
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
		return "CarrelloBean [Account_username=" + Account_username + ",Carrello_Id=" + Carrello_Id + "]";
	}
	
}