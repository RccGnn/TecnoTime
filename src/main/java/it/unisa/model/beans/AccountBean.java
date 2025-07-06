package it.unisa.model.beans;

import java.io.Serializable;
import java.time.LocalDate;

public class AccountBean implements Serializable, BeanMarker {

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
	private LocalDate dataNascita;
	private Ruoli ruolo;
	private String AccountId;
	
	public AccountBean() {}

	public AccountBean(String password, String username, String firstName, String lastName, char genderChr,
			String email, String nation,String numtel, String province, String city, String address, String aptnumber,
			String postalCode, LocalDate birthDate, Ruoli rule) {
		this.hashedPassword = password;
		this.username = username;
		this.nome = firstName;
		this.cognome = lastName;
		this.sesso = genderChr;
		this.email = email;
		this.numeroTelefono = numtel;
		this.nazione = nation;
		this.provincia = province;
		this.citta = city;
		this.via = address;
		this.numeroCivico = aptnumber;
		this.CAP =postalCode ;
		this.dataNascita = birthDate;
		this.ruolo = rule;
	
	}
	
	public void setAccountId(String accountid) {
		AccountId=accountid;
	}
	public String getAccountId() {
		return AccountId;
	}

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

	public LocalDate getDataNascita() {
	    return dataNascita;
	}

	public void setDataNascita(LocalDate dataNascita) {
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
				+ ", cognome=" + cognome + ",AccountId="+AccountId+", sesso=" + sesso +", email=" + email + ", numeroTelefono=" + numeroTelefono + ", nazione="
				+ nazione + ", provincia=" + provincia + ", citta=" + citta + ", via=" + via + ", numeroCivico="
				+ numeroCivico + ", CAP=" + CAP + ", dataNascita=" + dataNascita + ", ruolo=" + ruolo + "]";
	}

}