package it.unisa.model.beans;

public class AccountBean {
	
	private static final long serialVersionUID = 1L;
	
	private String password;
	private String username; 
	private String numeroTelefono; 
	private String nazione; 
	private String citta; 
	private String via;
	private String numeroCivico;
	private String CAP;
	private Ruoli ruolo;
	
	public String getPassword() {
	    return password;
	}

	public void setPassword(String password) {
	    this.password = password;
	}

	public String getUsername() {
	    return username;
	}

	public void setUsername(String username) {
	    this.username = username;
	}

	public String getNumeroTelefono() {
	    return numeroTelefono;
	}

	public void setNumeroTelefono(String numeroTelefono) {
	    this.numeroTelefono = numeroTelefono;
	}

	public String getNazione() {
	    return nazione;
	}

	public void setNazione(String nazione) {
	    this.nazione = nazione;
	}

	public String getCitta() {
	    return citta;
	}

	public void setCitta(String citta) {
	    this.citta = citta;
	}

	public String getVia() {
	    return via;
	}

	public void setVia(String via) {
	    this.via = via;
	}

	public String getNumeroCivico() {
	    return numeroCivico;
	}

	public void setNumeroCivico(String numeroCivico) {
	    this.numeroCivico = numeroCivico;
	}

	public String getCAP() {
	    return CAP;
	}

	public void setCAP(String CAP) {
	    this.CAP = CAP;
	}

	public Ruoli getRuolo() {
	    return ruolo;
	}

	public void setRuolo(Ruoli ruolo) {
	    this.ruolo = ruolo;
	}

}