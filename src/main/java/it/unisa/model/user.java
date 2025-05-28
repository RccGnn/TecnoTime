package it.unisa.model;

import java.time.LocalDate;

public class user {
	public user() {	}
	
	public user(String name,String lastName, LocalDate birthDate,String ssn,String address,int postalCode,String email,String telNumb,char gender,String nation) {
		this.name=name;
		this.lastName=lastName;
		this.address=address;
		this.birthDate=birthDate;
		this.ssn=ssn;
		this.postalCode=postalCode;
		this.email=email;
		this.telNumb=telNumb;
		this.gender=gender;
		this.nation=nation;		
	}
	
	//getter
	public String getName() { return name; }
	public String getLastName() {return lastName;}
    public String getEmail() { return email; }
    public String getTelNumb() { return telNumb; }
    public String getAddress() { return address;}
    public char getGender() { return gender; }
    public String getNation() { return nation; }
    public String getSsn() { return ssn;}
    public int postalCode() {return postalCode;}
    public LocalDate getBirthDate() { return birthDate;}

    //setter
	public void setName(String name) {
		this.name=name;
	}
    public void setLastName(String lastName) {
    	this.lastName=lastName;
    }
    public void setAddress(String address) {
    	this.address=address;
    }
    public void setEmail(String email) {
    	this.email=email;
    }
    public void setTelNumb(String telNumb ) {
    	this.telNumb=telNumb;
    }
    public void setGender(char gender) {
    	this.gender=gender;
    }
    public void setNation(String nation) {
    	this.nation=nation;
    }
    public void setSsn(String ssn) {
    	this.ssn=ssn;
    }
    public void setPostalCode(int postalcode) {
    	this.postalCode=postalcode;
    }
    public void setBirthDate(LocalDate birthdate) {
    	this.birthDate=birthdate;
	}
    


	private String name;
	private String lastName;
	private LocalDate birthDate;
	private String ssn;
	private String address;
	private int postalCode;
	private String email;
	private String telNumb;
	private char gender;
	private String nation;
}
