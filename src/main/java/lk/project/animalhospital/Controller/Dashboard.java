package lk.project.animalhospital.Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class Dashboard {

    @FXML
    private Button appointments;

    @FXML
    private Button emp;

    @FXML
    private Button invent;

    @FXML
    private Button owner;

    @FXML
    private Button paymnt;

    @FXML
    private Button pet;

    @FXML
    private Button sal;

    @FXML
    private Button service;


    @FXML
    private Button Invoice;

    @FXML
    private Button Shedule;

    @FXML
    private Button SmS;

    @FXML
    private Button extrsbtn111;


    @FXML
    private ImageView image;


    @FXML
    void SMSAction(ActionEvent event) {
        try {
            Stage stage = (Stage) Shedule.getScene().getWindow();
            Scene scene =new Scene(FXMLLoader.load(getClass().getResource("/view/Sms.fxml")));
            scene.getStylesheets().add(getClass().getResource("/Css/Login.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Pets & Vets Animal Hospital");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void ShedulebtnAction(ActionEvent event) {
        try {
            Stage stage = (Stage) Shedule.getScene().getWindow();
            Scene scene =new Scene(FXMLLoader.load(getClass().getResource("/view/Shedule.fxml")));
            scene.getStylesheets().add(getClass().getResource("/Css/Login.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Pets & Vets Animal Hospital");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    void invoiceAction(ActionEvent event) {
        try {
            Stage stage = (Stage) Invoice.getScene().getWindow();
            Scene scene =new Scene(FXMLLoader.load(getClass().getResource("/view/Invoice.fxml")));
            scene.getStylesheets().add(getClass().getResource("/Css/Login.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Pets & Vets Animal Hospital");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    public void initialize() {
        Image loginImage = new Image(getClass().getResourceAsStream("/images/dashboard.png"));
        image.setImage(loginImage);



    }

    @FXML
    void appointmentsbtn(ActionEvent event) {
        try {
            Stage stage = (Stage) appointments.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/Appointments.fxml")));
            scene.getStylesheets().add(getClass().getResource("/Css/Login.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Pets & Vets Animal Hospital");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void empbtn(ActionEvent event) {
        try {
            Stage stage = (Stage) pet.getScene().getWindow();
            Scene scene =new Scene(FXMLLoader.load(getClass().getResource("/view/Employee.fxml")));
            scene.getStylesheets().add(getClass().getResource("/Css/Login.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Pets & Vets Animal Hospital");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void inventorybtn(ActionEvent event) { //this is medicine button in dashboard
        try {
            Stage stage = (Stage) invent.getScene().getWindow();
            Scene scene =new Scene(FXMLLoader.load(getClass().getResource("/view/Medicine.fxml")));
            scene.getStylesheets().add(getClass().getResource("/Css/Login.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Pets & Vets Animal Hospital");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void ownerbtn(ActionEvent event) {
        try {
            Stage stage = (Stage) pet.getScene().getWindow();
            Scene scene =new Scene(FXMLLoader.load(getClass().getResource("/view/Owner.fxml.")));
            scene.getStylesheets().add(getClass().getResource("/Css/Login.css").toExternalForm());
            stage.setScene(scene);            stage.setTitle("Pets & Vets Animal Hospital");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void paymentsbtn(ActionEvent event) {
        try {
            Stage stage = (Stage) paymnt.getScene().getWindow();
            Scene scene =new Scene(FXMLLoader.load(getClass().getResource("/view/Payment.fxml")));
            scene.getStylesheets().add(getClass().getResource("/Css/Login.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Pets & Vets Animal Hospital");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void petbtn(ActionEvent event)  {
        try {
            Stage stage = (Stage) pet.getScene().getWindow();
            Scene scene =new Scene(FXMLLoader.load(getClass().getResource("/view/pet.fxml")));
            scene.getStylesheets().add(getClass().getResource("/Css/Login.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Pets & Vets Animal Hospital");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void salarybtn(ActionEvent event) {
        try {
            Stage stage = (Stage) pet.getScene().getWindow();
            Scene scene =new Scene(FXMLLoader.load(getClass().getResource("/view/Salary.fxml")));
            scene.getStylesheets().add(getClass().getResource("/Css/Login.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Pets & Vets Animal Hospital");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void servicebtn(ActionEvent event){
        try {
            Stage stage = (Stage) service.getScene().getWindow();
            Scene scene =new Scene(FXMLLoader.load(getClass().getResource("/view/service.fxml")));
            scene.getStylesheets().add(getClass().getResource("/Css/Login.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Pets & Vets Animal Hospital");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

