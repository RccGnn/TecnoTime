package it.unisa.control.Product;

import java.util.ArrayList;

import it.unisa.model.beans.CatalogoBean;

/**
 * Questa classe racchiude tutti i metodi usati per filtrare i prodotti
 */
public class Filters {
	/*
	 * FILTRI PER TUTTI GLI ARTICOLI
	 */
	public static ArrayList<CatalogoBean> nameFilter(ArrayList<CatalogoBean> catalogo, String nomeComparator) {

		if (nomeComparator == null || nomeComparator.trim().isEmpty() || catalogo == null) return catalogo;
			
		// se la stringa dell utente da comparare non è contenuta nel nome completo dell articolo verrà rimosso  
		catalogo.removeIf(c-> !c.getArticolo().getNome().toLowerCase().contains(nomeComparator.toLowerCase()));
		return catalogo;
	}

	public static ArrayList<CatalogoBean> priceFilter(ArrayList<CatalogoBean> catalogo, double min, double max) {
		
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

	public static ArrayList<CatalogoBean> contexFilter(ArrayList<CatalogoBean> catalogo, String contex) {
		
		if (catalogo == null || catalogo.isEmpty()) return catalogo;
		
		// La richiesta è avvenuta dalla pagina dei prodotti fisici
		if (contex.contains("articoliProdotti.jsp")) {
			catalogo.removeIf(c -> c.getPdFisico() == null); // espressione lambda per controllare se un parametro è null
		
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
		
	/*
	 * FILTRI PER I PRODOTTI DIGITALI
	 */
		
	/*
	 * FILTRI PER I SERVIZI 
	 */

	public static ArrayList<CatalogoBean> durationFilter(ArrayList<CatalogoBean> catalogo, double duration) {

		double lamdaduration = Math.abs(duration);
		if (catalogo == null || catalogo.isEmpty()) return catalogo;
			catalogo.removeIf(c->c.getServizio().getDurata()<lamdaduration);
		return catalogo;
	}
}