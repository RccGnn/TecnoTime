package it.unisa.model.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class CarrelloRiempitoBean implements BeanMarker, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<ArticoloCompletoBean> listaArticoli;
	private CarrelloBean carrello;
	
	public CarrelloBean getCarrello() {
		return carrello;
	}
	public void setCarrello(CarrelloBean carrello) {
		this.carrello = carrello;
	}
	public ArrayList<ArticoloCompletoBean> getListaArticoli() {
		return listaArticoli;
	}
	public void setListaArticoli(ArrayList<ArticoloCompletoBean> listaArticoli) {
		this.listaArticoli = listaArticoli;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CarrelloRiempitoBean [listaArticoli=" + listaArticoli + ", carrello=" + carrello + "]";
	}

}