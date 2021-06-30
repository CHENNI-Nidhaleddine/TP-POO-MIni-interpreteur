package dz.esi.exception;

public class ExistedVariableException extends VariableException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public  ExistedVariableException() {
		this.errorText="Erreur: nom de variable reservé ";
	}

}
