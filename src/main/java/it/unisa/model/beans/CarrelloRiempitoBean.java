package it.unisa.model.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class CarrelloRiempitoBean extends CarrelloBean implements BeanMarker, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<ArticoloCompletoBean> listaArticoli;
	
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
		return super.toString() + "CarrelloRiempitoBean [listaArticoli=" + listaArticoli + "]";
	}

}