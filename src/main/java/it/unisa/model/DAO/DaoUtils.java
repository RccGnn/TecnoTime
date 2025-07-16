package it.unisa.model.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.regex.*;
import java.util.stream.Collectors;

import it.unisa.control.Decoder;
import it.unisa.model.DAO.Promotion.PromozioneCompletaDao;
import it.unisa.model.beans.AccountBean;
import it.unisa.model.beans.ArticoloCompletoBean;
import it.unisa.model.beans.CarrelloRiempitoBean;
import it.unisa.model.beans.ImmagineBean;
import it.unisa.model.beans.ProdottoDigitaleBean;
import it.unisa.model.beans.ProdottoFisicoBean;
import it.unisa.model.beans.PromozioneCompletaBean;
import it.unisa.model.beans.Ruoli;
import it.unisa.model.beans.ServizioBean;

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
	
	public static <T> int countOccurencies(ArrayList<T> array, T oggetto) {
		int count = 0;
		for (T a : array) {
			if (a.equals(array))
				count++;
		}
		
		return count;
	}
	
	/**
	 * Questo metodo permette di impostare correttamente l'url delle immagini derivanti da DropBox.
	 * @param catalogo {@code ArrayList<ArticoloCompletoBean>} - lista di articoli 
	 * @return catalogo con gli url opportunamente modificati (solo quelli di dropbox)
	 */
	public static ArrayList<ArticoloCompletoBean> dropboxImagesDecoderUrl(ArrayList<ArticoloCompletoBean> catalogo) {
		
		ArrayList<ImmagineBean> imgs;
		for (ArticoloCompletoBean c : catalogo) {
			// Modifica le immagini in un formato visibile
        	imgs = c.getImmagini();
        	if (imgs != null) {
				imgs.forEach(img -> img.setUrl(Decoder.DecoderDropboxUrl(img.getUrl())) );
				c.setImmagini(imgs);
			} else {
				c.setImmagini(new ArrayList<ImmagineBean>(0));
			}
		}
		
		return catalogo;
	}
	
	
	/**
	 * Restituisce la somma dei prezzi di tutti gli articoli di un carrello;
	 * @param carrello {@code CarrelloRiempitoBean} - Carrello con articoli
	 * @return {@code couble: }{@value totale}
	 */
	public static double totaleCarrello(CarrelloRiempitoBean carrello) {
		double totale = 0;
		ArrayList<ArticoloCompletoBean> listaArticoli = carrello.getListaArticoli();

		for (ArticoloCompletoBean articolo : listaArticoli) {
			if (articolo.getPdFisico() != null)
				totale += articolo.getPdFisico().getPrezzo();
			if (articolo.getPdDigitale() != null)
				totale += articolo.getPdDigitale().getPrezzo();
			if (articolo.getServizio() != null)
				totale += articolo.getPdDigitale().getPrezzo();
		}
		
		return totale;
	}
	
	/**
	 * Restituisce la lista di articoli dove ai prezzi viene applicato lo sconto
	 * @param lista {@code ArrayList<ArticoloCompletoBean>} - lista di Articoli
	 * @return {@code ArrayList<ArticoloCompletoBean>: }{@value discountedList} 
	 */
	public static ArrayList<ArticoloCompletoBean> discountedItemList(ArrayList<ArticoloCompletoBean> lista) {
		PromozioneCompletaDao dao = new PromozioneCompletaDao();
		try {
			ArrayList<PromozioneCompletaBean> rawDiscountList = dao.doRetrieveByKeyProducts("");
			
			// Mappa le percentuali di sconto per ogni ID articolo univoco.
			// Usa Collectors.toMap con un merge function per gestire duplicati (mantiene il primo).
			Map<String, Double> articleDiscountPercentages = rawDiscountList.stream()
				.filter(disc -> disc.getRiguarda() != null && disc.getRiguarda().getCodiceIdentificativo() != null)
				.collect(Collectors.toMap(
					disc -> disc.getRiguarda().getCodiceIdentificativo(),
					PromozioneCompletaBean::getPercentualeSconto,
					(existing, replacement) -> existing // Se ci sono più sconti per lo stesso articolo, usa il primo trovato
				));

			// Applica gli sconti creando una nuova lista con articoli copiati manualmente
			ArrayList<ArticoloCompletoBean> discountedCopiedList = new ArrayList<>();
			
			for (ArticoloCompletoBean originalArticolo : lista) {
				// Crea una NUOVA istanza di ArticoloCompletoBean
				ArticoloCompletoBean copiedArticolo = new ArticoloCompletoBean();
				
				// Copia manualmente tutti i campi dell'ArticoloCompletoBean originale nella nuova istanza
				copiedArticolo.setCodiceIdentificativo(originalArticolo.getCodiceIdentificativo());
				copiedArticolo.setNome(originalArticolo.getNome());
				
				// Copia profonda di ProdottoFisicoBean (se presente)
				if (originalArticolo.getPdFisico() != null) {
					ProdottoFisicoBean originalPdFisico = originalArticolo.getPdFisico();
					ProdottoFisicoBean copiedPdFisico = new ProdottoFisicoBean();
					// Copia tutti i campi del ProdottoFisicoBean
					copiedPdFisico.setSeriale(originalPdFisico.getSeriale());
					copiedPdFisico.setPrezzo(originalPdFisico.getPrezzo()); // Prezzo originale
					copiedPdFisico.setDescrizione(originalPdFisico.getDescrizione());
					copiedPdFisico.setPreassemblato(originalPdFisico.isPreassemblato());
					copiedPdFisico.setQuantitaMagazzino(originalPdFisico.getQuantitaMagazzino());
					copiedPdFisico.setCodiceIdentificativo(originalPdFisico.getCodiceIdentificativo());
					copiedPdFisico.setCategoria(originalPdFisico.getCategoria());
					copiedPdFisico.setNome(originalPdFisico.getNome());
					copiedPdFisico.setDataUltimaPromozione(originalPdFisico.getDataUltimaPromozione());
					copiedPdFisico.setEnteErogatore(originalPdFisico.getEnteErogatore());
					copiedPdFisico.setDisponibilita(originalPdFisico.isDisponibilita());
					copiedArticolo.setPdFisico(copiedPdFisico);
				}

				// Copia profonda di ProdottoDigitaleBean (se presente)
				if (originalArticolo.getPdDigitale() != null) {
					ProdottoDigitaleBean originalPdDigitale = originalArticolo.getPdDigitale();
					ProdottoDigitaleBean copiedPdDigitale = new ProdottoDigitaleBean();
					// Copia tutti i campi del ProdottoDigitaleBean
					copiedPdDigitale.setCodiceSoftware(originalPdDigitale.getCodiceSoftware());
					copiedPdDigitale.setDescrizione(originalPdDigitale.getDescrizione());
					copiedPdDigitale.setPrezzo(originalPdDigitale.getPrezzo()); // Prezzo originale
					copiedPdDigitale.setNumeroChiavi(originalPdDigitale.getNumeroChiavi());
					copiedPdDigitale.setCodiceIdentificativo(originalPdDigitale.getCodiceIdentificativo());
					copiedPdDigitale.setCategoria(originalPdDigitale.getCategoria());
					copiedPdDigitale.setNome(originalPdDigitale.getNome());
					copiedPdDigitale.setDataUltimaPromozione(originalPdDigitale.getDataUltimaPromozione());
					copiedPdDigitale.setEnteErogatore(originalPdDigitale.getEnteErogatore());
					copiedPdDigitale.setDisponibilita(originalPdDigitale.isDisponibilita());
					copiedArticolo.setPdDigitale(copiedPdDigitale);
				}
				
				// Copia profonda di ServizioBean (se presente)
				if (originalArticolo.getServizio() != null) {
					ServizioBean originalServizio = originalArticolo.getServizio();
					ServizioBean copiedServizio = new ServizioBean();
					// Copia tutti i campi del ServizioBean
					copiedServizio.setCodiceServizio(originalServizio.getCodiceServizio());
					copiedServizio.setPrezzo(originalServizio.getPrezzo()); // Prezzo originale
					copiedServizio.setDescrizione(originalServizio.getDescrizione());
					copiedServizio.setDurata(originalServizio.getDurata());
					copiedServizio.setCodiceIdentificativo(originalServizio.getCodiceIdentificativo());
					copiedServizio.setCategoria(originalServizio.getCategoria());
					copiedServizio.setNome(originalServizio.getNome());
					copiedServizio.setDataUltimaPromozione(originalServizio.getDataUltimaPromozione());
					copiedServizio.setEnteErogatore(originalServizio.getEnteErogatore());
					copiedServizio.setDisponibilita(originalServizio.isDisponibilita());
					copiedArticolo.setServizio(copiedServizio);
				}
				
				// Copia profonda delle Immagini
				if (originalArticolo.getImmagini() != null) {
					ArrayList<ImmagineBean> copiedImmagini = new ArrayList<>();
					for (ImmagineBean originalImmagine : originalArticolo.getImmagini()) {
						ImmagineBean copiedImmagine = new ImmagineBean();
						copiedImmagine.setIndice(originalImmagine.getIndice());
						copiedImmagine.setUrl(originalImmagine.getUrl());
						copiedImmagine.setArticolo_codiceIdentificativo(originalImmagine.getArticolo_codiceIdentificativo());
						copiedImmagini.add(copiedImmagine);
					}
					copiedArticolo.setImmagini(copiedImmagini);
				}

				String codiceIdentificativo = copiedArticolo.getCodiceIdentificativo();
				
				// Verifica se esiste uno sconto per questo codice identificativo
				if (articleDiscountPercentages.containsKey(codiceIdentificativo)) {
					double percent = articleDiscountPercentages.get(codiceIdentificativo);
					float newPrice;

					// Applica lo sconto al prezzo dell'articolo copiato (uno solo tra i 3 tipi sarà non-null)
					if (copiedArticolo.getPdFisico() != null) {
						newPrice = (float) (copiedArticolo.getPdFisico().getPrezzo() * (1 - percent / 100));
						copiedArticolo.getPdFisico().setPrezzo(newPrice);
					} else if (copiedArticolo.getPdDigitale() != null) {
						newPrice = (float) (copiedArticolo.getPdDigitale().getPrezzo() * (1 - percent / 100));
						copiedArticolo.getPdDigitale().setPrezzo(newPrice);
					} else if (copiedArticolo.getServizio() != null) {
						newPrice = (float) (copiedArticolo.getServizio().getPrezzo() * (1 - percent / 100));
						copiedArticolo.getServizio().setPrezzo(newPrice);
					}
				}
				
				// Aggiungi l'articolo copiato (e potenzialmente scontato) alla nuova lista
				discountedCopiedList.add(copiedArticolo);
			}

			return discountedCopiedList; // Restituisce la nuova lista con gli sconti applicati

		} catch (SQLException e) {
			System.err.println("Errore applicazione degli sconti: " + e.getMessage());
			return new ArrayList<>(); // In caso di errore, restituisce una lista vuota per evitare NullPointerException
		}
	}
	
}