package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import bean.ArtistiEAscolti;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class SampleController {
	
	private Model m = new Model();
	
	public void setModel(Model m){
		this.m = m;
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combo;  //nome mesi

    @FXML
    private Button btnElenco;

    @FXML
    private Button btnDistMax;

    @FXML
    private TextArea txtResult;

    @FXML
    void doDistMax(ActionEvent event) {
    	String mese = combo.getValue();
    	int numeroMese = m.getConvertoInNumero(mese);  
    	m.buildGraph(numeroMese);

    }

    @FXML
    void doElenco(ActionEvent event) {
    	txtResult.clear();
    	if(combo.getValue()==null){
    		txtResult.appendText("Seleziona un mese!\n");
    		return;
    	}
    	String mese = combo.getValue();
    	int numeroMese = m.getConvertoInNumero(mese);   //funziona
    	
    	List<ArtistiEAscolti> artisti = m.getArtisti(numeroMese);          //errore
    	txtResult.appendText(artisti.toString());
    	
    	btnDistMax.setDisable(false);
    
    }

    @FXML
    void initialize() {
        assert combo != null : "fx:id=\"combo\" was not injected: check your FXML file 'Sample.fxml'.";
        assert btnElenco != null : "fx:id=\"btnElenco\" was not injected: check your FXML file 'Sample.fxml'.";
        assert btnDistMax != null : "fx:id=\"btnDistMax\" was not injected: check your FXML file 'Sample.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Sample.fxml'.";

        btnDistMax.setDisable(true);
        combo.getItems().addAll(m.getNomiMesi());
        
    }
}
