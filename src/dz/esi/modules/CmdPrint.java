package dz.esi.modules;

import dz.esi.exception.DivisionByZeroException;
import dz.esi.exception.InexistedVariableException;
import dz.esi.exception.MissingClosingParentheseException;
import dz.esi.exception.MissingClosingSquareBracketException;
import dz.esi.exception.MissingOpenningParentheseException;
import dz.esi.exception.MissingOpenningSquareBracketException;
import dz.esi.exception.MissingParentheseException;
import dz.esi.exception.ValueOutOfRangeException;
import dz.esi.exception.WrongExpressionException;

public class CmdPrint implements Cmd {
	
	//Empty Constructor
	public CmdPrint() {}
	

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		try {
		//Evaluating expression 
		Program.exp.evaluate();
		//Putting result in result in order to be printed
		Program.result="La valeur est: "+String.format("%.2f", Program.exp.res);
		
		}catch (MissingClosingParentheseException|MissingOpenningParentheseException|
				MissingClosingSquareBracketException| MissingOpenningSquareBracketException e) {
			// TODO Auto-generated catch block
			Program.result=e.getErrorText();

		}catch(InexistedVariableException e) {
			Program.result=e.getErrorText();

		} catch(DivisionByZeroException e) {
	    	Program.result=e.getErrorText();
	    }catch(ValueOutOfRangeException e) {
	    	Program.result=e.getErrorText();
	    }catch (MissingParentheseException e) {
		    //this will not be used at least with those functionalities 
		} catch (WrongExpressionException e) {
			// TODO Auto-generated catch block
			Program.result="Erreur : Expression erronée";
		} 
	}

}
