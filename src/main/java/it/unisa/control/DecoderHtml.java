package it.unisa.control;

// Classe di utilità per tradurre da html
public class DecoderHtml {
	public static String encodeHtml(String input) {
		if(input==null) return null;
		return  input.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;").replace("'","&#39").replace(" ","&nbsp;");
	} 

}