package it.unisa.model.Filters;

import java.util.Map;

public class BuildChecker {

	public static boolean isCompatible(Case c, SchedaMadre mb) {
		
		if(codificaATX(c.dimensione())==-1 && codificaATX(mb.dimensione())==-1)
			return false;
		if(codificaATX(c.dimensione())>=codificaATX(mb.dimensione())) {
			return true;
		}
		else return false; 
	}
	
	public static boolean isCompatible(Processore p , SchedaMadre mb) {
		return p.socket().equals(mb.socket());
	}
	
	public static boolean isCompatible(SchedaMadre mb, Ram ram) {
		return mb.tipoRamSupportata().equals(ram.SupportoRam());		
	}
	
	public static int minimumWatt(SchedaMadre mb, SchedaVideo gpu, Processore cpu) {
		 int value = (int) ((mb.watt()+gpu.watt()+cpu.watt()) * 1.30);
		 		if(value <= 500)
		 			return 500;
		 		if(value <= 750 && value > 500)
		 			return 750;
		 		if(value <= 850 && value > 750)
		 			return 850;
		 		if(value <= 1000 && value > 850)
		 			return 1000;
		 		if(value <= 1200 && value > 1000)
		 			return 1200;
		 		if(value <= 1500 && value > 1200)
		 			return 1500;
		 		return 0;
	}
	
	public static boolean buildValidator(Case c, SchedaMadre mb,Processore cpu, Ram ram, SchedaVideo gpu, Alimentatore psu ) {
		boolean res= false;
		boolean psuvalidator= false;
		if(c!=null && mb!=null && cpu!=null && ram!=null && gpu!=null){
			res=BuildChecker.isCompatible(cpu, mb) && BuildChecker.isCompatible(mb, ram) && BuildChecker.isCompatible(c, mb);
			if(psu.watt()>=minimumWatt(mb, gpu, cpu))
				psuvalidator=true;
			return res && psuvalidator;
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










