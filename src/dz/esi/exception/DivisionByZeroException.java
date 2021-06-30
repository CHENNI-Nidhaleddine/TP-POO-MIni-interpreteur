package dz.esi.exception;

public class DivisionByZeroException extends ArethmeticException{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


   public DivisionByZeroException() {
	   this.setErrorText("Erreur: Division By Zero");
   }

}
