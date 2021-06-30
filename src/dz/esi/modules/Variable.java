package dz.esi.modules;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Variable  extends Symbole{
	   //Attribute
	   private Double value;
	   
	   //Constructor without arguments
	   public Variable() {this.value=0.0;}
	   
	   //Constructor with arguments
	   public Variable(String nom,Double value) {
		   super(nom);
		   this.setValue(value); 
	   }
	   
	   //Adding Variable to symbols table:
	   public void ajouter() {
		  Program.symbols.add(this);
	   }
	   
	   @Override
	   public int recherche() {
		// TODO Auto-generated method stub
		  
		   for(int i=0;i<Program.symbols.size();i++) {
			   if(Program.symbols.get(i).getNom().equals(this.getNom())) {
				  
				   if(Program.symbols.get(i).getClass().getSimpleName().equals("Fonction")) {
					return -2;//Existed function with that name
				   }
				   return i; //Index of variable object in symbols table
			   }
		   }
		   return -1; //Variable does not exist
	  }
	   
	  //Getters / Setters:
	  public Double getValue() {
		   return value;
	  }
	  public void setValue(Double value) {
	    	this.value = value;
	  }
	    
	  //Property methods for Table Factory
	  public StringProperty NomProperty() {
	    	return new SimpleStringProperty(super.getNom());
	    }
	   
	  public StringProperty valueProperty() {
	    	return new SimpleStringProperty(Double.toString(this.value));
	   }
}
