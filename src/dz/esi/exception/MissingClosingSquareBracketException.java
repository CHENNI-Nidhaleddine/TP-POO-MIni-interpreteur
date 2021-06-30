package dz.esi.exception;

public class MissingClosingSquareBracketException extends MissingParentheseException {
     public MissingClosingSquareBracketException() {
    	 super.errorText="Erreur: crochet fermant manquant";
     }
}
