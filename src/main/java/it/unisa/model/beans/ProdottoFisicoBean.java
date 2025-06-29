package it.unisa.model.beans;

import java.io.Serializable;

public class ProdottoFisicoBean implements Serializable, BeanMarker{
	
	private static final long serialVersionUID = 1L;
	
	private String seriale;
	private boolean isPreassemblato;
	private int quantitaMagazzino;
	private String codiceIdentificativo;
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
	public String getArticolo_CodiceIdentificativo() {
		return codiceIdentificativo;
	}
	public void setArticolo_CodiceIdentificativo(String codiceIdentificativo) {
		this.codiceIdentificativo = codiceIdentificativo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "ProdottoFisicoBean [seriale=" + seriale + ", isPreassemblato=" + isPreassemblato
				+ ", quantitaMagazzino=" + quantitaMagazzino + ", codiceIdentificativo=" + codiceIdentificativo + "]";
	}
	
}