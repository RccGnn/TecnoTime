package it.unisa.model.beans;

import java.io.Serializable;
import java.util.Objects;

public class ElementoOrdineBean implements Serializable, BeanMarker{
	
	private static final long serialVersionUID = 1L;
	
	private int numero;
	private int numeroTransazione;
	private String codiceArticolo;
	private String nomeArticolo;
	private String urlImmagineArticolo;
	private double prezzoUnitario;
	private int quantitaArticolo;
	/**
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}
	/**
	 * @param numero the numero to set
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}
	/**
	 * @return the numeroTransazione
	 */
	public int getNumeroTransazione() {
		return numeroTransazione;
	}
	/**
	 * @param numeroTransazione the numeroTransazione to set
	 */
	public void setNumeroTransazione(int numeroTransazione) {
		this.numeroTransazione = numeroTransazione;
	}
	/**
	 * @return the codiceArticolo
	 */
	public String getCodiceArticolo() {
		return codiceArticolo;
	}
	/**
	 * @param codiceArticolo the codiceArticolo to set
	 */
	public void setCodiceArticolo(String codiceArticolo) {
		this.codiceArticolo = codiceArticolo;
	}
	/**
	 * @return the nomeArticolo
	 */
	public String getNomeArticolo() {
		return nomeArticolo;
	}
	/**
	 * @param nomeArticolo the nomeArticolo to set
	 */
	public void setNomeArticolo(String nomeArticolo) {
		this.nomeArticolo = nomeArticolo;
	}
	/**
	 * @return the urlImmagineArticolo
	 */
	public String getUrlImmagineArticolo() {
		return urlImmagineArticolo;
	}
	/**
	 * @param urlImmagineArticolo the urlImmagineArticolo to set
	 */
	public void setUrlImmagineArticolo(String urlImmagineArticolo) {
		this.urlImmagineArticolo = urlImmagineArticolo;
	}
	/**
	 * @return the prezzoUnitario
	 */
	public double getPrezzoUnitario() {
		return prezzoUnitario;
	}
	/**
	 * @param prezzoUnitario the prezzoUnitario to set
	 */
	public void setPrezzoUnitario(double prezzoUnitario) {
		this.prezzoUnitario = prezzoUnitario;
	}
	/**
	 * @return the quantitaArticolo
	 */
	public int getQuantitaArticolo() {
		return quantitaArticolo;
	}
	/**
	 * @param quantitaArticolo the quantitaArticolo to set
	 */
	public void setQuantitaArticolo(int quantitaArticolo) {
		this.quantitaArticolo = quantitaArticolo;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		return Objects.hash(codiceArticolo, nomeArticolo, numero, numeroTransazione, prezzoUnitario, quantitaArticolo,
				urlImmagineArticolo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ElementoOrdineBean other = (ElementoOrdineBean) obj;
		return Objects.equals(codiceArticolo, other.codiceArticolo) && Objects.equals(nomeArticolo, other.nomeArticolo)
				&& numero == other.numero && numeroTransazione == other.numeroTransazione
				&& Double.doubleToLongBits(prezzoUnitario) == Double.doubleToLongBits(other.prezzoUnitario)
				&& quantitaArticolo == other.quantitaArticolo && Objects.equals(urlImmagineArticolo, other.urlImmagineArticolo);
	}
	@Override
	public String toString() {
		return "ElementoOrdineBean [numero=" + numero + ", numeroTransazione=" + numeroTransazione + ", codiceArticolo="
				+ codiceArticolo + ", nomeArticolo=" + nomeArticolo + ", urlImmagineArticolo=" + urlImmagineArticolo
				+ ", prezzoUnitario=" + prezzoUnitario + ", quantitaArticolo=" + quantitaArticolo + "]";
	}
	
	
}