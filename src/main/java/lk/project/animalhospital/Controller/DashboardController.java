package lk.project.animalhospital.Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView image;

    @FXML
    void SMSAction(ActionEvent event) {
        navigateTo("/view/Sms.fxml");
    }

    @FXML
    void ScheduleBtnAction(ActionEvent event) {
        navigateTo("/view/schedule.fxml");
    }

    @FXML
    void invoiceAction(ActionEvent event) {
        navigateTo("/view/Invoice.fxml");
    }

    public void refreshPage() {
        navigateTo("/view/Appointments.fxml");
    }

    @FXML
    void appointmentsBtn(ActionEvent event) {
        navigateTo("/view/Appointments.fxml");
    }

    @FXML
    void employeeBtn(ActionEvent event) {
        navigateTo("/view/Employee.fxml");
    }

    @FXML
    void MedicineBTN(ActionEvent event) {
        navigateTo("/view/Medicine.fxml");
    }
    @FXML
    void ownerBtn(ActionEvent event) {
        navigateTo("/view/Owner.fxml");
    }

    @FXML
    void paymentsBtn(ActionEvent event) {
        navigateTo("/view/Payment.fxml");
    }

    @FXML
    void petBtn(ActionEvent event)  {
        navigateTo("/view/pet.fxml");
    }

    @FXML
    void salaryBtn(ActionEvent event) {
        navigateTo("/view/Salary.fxml");
    }

    @FXML
    void serviceBtn(ActionEvent event){
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
        Image loginImage = new Image(getClass().getResourceAsStream("/images/Pets & Vets Logo.png"));
        image.setImage(loginImage);
    }
}

