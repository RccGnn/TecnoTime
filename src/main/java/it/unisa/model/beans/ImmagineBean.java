package it.unisa.model.beans;

import java.io.Serializable;

public class ImmagineBean implements Serializable, BeanMarker{
	
	private static final long serialVersionUID = 1L;
	
	private int indice;
	private String url;
	private String Account_codiceIdentificativo;
	
	
	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getAccount_codiceIdentificativo() {
		return Account_codiceIdentificativo;
	}

	public void setAccount_codiceIdentificativo(String account_codiceIdentificativo) {
		Account_codiceIdentificativo = account_codiceIdentificativo;
	}

	@Override
	public String toString() {
		return "ImmagineBean [indice=" + indice + ", url=" + url + ", Account_codiceIdentificativo="
				+ Account_codiceIdentificativo + "]";
	}

}