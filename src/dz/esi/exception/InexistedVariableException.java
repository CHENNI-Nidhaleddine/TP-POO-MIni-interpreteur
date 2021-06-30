package dz.esi.exception;

public class InexistedVariableException extends VariableException{
    public InexistedVariableException() {
    	this.errorText="Erreur: Variable non declare";
    }
}
