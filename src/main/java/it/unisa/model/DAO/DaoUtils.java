package it.unisa.model.DAO;

import java.util.regex.*;

import it.unisa.model.beans.AccountBean;
import it.unisa.model.beans.Ruoli;

public class DaoUtils {
	
	/**
	 * Verifica ed assegna il valore di ruolo ad un oggetto AccountBean
	 * @param account oggetto a cui assegnare il {@code Ruolo}
	 * @return ruolo come oggetto della classe {@code Enum(Ruolo)} se assegnato
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
	 * @return ruolo convertito in {@code String} se assegnato
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
	
	/**
	 * Metodo di utilità per far visualizzare l'immagine contenuta nel link di dropbox piuttosto che la pagina html
	 * @param 	url {@code String} - url da convertire
	 * @return	{@code String} urlDecoded dove il parametro "dl=0" è sostituito con "raw=1"
	 */
	public static String dropboxUrlDecoder(String url) {
		if(url == null || !url.contains("www.dropbox.com"))
			return null;
		else
			return url.replace("dl=0", "raw=1");
	}

	/**
	 * Metodo di utilità per far visualizzare la pagina html piuttosto che l'immagine contenuta nel link di dropbox
	 * @param 	url {@code String} - url da convertire
	 * @return	{@code String} urlDecoded dove il parametro "raw=1" è sostituito con "dl=0"
	 */
	public static String dropboxUrlEncoder(String url) {
		if(url == null || !url.contains("www.dropbox.com"))
			return null;
		else
			return url.replace("raw=1", "dl=0");
	}
	
	
	/**
	 * Verifica se la stringa {@code suspect} può essere usata come clausula ORDER BY di una query SQL.
	 * Viene incluso il controllo per i parametri asc e desc di ORDER BY.
	 * @param whitelist	{@code String[]} - array di tutti gli attributi della tabella
	 * @param suspect {@code String} - stringa da controllare
	 * @return {@code true} se la stringa suspect può essere usata in una query SQL, {@code false} altrimenti 
	 */
	public static boolean checkWhitelist(String[] whitelist, String suspect) {
		if(whitelist == null) return false;
		
		boolean flag = false;
		
		for (String w : whitelist) {
			if(suspect.trim().toLowerCase().equals(w.toLowerCase()+" asc") || suspect.trim().toLowerCase().equals(w.toLowerCase()+" desc")) {
				flag = true;
				break;
			}
		}
		
		return flag;
	}
	
}




