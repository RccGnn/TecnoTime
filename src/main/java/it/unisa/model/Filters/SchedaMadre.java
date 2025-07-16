package it.unisa.model.Filters;

public record SchedaMadre(String nome, String marca, String socket, Boolean wifi, float PCI, String tipoRamSupportata, int watt) {
	
	  public static boolean ramCompatibileConSchedaMadre(Ram ram, SchedaMadre mb) {
	        return ram.tipoRam().equalsIgnoreCase(mb.tipoRamSupportata());
	    }
}
