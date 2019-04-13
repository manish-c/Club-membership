package Main;


import controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class HomeMain extends Application {

    static Stage stageprim, stage1, stage2, stage4, stage3, stage5, stage6,stage7;
    public String options = "";
    public static String major_check;


    @Override
    public void start(Stage stage) throws Exception {
        this.stage1 = stage;
        splashWindow();
    }

    public void splashWindow() {

        try {

            FXMLLoader loader = new FXMLLoader(HomeMain.class.getResource("/view/Loading.fxml"));
            AnchorPane pane = loader.load();
            LoadingController controller = loader.getController();
            controller.Main(this, stage1);
            Scene scene = new Scene(pane);
            stage1.initStyle(StageStyle.UNDECORATED);
            scene.getStylesheets().add(HomeMain.class.getResource("/style/StyleSheet.css").toExternalForm());
            stage1.setScene(scene);
            stage1.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadingWindowClose() {
        stage1.close();
    }

    public void signinWindow() {

        try {
            FXMLLoader loader = new FXMLLoader(HomeMain.class.getResource("/view/Signin.fxml"));
            AnchorPane pane = loader.load();
            SigninController controller = loader.getController();
            stageprim = new Stage();
            controller.Main(this, stageprim);
            Scene scene = new Scene(pane);
            stageprim.setTitle("Sign in");
            scene.getStylesheets().add(HomeMain.class.getResource("/style/StyleSheet.css").toExternalForm());
            stageprim.setResizable(false);
            stageprim.setScene(scene);
            stageprim.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void signInClose() {
        stageprim.close();
    }

    public void signupWindow() {

        try {
            FXMLLoader loader = new FXMLLoader(HomeMain.class.getResource("/view/Signup.fxml"));
            AnchorPane pane = loader.load();
            SignupConroller controller = loader.getController();
            controller.main(this);
            stage2 = new Stage();
            Scene scene = new Scene(pane);
            scene.getStylesheets().add(HomeMain.class.getResource("/style/StyleSheet.css").toExternalForm());
            stage2.setTitle("Register");
            stage2.setResizable(false);
            stage2.setScene(scene);
            stage2.initStyle(StageStyle.UNDECORATED);
            stage2.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void signUPClose() {
        stage2.close();
    }

    public void homeWindow() {

        try {
            FXMLLoader loader = new FXMLLoader(HomeMain.class.getResource("/view/HomeWindow.fxml"));
            AnchorPane pane = loader.load();
            HomeWindowController controller = loader.getController();
            stage3 = new Stage();
            controller.main(this, stage3);
            Scene scene = new Scene(pane);
            stage3.setTitle("Home");
            stage3.setResizable(true);
            stage3.setScene(scene);
            stage3.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void mainWindowClose() {
        stage3.close();
    }

    public void subViewUpdateWindow(int admin) {
        if (admin == 0) {

            try {
                FXMLLoader loader = new FXMLLoader(HomeMain.class.getResource("/view/SubUpdateUser.fxml"));
                AnchorPane pane = loader.load();
                SubUpdateUserController controller = loader.getController();
                stage4 = new Stage();
                controller.main(this, stage4);
                Scene scene = new Scene(pane);
                scene.getStylesheets().add(HomeMain.class.getResource("/style/StyleSheet.css").toExternalForm());
                stage4.setTitle(options + "Update");
                stage4.setFullScreen(true);
                stage4.setResizable(true);
                stage4.setScene(scene);

                stage4.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (admin == 1) {

            try {
                FXMLLoader loader = new FXMLLoader(HomeMain.class.getResource("/view/SubUpdateAdmin.fxml"));
                AnchorPane pane = loader.load();
                SubUpdateAdminController controller = loader.getController();
                stage4 = new Stage();
                controller.main(this, stage4);
                Scene scene = new Scene(pane);
                scene.getStylesheets().add(HomeMain.class.getResource("/style/StyleSheet.css").toExternalForm());
                stage4.setTitle(options + "Update Users");
                stage4.setFullScreen(true);
                stage4.setResizable(true);
                stage4.setScene(scene);

                stage4.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if( admin == 2){
            try {
                FXMLLoader loader = new FXMLLoader(HomeMain.class.getResource("/view/SubView.fxml"));
                AnchorPane pane = loader.load();
                SubViewController controller = loader.getController();
                stage4 = new Stage();
                controller.main(this, stage4);
                Scene scene = new Scene(pane);
                scene.getStylesheets().add(HomeMain.class.getResource("/style/StyleSheet.css").toExternalForm());
                stage4.setTitle(options + "View Users");
                stage4.setFullScreen(true);
                stage4.setResizable(true);
                stage4.setScene(scene);

                stage4.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    public void SubWindowClose() {
        stage4.close();
    }

    public static void statisticsWindow(String major_c) {
        major_check = major_c;

        try {
            FXMLLoader loader = new FXMLLoader(HomeMain.class.getResource("/view/SubStatistics.fxml"));
            AnchorPane pane = loader.load();
            SubStatisticsController controller = loader.getController();
            stage5 = new Stage();
            Scene scene = new Scene(pane);
            scene.getStylesheets().add(HomeMain.class.getResource("/style/StyleSheet.css").toExternalForm());
            stage5.setTitle("Statistics");
            stage5.setResizable(true);
            stage5.setScene(scene);
            stage5.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void AboutWindow() {

        try {
            FXMLLoader loader = new FXMLLoader(HomeMain.class.getResource("/view/NavAbout.fxml"));
            AnchorPane pane = loader.load();
            NavAboutController controller = loader.getController();
            stage6 = new Stage();
            Scene scene = new Scene(pane);
            scene.getStylesheets().add(HomeMain.class.getResource("/style/StyleSheet.css").toExternalForm());
            stage6.setTitle("About");
            stage6.setResizable(false);
            stage6.setScene(scene);
            stage6.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        launch(args);
    }

}
