package dz.esi.exception;

public class ValueOutOfRangeException extends ArethmeticException {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValueOutOfRangeException() {
		this.setErrorText("Erreur: le parametre de la fonction est hors intervale");
	 }
}
