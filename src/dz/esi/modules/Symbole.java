package dz.esi.modules;

public abstract class Symbole {
	 
	   //Attributes 
	   private String nom;
	   
	   
	   //Empty Constructor
	   public Symbole() {}
	   
	   //Constructor with argument
	   public Symbole(String nom) {
		   this.nom=nom;
	   }
	   
	   
	   public abstract int recherche();
	   
	   
	   //Getters /Setters : 
	   public String getNom() {
	     	return nom;
	   }
	   
	   public void setNom(String nom) {
	     	this.nom = nom;
	   }
	   
	
	    
	    
}
