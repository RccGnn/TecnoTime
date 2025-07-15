package it.unisa.model.Filters;

public record SchedaMadre(String nome, String socket,String tipoRamSupportata,String PCI) {
	
	  public static boolean ramCompatibileConSchedaMadre(Ram ram, SchedaMadre mb) {
	        return ram.tipoRam().equalsIgnoreCase(mb.tipoRamSupportata());
	    }
}
