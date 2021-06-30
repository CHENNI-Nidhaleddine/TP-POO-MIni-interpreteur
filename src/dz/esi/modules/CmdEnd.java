package dz.esi.modules;

public class CmdEnd implements Cmd {
    
	//Empty Constructor
	public CmdEnd() {}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		   Program.result="Fin de Programme";//this will be printed in the interpreter 
 		   Program.symbols.clear();// clearing the table of symbols from variables
	}

}
