package it.unisa.model.beans;

import java.io.Serializable;

public class ProdottoFisicoBean extends ArticoloBean implements Serializable, BeanMarker, BeanProductItem{
	
	private static final long serialVersionUID = 1L;
	
	private String seriale;
	private boolean isPreassemblato;
	private int quantitaMagazzino;
	private String Articolo_codiceIdentificativo;
	private float prezzo;
	private String descrizione;
	
	public String getSeriale() {
		return seriale;
	}
	public void setSeriale(String seriale) {
		this.seriale = seriale;
	}
	public boolean isPreassemblato() {
		return isPreassemblato;
	}
	public void setPreassemblato(boolean isPreassemblato) {
		this.isPreassemblato = isPreassemblato;
	}
	public int getQuantitaMagazzino() {
		return quantitaMagazzino;
	}
	public void setQuantitaMagazzino(int quantitaMagazzino) {
		this.quantitaMagazzino = quantitaMagazzino;
	}
	public String getArticolo_codiceIdentificativo() {
		return Articolo_codiceIdentificativo;
	}
	public void setArticolo_codiceIdentificativo(String Articolo_codiceIdentificativo) {
		this.Articolo_codiceIdentificativo = Articolo_codiceIdentificativo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
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
		return super.toString() + "ProdottoFisicoBean [seriale=" + seriale + ", isPreassemblato=" + isPreassemblato
				+ ", quantitaMagazzino=" + quantitaMagazzino + ", Articolo_codiceIdentificativo="
				+ Articolo_codiceIdentificativo + ", prezzo=" + prezzo + ", descrizione=" + descrizione + "]";
	}
}