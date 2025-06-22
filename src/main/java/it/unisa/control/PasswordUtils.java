package it.unisa.control;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {
	public static String hashPassword(String pwd) {
		return BCrypt.hashpw(pwd, BCrypt.gensalt(12));
	}
	public static boolean checkPasswordHashed(String pwd, String hashedpwd) {
		return BCrypt.checkpw(pwd, hashedpwd);
	}
}
