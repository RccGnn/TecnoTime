package it.unisa.control;

public class pwdValidator {
	public static boolean isValid(String pwd) {
		if(pwd.length()<9) {
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
}
