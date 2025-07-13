package it.unisa.model.beans;

import java.io.Serializable;
import java.util.Objects;

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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ Objects.hash(Articolo_codiceIdentificativo, codiceSoftware, descrizione, numeroChiavi, prezzo);
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
		ProdottoDigitaleBean other = (ProdottoDigitaleBean) obj;
		return Objects.equals(Articolo_codiceIdentificativo, other.Articolo_codiceIdentificativo)
				&& Objects.equals(codiceSoftware, other.codiceSoftware)
				&& Objects.equals(descrizione, other.descrizione) && numeroChiavi == other.numeroChiavi
				&& Float.floatToIntBits(prezzo) == Float.floatToIntBits(other.prezzo);
	}
	
	
}