package fi.utu.tech.gui.javafx.assignment3;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import fi.utu.tech.gui.javafx.WordIterator;
import fi.utu.tech.gui.javafx.assignment1.HashCrack;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class CrackerController {

    @FXML
    private ListView<String> reversedList;

    @FXML
    private TextField hashInputField;

    @FXML
    private Button crackBtn;

    @FXML
    void crackBtnAction(ActionEvent event) {
    	crackBtn.setDisable(true); // ei voi aktivoida crackBtn
    	new Thread( // uusi Thread, joka ajetaan alla spesifioiduilla määritteillä
    	new Runnable(){ // implementoidaan Runnable
    		@Override
    		public void run() {
    			final var inputHash = hashInputField.getText();
    				try {
            /*
             * Cracking the given input hash with input values that are maximum of 4 characters long,
             * using the characters in default dictionary (a-ö, A-Ö, 0-9, space, line feed, carriage return)
             * and specifying that the algorithm is md5 and the values should be encoded utf-8 before hashing
             */
            String result = new HashCrack(inputHash, 4, WordIterator.DEFAULT_DICT, "md5", "utf-8").bruteForce();
            Platform.runLater(() -> {reversedList.getItems().add(result); // Säieturvallinen tapa tehdä: Platform.runlater joka sitten ajaa: lisätään käyttöliittymän reversed listaan result eli hashcrackin käännös
            crackBtn.setDisable(false);}); // asetetaan crackBtn taas käytettäväksi listalisäyksen jälkeen
            
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    		}
    		
    	}).start(); // ajetaan määritelty Thread

    }}