package it.unisa.model.beans;

import java.io.Serializable;
import java.util.Objects;

public class AssociatoABean implements Serializable, BeanMarker{

	private static final long serialVersionUID = 1L;
	
	private String username;
    private String IDPromozione;
    private String codicePromozione;
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
	 * @return the codicePromozione
	 */
	public String getCodicePromozione() {
		return codicePromozione;
	}
	/**
	 * @param codicePromozione the codicePromozione to set
	 */
	public void setCodicePromozione(String codicePromozione) {
		this.codicePromozione = codicePromozione;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		return Objects.hash(IDPromozione, codicePromozione, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AssociatoABean other = (AssociatoABean) obj;
		return Objects.equals(IDPromozione, other.IDPromozione)
				&& Objects.equals(codicePromozione, other.codicePromozione) && Objects.equals(username, other.username);
	}
	@Override
	public String toString() {
		return "AssociatoABean [username=" + username + ", IDPromozione=" + IDPromozione + ", codicePromozione="
				+ codicePromozione + "]";
	}
	
}