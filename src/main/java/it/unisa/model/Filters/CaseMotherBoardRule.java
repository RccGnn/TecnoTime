package it.unisa.model.Filters;

import java.util.Map;

public class CaseMotherBoardRule implements Compatible<Case, SchedaMadre>{

public boolean isCompatible(Case c, SchedaMadre mb) {
		
		if(codificaATX(c.dimensione())==-1 && codificaATX(mb.dimensione())==-1)
			return false;
		if(codificaATX(c.dimensione())>=codificaATX(mb.dimensione())) {
			return true;
		}
		else return false; 
	}
	
	
	
    private static final Map<String, Integer> codiciFormato = Map.of(
            "E-ATX", 4,
            "ATX", 3,
            "MICRO-ATX", 2,
            "MINI-ITX", 1
        );

    public static int codificaATX(String formato) {
        return codiciFormato.getOrDefault(formato.toUpperCase(), -1); // restituisce -1 se non trova
    }
}
