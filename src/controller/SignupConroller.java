package controller;

import Main.Client;
import Main.HomeMain;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ResourceBundle;



public class SignupConroller implements Initializable {

    @FXML
    private JFXTextField firstName;

    @FXML
    private JFXTextField lastName;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXTextField userName;


    static HomeMain s;

    public void main(HomeMain s) {
        this.s = s;

    }

    private static String signupdata;

    public static void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error ");
        alert.setHeaderText("there is an error happened !");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static void showInfo(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sign up ");
        alert.setHeaderText("Information");
        alert.setContentText(msg);
        alert.showAndWait();
    }
    
     public  RequiredFieldValidator validator(String msg){
        RequiredFieldValidator validator=new RequiredFieldValidator();
        validator.setOpacity(0.5);
        validator.setMessage(msg);         
        return validator;
        }


    @FXML
    void regist(ActionEvent event) {
        try {
            if (firstName.getText().isEmpty() || lastName.getText().isEmpty()
                    | userName.getText().isEmpty() || password.getText().isEmpty()) {

                firstName.validate();
                lastName.validate();
                userName.validate();
                password.validate();

                
            } else {

                signupdata = "signup/" + firstName.getText() + " " + lastName.getText() + "/" + userName.getText() + "/" + password.getText() ;
                String mes = Client.connect(signupdata);

                if (mes.equals("YES")) {

                    showInfo(userName.getText() + " is registered successfully");
                    s.signUPClose();
                    s.signinWindow();

                } else if (mes.equals("NO")) {
                    showError("There was some issue with signup");
                }


            }
        } catch (NumberFormatException c) {
            showError(c.getMessage());

        } catch (NullPointerException cc) {
            showError("Please , Fill all fields");

        }
    }

    @FXML
    void cancelAction(ActionEvent event) {
        s.signUPClose();
        s.signinWindow();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        firstName.getValidators().add(validator("Input is required"));
        lastName.getValidators().add(validator("Input is required"));
        userName.getValidators().add(validator("Input is required"));
        password.getValidators().add(validator("Input is required"));

    }

}
