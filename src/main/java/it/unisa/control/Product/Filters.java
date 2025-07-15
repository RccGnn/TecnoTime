package it.unisa.control.Product;

import java.util.ArrayList;

import it.unisa.model.beans.ArticoloCompletoBean;

/**
 * Questa classe racchiude tutti i metodi usati per filtrare i prodotti
 */
public class Filters {
	/*
	 * FILTRI PER TUTTI GLI ARTICOLI
	 */
	public static ArrayList<ArticoloCompletoBean> disponibilitaFilter(ArrayList<ArticoloCompletoBean> catalogo) {
		if (catalogo == null)	return catalogo;

		catalogo.removeIf(art -> art.getPdFisico() != null && !art.getPdFisico().isDisponibilita());
		catalogo.removeIf(art -> art.getPdDigitale() != null && !art.getPdDigitale().isDisponibilita());
		return catalogo;
	}
	
	public static ArrayList<ArticoloCompletoBean> nameFilter(ArrayList<ArticoloCompletoBean> catalogo, String nomeComparator) {

		if (nomeComparator == null || nomeComparator.trim().isEmpty() || catalogo == null) return catalogo;
			
		// se la stringa dell utente da comparare non è contenuta nel nome completo dell articolo, questo verrà rimosso
		
		catalogo.removeIf(c-> !c.getNome().toLowerCase().contains(nomeComparator.toLowerCase()));
		return catalogo;
	}

	public static ArrayList<ArticoloCompletoBean> priceFilter(ArrayList<ArticoloCompletoBean> catalogo, double min, double max) {
		
		double minValue = Math.abs(min);
		double maxValue = Math.abs(max);
		
		if (min > max || catalogo == null) return catalogo;
		
		catalogo.removeIf(c -> {  //lambda per controllo su filtro prezzo
		    double price = 0;

		    if (c.getPdDigitale() != null)
		        price = c.getPdDigitale().getPrezzo();
		    else if (c.getPdFisico() != null)
		        price = c.getPdFisico().getPrezzo();
		    else if (c.getServizio() != null)
		        price = c.getServizio().getPrezzo();

		    // Rimuovi se il prezzo NON è nel range 
		    return price < minValue || price > maxValue;
		});
		return catalogo;
	}

	/*
	 * FILTRO PER IL CONTESTO
	 */

	public static ArrayList<ArticoloCompletoBean> contexFilter(ArrayList<ArticoloCompletoBean> catalogo, String contex) {
		
		if (catalogo == null || catalogo.isEmpty()) return catalogo;
		
		// La richiesta è avvenuta dalla pagina dei prodotti fisici
		if (contex.contains("articoliProdotti.jsp")) {
			// espressione lambda per controllare se un parametro è null, in caso affermativo viene eliminato dalla lista dei prodotti
			catalogo.removeIf(c -> c.getPdFisico() == null); 
		
		// La richiesta è avvenuta dalla pagina dei prodotti digitali
		} else if (contex.contains("articoliLicenze.jsp")) {
			catalogo.removeIf(c-> c.getPdDigitale() == null); 
	
		// La richiesta è avvenuta dalla pagina dei servizi
		} else if (contex.contains("articoliServizi.jsp")) {
			catalogo.removeIf(c-> c.getServizio() == null); 
		}
			
		return catalogo;
	}
	
	/*
	 * FILTRI PER I PRODOTTI FISICI
	 */
	public static ArrayList<ArticoloCompletoBean> categoryFilter(ArrayList<ArticoloCompletoBean> catalogo, String categoria) {

		if (catalogo == null || catalogo.isEmpty()) return catalogo;
			catalogo.removeIf(c -> !c.getPdFisico().getCategoria().toLowerCase().trim().contains(categoria.toLowerCase().trim()));
		return catalogo;
	}

	/*
	 * FILTRI PER I PRODOTTI DIGITALI
	 */
		
	/*
	 * FILTRI PER I SERVIZI 
	 */
	public static ArrayList<ArticoloCompletoBean> durationFilter(ArrayList<ArticoloCompletoBean> catalogo, double duration) {

		double lamdaduration = Math.abs(duration);
		if (catalogo == null || catalogo.isEmpty()) return catalogo;
			catalogo.removeIf(c->c.getServizio().getDurata()<lamdaduration);
		return catalogo;
	}
}