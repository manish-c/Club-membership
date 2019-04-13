package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login {
    private static Connection conn = null;
    private static PreparedStatement stat = null;
    //private static String url = "jdbc:mysql://128.235.208.216:3306/mc677";
    private static String url = "jdbc:mysql://sql1.njit.edu:3306/mc677";
    private static String Password = "IxTBFborB";
    private static String username = "mc677";
    private static String sqlInsert;
    String checkUser, checkPw;
    static ResultSet result;
    static String nameforHome = new String();
    static String log_s = new String();
    static String user_str = new String();


    public static void main(String[] args ){

    }

    public static String login_user(String info) {

        System.out.println("inside login_user ");
        String mess1 = info;
        String info1[]= mess1.split("/", 2);
        System.out.println("new info1[0]: " + info1[0]);
        System.out.println("new info1[1]: " + info1[1]);

        String sqlSelect = "select * from mc677.User where username='" + info1[0] + "' and password='" + info1[1]  + "' ";
        System.out.println("SQL : " + sqlSelect);

        try {
            Thread.sleep(1000);

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, Password);
            stat = conn.prepareStatement(sqlSelect);

            result = stat.executeQuery();

            if (result.next()) {

                if ((result.getString("username")).equals(info1[0])
                        && (result.getString("password")).equals(info1[1])   ) {

                    nameforHome = result.getString("name");
                    System.out.println("SQL result: " + result);
                    System.out.println("nameforHome: " + nameforHome);
                    log_s = "YES/" + nameforHome;
                }
            }
            else{
                log_s = "NO";
            }
        }catch (Exception e) {
            System.out.println(e);
        }
        finally {
            try {
                conn.close();
                stat.close();
            } catch (Exception rr) {
                System.out.println(rr);
            }
        }
        System.out.println("log_s: " + log_s);

        return log_s;
    }


    public static String signup_user(String info) {

        System.out.println("inside signup_user ");
        String mess1 = info;
        String info1[]= mess1.split("/");
        System.out.println("new info1[0]: " + info1[0]);
        System.out.println("new info1[1]: " + info1[1]);
        System.out.println("new info1[2]: " + info1[2]);


        String sqlSelect = "INSERT INTO mc677.User(name,username,password) VALUES ('" + info1[0] + "','"  + info1[1] + "','"  + info1[2] + "') ";
        System.out.println("SQL : " + sqlSelect);

        try {
            Thread.sleep(1000);

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, Password);
            stat = conn.prepareStatement(sqlSelect);

            int result_l = stat.executeUpdate();

            System.out.println(" result of insert: " + result_l);

            if (result_l != '0') {

                log_s = "YES";

            }
            else{
                log_s = "NO";
            }
        }catch (Exception e) {
            System.out.println(e);
        }
        finally {
            try {
                conn.close();
                stat.close();
            } catch (Exception rr) {
                System.out.println(rr);
            }
        }
        System.out.println("log_s: " + log_s);

        return log_s;
    }


    public static String sel_user(String info) {

        System.out.println("inside sel_user ");
        String mess1 = info;


        System.out.println("SQL : " + mess1);

        try {
            Thread.sleep(1000);

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, Password);
            stat = conn.prepareStatement(mess1);

            ResultSet result_u = stat.executeQuery();
            int cnt = 0;
            System.out.println(" result of users: " + result_u);
            user_str = "";
            if (result_u.next()) {
                result_u.beforeFirst();
                while (result_u.next()) {


                    user_str = user_str
                            + result_u.getString("username")
                            + "/" + result_u.getString("password")
                            + "/" + result_u.getString("name")
                            + "/" + result_u.getString("ucid")
                            + "/" + result_u.getString("major")
                            + "/" + result_u.getString("place")
                            + "/" + result_u.getString("add1")
                            + "/" + result_u.getString("email")
                            + "/" + result_u.getString("admin")
                            + "/" + result_u.getString("dateadded").substring(0,10)
                            + "/" ;

                }
                log_s = "YES/" + user_str;
            }
            else{
                log_s = "NO";
            }

        }catch (Exception e) {
            System.out.println(e);
        }
        finally {
            try {
                conn.close();
                stat.close();
            } catch (Exception rr) {
                System.out.println(rr);
            }
        }
        System.out.println("log_s: " + log_s);

        return log_s;
    }


    public static String admin_c(String info) {

        System.out.println("inside admin_c ");
        String mess1 = info;
        String info1[]= mess1.split("/", 2);
        System.out.println("new info1[0]: " + info1[0]);


        String sqlSelect = "select admin from mc677.User where username='" + info1[0] + "' ";
        System.out.println("SQL : " + sqlSelect);

        try {
            Thread.sleep(1000);

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, Password);
            stat = conn.prepareStatement(sqlSelect);

            result = stat.executeQuery();

            if (result.next()) {

                if ((result.getInt("admin")) == 1)
                        {
                    log_s = "YES";
                } else if ((result.getInt("admin")) == 0)
                {
                    log_s = "NO";
                }
            }
            else{
                log_s = "NO";
            }
        }catch (Exception e) {
            System.out.println(e);
        }
        finally {
            try {
                conn.close();
                stat.close();
            } catch (Exception rr) {
                System.out.println(rr);
            }
        }
        System.out.println("log_s: " + log_s);

        return log_s;
    }


    public static String insert_u(String info) {

        System.out.println("inside insert_u ");
        String mess1 = info;
        String info1[] = new String[8];
        info1 = mess1.split("/");
        System.out.println("length of array : " + info1.length );


        if(info1[8].isEmpty()){
            info1[8] = "0";
            System.out.println("new info1[0]: " + info1[0]);
        } else
        {
            System.out.println("new info1[0]: " + info1[8]);
        }


        String sqlSelect = "INSERT INTO mc677.User (username, password, name, ucid, major, mob, add1, email, admin) VALUES ('" + info1[0] + "','"  + info1[1] + "','"  + info1[2] + "','" + info1[3] + "','"  + info1[4]
                + "','" + info1[5] + "','" + info1[6] + "','"  + info1[7] + "','" + info1[8] + "') ";
        System.out.println("SQL : " + sqlSelect);

        try {
            Thread.sleep(1000);

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, Password);
            stat = conn.prepareStatement(sqlSelect);

            int result_l = stat.executeUpdate();

            System.out.println(" result of insert: " + result_l);

            if (result_l != '0') {

                log_s = "YES";

            }
            else{
                log_s = "NO";
            }
        } catch (ArrayIndexOutOfBoundsException a){
            System.out.println(a);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                conn.close();
                stat.close();
            } catch (Exception rr) {
                System.out.println(rr);
            }
        }
        System.out.println("log_s: " + log_s);

        return log_s;
    }

    public static String delete_u(String info) {

        System.out.println("inside delete_u ");
        String mess1 = info;
        String info1[]= mess1.split("/");
        System.out.println("new info1[0]: " + info1[0]);


        String sqlSelect = "DELETE FROM mc677.User WHERE username = '" + info1[0] + "' ";
        System.out.println("SQL : " + sqlSelect);

        try {
            Thread.sleep(1000);

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, Password);
            stat = conn.prepareStatement(sqlSelect);

            int result_l = stat.executeUpdate();

            System.out.println(" result of insert: " + result_l);

            if (result_l != '0') {

                log_s = "YES";

            }
            else{
                log_s = "NO";
            }
        } catch (ArrayIndexOutOfBoundsException a){
            System.out.println(a);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                conn.close();
                stat.close();
            } catch (Exception rr) {
                System.out.println(rr);
            }
        }
        System.out.println("log_s: " + log_s);

        return log_s;
    }

    public static String update_u(String info) {

        System.out.println("inside update_u ");
        String mess1 = info;
        String info1[] = new String[8];
        info1 = mess1.split("/");
        System.out.println("length of array : " + info1.length );
        String sqlSelect = "";

        if(info1.length == 8){
            sqlSelect = "UPDATE mc677.User SET name = '" + info1[0] + "', ucid = '"  + info1[1] + "', major = '"  + info1[2] + "', place = '" + info1[3] + "', add1 = '"  + info1[4]
                    + "', email = '" + info1[5] + "', admin = '" + info1[6] + "' WHERE username = '"  + info1[7] + "'";
        } else if(info1.length == 9){
            sqlSelect = "UPDATE mc677.User SET name = '" + info1[0] + "', ucid = '"  + info1[1] + "', major = '"  + info1[2] + "', place = '" + info1[3] + "', add1 = '"  + info1[4]
                    + "', email = '" + info1[5] + "', admin = '" + info1[6] + "', password = '" + info1[7] + "' WHERE username = '"  + info1[8] + "'";
        }




        System.out.println("SQL : " + sqlSelect);

        try {
            Thread.sleep(1000);

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, Password);
            stat = conn.prepareStatement(sqlSelect);

            int result_l = stat.executeUpdate();

            System.out.println(" result of update: " + result_l);

            if (result_l != '0') {
                log_s = "YES";
            }
            else{
                log_s = "NO";
            }
        } catch (ArrayIndexOutOfBoundsException a){
            System.out.println(a);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                conn.close();
                stat.close();
            } catch (Exception rr) {
                System.out.println(rr);
            }
        }
        System.out.println("log_s: " + log_s);

        return log_s;
    }


    public static String graph_a(String info) {

        System.out.println("inside graph_a ");
        String mess1 = info;
        String info1[]= mess1.split("/");
        System.out.println("new info1[2]: " + info1[2]);
        Integer a;

        if(info1[2].equals("All")){
            info1[2] = "'Biology', 'Arts', 'CS', 'Data Science'";
        }


        String sqlSelect = "select count(*) as CNT from mc677.User where month(dateadded) = month(current_date - INTERVAL " + info1[0] +  " MONTH) " +
                "and year(dateadded) = year(current_date - INTERVAL " + info1[1] + " YEAR ) and major in (" + info1[2] + ")";
        System.out.println("SQL : " + sqlSelect);

        try {
            Thread.sleep(1000);

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, Password);
            stat = conn.prepareStatement(sqlSelect);

            result = stat.executeQuery();

            if (result.next()) {

                a =result.getInt("CNT");

                log_s = a.toString();
            }
            else{
                log_s = "NO";
            }
        }catch (Exception e) {
            System.out.println(e);
        }
        finally {
            try {
                conn.close();
                stat.close();
            } catch (Exception rr) {
                System.out.println(rr);
            }
        }
        System.out.println("log_s: " + log_s);

        return log_s;
    }


    public static String graph_m(String info) {

        System.out.println("inside graph_m ");
        String mess1 = info;
        String info1[]= mess1.split("/", 2);
        System.out.println("new info1[0]: " + info1[0]);
        String a;

        String sqlSelect = "select major from mc677.User where username='" + info1[0] + "' ";
        System.out.println("SQL : " + sqlSelect);

        try {
            Thread.sleep(1000);

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, Password);
            stat = conn.prepareStatement(sqlSelect);

            result = stat.executeQuery();

            if (result.next()) {

                a =result.getString("major");
                log_s = a;
            }
            else{
                log_s = "NO";
            }
        }catch (Exception e) {
            System.out.println(e);
        }
        finally {
            try {
                conn.close();
                stat.close();
            } catch (Exception rr) {
                System.out.println(rr);
            }
        }
        System.out.println("log_s: " + log_s);

        return log_s;
    }

}

