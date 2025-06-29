package it.unisa.model.beans;

import java.io.Serializable;

public class ImmagineBean implements Serializable, BeanMarker{
	
	private static final long serialVersionUID = 1L;
	
	private int indice;
	private String url;
	private String Articolo_codiceIdentificativo;
	
	
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
	
	public String getArticolo_codiceIdentificativo() {
		return Articolo_codiceIdentificativo;
	}

	public void setArticolo_codiceIdentificativo(String Articolo_codiceIdentificativo) {
		this.Articolo_codiceIdentificativo = Articolo_codiceIdentificativo;
	}

	@Override
	public String toString() {
		return "ImmagineBean [indice=" + indice + ", url=" + url + ", Articolo_codiceIdentificativo="
				+ Articolo_codiceIdentificativo + "]";
	}

}