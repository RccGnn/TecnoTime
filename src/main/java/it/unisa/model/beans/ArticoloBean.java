package it.unisa.model.beans;

import java.io.Serializable;
import java.time.LocalDate;

public class ArticoloBean implements Serializable, BeanMarker{

	private static final long serialVersionUID = 1L;
	
	private String codiceIdentificativo;
	private String categoria;
	private String nome;
	private LocalDate dataUltimaPromozione;
	private String enteErogatore;
	private boolean disponibilita;
	
	
	public String getCodiceIdentificativo() {
		return codiceIdentificativo;
	}
	
	public void setCodiceIdentificativo(String codiceIdentificativo) {
		this.codiceIdentificativo = codiceIdentificativo;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
		
	public LocalDate getDataUltimaPromozione() {
		return dataUltimaPromozione;
	}
	
	public void setDataUltimaPromozione(LocalDate dataUltimaPromozione) {
		this.dataUltimaPromozione = dataUltimaPromozione;
	}
	
	public String getEnteErogatore() {
		return enteErogatore;
	}
	
	public void setEnteErogatore(String enteErogatore) {
		this.enteErogatore = enteErogatore;
	}
	
	public boolean isDisponibilita() {
		return disponibilita;
	}
	
	public void setDisponibilita(boolean disponibilita) {
		this.disponibilita = disponibilita;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ArticoloBean [codiceIdentificativo=" + codiceIdentificativo + ", categoria=" + categoria + ", nome="
				+ nome + ", dataUltimaPromozione=" + dataUltimaPromozione + ", enteErogatore=" + enteErogatore
				+ ", disponibilita=" + disponibilita + "]";
	}		
	
}