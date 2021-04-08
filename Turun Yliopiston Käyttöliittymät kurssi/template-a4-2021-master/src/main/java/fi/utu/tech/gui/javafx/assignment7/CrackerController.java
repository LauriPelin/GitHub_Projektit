package fi.utu.tech.gui.javafx.assignment7;

import fi.utu.tech.gui.javafx.WordIterator;
import javafx.beans.binding.Bindings;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class CrackerController {
    HashCrackTask crackerTask;

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
    void crackBtnAction(ActionEvent event) {
        HashCrackTask crackerTask = new HashCrackTask(hashInputField.getText(), 4, WordIterator.DEFAULT_DICT, "md5", "utf-8");
        statusLabel.textProperty().bind(crackerTask.messageProperty());
        crackingProgressBar.progressProperty().bind(crackerTask.progressProperty());
        crackBtn.textProperty().bind(Bindings.when(crackerTask.runningProperty()).then("Cancel").otherwise("Crack"));
        crackBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                crackerTask.cancel();
            }
        });
        
        try {
            new Thread(crackerTask).start();
        } catch (Exception e) {
        }
        
        crackerTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent woe) {
                if(!crackerTask.getValue().equals("")) {
                    reversedList.getItems().add(crackerTask.getValue());
                }
                crackBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        crackBtnAction(actionEvent);
                    }
                });
                
            }
        });
        
        crackerTask.setOnCancelled(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                crackBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        crackBtnAction(actionEvent);
                    }
                });
                
            }
        });
        
    }

    
}
