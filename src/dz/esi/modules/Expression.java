package dz.esi.modules;

import java.util.Stack;

import dz.esi.exception.DivisionByZeroException;
import dz.esi.exception.InexistedVariableException;
import dz.esi.exception.MissingClosingParentheseException;
import dz.esi.exception.MissingClosingSquareBracketException;
import dz.esi.exception.MissingOpenningParentheseException;
import dz.esi.exception.MissingOpenningSquareBracketException;
import dz.esi.exception.MissingParentheseException;
import dz.esi.exception.WrongExpressionException;

public class Expression {
	
	   //Attributes: 
       public String exp;// this will contain the text of the expression
       public  double res; //this will contain the result of expression
    
       //Constructor without Arguments 
       public Expression() {}
    
       //Constructor with argument
	   public Expression(String exp) throws WrongExpressionException {
		   this.exp=exp;
		   if(exp=="") throw new WrongExpressionException();
	   }

	
	   //Calculating the expression result
       public void evaluate() throws WrongExpressionException  {	
       	   evalParentheses(this.exp);
		   replaceVariables();
		   this.res=evalSyntax(this.exp);
		   if(Double.isInfinite(res)) {throw new DivisionByZeroException();}
		}
	    	
       
       //Replacing Variables by there values figure in the symbols table
	   public  void replaceVariables() throws InexistedVariableException {
		   String[] vars;
		   Variable v;
		   int result;
		   vars=this.exp.split("[\\(\\)\\[\\]\\*\\+\\-\\/\\^\\?]");
		   for(int i=0;i<vars.length;i++) {
				vars[i]=vars[i].replace(" ","");
				 v= new Variable(vars[i],0.0);
				 result=v.recherche();
				 
				 //if variable not existing
				 if(result==-1) {
					 System.out.print(vars[i]);
					 try{Double.parseDouble(vars[i]); //check if it is a number 
					 }catch(Exception e) {
						 if(vars[i]!="") throw new  InexistedVariableException();
					 }
				 }
				 
				 else if(result>-1) {//Replacing variable by its value
					 this.exp=this.exp.replace(vars[i], Double.toString((((Variable) Program.symbols.get(result)).getValue())));
				 }
			}
		
		}
	   
	   
	    //Checking if Parentheses are equitable in the expression
	    public void evalParentheses( String str) throws MissingParentheseException {
			Stack <Character> stk = new Stack <Character>();//to Evaluate ()
			Stack <Character> stk2 = new Stack <Character>();//to Evaluate []
			
			int i=0;//Loop index
			
			while(i<str.length()) {
				
				if(str.charAt(i)=='(') {
					stk.push(str.charAt(i));
					i++;
					continue;
				}
				else if(str.charAt(i)==')') {
					if(stk.isEmpty()) {throw new MissingOpenningParentheseException();}
					else stk.pop();
				}
				
				
				if(str.charAt(i)=='[') {
					stk2.push(str.charAt(i));
					i++;
					continue;
				}
				else if(str.charAt(i)==']') {
					if(stk2.isEmpty())  {throw new MissingOpenningSquareBracketException();}
					else stk2.pop();
				}
				
				i++;
			}
			
			if(!stk.isEmpty())  {throw new MissingClosingParentheseException();};
			if(!stk2.isEmpty())  {throw new MissingClosingSquareBracketException(); };
		}
        
	    
	    //Calculates and return the result of expression
		public  double evalSyntax(String str) throws WrongExpressionException  {
			
			//Anonym Class for evaluation
			return new Object() {
				
		        int pos = -1, //Saving position of actual letter in expression
		        	ch;//Saving the actual letter
		        
		        void nextChar() {
		            ch = (++pos < str.length()) ? str.charAt(pos) : -1;
		        }

		        boolean nextIfExist(int charToExist)  {
		            while (ch == ' ') nextChar();
		            if (ch == charToExist) {	
		                nextChar();
		                return true;
		            }
		            return false;
		        }

		        double parse() throws WrongExpressionException {
		            nextChar();
		            double x = parseExpression();
		            if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
		            return x;
		        }

		        // Command line (BNF):
		        // expression = term | expression `+` term | expression `-` term
		        // term = factor | term `*` factor | term `/` factor
		        // factor = `+` factor | `-` factor | `(` expression `)`
		        //        | number | function factor | factor `^` factor

		        double parseExpression() throws WrongExpressionException {
		            double x = parseTerm();
		            
		            for (;;) {
		                if      (nextIfExist('+')) x += parseTerm(); // addition
		                else if (nextIfExist('-')) x -= parseTerm(); // subtraction
		                else return x;
		            }
		        }

		        double parseTerm() throws WrongExpressionException{
		            double x = parseFactor();
		            for (;;) {
		                if      (nextIfExist('*')) x *= parseFactor(); // multiplication
		                else if (nextIfExist('/')) x /= parseFactor(); // division
		                else return x;
		            }
		        }

		        double parseFactor() throws NumberFormatException, WrongExpressionException{
		        	
		            if (nextIfExist('+')) return parseFactor(); // unary plus
		            if (nextIfExist('-')) return -parseFactor(); // unary minus
	               
		            double x;
		            int startPos = this.pos;
		            if (nextIfExist('(')) { // parenthese
		                x = parseExpression();
		                nextIfExist(')');
		            }else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
		                while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
		                x = Double.parseDouble(str.substring(startPos, this.pos));
		            } else if ((ch >= 'a' && ch <= 'z')) { // functions
		                while ((ch >= 'a' && ch <= 'z') ||((ch >= '0' && ch <= '9'))) nextChar();
		                String func = str.substring(startPos, this.pos);
		            
		                x = parseFactor();
		             
		                Fonction f=new Fonction(func,null);
		                int idx=f.recherche();
		                f=(Fonction) Program.symbols.get(idx);
		                x=f.getCorp().apply(x);
		            } else {
		            	
		            	throw new WrongExpressionException();
    	            	
		 		        
		            }

		            if (nextIfExist('^')) x = Math.pow(x, parseFactor()); // exponentiation
	         
		            return x;
		        }
		    }.parse();
		  
		  
			}

}