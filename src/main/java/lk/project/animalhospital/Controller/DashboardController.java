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

    public void refreshPage() {
        navigateTo("/view/Appointments.fxml");
    }

    @FXML
    void appointmentsbtn(ActionEvent event) {
        navigateTo("/view/Appointments.fxml");
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

