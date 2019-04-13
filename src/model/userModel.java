package model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class userModel extends RecursiveTreeObject<userModel>{
    
   public StringProperty username, password, name, ucid, major, place, add1, email, admin, dateadded;

    public userModel(String username, String password, String name, String ucid, String major, String place, String add1, String email, String admin, String dateadded) {
        
        this.username =new SimpleStringProperty(username);
        this.password =new SimpleStringProperty(password);
        this.name =new SimpleStringProperty(name);
        this.ucid =new SimpleStringProperty(ucid);
        this.major =new SimpleStringProperty(major);
        this.place =new SimpleStringProperty(place);
        this.add1 =new SimpleStringProperty(add1);
        this.email =new SimpleStringProperty(email);
        this.admin =new SimpleStringProperty(admin);
        this.dateadded =new SimpleStringProperty(dateadded);

    }

    public String getPassword() {
        return password.get();
    }

    public String getName() {
        return name.get();
    }

    public String getMajor() {
        return major.get();
    }

    public String getUcid() {
        return ucid.get();
    }

    public String getUsername() {
        return username.get();
    }

    public String getPlace() {
        return place.get();
    }

    public String getAdd1() {
        return add1.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getAdmin() {
        return admin.get();
    }

    public String getDateadded() { return dateadded.get(); }

    public void setPassword(String Age) {
        this.password =new SimpleStringProperty(Age);
    }
    public void setName(String Date) {
        this.name =new SimpleStringProperty(Date);
    }
    public void setMajor(String major) {
        this.major =new SimpleStringProperty(major);
    }
    public void setUcid(String ucid) {
        this.ucid =new SimpleStringProperty(ucid);
    }
    public void setUsername(String username) {
        this.username =new SimpleStringProperty(username);
    }
    //public void setadd1(String place) { this.add1 =new SimpleStringProperty(add1); }
    public void setPlace(String place) {
        this.place =new SimpleStringProperty(place);
    }
    public void setemail(String email) {
        this.place =new SimpleStringProperty(email);
    }
    public void setadmin(String admin) {
        this.place =new SimpleStringProperty(admin);
    }
    public void setDateadded(String dateadded) {
        this.place =new SimpleStringProperty(dateadded);
    }


}
