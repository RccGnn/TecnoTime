package it.unisa.model.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class CarrelloRiempitoBean implements BeanMarker, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String  Account_username;
	private ArrayList<ContieneBean> quantitaArticoli;
	private ArrayList<CatalogoBean> listaArticoli;
	
	public String getAccount_username() {
		return Account_username;
	}
	public void setAccount_username(String account_username) {
		Account_username = account_username;
	}
	public ArrayList<ContieneBean> getQuantitaArticoli() {
		return quantitaArticoli;
	}
	public void setQuantitaArticoli(ArrayList<ContieneBean> quantitaArticoli) {
		this.quantitaArticoli = quantitaArticoli;
	}
	public ArrayList<CatalogoBean> getListaArticoli() {
		return listaArticoli;
	}
	public void setListaArticoli(ArrayList<CatalogoBean> listaArticoli) {
		this.listaArticoli = listaArticoli;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "CarrelloRiempitoBean [Account_username=" + Account_username + ", quantitaArticoli=" + quantitaArticoli
				+ ", listaArticoli=" + listaArticoli + "]";
	}
	
}