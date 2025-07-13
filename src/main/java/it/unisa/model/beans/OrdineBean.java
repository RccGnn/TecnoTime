package it.unisa.model.beans;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class OrdineBean implements Serializable, BeanMarker{
	
	private static final long serialVersionUID = 1L;
	
	private int numeroTransazione;
	private double totale;
	private Date dataTransazione;
	private Time oraTransazione;
	private String username;
	private String nazione;
	private String provincia;
	private String citta;
	private String via;
	private String numeroCivico;
	private String CAP;
	
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
	 * @return the totale
	 */
	public double getTotale() {
		return totale;
	}

	/**
	 * @param totale the totale to set
	 */
	public void setTotale(double totale) {
		this.totale = totale;
	}

	/**
	 * @return the dataTransazione
	 */
	public Date getDataTransazione() {
		return dataTransazione;
	}

	/**
	 * @param dataTransazione the dataTransazione to set
	 */
	public void setDataTransazione(Date dataTransazione) {
		this.dataTransazione = dataTransazione;
	}

	/**
	 * @return the oraTransazione
	 */
	public Time getOraTransazione() {
		return oraTransazione;
	}

	/**
	 * @param oraTransazione the oraTransazione to set
	 */
	public void setOraTransazione(Time oraTransazione) {
		this.oraTransazione = oraTransazione;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the nazione
	 */
	public String getNazione() {
		return nazione;
	}

	/**
	 * @param nazione the nazione to set
	 */
	public void setNazione(String nazione) {
		this.nazione = nazione;
	}

	/**
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}

	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	/**
	 * @return the citta
	 */
	public String getCitta() {
		return citta;
	}

	/**
	 * @param citta the citta to set
	 */
	public void setCitta(String citta) {
		this.citta = citta;
	}

	/**
	 * @return the via
	 */
	public String getVia() {
		return via;
	}

	/**
	 * @param via the via to set
	 */
	public void setVia(String via) {
		this.via = via;
	}

	/**
	 * @return the numeroCivico
	 */
	public String getNumeroCivico() {
		return numeroCivico;
	}

	/**
	 * @param numeroCivico the numeroCivico to set
	 */
	public void setNumeroCivico(String numeroCivico) {
		this.numeroCivico = numeroCivico;
	}

	/**
	 * @return the cAP
	 */
	public String getCAP() {
		return CAP;
	}

	/**
	 * @param cAP the cAP to set
	 */
	public void setCAP(String cAP) {
		CAP = cAP;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "OrdineBean [numeroTransazione=" + numeroTransazione + ", totale=" + totale + ", dataTransazione="
				+ dataTransazione + ", oraTransazione=" + oraTransazione + ", username=" + username + ", nazione="
				+ nazione + ", provincia=" + provincia + ", citta=" + citta + ", via=" + via + ", numeroCivico="
				+ numeroCivico + ", CAP=" + CAP + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(CAP, citta, dataTransazione, nazione, numeroCivico, numeroTransazione, oraTransazione,
				provincia, totale, username, via);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdineBean other = (OrdineBean) obj;
		return Objects.equals(CAP, other.CAP) && Objects.equals(citta, other.citta)
				&& Objects.equals(dataTransazione, other.dataTransazione) && Objects.equals(nazione, other.nazione)
				&& Objects.equals(numeroCivico, other.numeroCivico) && numeroTransazione == other.numeroTransazione
				&& Objects.equals(oraTransazione, other.oraTransazione) && Objects.equals(provincia, other.provincia)
				&& Double.doubleToLongBits(totale) == Double.doubleToLongBits(other.totale)
				&& Objects.equals(username, other.username) && Objects.equals(via, other.via);
	}

}