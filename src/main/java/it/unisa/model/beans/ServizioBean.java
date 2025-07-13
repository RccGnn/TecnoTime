package it.unisa.model.beans;

import java.io.Serializable;
import java.util.Objects;

public class ServizioBean extends ArticoloBean implements Serializable, BeanMarker, BeanProductItem{
	
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
		return super.toString() + "ServizioBean [codiceServizio=" + codiceServizio + ", durata=" + durata
				+ ", Articolo_codiceIdentificativo=" + Articolo_codiceIdentificativo + ", prezzo=" + prezzo
				+ ", descrizione=" + descrizione + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ Objects.hash(Articolo_codiceIdentificativo, codiceServizio, descrizione, durata, prezzo);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServizioBean other = (ServizioBean) obj;
		return Objects.equals(Articolo_codiceIdentificativo, other.Articolo_codiceIdentificativo)
				&& Objects.equals(codiceServizio, other.codiceServizio)
				&& Objects.equals(descrizione, other.descrizione)
				&& Double.doubleToLongBits(durata) == Double.doubleToLongBits(other.durata)
				&& Double.doubleToLongBits(prezzo) == Double.doubleToLongBits(other.prezzo);
	}
	
}