package it.unisa.model.beans;
import java.io.Serializable;
import java.util.Objects;
import java.sql.Date;

public class PromozioneBean implements Serializable, BeanMarker {

	private static final long serialVersionUID = 1L;
	
    private int IDPromozione;
    private Date dataInizio;
    private String nomesconto;
    private String descrizione;
    private double percentualeSconto;
	/**
	 * @return the iDPromozione
	 */
	public int getIDPromozione() {
		return IDPromozione;
	}
	/**
	 * @param iDPromozione the iDPromozione to set
	 */
	public void setIDPromozione(int iDPromozione) {
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
	public String getNomesconto() {
		return nomesconto;
	}
	public void setNomesconto(String nome) {
		this.nomesconto=nome;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	/**
	 * @param durata the durata to set
	 */
	public void setDescrizione (String descrizione) {
		this.descrizione = descrizione;
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
		return Objects.hash(IDPromozione, nomesconto,descrizione, dataInizio, percentualeSconto);
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
		return Objects.equals(IDPromozione, other.IDPromozione) && Objects.equals(nomesconto, other.nomesconto) 
				&& Objects.equals(dataInizio, other.dataInizio)	&& descrizione == other.descrizione 
				&& Double.doubleToLongBits(percentualeSconto) == Double.doubleToLongBits(other.percentualeSconto);
	}
	@Override
	public String toString() {
		return "PromozioneBean [IDPromozione=" + IDPromozione + ", nomesconto=" + nomesconto + ", dataInizio=" + dataInizio + ", descrizone=" + descrizione
				+ ", percentualeSconto=" + percentualeSconto + "]";
	}
    
    
}
