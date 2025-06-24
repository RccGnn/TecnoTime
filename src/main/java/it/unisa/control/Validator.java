package it.unisa.control;

import it.unisa.model.DAO.AccountDao;
import it.unisa.model.beans.AccountBean;

public class Validator {
	public static boolean pwdValidator(String pwd, String pwdConferm) {
		if(pwd.length()<9||!pwd.equals(pwdConferm)) {
			return false;
		}
		else {
			boolean hasUpper = false;
	        boolean hasLower = false;
	        boolean hasDigit = false;
	        boolean hasSymbol = false;

	        for (char ch : pwd.toCharArray()) {
	            if (Character.isUpperCase(ch)) {
	                hasUpper = true;
	            } else if (Character.isLowerCase(ch)) {
	                hasLower = true;
	            } else if (Character.isDigit(ch)) {
	                hasDigit = true;
	            } else if (!Character.isLetterOrDigit(ch)) {
	                hasSymbol = true;
	            }
	        }

	        return hasUpper && hasLower && hasDigit && hasSymbol;
	    }		
	}
	
	/**
	 * Metodo di utilità per poter verificare un account è presente nel database dato l'username.
	 * @param username - {@code String} username da ricercare
	 * @return {@code false} se l'account con username {@code username} non è presente sul database,
	 * {@code true} altrimenti
	 */
	public static boolean checkUsername(String username) {
		if(username == null)
			return false;
		
		boolean flag = false;
		
		try {
			AccountDao dao = new AccountDao();
			AccountBean account = dao.doRetrieveByKey(username);
			if(account == null)
				flag = true;
			else
				flag = false;
		} catch (java.sql.SQLException e) {
			System.err.print(e.getMessage());
		} 
		
		return flag;
	}
	
	/**
	 * Metodo di utilità per poter verificare un account è presente nel database dato l'email.
	 * @param email - {@code String} email da ricercare
	 * @return {@code false} se l'account con email {@code email} è presente sul database,
	 * {@code true} se NON lo è
	 */
	public static boolean checkEmail(String email) {
		if(email == null)
			return false;
		
		boolean flag = false;
		
		try {
			AccountDao dao = new AccountDao();
			AccountBean account = dao.doRetrieveByUnique(email);
			if(account == null)
				flag = true;
			else
				flag = false;
		} catch (java.sql.SQLException e) {
			System.err.print(e.getMessage());
		} 
		
		return flag;
	}
}
