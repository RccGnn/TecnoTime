package it.unisa.model.beans;

import java.io.Serializable;

public class ServizioBean implements Serializable, BeanMarker{
	
	private static final long serialVersionUID = 1L;
	
	private String codiceServizio;
	private double durata;
	private String Articolo_codiceIdentificativo;
	private double prezzo;
	private String descrizione;
	
	public String getCodiceServizio() {
		return codiceServizio;
	}
	public void setCodiceServizio(String codiceServizio) {
		this.codiceServizio = codiceServizio;
	}
	public double getDurata() {
		return durata;
	}
	public void setDurata(double durata) {
		this.durata = durata;
	}
	public String getArticolo_codiceIdentificativo() {
		return Articolo_codiceIdentificativo;
	}
	public void setArticolo_codiceIdentificativo(String articolo_codiceIdentificativo) {
		Articolo_codiceIdentificativo = articolo_codiceIdentificativo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	@Override
	public String toString() {
		return "ServizioBean [codiceServizio=" + codiceServizio + ", durata=" + durata
				+ ", Articolo_codiceIdentificativo=" + Articolo_codiceIdentificativo + ", prezzo=" + prezzo
				+ ", descrizione=" + descrizione + "]";
	}
	
}