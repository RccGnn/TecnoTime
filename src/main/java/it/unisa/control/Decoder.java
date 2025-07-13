package it.unisa.control;

// Classe di utilità per tradurre da html
public class Decoder {
	
	/**
	 * Metodo di utilità usato per convertire in formato HTML alcuni caratteri speciali.
	 * @param input - {@code String} testo da convertire
	 * @return {@code null} se {@code input} è null, stringa formattata altrimenti
	 */
	public static String encodeHtml(String input) {
		if(input==null) return null;
		return  input.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;").replace("'","&#39").replace(" ","&nbsp;");
	}
	
	/**
	 * Metodo che permette di convertire l'url dropBox di un'immagine in un formato "renderizzabile" da html
	 * (Funzione inversa di EncoderDropBox)
	 * @param url {@code String} - url da convertire
	 * @return newUrl {@code String} - url convertito
	 */
	public static String DecoderDropboxUrl(String url) {
		
		String newUrl;
		if(url == null || url.trim().equals(""))
			newUrl = url;
		else
			newUrl = url.replace("&dl=0", "&raw=1");
		
		return newUrl;
	}

	/**
	 * Metodo che permette di convertire l'url dropBox di un'immagine in un formato non "renderizzabile" da html
	 * (Funzione inversa di DecoderDropBox)
	 * @param url {@code String} - url da convertire
	 * @return newUrl {@code String} - url convertito
	 */
	public static String EncoderDropboxUrl(String url) {
		
		String newUrl;
		if(url == null || url.trim().equals(""))
			newUrl = url;
		else
			newUrl = url.replace("&raw=1", "&dl=0");
		
		return newUrl;
	}

}