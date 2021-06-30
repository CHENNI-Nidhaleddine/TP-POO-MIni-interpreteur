package dz.esi.exception;

public class ArethmeticException extends RuntimeException {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String errorText;

    public String getErrorText() {
	    return errorText;
    }

   public void setErrorText(String errorText) {
	    this.errorText = errorText;
   }
  
}
