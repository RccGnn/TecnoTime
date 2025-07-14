package it.unisa.model.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Utilizzare metodi ereditati comporta che le modifiche avvengono solo per 
 * l'oggetto OdineBean e non per la lista di elementi contenuti nell'ordine
 */
public class OrdineCompletoBean extends OrdineBean implements Serializable, BeanMarker{
	
	private static final long serialVersionUID = 1L;

	private ArrayList<ElementoOrdineBean> elementiOrdine = new ArrayList<ElementoOrdineBean>();

	/**
	 * @return the elementiOrdine
	 */
	public ArrayList<ElementoOrdineBean> getElementiOrdine() {
		return elementiOrdine;
	}

	/**
	 * @param elementiOrdine the elementiOrdine to set
	 */
	public void setElementiOrdine(ArrayList<ElementoOrdineBean> elementiOrdine) {
		this.elementiOrdine = elementiOrdine;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(elementiOrdine);
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
		OrdineCompletoBean other = (OrdineCompletoBean) obj;
		return checkArrayList(other);
	}

	@Override
	public String toString() {
		return "OrdineCompletoBean [elementiOrdine=" + elementiOrdine + "]";
	}
	
	
	private boolean checkArrayList(OrdineCompletoBean other) {
		ArrayList<ElementoOrdineBean> otherElementiOrdine = other.elementiOrdine;

		int size = elementiOrdine.size();
		if (otherElementiOrdine.size() != size)
			return false;
		
		for (int i = 0; i < size; i++) {
			if (!otherElementiOrdine.get(i).equals(elementiOrdine.get(i)))
				return false;
		}
		
		return true;
	}
}