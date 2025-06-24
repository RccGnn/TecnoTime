package it.unisa.control;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {
	
	/**
	 * Restituisce l'hash di una passvord.
	 * @param pwd {@code String} - password da hashare (encriptare)
	 * @return {@code String} - hash sottoforma di stringa
	 * 
	 * @see org.mindrot.jbcrypt.BCrypt
	 */
	public static String hashPassword(String pwd) {
		return BCrypt.hashpw(pwd, BCrypt.gensalt(12));
	}
	
	/**
	 * Verifica l'hash di una password.
	 * @param pwd {@code String} - password sottoforma di stringa
	 * @param hashedpwd {@code String} - hash sottoforma di stringa
	 * @return {@code true} se l'hash decriptato {@code hashedpwd} corrisponde alla password {@code pwd}
	 * 
	 * @see org.mindrot.jbcrypt.BCrypt
	 */
	public static boolean checkPasswordHashed(String pwd, String hashedpwd) {
		return BCrypt.checkpw(pwd, hashedpwd);
	}
}
