package fi.utu.tech.gui.javafx.assignment5;

import fi.utu.tech.gui.javafx.WordIterator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.event.EventHandler;
import javafx.concurrent.WorkerStateEvent;

public class CrackerController {

    @FXML
    private ListView<String> reversedList;

    @FXML
    private TextField hashInputField;

    @FXML
    private Button crackBtn;
    
    @FXML
    private Label statusLabel;

    @FXML
    void crackBtnAction(ActionEvent event) {
        HashCrackTask crackerTask = new HashCrackTask(hashInputField.getText(), 4, WordIterator.DEFAULT_DICT, "md5", "utf-8");
        crackBtn.setDisable(true);
        statusLabel.textProperty().bind(crackerTask.messageProperty());
        try {
        	new Thread(crackerTask).start();
        	
        }
        catch(Exception e) {
        	
        }
        crackerTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
        	@Override
        	public void handle(WorkerStateEvent workerStateEvent) {
        		if(!crackerTask.getValue().equals("")) {
        			reversedList.getItems().add(crackerTask.getValue());
        		}
        		crackBtn.setDisable(false);
        	}
        });
    }

}