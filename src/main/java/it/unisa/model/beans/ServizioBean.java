package it.unisa.model.beans;

import java.io.Serializable;

/*
 * CREATE TABLE Servizio (
  codiceServizio     VARCHAR(20)    PRIMARY KEY NOT NULL,
  durata           	 INT            NOT NULL, -- ORE/GIORNI
  codiceArticolo     VARCHAR(20)    NOT NULL,
  CONSTRAINT FOREIGN KEY (codiceArticolo) REFERENCES Articolo(codiceIdentificativo)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

 */
public class ServizioBean implements Serializable, BeanMarker{
	
	private static final long serialVersionUID = 1L;
	
	private String codiceServizio;
	private int durata;
	private String Articolo_codiceIdentificativo;
	
	public String getCodiceServizio() {
		return codiceServizio;
	}
	public void setCodiceServizio(String codiceServizio) {
		this.codiceServizio = codiceServizio;
	}
	public int getDurata() {
		return durata;
	}
	public void setDurata(int durata) {
		this.durata = durata;
	}
	public String getArticolo_codiceIdentificativo() {
		return Articolo_codiceIdentificativo;
	}
	public void setArticolo_codiceIdentificativo(String articolo_codiceIdentificativo) {
		Articolo_codiceIdentificativo = articolo_codiceIdentificativo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "ServizioBean [codiceServizio=" + codiceServizio + ", durata=" + durata
				+ ", Articolo_codiceIdentificativo=" + Articolo_codiceIdentificativo + "]";
	}
	
	
}