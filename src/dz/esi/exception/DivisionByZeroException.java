package dz.esi.exception;

public class DivisionByZeroException extends RuntimeException{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private String errorText;
   public DivisionByZeroException() {
	   this.setErrorText("Erreur: Division By Zero");
   }
public String getErrorText() {
	return errorText;
}
public void setErrorText(String errorText) {
	this.errorText = errorText;
}
}
