package application;
	
import dz.esi.exception.InexistedCommandException;
import dz.esi.modules.Program;
import dz.esi.modules.Variable;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Main extends Application {
	
	public Stage stage=new Stage();
	public Scene scene;
	public TextArea cmd=new TextArea();
	TableColumn<Variable,String> colNom;//the variable column
	TableColumn<Variable,String> colValue;	//the values column
	public TableView<Variable> symbols=new TableView<Variable>();//Variable-Value table of interface
	

	public static void main(String[] args) {
        // TODO Auto-generated method stub
			launch(args);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage primaryStage) {
		
		//Prepare the table of symbols 
		colNom=new TableColumn<Variable,String>("Variable");
		colValue=new TableColumn<Variable,String>("Valeur");
		colNom.setCellValueFactory(cellData->cellData.getValue().NomProperty());
		colValue.setCellValueFactory(cellData->cellData.getValue().valueProperty());
		symbols.getColumns().addAll(colNom,colValue);
		
		//Adjusting size
		colNom.setPrefWidth(90);colNom.setMinWidth(90);colNom.setMaxWidth(90);
		colValue.setPrefWidth(90);colValue.setMinWidth(90);colValue.setMaxWidth(90);
	  
		//Adjusting Style
	    colNom.setStyle("-fx-background-color: black ;");
	    colValue.setStyle("-fx-background-color: black ;");
	    symbols.setStyle("-fx-background-color: black ;-fx-text-fill: #ffffff;");
	    
	    
		try {
			
		   HBox pane=new HBox();
		   
		   //Adjusting size
		   cmd.setPrefSize(525,520);
		   cmd.setMaxWidth(520);
		   cmd.setMinWidth(520);
		   cmd.setMinHeight(500);
		   cmd.setPrefHeight(500);
		   cmd.setMaxHeight(500);
		   
		   
		   //Setting info text
		   cmd.setText("Realisé par CHENNI Nidhaleddine-MAHMAHI Anis-G08"
		   		+ "\nEncadre par SADEG Souhila"
		   		+ "\n\nvos commandes. Tapez end pour terminer votre programme.\r\n"
		   		+ "Une commande doit être de la forme\r\n"
		   		+ "let <variable> = <expression>\r\n"
		   		+ "ou\r\n"
		   		+ "print <expression>\n\n> ");
		   
		   cmd.requestFocus();
           cmd.end();//putting cursor in the end of the  line 
           
           
		   pane.getChildren().addAll(cmd,symbols);
		  
		   scene=new Scene(pane,700,500);
		
          
		   cmd.textProperty().addListener(new ChangeListener<Object>() {
		    	  @Override
		    	  public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
		    	      cmd.setScrollTop(Double.POSITIVE_INFINITY); //this will scroll to the top
		    	  }});
		
		  
		   cmd.addEventHandler(KeyEvent.KEY_PRESSED, e->{
			   if(e.getCode()==KeyCode.ENTER) {
				   
				   String[] vars=cmd.getText().toString().split("\n");
				   if(!vars[vars.length-1].equals("> ")) {
				   Program.expression=vars[vars.length-1];
				   try {
					Program.launch();
					if (Program.result!="Fin de Programme") {
					cmd.setText(cmd.getText()+"\n"+Program.result+"\n\n> ");}
					else {
						cmd.setText(cmd.getText()+"\n"+Program.result);
						cmd.setEditable(false);
					}
					 symbols.setItems(loadData());
				    
				} catch (InexistedCommandException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				   }
				   e.consume();
	                cmd.requestFocus();
	                cmd.end();  
			   }
			   else if(e.getCode()==KeyCode.BACK_SPACE) {//this in order to prevent deleting when line contain just > 
				   String[] vars=cmd.getText().toString().split("\n");
				   String v=vars[vars.length-1];
				  
				   v=v+"|";
				
				   if(v.charAt(2)!='|') {
					   cmd.setText((String) cmd.getText().subSequence(0,cmd.getText().toString().length()-1));
					    
				   };
				   e.consume();
	                cmd.requestFocus();
	                cmd.end();
			   }
			   else if(e.getCode()==KeyCode.SPACE) {//this in order to prevent spacing before command
				   String[] vars=cmd.getText().toString().split("\n");
				   String v=vars[vars.length-1];
				  
				   v=v+"|";
				
				   if(v.charAt(2)=='|') {
					   cmd.setText((String) cmd.getText().subSequence(0,cmd.getText().toString().length()-1));
					    
				   };
				   e.consume();
	               cmd.requestFocus();
	               cmd.end();
			   }
			  
		   });
		   
		   scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        
		   stage.setOnCloseRequest(e->{
			   e.consume();
			   closeEvent();
		   });
		   stage.setResizable(false);
		   stage.setScene(scene);
		   stage.getIcons().add(new Image("/logoOOP.png"));
		   stage.setTitle("Mini Interpreteur");
		   stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	public ObservableList<Variable> loadData(){ //loading Program.symbols into the interface table
		
		ObservableList<Variable> data=  FXCollections.observableArrayList();
		for(int i=0;i<Program.symbols.size();i++) {
			if(!Program.symbols.get(i).getClass().getSimpleName().equals("Fonction")) {
			data.add(new Variable(Program.symbols.get(i).getNom(),((Variable) Program.symbols.get(i)).getValue()));
		    }
	    }
		return data;
	}
	
	
	
	
	//Event Handlers 
	private void closeEvent() {
		// TODO Auto-generated method stub
	
		Button btnYes=new Button("Oui");	Button btnNo=new Button("Non");
		Label text=new Label("Etes-vous sur de vouloir quitter?");
		
		Stage stage2=new Stage();
		
		btnYes.setMinWidth(60);btnNo.setMinWidth(60);
		
		btnYes.setOnAction(e->{
			stage2.close();
			stage.close();
		});
		
		btnNo.setOnAction(e->{
			stage2.close();	
		});
		
		HBox hline=new HBox(30,btnYes,btnNo);
		hline.setAlignment(Pos.CENTER);
		
		VBox vline=new VBox(30,text,hline);
		vline.setAlignment(Pos.CENTER);
		
		Scene scene2=new Scene(vline);
		
		text.setStyle(" -fx-font-family: Consolas; -fx-font-size: 14;-fx-text-fill: #000;");
		btnYes.setStyle("-fx-font-family: Consolas;-fx-font-size: 14; -fx-text-fill: #000;");
		btnNo.setStyle("-fx-font-family: Consolas;-fx-font-size: 14;-fx-text-fill: #000;");
		
		stage2.initModality(Modality.APPLICATION_MODAL);
		stage2.setTitle("Confirmation");
		stage2.setScene(scene2);
		stage2.setHeight(150);
		stage2.setWidth(300);
		stage2.showAndWait();
	}
}
