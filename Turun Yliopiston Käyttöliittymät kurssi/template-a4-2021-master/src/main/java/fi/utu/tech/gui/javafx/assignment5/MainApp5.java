package fi.utu.tech.gui.javafx.assignment5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp5 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Getting the reference to "class object of this class"
        var resourceRoot = getClass();
        // The fxml filename that is in resources folder
        var form = "Cracker.fxml";

        // Give the FXML resource to the FXMLLoader
        var loader = new FXMLLoader(resourceRoot.getResource(form));

        // Load and parse the FXML into an Java object (Parent)
        Parent root = loader.load();
        //loader.getController();

        // This is just the usual: Setting scene, showing stage
        stage.setTitle("MD5 Cracker");
        var scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
