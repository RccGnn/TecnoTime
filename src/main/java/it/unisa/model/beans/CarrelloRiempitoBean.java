package it.unisa.model.beans;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * CarrelloRiempito è impostato per essere usato come un carrello da riempire piuttosto che instanziare.
 * Nello specifico, se si devono aggiungere prodotti ad un carrello allora si usa la sottoclasse; se invece
 * si vuole eliminare del tutto il carrello e non solo svuotarlo (i.e.: l'utente decide di eliminare l'account)
 * allora si chiama il meotodo doDelete della superclasse
 */
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