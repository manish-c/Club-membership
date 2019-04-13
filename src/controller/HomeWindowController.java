package controller;

import Main.Client;
import Main.HomeMain;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class HomeWindowController implements Initializable {

    HomeMain homeMain;
    Stage stage;
    public static String tableName = "";

    public void main(HomeMain homeMain, Stage stage) {
        this.homeMain = homeMain;
        this.stage = stage;
    }

    @FXML
    private Pane backgroundPane;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger humburger;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {

            FadeTransition fadeout = new FadeTransition(Duration.seconds(2.5), backgroundPane);
            fadeout.setFromValue(1);
            fadeout.setToValue(0);
            fadeout.play();

            fadeout.setOnFinished(event -> {

                backgroundPane.setStyle(" -fx-background-image: url(\"/src/background_1.jpg\");");

                FadeTransition fadein = new FadeTransition(Duration.seconds(2.5), backgroundPane);
                fadein.setFromValue(0);
                fadein.setToValue(0.6);
                fadein.play();

                fadein.setOnFinished(e -> {

                    backgroundPane.setStyle(" -fx-background-image: url(\"/src/background_2.jpg\");");
                    FadeTransition fadein2 = new FadeTransition(Duration.seconds(2.5), backgroundPane);
                    fadein2.setFromValue(0);
                    fadein2.setToValue(1);
                    fadein2.play();

                    fadein2.setOnFinished(event2 -> {

                        backgroundPane.setStyle(" -fx-background-image: url(\"/src/background_3.jpg\");");

                        FadeTransition fadein3 = new FadeTransition(Duration.seconds(2.5), backgroundPane);
                        fadein3.setFromValue(1);
                        fadein3.setToValue(0);
                        fadein3.play();

                        fadein3.setOnFinished(event3 -> {
                            backgroundPane.setStyle(" -fx-background-image: url(\"/src/background_4.jpg\");");

                            FadeTransition fadein4 = new FadeTransition(Duration.seconds(2.5), backgroundPane);
                            fadein4.setFromValue(0);
                            fadein4.setToValue(1);
                            fadein4.play();

                            fadein4.setOnFinished(event4 -> {
                                backgroundPane.setStyle(" -fx-background-image: url(\"/src/background_5.jpg\");");

                                FadeTransition fadein5 = new FadeTransition(Duration.seconds(2.5), backgroundPane);
                                fadein5.setFromValue(0);
                                fadein5.setToValue(1);
                                fadein5.play();

                            });

                        });
                    });

                });

            });

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/NavHome.fxml"));
            AnchorPane pane = loader.load();
            drawer.setSidePane(pane);
            HamburgerBackArrowBasicTransition hamburderTrans = new HamburgerBackArrowBasicTransition(humburger);
            hamburderTrans.setRate(-1);
            humburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                hamburderTrans.setRate(hamburderTrans.getRate() * -1);
                hamburderTrans.play();

                if (drawer.isShown()) {
                    drawer.close();
                } else {
                    drawer.open();
                }

            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    public void openViewWindow(ActionEvent event) {
        try {
            tableName = "User";
            homeMain.options = "View Members";
            int admin = 2;
            homeMain.subViewUpdateWindow(admin);
            homeMain.mainWindowClose();
        } catch (Exception ex) {
        }
    }

    @FXML
    public void openUpdateWindow(ActionEvent event) {
        try {
            tableName = "User";
            homeMain.options = "Update";
            int admin1 = 0;

            String sqlInsert = "admin/" + SigninController.usernameforHome;
            String mes = Client.connect(sqlInsert);
            if(mes.equals("YES")){
                admin1 = 1;
            }else {
                admin1 = 0;
            }

            homeMain.subViewUpdateWindow(admin1);
            homeMain.mainWindowClose();

        } catch (Exception ex) {
        }
    }

    @FXML
    public void openStatWindow(ActionEvent event) {
        try {
            tableName = "User";
            homeMain.options = "Statistics";
            String sqlInsert = "graph_m/" + SigninController.usernameforHome;
            String major_check = Client.connect(sqlInsert);
            homeMain.statisticsWindow(major_check);
            homeMain.mainWindowClose();

        } catch (Exception ex) {
        }
    }

}
