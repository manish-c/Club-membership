package controller;

import Main.Client;
import Main.HomeMain;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.userModel;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;


public class SubViewController implements Initializable {

    @FXML
    private JFXTreeTableView<userModel> tableView;
    ObservableList<userModel> userList;


    @FXML
    private JFXTextField searchTF;

    String tablename, tableucid, tableplace, tableadd1, tableemail, tablemajor;




    HomeMain homeMain;
    Stage stage;

    public void main(HomeMain homeMain, Stage stage) {
        this.homeMain = homeMain;
        this.stage = stage;
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

        tablename = pModel.getValue().getName();
        tableucid = pModel.getValue().getUcid();
        tableplace = pModel.getValue().getPlace();
        tableadd1 = pModel.getValue().getAdd1();
        tableemail = pModel.getValue().getEmail();
        tablemajor = pModel.getValue().getMajor();

    }

    void addrowsToTable() {


        String sqlSelect = "select username, password, name, ucid, major, place, add1, email, admin, dateadded from mc677." + HomeWindowController.tableName + " order by 1 ";

        String sel_user = "user/" + sqlSelect;
        String mes = Client.connect(sel_user);

        String mes_usr[] = mes.split("/",2);

        if (mes_usr[0].equals("YES")) {
            String mes_usr_data[] = mes_usr[1].split("/");

            for (int j = 0; j < mes_usr_data.length; j++) {
                System.out.println("mes_usr_data["+j+"] : " + mes_usr_data[j]);

            }
            System.out.println("mes_usr_data.length : " + mes_usr_data.length);

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
    void back(ActionEvent event) {
        homeMain.homeWindow();
        homeMain.SubWindowClose();
    }

}
