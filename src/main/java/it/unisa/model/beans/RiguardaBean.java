package it.unisa.model.beans;

import java.io.Serializable;
import java.util.Objects;

public class RiguardaBean implements Serializable, BeanMarker {
	
	private static final long serialVersionUID = 1L;
	
	private String IDPromozione;
	private String codiceIdentificativo;
	
	/**
	 * @return the iDPromozione
	 */
	public String getIDPromozione() {
		return IDPromozione;
	}
	/**
	 * @param iDPromozione the iDPromozione to set
	 */
	public void setIDPromozione(String iDPromozione) {
		IDPromozione = iDPromozione;
	}
	/**
	 * @return the codiceIdentificativo
	 */
	public String getCodiceIdentificativo() {
		return codiceIdentificativo;
	}
	/**
	 * @param codiceIdentificativo the codiceIdentificativo to set
	 */
	public void setCodiceIdentificativo(String codiceIdentificativo) {
		this.codiceIdentificativo = codiceIdentificativo;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "RiguardaBean [IDPromozione=" + IDPromozione + ", codiceIdentificativo=" + codiceIdentificativo + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(IDPromozione, codiceIdentificativo);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RiguardaBean other = (RiguardaBean) obj;
		return Objects.equals(IDPromozione, other.IDPromozione)
				&& Objects.equals(codiceIdentificativo, other.codiceIdentificativo);
	}
	
	
}
