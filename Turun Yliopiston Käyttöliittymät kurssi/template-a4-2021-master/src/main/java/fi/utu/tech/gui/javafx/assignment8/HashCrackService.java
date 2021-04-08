package fi.utu.tech.gui.javafx.assignment8;

import fi.utu.tech.gui.javafx.WordIterator;
import javafx.beans.binding.Bindings;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


public class HashCrackService extends Service<String> {

    private ListView<String> reversedList;
    private TextField hashInputField;
    private Button crackBtn;
    private ProgressBar crackingProgressBar;
    private Label statusLabel;
    private CrackerController cc;
    private String returnable;
    HashCrackTask cracker;
    // Implementoitu hashcrackservice, joka on luotu täyttämään servicen vaatimukset
    
    public HashCrackService(ListView<String> rl, TextField hif, Button btn, ProgressBar cpb, Label sl, CrackerController cc){
        reversedList = rl;
        hashInputField = hif;
        crackBtn = btn;
        crackingProgressBar = cpb;
        crackingProgressBar.progressProperty().bind(this.progressProperty());
        statusLabel = sl;
        this.cc= cc;

        crackBtn.textProperty().bind(Bindings.when(this.runningProperty()).then("Cancel").otherwise("Crack"));


        setOnSucceeded(s -> {
            if(!this.getValue().equals("")) {
                reversedList.getItems().add(this.getValue());
            }
            crackBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    cc.crackBtnAction(actionEvent);
                }
                
            });
            
        });

        setOnCancelled(cancelled -> {
            cracker.cancel();
            crackBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    cc.crackBtnAction(actionEvent);
                }
                
            });
            
        });
        
    }

    @Override
    protected Task<String> createTask() {
        cracker = new HashCrackTask(hashInputField.getText(), 4, WordIterator.DEFAULT_DICT, "md5", "utf-8");

        crackingProgressBar.progressProperty().bind(cracker.progressProperty());
        statusLabel.textProperty().bind(cracker.messageProperty());

        crackBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                HashCrackService.super.cancel();
            }
            
        });
        

        return new Task<String>() {
            protected String call(){
                try {
                    return cracker.bruteForce();
                } catch (Exception e){

                }
                return null;
            }
        };
    }
}
