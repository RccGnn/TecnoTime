package it.unisa.model.beans;

import java.io.Serializable;

public class ProdottoDigitaleBean extends ArticoloBean implements Serializable, BeanMarker, BeanProductItem{
	
	private static final long serialVersionUID = 1L;
	
	private String codiceSoftware;
	private int numeroChiavi;
	private String Articolo_codiceIdentificativo;
	private float prezzo;
	private String descrizione;
		
	public String getCodiceSoftware() {
		return codiceSoftware;
	}
	public void setCodiceSoftware(String codiceSoftware) {
		this.codiceSoftware = codiceSoftware;
	}
	public int getNumeroChiavi() {
		return numeroChiavi;
	}
	public void setNumeroChiavi(int numeroChiavi) {
		this.numeroChiavi = numeroChiavi;
	}
	public String getArticolo_codiceIdentificativo() {
		return Articolo_codiceIdentificativo;
	}
	public void setArticolo_codiceIdentificativo(String articolo_codiceIdentificativo) {
		Articolo_codiceIdentificativo = articolo_codiceIdentificativo;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return super.toString() + "ProdottoDigitaleBean [codiceSoftware=" + codiceSoftware + ", numeroChiavi=" + numeroChiavi
				+ ", Articolo_codiceIdentificativo=" + Articolo_codiceIdentificativo + ", prezzo=" + prezzo
				+ ", descrizione=" + descrizione + "]";
	}
}