package dz.esi.exception;

public class ExistedVariableAsFunctionException extends ExistedVariableException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public  ExistedVariableAsFunctionException() {
		this.errorText="Erreur: nom de variable est un nom de fontion standard";
	}
}
