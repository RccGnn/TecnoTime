package it.unisa.model.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class CatalogoViewBean implements Serializable, BeanMarker{
	
	private static final long serialVersionUID = 1L;

	private ArrayList<ImmagineBean> immagini;
	private ArticoloBean articolo;
	private ProdottoFisicoBean pdFisico;
	private ProdottoDigitaleBean pdDigitale;
	private ServizioBean servizio;
	
}