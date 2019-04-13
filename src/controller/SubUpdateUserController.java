package controller;

import Main.Client;
import Main.HomeMain;
import com.jfoenix.controls.*;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import model.userModel;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class SubUpdateUserController implements Initializable {

    @FXML
    private JFXTreeTableView<userModel> tableView;
    ObservableList<userModel> userList;



    @FXML
    private JFXTextField insertpassword, insertname, insertucid, insertplace, insertmajor, insertadd1, insertemail;

    @FXML
    private GridPane InsertGridPane;


    @FXML
    private Label username_label, admin_label, dateadded_label;


    String user2del, userpass;



    public static void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error ");
        alert.setHeaderText("there is an error happened !");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    HomeMain homeMain;
    Stage stage;

    public void main(HomeMain homeMain, Stage stage) {
        this.homeMain = homeMain;
        this.stage = stage;
    }

    public RequiredFieldValidator validator(String msg) {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setOpacity(0.5);
        validator.setMessage(msg);
        return validator;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {


        JFXTreeTableColumn<userModel, String> namecolumn = new JFXTreeTableColumn<>("Name");

        namecolumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<userModel, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<userModel, String> param) {
                return param.getValue().getValue().name;

            }
        });

        JFXTreeTableColumn<userModel, String> ucidcolumn = new JFXTreeTableColumn<>("UCID");

        ucidcolumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<userModel, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<userModel, String> param) {
                return param.getValue().getValue().ucid;

            }
        });

        JFXTreeTableColumn<userModel, String> mobcolumn = new JFXTreeTableColumn<>("Work Place");

        mobcolumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<userModel, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<userModel, String> param) {
                return param.getValue().getValue().place;

            }
        });

        JFXTreeTableColumn<userModel, String> add1column = new JFXTreeTableColumn<>("Address");

        add1column.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<userModel, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<userModel, String> param) {
                return param.getValue().getValue().add1;

            }
        });

        JFXTreeTableColumn<userModel, String> emailcolumn = new JFXTreeTableColumn<>("E-mail");

        emailcolumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<userModel, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<userModel, String> param) {
                return param.getValue().getValue().email;

            }
        });

        JFXTreeTableColumn<userModel, String> majorcolumn = new JFXTreeTableColumn<>("Major");
        majorcolumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<userModel, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<userModel, String> param) {
                return param.getValue().getValue().major;

            }
        });

        userList = FXCollections.observableArrayList();
        addrowsToTable();


}


    void addrowsToTable() {


        String sqlSelect = "select username, password, name, ucid, major, place, add1, email, admin, dateadded from mc677." + HomeWindowController.tableName + " where username = '" + SigninController.usernameforHome + "' order by 1 ";

        String sel_user = "user/" + sqlSelect;
        String mes = Client.connect(sel_user);

        String mes_usr[] = mes.split("/",2);

        String mes_usr_data[] = mes_usr[1].split("/");


        System.out.println("mes_usr_data.length : " + mes_usr_data.length);

        if (mes_usr[0].equals("YES")) {
            mes_usr_data = mes_usr[1].split("/");


            for (int j = 0; j < ((mes_usr_data.length)); j= j+10) {
                userList.add(new userModel(mes_usr_data[j], mes_usr_data[j+1],
                        mes_usr_data[j+2], mes_usr_data[j+3],
                        mes_usr_data[j+4], mes_usr_data[j+5],
                        mes_usr_data[j+6], mes_usr_data[j+7],
                        mes_usr_data[j+8], mes_usr_data[j+9]));
            }
        } else if (mes_usr[0].equals("NO")) {
            System.out.println("Error with selecting rows");
        }

        userpass = mes_usr_data[1];
        //insertpassword.setText(mes_usr_data[1]);
        insertname.setText(mes_usr_data[2]);
        insertucid.setText(mes_usr_data[3]);
        insertmajor.setText(mes_usr_data[4]);
        insertplace.setText(mes_usr_data[5]);
        insertadd1.setText(mes_usr_data[6]);
        insertemail.setText(mes_usr_data[7]);

        username_label.setText(mes_usr_data[0]);
        admin_label.setText(mes_usr_data[8]);
        dateadded_label.setText(mes_usr_data[9]);


    }

    @FXML
    void showpassword(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confidential info");
        alert.setHeaderText("Password");
        alert.setContentText(userpass);
        alert.showAndWait();
    }

    @FXML
    void delelePatientRow(ActionEvent event) {
        try {



            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confimation");

            alert.setHeaderText("Confirmation");
            alert.setContentText("Are you sure you want to delete your account?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {


                String sel_user = "delete/" + user2del;

                System.out.println("delete SQL :" + sel_user);
                String mes = Client.connect(sel_user);
                if (mes.equals("YES")) {
                    Notifications notification = Notifications.create()
                            .title("Successfully deleted")
                            .text(user2del + " was deleted")
                            .hideAfter(Duration.seconds(3))
                            .position(Pos.BOTTOM_RIGHT);
                    notification.showInformation();
                    homeMain.signinWindow();
                    homeMain.SubWindowClose();
                } else if (mes.equals("NO")) {
                    System.out.println("Not Deleted");
                }

            }else if (result.get() == ButtonType.CANCEL) {
                alert.close();
            }
            } catch(NullPointerException l){
                showError(l.getMessage());
            }

    }

    @FXML
    void updatePatientRow(ActionEvent event) {

        TreeItem<userModel> pModel = new TreeItem<>();

        String sqlUpdate = "update/" + insertname.getText() + "/" + insertucid.getText() + "/" + insertmajor.getText() + "/" + insertplace.getText()
                + "/" + insertadd1.getText() + "/" + insertemail.getText() + "/" + admin_label.getText() + "/" + insertpassword.getText() + "/" + username_label.getText() + "/" ;

        String mes = Client.connect(sqlUpdate);

        if(mes.equals("YES")){


            userModel user_Model = new userModel(username_label.getText(), insertpassword.getText(), insertname.getText(), insertucid.getText(), insertmajor.getText(), insertplace.getText(), insertadd1.getText(),
                    insertemail.getText(), admin_label.getText(), dateadded_label.getText());
            pModel.setValue(user_Model);

            Notifications notification = Notifications.create()
                    .title("Successfully Updated")
                    .text( username_label.getText() + " was updated")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT);
            notification.showInformation();
            userpass = insertpassword.getText();
            insertpassword.setText(null);

        } else if (mes.equals("NO")){
            System.out.println("Not Updated");
        }

    }



    @FXML
    void back(ActionEvent event) {
        homeMain.homeWindow();
        homeMain.SubWindowClose();
    }

}
