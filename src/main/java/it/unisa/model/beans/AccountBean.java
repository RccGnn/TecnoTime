package it.unisa.model.beans;

import java.util.Date;

public class AccountBean {
	
	private static final long serialVersionUID = 1L;
	
	private String hashedPassword;
	private String username;
	private String nome;
	private String cognome;
	private char sesso;
	private String email;
	private String numeroTelefono; 
	private String nazione;
	private String provincia;
	private String citta; 
	private String via;
	private String numeroCivico;
	private String CAP;
	private Date dataNascita;
	private Ruoli ruolo;
	
	public String gethashedPassword() {
	    return hashedPassword;
	}

	public void sethashedPassword(String hashedPassword) {
	    this.hashedPassword = hashedPassword;
	}

	public String getUsername() {
	    return username;
	}

	public void setUsername(String username) {
	    this.username = username;
	}

	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setSesso(char sesso) {
		this.sesso = sesso;
	}
	
	public char getSesso() {
		return sesso;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getEmail() {
	    return email;
	}

	public void setEmail(String email) {
	    this.email = email;
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

	public String getProvincia() {
	    return provincia;
	}

	public void setProvincia(String provincia) {
	    this.provincia = provincia;
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

	public Date getDataNascita() {
	    return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
	    this.dataNascita = dataNascita;
	}

	public Ruoli getRuolo() {
	    return ruolo;
	}

	public void setRuolo(Ruoli ruolo) {
	    this.ruolo = ruolo;
	}

	@Override
	public String toString() {
		return "AccountBean [hashedPassword=" + hashedPassword + ", username=" + username + ", nome=" + nome
				+ ", cognome=" + cognome + ", sesso=" + sesso +", email=" + email + ", numeroTelefono=" + numeroTelefono + ", nazione="
				+ nazione + ", provincia=" + provincia + ", citta=" + citta + ", via=" + via + ", numeroCivico="
				+ numeroCivico + ", CAP=" + CAP + ", dataNascita=" + dataNascita + ", ruolo=" + ruolo + "]";
	}

}