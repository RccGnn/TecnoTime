package it.unisa.control;

public class DecoderHtml {
	public static String encodeHtml(String input) {
		if(input==null) return null;
		return  input.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;").replace("'","&#39").replace(" ","&nbsp;");
	} 

}