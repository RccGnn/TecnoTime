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
}