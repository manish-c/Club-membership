package controller;

import Main.Client;
import Main.HomeMain;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static Main.HomeMain.major_check;



public class SubStatisticsController implements Initializable {

    @FXML
    private BarChart<?, ?> barChart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    HomeMain su;


    public void Main(HomeMain su, Stage stage) {

        this.su = su;
    }



    int countUser;

    @Override

    public void initialize(URL url, ResourceBundle rb) {

        LocalDate today = LocalDate.now();
        int g_month1 = 0;
        String monthcheck;


        XYChart.Series set = new XYChart.Series<>();
        g_month1 = 0;
        countUserfunc(g_month1);
        monthcheck = today.getMonth().minus(g_month1).name();
        set.getData().add(new XYChart.Data(monthcheck, countUser));

        XYChart.Series set2 = new XYChart.Series<>();
        g_month1 = 1;
        countUserfunc(g_month1);
        monthcheck = today.getMonth().minus(g_month1).name();
        set2.getData().add(new XYChart.Data(monthcheck, countUser));

        XYChart.Series set3 = new XYChart.Series<>();
        g_month1 = 2;
        countUserfunc(g_month1);
        monthcheck = today.getMonth().minus(g_month1).name();
        set3.getData().add(new XYChart.Data(monthcheck, countUser));

        XYChart.Series set4 = new XYChart.Series<>();
        g_month1 = 3;
        countUserfunc(g_month1);
        monthcheck = today.getMonth().minus(g_month1).name();
        set4.getData().add(new XYChart.Data(monthcheck, countUser));

        XYChart.Series set5 = new XYChart.Series<>();
        g_month1 = 4;
        countUserfunc(g_month1);
        monthcheck = today.getMonth().minus(g_month1).name();
        set5.getData().add(new XYChart.Data(monthcheck, countUser));

        XYChart.Series set6 = new XYChart.Series<>();
        g_month1 = 5;
        countUserfunc(g_month1);
        monthcheck = today.getMonth().minus(g_month1).name();
        set6.getData().add(new XYChart.Data(monthcheck, countUser));


        barChart.getData().addAll(set,set2,set3,set4,set5,set6);

    }

    public void countUserfunc(Integer g_month) {
        countUser = 0;

        String g_year;

        LocalDate today = LocalDate.now();
        int yearcheck = today.getMonthValue();

        System.out.println("yearcheck:" + yearcheck );

        if(g_month >= yearcheck ){
            g_year = "1";
        }else{
            g_year = "0";
        }


        String sqlSelect = "graph/" + g_month + "/" + g_year + "/" + major_check + "/";
        String mes = Client.connect(sqlSelect);

        countUser = (Integer.parseInt(mes));

    }

}
