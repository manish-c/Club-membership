package controller;

import Main.Client;
import Main.HomeMain;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.util.ResourceBundle;

import static controller.SignupConroller.showError;


public class SigninController implements Initializable {

    @FXML
    private ImageView logoImage;

    @FXML
    private JFXButton signinBTN;

    @FXML
    private JFXButton signupBTN;

    @FXML
    private JFXTextField usernameTF;

    @FXML
    private JFXPasswordField passwordTF;


   public String checkUser, checkPw;


    public static String usernameforHome = "";
    public static String nameforHome = "";

    HomeMain su;
    Stage stage;

    public void Main(HomeMain su, Stage stage) {
        this.stage = stage;
        this.su = su;

    }

    public RequiredFieldValidator validator(String msg) {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setOpacity(0.5);
        validator.setMessage(msg);
        return validator;
    }

    @FXML
    void signinAction(ActionEvent event) {

//---------------------------------
        checkUser = usernameTF.getText();
        System.out.println("checkUser : " + checkUser);
        checkPw = passwordTF.getText();
        System.out.println("checkPw : " + checkPw);

        String login_c = "login/" + checkUser + "/" + checkPw;
        String mes = Client.connect(login_c);

        String mes_log[] = mes.split("/",2);


        try {

            if (usernameTF.getText().isEmpty() || passwordTF.getText().isEmpty()) {

                usernameTF.validate();
                passwordTF.validate();
            } else {

                if (mes_log[0].equals("YES")) {

                    nameforHome = mes_log[1];
                    usernameforHome = checkUser;
                    su.homeWindow();
                    su.signInClose();

                    Notifications notification = Notifications.create()
                            .title("Sign in complete ")
                            .text(usernameforHome + " has loged in")
                            .hideAfter(Duration.seconds(3))
                            .position(Pos.BOTTOM_RIGHT);
                    notification.showInformation();

                } else if (mes_log[0].equals("NO")) {
                    showError("username or password is invalid ");
                }
            }
        } catch (NullPointerException l) {
            showError(l.getMessage());
        }
    }


    @FXML
    void signupAction(ActionEvent event) {
        try {
            su.signInClose();
            su.signupWindow();
        } catch (Exception ex) {
        }
    }


    public void initialize(URL url, ResourceBundle rb) {

        usernameTF.getValidators().add(validator("Input is required"));
        passwordTF.getValidators().add(validator("Input is required"));

        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(122), logoImage);
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(10 * 720);
        rotateTransition.play();

    }


}