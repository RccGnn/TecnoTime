package it.unisa.model.Filters;

public class MotherBoardRamRule implements Compatible <SchedaMadre,Ram> {
	@Override
	public boolean isCompatible(SchedaMadre mb, Ram ram) {
		return mb.tipoRamSupportata().equals(ram.SupportoRam());
	}

}
