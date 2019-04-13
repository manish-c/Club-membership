package controller;

import Main.Client;
import Main.HomeMain;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import model.userModel;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.function.Predicate;



public class SubUpdateAdminController implements Initializable {

    @FXML
    private JFXTreeTableView<userModel> tableView;
    ObservableList<userModel> userList;
    @FXML
    private JFXTextField searchTF;

    @FXML
    private JFXTextField insertpassword, insertusername, insertname, insertucid, insertplace, insertmajor, insertadd1, insertemail, insertadmin;

    @FXML
    private GridPane InsertGridPane;

    @FXML
    private Label username_label, admin_label, dateadded_label;

    String tablename, tableucid, tablemob, tableadd1, tableemail, tablemajor ;
    String user_name;



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


        insertusername.getValidators().add(validator("User is required"));
        insertpassword.getValidators().add(validator("Password is required"));
        insertname.getValidators().add(validator("Name is required"));
        insertucid.getValidators().add(validator("UCID is required"));
        insertmajor.getValidators().add(validator("Input is required"));
        insertplace.getValidators().add(validator("Input is required"));
        insertadd1.getValidators().add(validator("Input is required"));
        insertemail.getValidators().add(validator("Input is required"));
        insertadmin.getValidators().add(validator("Input is required"));


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

        JFXTreeTableColumn<userModel, String> placecolumn = new JFXTreeTableColumn<>("Work Place");

        placecolumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<userModel, String>, ObservableValue<String>>() {

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

        TreeItem<userModel> root = new RecursiveTreeItem<userModel>(userList, RecursiveTreeObject::getChildren);
        tableView.getColumns().addAll(namecolumn, ucidcolumn, placecolumn, add1column, emailcolumn, majorcolumn);
        tableView.setRoot(root);
        tableView.setShowRoot(false);

        searchTF.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                tableView.setPredicate(new Predicate<TreeItem<userModel>>() {

                    @Override
                    public boolean test(TreeItem<userModel> t) {

                        boolean flag = t.getValue().name.getValue().contains(newValue)
                                || t.getValue().ucid.getValue().contains(newValue)
                                || t.getValue().place.getValue().contains(newValue)
                                || t.getValue().add1.getValue().contains(newValue)
                                || t.getValue().email.getValue().contains(newValue)
                                || t.getValue().major.getValue().contains(newValue);
                        return flag;

                    }
                });
            }

        });

        tableView.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                ->
                showDetails(newValue)
        );


}

    public void showDetails(TreeItem<userModel> pModel) {

        //right bottom view
        username_label.setText(pModel.getValue().getUsername());
        admin_label.setText(pModel.getValue().getAdmin());
        dateadded_label.setText(pModel.getValue().getDateadded());
        user_name = username_label.getText();

        //Insertion
      //  insertusername.setText(pModel.getValue().getUsername());
      //  insertpassword.setText(pModel.getValue().getPassword());
        insertname.setText(pModel.getValue().getName());
        insertucid.setText(pModel.getValue().getUcid());
        insertmajor.setText(pModel.getValue().getMajor());
        insertplace.setText(pModel.getValue().getPlace());
        insertadd1.setText(pModel.getValue().getAdd1());
        insertemail.setText(pModel.getValue().getEmail());
        insertadmin.setText(pModel.getValue().getAdmin());

        //Table view
        tablename = pModel.getValue().getName();
        tableucid = pModel.getValue().getUcid();
        tablemob = pModel.getValue().getPlace();
        tableadd1 = pModel.getValue().getAdd1();
        tableemail = pModel.getValue().getEmail();
        tablemajor = pModel.getValue().getMajor();

    }


    @FXML
    void insertPatientData(ActionEvent event) {
        try {
            System.out.println("inside insert" );

            if (insertusername.getText().isEmpty() || insertpassword.getText().isEmpty()
                    | insertname.getText().isEmpty() || insertucid.getText().isEmpty()) {
                System.out.println("inside up" );
                insertusername.validate();
                insertpassword.validate();
                insertname.validate();
                insertucid.validate();
                System.out.println("inside down" );

            } else {

                System.out.println("inside else" );

                if (insertadmin.getText().isEmpty()) {
                    insertadmin.setText("0");
                }
                System.out.println("insertadmin.getText():" + insertadmin.getText());

                String sqlInsert = "insert/" + insertusername.getText() + "/" + insertpassword.getText() + "/" + insertname.getText() + "/" + insertucid.getText()
                        + "/" + insertmajor.getText() + "/" + insertplace.getText() + "/" + insertadd1.getText() + "/" + insertemail.getText() + "/" + insertadmin.getText() + "/" ;

                String mes = Client.connect(sqlInsert);



                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Calendar cal = Calendar.getInstance();
                dateadded_label.setText("" + dateFormat.format(cal));
                System.out.println(dateFormat.format(cal));

                userList.add(new userModel(insertusername.getText(), insertpassword.getText(), insertname.getText(), insertucid.getText(), insertmajor.getText(), insertplace.getText(), insertadd1.getText(),
                        insertemail.getText(), insertadmin.getText(), dateadded_label.getText()));

                if(mes.equals("YES")){
                    Notifications notification = Notifications.create()
                            .title("Successfully Added")
                            .text(insertusername.getText() + " was added")
                            .hideAfter(Duration.seconds(3))
                            .position(Pos.BOTTOM_RIGHT);
                    notification.showInformation();
                } else if (mes.equals("NO")){
                    System.out.println("Not Deleted");
                }

                clear();

            }
        }
        catch (NullPointerException cc) {
            showError("Please All inputs are requires");

        }
        catch (NumberFormatException c) {
            showError("password must be number");

        } catch (Error e) {
            showError(e.getMessage());
        } catch (Exception f) {
            showError(f.getMessage());

        }
    }

    void addrowsToTable() {


        String sqlSelect = "select username, password, name, ucid, major, place, add1, email, admin, dateadded from mc677." + HomeWindowController.tableName + " order by 1 ";

        String sel_user = "user/" + sqlSelect;
        String mes = Client.connect(sel_user);

        String mes_usr[] = mes.split("/",2);

        if (mes_usr[0].equals("YES")) {
            String mes_usr_data[] = mes_usr[1].split("/");

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


    }

    @FXML
    void delelePatientRow(ActionEvent event) {
        try {
            int index = tableView.getSelectionModel().getSelectedIndex();


            String sel_user = "delete/" + user_name;

            System.out.println("delete SQL :"+sel_user);
            String mes = Client.connect(sel_user);
            if(mes.equals("YES")){
                Notifications notification = Notifications.create()
                        .title("Successfully deleted")
                        .text(user_name + " was deleted")
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.BOTTOM_RIGHT);
                notification.showInformation();
                userList.remove(index);
            } else if (mes.equals("NO")){
                System.out.println("Not Deleted");
            }
            clear();
        } catch (NullPointerException l) {
            showError(l.getMessage());
        }

    }

    @FXML
    void updatePatientRow(ActionEvent event) {


        TreeItem<userModel> pModel = tableView.getSelectionModel().getSelectedItem();

        String sqlUpdate = "update/" + insertname.getText() + "/" + insertucid.getText() + "/" + insertmajor.getText() + "/" + insertplace.getText()
                + "/" + insertadd1.getText() + "/" + insertemail.getText() + "/" + insertadmin.getText() + "/" + user_name + "/" ;

        String mes = Client.connect(sqlUpdate);

        if(mes.equals("YES")){

            userModel user_Model = new userModel(insertusername.getText(), insertpassword.getText(), insertname.getText(), insertucid.getText(), insertmajor.getText(), insertplace.getText(), insertadd1.getText(),
                    insertemail.getText(), insertadmin.getText(), dateadded_label.getText());
            pModel.setValue(user_Model);

            Notifications notification = Notifications.create()
                    .title("Successfully Updated")
                    .text(user_name + " was updated")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT);
            notification.showInformation();
        } else if (mes.equals("NO")){
            System.out.println("Not Updated");
        }
        clear();
    }

    @FXML
    void clearFields(ActionEvent event) {
        clear();

    }

    public void clear() {

        insertusername.setText(null);
        insertpassword.setText(null);
        insertname.setText(null);
        insertucid.setText(null);
        insertmajor.setText(null);
        insertplace.setText(null);
        insertadd1.setText(null);
        insertemail.setText(null);
        insertadmin.setText(null);

        username_label.setText(null);
        admin_label.setText(null);
        dateadded_label.setText(null);

    }


    @FXML
    void back(ActionEvent event) {
        homeMain.homeWindow();
        homeMain.SubWindowClose();
    }

}
