package it.unisa.model.Filters;

import java.util.List;

public record Processore (String nome, List<String> socketCompatibili){
	
	public static boolean isCompatibile(Processore cpu, SchedaMadre mb) {
    return cpu.socketCompatibili().contains(mb.socket());
}
}