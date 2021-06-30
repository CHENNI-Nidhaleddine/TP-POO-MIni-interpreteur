package dz.esi.exception;

public class MissingClosingParentheseException extends MissingParentheseException {
	   public MissingClosingParentheseException() {
			  super.errorText="Erreur: Parenthese fermante manquante";
		   }
}