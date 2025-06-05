package lk.project.animalhospital.Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {

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
    private Button button;

    @FXML
    private Button SmS;

    @FXML
    private AnchorPane anchorPane;

    private void login(String fxmlPath) throws IOException {
        Stage stage = (Stage) button.getScene().getWindow();
        Scene scene =new Scene(FXMLLoader.load(getClass().getResource(fxmlPath)));
        scene.getStylesheets().add(getClass().getResource("/Css/Login.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Pets & Vets Animal Hospital");
        stage.show();

    }


    @FXML
    void SMSAction(ActionEvent event) {
        try {
            login("/view/Sms.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void ShedulebtnAction(ActionEvent event) {
        try {
           login("/view/Shedule.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void invoiceAction(ActionEvent event) {
        try {
           login("/view/Invoice.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void initialize() {

    }

    @FXML
    void appointmentsbtn(ActionEvent event) {
        try {
           login("/view/Appointments.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void empbtn(ActionEvent event) {
        try {
           login("/view/Employee.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void inventorybtn(ActionEvent event) { //this is medicine button in dashboard
        try {
            login("/view/Medicine.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void ownerbtn(ActionEvent event) {
        try {
           login("/view/Owner.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void paymentsbtn(ActionEvent event) {
        try {
            login("/view/Payment.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void petbtn(ActionEvent event)  {
        try {
            login("/view/pet.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void salarybtn(ActionEvent event) {
        try {
            login("/view/Salary.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void servicebtn(ActionEvent event){
        try {
            login("/view/service.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

