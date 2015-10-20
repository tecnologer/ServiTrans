/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servitrans;

import com.db4o.ObjectContainer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Tecnologer
 */
public class ServiTrans extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
       login Login=new login();
       
       Login.setVisible(true);
       Login.setLocationRelativeTo(null);
    }
}
