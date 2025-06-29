package it.unisa.control;

// Classe di utilità per tradurre da html
public class DecoderHtml {
	
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
	 * Metodo di utilità per far visualizzare l'immagine contenuta nel link di dropbox piuttosto che la pagina html
	 * @param 	url {@code String} - url da convertire
	 * @return	{@code String} urlDecoded dove il parametro "dl=0" è sostituito con "raw=1"
	 */
	public static String dropboxUrlDecoder(String url) {
		if(url == null || !url.contains("www.dropbox.com"))
			return null;
		else
			return url.replace("dl=0", "raw=1");
	}

	/**
	 * Metodo di utilità per far visualizzare la pagina html piuttosto che l'immagine contenuta nel link di dropbox
	 * @param 	url {@code String} - url da convertire
	 * @return	{@code String} urlDecoded dove il parametro "raw=1" è sostituito con "dl=0"
	 */
	public static String dropboxUrlEncoder(String url) {
		if(url == null || !url.contains("www.dropbox.com"))
			return null;
		else
			return url.replace("raw=1", "dl=0");
	}
}