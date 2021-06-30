package dz.esi.exception;

public class ExistedVariableAsCommandException extends ExistedVariableException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public  ExistedVariableAsCommandException() {
		this.errorText="Erreur: nom de variable est un nom de commande";
	}
}

