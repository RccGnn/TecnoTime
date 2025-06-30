package it.unisa.model.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class CatalogoBean implements Serializable, BeanMarker{
	
	private static final long serialVersionUID = 1L;

	private ArticoloBean articolo;
	private ProdottoFisicoBean pdFisico;
	private ProdottoDigitaleBean pdDigitale;
	private ServizioBean servizio;
	
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "CatalogoViewBean [articolo=" + articolo + ", pdFisico=" + pdFisico
				+ ", pdDigitale=" + pdDigitale + ", servizio=" + servizio + "]";
	}
	
}