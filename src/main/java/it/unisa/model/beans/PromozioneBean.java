package it.unisa.model.beans;
import java.io.Serializable;
import java.util.Objects;
import java.sql.Date;

public class PromozioneBean implements Serializable, BeanMarker {

	private static final long serialVersionUID = 1L;
	
    private String IDPromozione;
    private Date dataInizio;
    private int durata;
    private double percentualeSconto;
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
	 * @return the dataInizio
	 */
	public Date getDataInizio() {
		return dataInizio;
	}
	/**
	 * @param dataInizio the dataInizio to set
	 */
	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}
	/**
	 * @return the durata
	 */
	public int getDurata() {
		return durata;
	}
	/**
	 * @param durata the durata to set
	 */
	public void setDurata(int durata) {
		this.durata = durata;
	}
	/**
	 * @return the percentualeSconto
	 */
	public double getPercentualeSconto() {
		return percentualeSconto;
	}
	/**
	 * @param percentualeSconto the percentualeSconto to set
	 */
	public void setPercentualeSconto(double percentualeSconto) {
		this.percentualeSconto = percentualeSconto;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		return Objects.hash(IDPromozione, dataInizio, durata, percentualeSconto);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PromozioneBean other = (PromozioneBean) obj;
		return Objects.equals(IDPromozione, other.IDPromozione) && Objects.equals(dataInizio, other.dataInizio)
				&& durata == other.durata
				&& Double.doubleToLongBits(percentualeSconto) == Double.doubleToLongBits(other.percentualeSconto);
	}
	@Override
	public String toString() {
		return "PromozioneBean [IDPromozione=" + IDPromozione + ", dataInizio=" + dataInizio + ", durata=" + durata
				+ ", percentualeSconto=" + percentualeSconto + "]";
	}
    
    
}
