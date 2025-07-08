package it.unisa.model.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class ArticoloCompletoBean implements Serializable, BeanMarker{
	
	private static final long serialVersionUID = 1L;
	
	private String codiceIdentificativo;
	private String nome;
	
	private ProdottoFisicoBean pdFisico;
	private ProdottoDigitaleBean pdDigitale;
	private ServizioBean servizio;
	private ArrayList<ImmagineBean> immagini;
	
	public String getCodiceIdentificativo() {
		return codiceIdentificativo;
	}
	public void setCodiceIdentificativo(String codiceIdentificativo) {
		this.codiceIdentificativo = codiceIdentificativo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public ProdottoFisicoBean getPdFisico() {
		return pdFisico;
	}
	public void setPdFisico(ProdottoFisicoBean pdFisico) {
		this.pdFisico = pdFisico;
	}
	public ProdottoDigitaleBean getPdDigitale() {
		return pdDigitale;
	}
	public void setPdDigitale(ProdottoDigitaleBean pdDigitale) {
		this.pdDigitale = pdDigitale;
	}
	public ServizioBean getServizio() {
		return servizio;
	}
	public void setServizio(ServizioBean servizio) {
		this.servizio = servizio;
	}
	public ArrayList<ImmagineBean> getImmagini() {
		return immagini;
	}
	public void setImmagini(ArrayList<ImmagineBean> immagini) {
		this.immagini = immagini;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return super.toString()+"ArticoloCompletoBean [codiceIdentificativo=" + codiceIdentificativo + ", nome=" + nome + ", pdFisico="
				+ pdFisico + ", pdDigitale=" + pdDigitale + ", servizio=" + servizio + ", immagini=" + immagini + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(codiceIdentificativo, immagini, nome, pdDigitale, pdFisico, servizio);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArticoloCompletoBean other = (ArticoloCompletoBean) obj;
		return Objects.equals(codiceIdentificativo, other.codiceIdentificativo)
				/*&& Objects.equals(immagini, other.immagini)*/ && Objects.equals(nome, other.nome)
				&& Objects.equals(pdDigitale, other.pdDigitale) && Objects.equals(pdFisico, other.pdFisico)
				&& Objects.equals(servizio, other.servizio);
	}
	
	
	
}