package dz.esi.exception;

public class WrongExpressionException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String errorText;
    public  WrongExpressionException() {
    	errorText="Erreur : Expression Erronee";
    }
    public void setErrorText(String errorText) {
    	this.errorText=errorText;
    }
    
    public String getErrorText() {
    	return this.errorText;
    }
}
