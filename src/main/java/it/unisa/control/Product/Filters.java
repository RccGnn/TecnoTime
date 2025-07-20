package it.unisa.control.Product;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import it.unisa.model.beans.ArticoloCompletoBean;
import it.unisa.model.beans.OrdineCompletoBean;

/**
 * Questa classe racchiude tutti i metodi usati per filtrare i prodotti
 */
public class Filters {
	/*
	 * FILTRI PER TUTTI GLI ARTICOLI
	 */
	
	public static ArrayList<ArticoloCompletoBean> pageFilter(ArrayList<ArticoloCompletoBean> catalogo, double offset, double limit) {
	
		if (catalogo == null)	return catalogo;
	
		// Verifica che offset non sia > o = alla dimensione della lista, in quanta limit è > 0
		if (offset < 0 || limit < 0) return catalogo;
				
		// Seleziona solo gli elementi nel range accettabile
		ArrayList <ArticoloCompletoBean> temp = new ArrayList<>();
		for (int i = (int) offset; i < catalogo.size(); i++) {
			if (i >= offset && i < limit)
				temp.add(catalogo.get(i));
		}
		
		return temp;
	}
	
	public static ArrayList<ArticoloCompletoBean> disponibilitaFilter(ArrayList<ArticoloCompletoBean> catalogo) {
		if (catalogo == null)	return catalogo;

		catalogo.removeIf(art -> art.getPdFisico() != null && !art.getPdFisico().isDisponibilita());
		catalogo.removeIf(art -> art.getPdDigitale() != null && !art.getPdDigitale().isDisponibilita());
		return catalogo;
	}
	
	public static ArrayList<ArticoloCompletoBean> brandFilter(ArrayList<ArticoloCompletoBean> catalogo, String brandName) {

		if (brandName == null || brandName.trim().isEmpty() || catalogo == null) return catalogo;
			
		// se la stringa dell utente da comparare non è contenuta nel nome completo dell articolo, questo verrà rimosso
		ArrayList<ArticoloCompletoBean> newCatalogo = new ArrayList<>();
		for (ArticoloCompletoBean articolo : catalogo) {
			if (articolo.getPdDigitale() != null && articolo.getPdDigitale().getCategoria().toLowerCase().contains(brandName.toLowerCase()) )
				newCatalogo.add(articolo);
		    else if (articolo.getPdFisico() != null && articolo.getPdFisico().getCategoria().toLowerCase().contains(brandName.toLowerCase()) )
				newCatalogo.add(articolo);
		    else if (articolo.getServizio() != null && articolo.getServizio().getCategoria().toLowerCase().contains(brandName.toLowerCase()) )
				newCatalogo.add(articolo);
		}

		return newCatalogo;
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
		if (contex.contains("articoliProdotti.jsp") || contex.contains("DisplaySubMenu1")) {
			// espressione lambda per controllare se un parametro è null, in caso affermativo viene eliminato dalla lista dei prodotti
			catalogo.removeIf(c -> c.getPdFisico() == null); 
		
		// La richiesta è avvenuta dalla pagina dei prodotti digitali
		} else if (contex.contains("articoliLicenze.jsp") || contex.contains("DisplaySubMenu2")) {
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

		double lambdaduration = Math.abs(duration);
		if (catalogo == null || catalogo.isEmpty()) return catalogo;
			catalogo.removeIf(c->c.getServizio().getDurata()<lambdaduration);
		return catalogo;
	}
	
	
	
	/*
	 * FILTRI DEGLI ORDINI
	 */
	
	public static ArrayList<OrdineCompletoBean> priceOrderFilter(ArrayList<OrdineCompletoBean> listaOrdini, double priceMin, double priceMax) {

		final double lambdaMin = Math.abs(priceMin);
		final double lambdaMax = Math.abs(priceMax);
		if (listaOrdini == null || listaOrdini.isEmpty()) return listaOrdini;
			listaOrdini.removeIf(ord -> (ord.getTotale() < lambdaMin || ord.getTotale() > lambdaMax) );
		return listaOrdini;
	}
	
	public static ArrayList<OrdineCompletoBean> dateOrderFilter(ArrayList<OrdineCompletoBean> listaOrdini, String dateMin, String dateMax) {

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        
        if (dateMin == null)
        	dateMin = "1970-01-01";
        if (dateMax == null)
        	dateMax = "3000-01-01";

        final LocalDate lambdaMin = LocalDate.parse(dateMin, formatter);
		final LocalDate lambdaMax = LocalDate.parse(dateMax, formatter);

		if (listaOrdini == null || listaOrdini.isEmpty()) return listaOrdini;
			listaOrdini.removeIf(ord -> (ord.getDataTransazione().toLocalDate().isBefore(lambdaMin) || ord.getDataTransazione().toLocalDate().isAfter(lambdaMax) ));
		return listaOrdini;
	}
}