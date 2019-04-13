
package controller;

import Main.HomeMain;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;


public class LoadingController implements Initializable {
    @FXML
    private AnchorPane splashAnchorPane;
   
        HomeMain su;
        Stage stage;
        
      public  void Main(HomeMain su, Stage stage){
        this.stage=stage;
        this.su=su;
        
        }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       new loading().start();
    }
    
    class loading extends Thread{

        @Override
        public void run() {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    try {
                        Thread.sleep(1500);
                        FadeTransition fadeout=new FadeTransition(Duration.seconds(4), splashAnchorPane);
                        fadeout.setFromValue(1);
                        fadeout.setToValue(0);
                        fadeout.setCycleCount(1);
                        fadeout.play();
                        
                        fadeout.setOnFinished(e ->{
                         su.loadingWindowClose();
                         su.signinWindow();

                        });
                       
                    } 
                    catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
    
    }
    
}
