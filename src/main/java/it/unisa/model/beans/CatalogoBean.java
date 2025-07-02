package it.unisa.model.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class CatalogoBean implements Serializable, BeanMarker{
	
	private static final long serialVersionUID = 1L;

	private ArticoloBean articolo;
	private ProdottoFisicoBean pdFisico;
	private ProdottoDigitaleBean pdDigitale;
	private ServizioBean servizio;
	private ArrayList<ImmagineBean> immagini;
	
	public CatalogoBean() {
		articolo = new ArticoloBean();
		pdFisico = new ProdottoFisicoBean();
		pdDigitale = new ProdottoDigitaleBean();
	}
	
	public ArticoloBean getArticolo() {
		return articolo;
	}
	public void setArticolo(ArticoloBean articolo) {
		this.articolo = articolo;
	}
	public ProdottoFisicoBean getPdFisico() {
		return pdFisico;
	}
	public void setPdFisico(ProdottoFisicoBean pdFisico) {
		this.pdFisico = pdFisico;
	}
	public ProdottoDigitaleBean getPdDigitale() {
		return pdDigitale;
	}
	public void setPdDigitale(ProdottoDigitaleBean pdDigitale) {
		this.pdDigitale = pdDigitale;
	}
	public ServizioBean getServizio() {
		return servizio;
	}
	public void setServizio(ServizioBean servizio) {
		this.servizio = servizio;
	}
	public ArrayList<ImmagineBean> getImmagini() {
		return immagini;
	}
	public void setImmagini(ArrayList<ImmagineBean> immagini) {
		this.immagini = immagini;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CatalogoBean [articolo=" + articolo + ", pdFisico=" + pdFisico + ", pdDigitale=" + pdDigitale
				+ ", servizio=" + servizio + ", immagini=" + immagini + "]";
	}
	
}