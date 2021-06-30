package dz.esi.exception;

public class MissingOpenningParentheseException extends MissingParentheseException {
   public MissingOpenningParentheseException() {
	  super.errorText="Erreur: Parenthese ouvrante manquante";
   }
}
