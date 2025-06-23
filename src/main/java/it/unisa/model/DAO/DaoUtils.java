package it.unisa.model.DAO;

import java.util.regex.*;

import it.unisa.model.beans.AccountBean;
import it.unisa.model.beans.Ruoli;

public class DaoUtils {
	
	public static String getRuoloAccount(AccountBean account) {
		
		String email = account.getEmail();
		Pattern pattern = Pattern.compile("^.+@tecnotime.it$", Pattern.CASE_INSENSITIVE);
		Matcher match = pattern.matcher(email);
		
		Ruoli ruolo = null;
		if(match.find())
			ruolo = Ruoli.amministratore;
		else
			ruolo = Ruoli.utente_registrato;
		
		return ruolo.toString();
	}
}