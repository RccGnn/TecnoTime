package it.unisa.model.Filters;

public record SchedaVideo(String nome,String marca, float PCI , int vram, String tipoRam, int Watt) {
	
	public static boolean isCompatibile(SchedaVideo vc, SchedaMadre mb) {
	    return vc.PCI().equals(mb.PCI());
}
	
}
