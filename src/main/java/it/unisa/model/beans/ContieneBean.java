package it.unisa.model.beans;

import java.io.Serializable;

public class ContieneBean implements BeanMarker, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String Account_username;
	private int Carrello_id;
	private String Articolo_codiceIdentificativo;
	private int quantità;
	
	public String getAccount_username() {
		return Account_username;
	}
	public void setAccount_username(String account_username) {
		Account_username = account_username;
	}
	public void setCarrello_id(int carrelloid) {
		Carrello_id=carrelloid;
	}
	public int getCarrello_id() {
		return Carrello_id;
	}
	
	public String getArticolo_codiceIdentificativo() {
		return Articolo_codiceIdentificativo;
	}
	public void setArticolo_codiceIdentificativo(String articolo_codiceIdentificativo) {
		Articolo_codiceIdentificativo = articolo_codiceIdentificativo;
	}
	public int getQuantità() {
		return quantità;
	}
	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "ContieneBean [Account_username=" + Account_username + ", Articolo_codiceIdentificativo="
				+ Articolo_codiceIdentificativo + ",Carrello_id="+Carrello_id+", quantità=" + quantità + "]";
	}

}