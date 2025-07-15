package it.unisa.model.Filters;

public record SchedaVideo(String nome,String PCI) {
	
	public static boolean isCompatibile(SchedaVideo vc, SchedaMadre mb) {
	    return vc.PCI().equals(mb.PCI());
}
	
}
