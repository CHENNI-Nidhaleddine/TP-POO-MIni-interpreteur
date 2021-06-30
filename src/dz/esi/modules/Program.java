package dz.esi.modules;

import java.util.ArrayList;

import dz.esi.exception.InexistedCommandException;
import dz.esi.exception.WrongExpressionException;

public class Program {
	
	//Attribute 
    public static ArrayList<Symbole>  symbols=new ArrayList<Symbole>();
    public static Expression exp;    
    public static String result;
    public static String expression;
    
    
    public static void launch() throws InexistedCommandException {

 	     String cmd=new String();//Saving cmd 
 	   
 	  
 	     //Adding standards Function to symbols:
 	     AddStandardsFunctions();

 		 cmd=Program.expression.split(" ")[1];
 		   
 		 try {
 		   if(cmd.equals(Commandes.let.toString())) {
 			  cmd="> "+cmd;
 			  Program.exp=new Expression(expression.replaceFirst(cmd,""));
 			  Cmd let=new CmdLet();
 			  let.execute();
 	         }
 		   else if (cmd.equals(Commandes.print.toString())) {
 			  cmd="> "+cmd;
 			  Program.exp=new Expression(expression.replaceFirst(cmd,""));
 			  Cmd print=new CmdPrint();
 			  print.execute();
 		   }
 		   else if(cmd.equals(Commandes.end.toString())) {
 			   Cmd end=new CmdEnd();
 			   end.execute();
 		   }
 		   else {
 			   throw new InexistedCommandException(); 
 		   }
 	       }catch(InexistedCommandException e) {
 		   Program.result=e.getErrorText();
 		   System.out.println("Erreur: Commande n'exite pas ");
 	       }catch(WrongExpressionException e) {
 		   Program.result=e.getErrorText();
 	       }
 		 }
       
    
        public static void AddStandardsFunctions() {
     	   Fonction sin=new Fonction("sin",x -> Math.sin(Math.toRadians(x)));
     	   Fonction cos=new Fonction("cos",x -> Math.cos(Math.toRadians(x)));
     	   Fonction tan=new Fonction("tan",x -> Math.tan(Math.toRadians(x)));
     	   Fonction log=new Fonction("log",x -> Math.log(x));
     	   Fonction exp=new Fonction("exp",x -> Math.exp(x));
     	   Fonction sqrt=new Fonction("sqrt",x -> Math.sqrt(x));
     	   Fonction abs=new Fonction("abs",x -> Math.abs(x));
         
     	   Program.symbols.add(sin);
     	   Program.symbols.add(cos);
     	   Program.symbols.add(tan);
     	   Program.symbols.add(log);
     	   Program.symbols.add(exp);
     	   Program.symbols.add(sqrt);
     	   Program.symbols.add(abs);   	  
      
        }

}