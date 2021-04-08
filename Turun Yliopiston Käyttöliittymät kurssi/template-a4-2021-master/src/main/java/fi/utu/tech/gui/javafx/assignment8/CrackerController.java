package fi.utu.tech.gui.javafx.assignment8;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class CrackerController {
	
	private HashCrackService service;
    
    public void initialize() {
        service = new HashCrackService(reversedList, hashInputField, crackBtn, crackingProgressBar, statusLabel, this);
    }

    @FXML
    private ListView<String> reversedList;

    @FXML
    private TextField hashInputField;

    @FXML
    private Button crackBtn;

    @FXML
    private ProgressBar crackingProgressBar;

    @FXML
    private Label statusLabel;

    @FXML
    void crackBtnAction(ActionEvent event) { // ei ihmeempää
    	service.restart();
    }

}
