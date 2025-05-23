package it.unisa.model;

import java.time.LocalDate;

public class user {
	public user() {	}
	
	public user(String name,String lastName, LocalDate birthDate,String ssn,String address,int postalCode,String email,int telNumb,char gender,String nation) {
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
	
	public String getName() { return name; }
	public String getLastName() {return lastName;}
    public String getEmail() { return email; }
    public int getTelNumb() { return telNumb; }
    public String getAddress() { return address;}
    public char getGender() { return gender; }
    public String getNation() { return nation; }
    public String getSsn() { return ssn;}
    public int postalCode() {return postalCode;}
    public LocalDate getBirthDate() { return birthDate;}
    
	
	private String name;
	private String lastName;
	private LocalDate birthDate;
	private String ssn;
	private String address;
	private int postalCode;
	private String email;
	private int telNumb;
	private char gender;
	private String nation;
}
