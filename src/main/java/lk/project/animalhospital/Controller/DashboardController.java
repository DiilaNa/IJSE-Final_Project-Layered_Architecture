package lk.project.animalhospital.Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Button appointments;

    @FXML
    private Button emp;

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

    @FXML
    void SMSAction(ActionEvent event) {
        navigateTo("/view/Sms.fxml");
    }

    @FXML
    void ShedulebtnAction(ActionEvent event) {
        navigateTo("/view/Shedule.fxml");
    }

    @FXML
    void invoiceAction(ActionEvent event) {
        navigateTo("/view/Invoice.fxml");
    }

    public void refreshPage() {
        navigateTo("/view/Appointments.fxml");
    }

    @FXML
    void appointmentsbtn(ActionEvent event) {
        navigateTo("/view/Appointments.fxml");
    }

    @FXML
    void empbtn(ActionEvent event) {
        navigateTo("/view/Employee.fxml");
    }

    @FXML
    void MedicineBTN(ActionEvent event) {
        navigateTo("/view/Medicine.fxml");
    }
    @FXML
    void ownerbtn(ActionEvent event) {
        navigateTo("/view/Owner.fxml");
    }

    @FXML
    void paymentsbtn(ActionEvent event) {
        navigateTo("/view/Payment.fxml");
    }

    @FXML
    void petbtn(ActionEvent event)  {
        navigateTo("/view/pet.fxml");
    }

    @FXML
    void salarybtn(ActionEvent event) {
        navigateTo("/view/Salary.fxml");
    }

    @FXML
    void servicebtn(ActionEvent event){
        navigateTo("/view/service.fxml");
    }

    private void navigateTo(String fxmlPath) {
        try {
            anchorPane.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            load.getStylesheets().add(getClass().getResource("/Css/Login.css").toExternalForm());
            load.prefWidthProperty().bind(anchorPane.widthProperty());
            load.prefHeightProperty().bind(anchorPane.heightProperty());
            anchorPane.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load page!").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshPage();
    }
}

