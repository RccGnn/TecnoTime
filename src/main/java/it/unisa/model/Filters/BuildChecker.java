package it.unisa.model.Filters;

public class BuildChecker {
	
	private static Compatible<Case, SchedaMadre> caseRule = new CaseMotherBoardRule();
	private static Compatible<Processore,SchedaMadre> cpuRule = new ProcessorMotherBoardRule();
	private static Compatible<SchedaMadre, Ram> ramRule = new MotherBoardRamRule();
	
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
		if(c!=null && mb!=null && cpu!=null && ram!=null && gpu!=null) {
			res= caseRule.isCompatible(c, mb) && cpuRule.isCompatible(cpu, mb) && ramRule.isCompatible(mb, ram) ;
			if(psu.watt()>=minimumWatt(mb, gpu, cpu))
				psuvalidator=true;
			return res && psuvalidator;
		}
		else return false;
	}
	
	

}










