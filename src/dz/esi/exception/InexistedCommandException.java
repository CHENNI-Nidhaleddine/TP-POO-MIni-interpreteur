package dz.esi.exception;

public class InexistedCommandException extends WrongExpressionException{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public  InexistedCommandException() {
	   super.errorText="Erreur: Commande n'existe pas";
   }
}
