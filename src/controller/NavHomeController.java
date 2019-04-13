
package controller;

import Main.HomeMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;


public class NavHomeController implements Initializable {

    @FXML
    private Label NameLB;

    @FXML
    private Label usernameLB;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usernameLB.setText(SigninController.usernameforHome);
        NameLB.setText(SigninController.nameforHome);
    }

    @FXML
    void about(ActionEvent event) {
        HomeMain.AboutWindow();
    }

    @FXML
    void exit(ActionEvent event) {

        HomeMain.mainWindowClose();
    }
}
