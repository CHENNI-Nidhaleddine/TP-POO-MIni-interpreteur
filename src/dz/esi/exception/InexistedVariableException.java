package dz.esi.exception;

public class InexistedVariableException extends VariableException{
    public InexistedVariableException(String vars) {
    	this.errorText="Erreur: Variable '"+vars+"' non declaree";
    }
}
