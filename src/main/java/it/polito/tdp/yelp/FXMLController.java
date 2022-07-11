/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.yelp;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.yelp.model.Adiacenza;
import it.polito.tdp.yelp.model.Model;
import it.polito.tdp.yelp.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnUtenteSimile"
    private Button btnUtenteSimile; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtX2"
    private TextField txtX2; // Value injected by FXMLLoader

    @FXML // fx:id="cmbAnno"
    private ComboBox<Integer> cmbAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtN"
    private TextField txtN; // Value injected by FXMLLoader

    @FXML // fx:id="cmbUtente"
    private ComboBox<User> cmbUtente; // Value injected by FXMLLoader

    @FXML // fx:id="txtX1"
    private TextField txtX1; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	
    	txtResult.clear();
    	
    	try {
    		int n = Integer.parseInt(this.txtN.getText());
    		int anno = this.cmbAnno.getValue();
    		String msg = this.model.creag(n,anno);
    		txtResult.appendText(msg);
    		
    	}catch(NumberFormatException e) {
    		txtResult.appendText("Errore inserire un numero di recensioni corretto");
    	}
    	
    	
    
    	this.cmbUtente.getItems().clear();
    	this.cmbUtente.getItems().addAll(this.model.getUtenti());
    	
    }

    @FXML
    void doUtenteSimile(ActionEvent event) {
    	
    	txtResult.clear();
    	
    	User u = this.cmbUtente.getValue();
    	
    	if(u == null) {
    		txtResult.appendText("errore, devi inserire un utente");
    	}
    	
    	txtResult.appendText("Utenti più simili a "+u+": \n");
    	List<Adiacenza> lista = this.model.utentiPiuSimili(u);
    	txtResult.appendText(lista+" ");
    	
    }
    
    @FXML
    void doSimula(ActionEvent event) {
    	txtResult.clear();
    	try {
    		int x1 = Integer.parseInt(this.txtX1.getText());
    		int x2 = Integer.parseInt(this.txtX2.getText());
    		
    		if(x2>this.model.getUtenti().size()) {
    			txtResult.appendText("errore, inserire un numero corretto di utenti");
    		}
    		
    		if(x1>x2) {
    			txtResult.appendText("errore, inserire un valore minore");
    		}
    		
    		
    	}catch(NumberFormatException e) {
    		txtResult.appendText("errore, inserire un valore corretto");
    	}

    }
    

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnUtenteSimile != null : "fx:id=\"btnUtenteSimile\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtX2 != null : "fx:id=\"txtX2\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbAnno != null : "fx:id=\"cmbAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtN != null : "fx:id=\"txtN\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbUtente != null : "fx:id=\"cmbUtente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtX1 != null : "fx:id=\"txtX1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	
    	for(int i = 2005;i<=2013;i++) {
    		this.cmbAnno.getItems().add(i);
    	}
    }
}
