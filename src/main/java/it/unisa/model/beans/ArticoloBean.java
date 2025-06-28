package it.unisa.model.beans;

import java.io.Serializable;
import java.time.LocalDate;

public class ArticoloBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String codiceIdentificativo;
	private String descrizione;
	private String tipologia;
	private String nome;
	private float prezzo;
	private LocalDate dataUltimaPromozione;
	private String enteErogatore;
	private boolean disponibilita;
	
	
	public String getCodiceIdentificativo() {
		return codiceIdentificativo;
	}
	
	public void setCodiceIdentificativo(String codiceIdentificativo) {
		this.codiceIdentificativo = codiceIdentificativo;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public String getTipologia() {
		return tipologia;
	}
	
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public float getPrezzo() {
		return prezzo;
	}
	
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
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
		
}