package dz.esi.exception;

public class MissingOpenningSquareBracketException extends MissingParentheseException {
	   public MissingOpenningSquareBracketException() {
	    	 super.errorText="Erreur: crochet ouvrant manquant";
	     }
}
