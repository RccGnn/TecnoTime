package it.unisa.model.Filters;

public class ProcessorMotherBoardRule implements Compatible<Processore, SchedaMadre> {
	
	public boolean isCompatible(Processore cpu, SchedaMadre mb) {
		return cpu.socket().equals(mb.socket());
	}

}
