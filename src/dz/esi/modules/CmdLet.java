package dz.esi.modules;

import java.util.Stack;

import dz.esi.exception.DivisionByZeroException;
import dz.esi.exception.ExistedVariableAsCommandException;
import dz.esi.exception.ExistedVariableAsFunctionException;
import dz.esi.exception.ValueOutOfRangeException;
import dz.esi.exception.WrongExpressionException;

public class CmdLet implements Cmd {
	
	//Attribute
	private static Stack <String> stk = new Stack <String>();//this will contain the variables list
	
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		try {
		    process();
			Program.result="ok";
		} catch (WrongExpressionException e) {
			// TODO Auto-generated catch block
			Program.result=e.getErrorText();
	    }catch(DivisionByZeroException e) {
	    	Program.result=e.getErrorText();
	    }catch(ValueOutOfRangeException e) {
	    	Program.result=e.getErrorText();
	    }
		
	}
	
	//Separating variable and there expressions then add them to symbols after affectation 
	public void process() throws WrongExpressionException {
			 String[] vars;
			 String[] affects;
			 affects=Program.exp.exp.split("[\\,]");//Separating by , ,so to get variable=expression 
			 
			 for(int j=0;j<affects.length;j++) {
			 vars=affects[j].split("[\\=]");//separates by =,so to get variable or its expression
			 stk.clear();
			 for(int i=0;i<vars.length;i++) {
				 vars[i]=vars[i].trim();
				 if(stk.isEmpty()) {isValidVariable(vars[i]);stk.push(vars[i]); }
				 else {
					 if(affects[j].contains("="+vars[i]+"=")) throw new WrongExpressionException();
					 Expression exp=new Expression(vars[i]);
					 
					 exp.evaluate();
					 Variable v=new Variable(stk.pop(),exp.res);
					 stk.clear();
//					 if(Double.isInfinite(exp.res)) {throw new WrongExpressionException();}
					 int k=v.recherche();
					 if(k==-1) v.ajouter();
					else {
						Program.symbols.set(k, v);
					}
				 }
			 }
			 
			 //Initializing variables declared without expression to 0
			 while(!stk.isEmpty()) {
				 Variable v=new Variable(stk.pop(),0.0);
				 int k=v.recherche();
					if(k==-1) v.ajouter();
					else {
						Program.symbols.set(k, v);
					}
				
			 }
			 }
		 }
	
	     //Validating variable
		 public void isValidVariable(String var) throws WrongExpressionException {
			 String charNonAuto="^*/%@!?|\\.)}[({]+- ";
			 
			   
			  
			    Variable v=new Variable(var,0.0);
				int l=v.recherche();
				if(l==-2) {//Existed as Functions
					throw new ExistedVariableAsFunctionException();
				}else if(var.equals(Commandes.let.toString()) || var.equals(Commandes.end.toString()) || 
						var.equals(Commandes.print.toString())) { //Existed as command
				 throw new ExistedVariableAsCommandException();		
		    	}
				if(var.charAt(0)>='0' && var.charAt(0)<='9'){ //if starts with number
					throw new WrongExpressionException();
				}
			
				for(int i=0;i<charNonAuto.length();i++) {//if variable contains Non authorized characters 
					if(var.contains(Character.toString(charNonAuto.charAt(i)))) {
					throw new WrongExpressionException();}
				}
				
		 }
		 

}
