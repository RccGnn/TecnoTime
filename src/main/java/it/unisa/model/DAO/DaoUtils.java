package it.unisa.model.DAO;

import java.util.regex.*;

import it.unisa.model.beans.AccountBean;
import it.unisa.model.beans.Ruoli;

public class DaoUtils {
	
	/**
	 * Verifica ed assegna il valore di ruolo ad un oggetto AccountBean
	 * @param account oggetto a cui assegnare il {@code Ruolo}
	 * @return ruolo, se assegnato
	 */
	public static String getRuoloAccountString(AccountBean account) throws NullPointerException{
		
		if (account == null)
			throw new NullPointerException("Oggetto account nullo");
		
		String email = account.getEmail();
		Pattern pattern = Pattern.compile("^.+@tecnotime.it$");
		Matcher match = pattern.matcher(email);
		
		Ruoli ruolo = null;
		if(match.find())
			ruolo = Ruoli.amministratore;
		else
			ruolo = Ruoli.utente_registrato;
		
		return ruolo.toString();
	}
	
	/**
	 * Verifica ed assegna il valore di ruolo ad un oggetto AccountBean
	 * @param account oggetto a cui assegnare il {@code Ruolo}
	 * @return ruolo, se assegnato
	 */
	public static Ruoli getRuoloAccount(AccountBean account) throws NullPointerException{
		
		if (account == null)
			throw new NullPointerException("Oggetto account nullo");
		
		String email = account.getEmail();
		Pattern pattern = Pattern.compile("^.+@tecnotime.it$");
		Matcher match = pattern.matcher(email);
		
		Ruoli ruolo = null;
		if(match.find())
			ruolo = Ruoli.amministratore;
		else
			ruolo = Ruoli.utente_registrato;
		
		return ruolo;
	}
}