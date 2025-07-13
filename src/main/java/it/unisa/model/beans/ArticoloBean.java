package it.unisa.model.beans;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class ArticoloBean implements Serializable, BeanMarker{

	private static final long serialVersionUID = 1L;
	
	private String codiceIdentificativo;
	private String categoria;
	private String nome;
	private Date dataUltimaPromozione;
	private String enteErogatore;
	private boolean disponibilita;
	
	
	public String getCodiceIdentificativo() {
		return codiceIdentificativo;
	}
	
	public void setCodiceIdentificativo(String codiceIdentificativo) {
		this.codiceIdentificativo = codiceIdentificativo;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
		
	public Date getDataUltimaPromozione() {
		return dataUltimaPromozione;
	}
	
	public void setDataUltimaPromozione(Date dataUltimaPromozione) {
		this.dataUltimaPromozione = dataUltimaPromozione;
	}
	
	public String getEnteErogatore() {
		return enteErogatore;
	}
	
	public void setEnteErogatore(String enteErogatore) {
		this.enteErogatore = enteErogatore;
	}
	
	public boolean isDisponibilita() {
		return disponibilita;
	}
	
	public void setDisponibilita(boolean disponibilita) {
		this.disponibilita = disponibilita;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ArticoloBean [codiceIdentificativo=" + codiceIdentificativo + ", categoria=" + categoria + ", nome="
				+ nome + ", dataUltimaPromozione=" + dataUltimaPromozione + ", enteErogatore=" + enteErogatore
				+ ", disponibilita=" + disponibilita + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoria, codiceIdentificativo, dataUltimaPromozione, disponibilita, enteErogatore, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArticoloBean other = (ArticoloBean) obj;
		return Objects.equals(categoria, other.categoria)
				&& Objects.equals(codiceIdentificativo, other.codiceIdentificativo)
				&& Objects.equals(dataUltimaPromozione, other.dataUltimaPromozione)
				&& disponibilita == other.disponibilita && Objects.equals(enteErogatore, other.enteErogatore)
				&& Objects.equals(nome, other.nome);
	}
	
	
	
}