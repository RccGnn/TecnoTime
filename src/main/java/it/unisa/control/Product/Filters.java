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

		ArrayList<CatalogoBean> filtered = new ArrayList<>(0);
		if (nomeComparator == null || nomeComparator.trim().isEmpty() || catalogo == null) return filtered;

		for (CatalogoBean c : catalogo) {

			String nome = c.getArticolo().getNome();			
			if(nome != null && nome.toLowerCase().contains(nomeComparator.toLowerCase()))
				filtered.add(c);
		}
		return filtered;
	}

	public static ArrayList<CatalogoBean> priceFilter(ArrayList<CatalogoBean> catalogo, double min, double max) {
		
		min = Math.abs(min);
		max = Math.abs(max);
		ArrayList<CatalogoBean> filtered = new ArrayList<>(0);
		if (min > max || catalogo == null) return filtered;
		
		for (CatalogoBean c : catalogo) {

			double price = 0;
			// L'articolo considerato è un prodotto digitale
			if (c.getPdDigitale() != null)
				price = c.getPdDigitale().getPrezzo();
			// L'articolo considerato è un prodotto fisico
			else if (c.getPdFisico() != null)
				price = c.getPdFisico().getPrezzo();
			// L'articolo considerato è un servizio
			else if (c.getServizio() != null)
				price = c.getServizio().getPrezzo();
			
			if(price >= min && price <= max)
				filtered.add(c);
		}
		return filtered;
	}

	/*
	 * FILTRO PER IL CONTESTO
	 */

	public static ArrayList<CatalogoBean> contexFilter(ArrayList<CatalogoBean> catalogo, String contex) {
		
		ArrayList<CatalogoBean> filtered = new ArrayList<CatalogoBean>(0);
		if (catalogo == null || catalogo.isEmpty()) return filtered;
		
		// La richiesta è avvenuta dalla pagina dei prodotti fisici
		if (contex.contains("articoliProdotti.jsp")) {
			for (CatalogoBean c : catalogo) {
				//System.out.print("AO1");
				if (c.getPdFisico() != null) filtered.add(c);
			}
		// La richiesta è avvenuta dalla pagina dei prodotti digitali
		} else if (contex.contains("articoliLicenze.jsp")) {
			for (CatalogoBean c : catalogo) {
				//System.out.print("AO2");
				if (c.getPdDigitale() != null) filtered.add(c);
			}
		// La richiesta è avvenuta dalla pagina dei servizi
		} else if (contex.contains("articoliServizi.jsp")) {
			for (CatalogoBean c : catalogo) {
				//System.out.print("AO3");
				if (c.getServizio() != null) filtered.add(c);
			}
		}
			
		return filtered;
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

		ArrayList<CatalogoBean> filtered = new ArrayList<>(0);
		duration = Math.abs(duration);
		if (catalogo == null || catalogo.isEmpty()) return filtered;

		for (CatalogoBean c : catalogo) {

			double durata = c.getServizio().getDurata();			
			if(durata >= duration)
				filtered.add(c);
		}
		return filtered;
	}
}