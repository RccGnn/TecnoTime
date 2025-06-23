package it.unisa.control;

import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;

import it.unisa.model.DAO.AccountDao;
import it.unisa.model.beans.AccountBean;

public class PasswordUtils {
	
	public static String hashPassword(String pwd) {
		return BCrypt.hashpw(pwd, BCrypt.gensalt(12));
	}
	
	public static boolean checkPasswordHashed(String pwd, String hashedpwd) {
		return BCrypt.checkpw(pwd, hashedpwd);
	}
	
	public static boolean checkUsername(String username) {
		boolean flag = false;
		try {
			AccountDao dao = new AccountDao();
			ArrayList<AccountBean> accList = dao.doRetrieveAll(username);
			if(accList == null)
				flag = false;
			else
				flag = true;
		} catch (java.sql.SQLException e) {
			System.err.print(e.getMessage());
		}
		
		return flag;
	}
}
