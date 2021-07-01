package dz.esi.modules;

import java.util.function.Function;

public class Fonction extends Symbole {
	   //Attribute
	   private Function<Double,Double> corp;
	   
	   //Constructor without arguments
	   public Fonction() {}
	   
	   //Constructor with arguments 
	   public Fonction(String nom,Function<Double,Double> corp) {
	 	  super(nom);
	 	  this.corp=corp; 
	    }

	    @Override
	    public int recherche() {
	    //this function defers for the one of Variable object,because it didn't verify the class name 
	    //of the elements ,in this point we are sure that this element is a function because we didn't
	    //allow insertion of variable with name of function from the first time
	    
	 	// TODO Auto-generated method stub
	 	   for(int i=0;i<Program.symbols.size();i++) {
	 		   if(Program.symbols.get(i).getNom().equals(this.getNom())) {
	 			   return i;//index of function in symbols
	 		   }
	 	   }
	 	  
	 	   return -1; //doesn't exist
	    }
	    
	    //Getters / Setters:
	    public Function<Double,Double> getCorp() {
	 	  return corp;
	    }
	    public void setCorp(Function<Double,Double> corps) {
	 	  this.corp = corps;
	    }
	    
	    
}
