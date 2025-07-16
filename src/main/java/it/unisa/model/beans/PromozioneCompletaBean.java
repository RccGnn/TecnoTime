package it.unisa.model.beans;

import java.io.Serializable;
import java.util.Objects;

public class PromozioneCompletaBean extends PromozioneBean implements Serializable, BeanMarker{

	private static final long serialVersionUID = 1L;

	private RiguardaBean riguarda;
	private AssociatoABean associato;
	/**
	 * @return the riguarda
	 */
	public RiguardaBean getRiguarda() {
		return riguarda;
	}
	/**
	 * @param riguarda the riguarda to set
	 */
	public void setRiguarda(RiguardaBean riguarda) {
		this.riguarda = riguarda;
	}
	/**
	 * @return the associato
	 */
	public AssociatoABean getAssociato() {
		return associato;
	}
	/**
	 * @param associato the associato to set
	 */
	public void setAssociato(AssociatoABean associato) {
		this.associato = associato;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "PromozioneCompletaBean [riguarda=" + riguarda + ", associato=" + associato + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(associato, riguarda);
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
		PromozioneCompletaBean other = (PromozioneCompletaBean) obj;
		return Objects.equals(associato, other.associato) && Objects.equals(riguarda, other.riguarda);
	}
	
}
