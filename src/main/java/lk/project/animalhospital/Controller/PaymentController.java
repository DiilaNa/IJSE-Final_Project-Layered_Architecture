package lk.project.animalhospital.Controller;

import lk.project.animalhospital.bo.BOFactory;
import lk.project.animalhospital.bo.Custom.PaymentBO;
import lk.project.animalhospital.model.PaymentDto;
import lk.project.animalhospital.model.PetTm.PaymentTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image loginImage = new Image(getClass().getResourceAsStream("/images/ALL PET.png"));
        image.setImage(loginImage);

        tablePayID.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        tableTime.setCellValueFactory(new PropertyValueFactory<>("paymentTime"));
        tableMethod.setCellValueFactory(new PropertyValueFactory<>("paymentMethodd"));
        tableDAte.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load id").show();
        }
    }

    @FXML
    private Button back;

    @FXML
    private Button delete;

    @FXML
    private ImageView image;

    @FXML
    private TextField payDate;

    @FXML
    private TextField payID;


    @FXML
    private TextField payTime;

    @FXML
    private TableView<PaymentTM> table;

    @FXML
    private TableColumn<PaymentTM, String> tableDAte;

    @FXML
    private TableColumn<PaymentTM, String> tableMethod;

    @FXML
    private TableColumn<PaymentTM, String> tablePayID;

    @FXML
    private TableColumn<PaymentTM, String> tableTime;

    @FXML
    private TextField payMethod;

   PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOType.PAYMENT);

    @FXML
    void deleteAction(ActionEvent event) {
        PaymentTM selected = table.getSelectionModel().getSelectedItem();

        if (selected == null) {
            new Alert(Alert.AlertType.WARNING, "Please select an id to delete.").show();
            return;
        }
        try {
            boolean isDeleted = paymentBO.deletePayment(selected.getPaymentId());
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, " deleted successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete .").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An error occurred while deleting.").show();
        }

    }

    @FXML
    void tableAction(MouseEvent event) {
        PaymentTM paymentTM = table.getSelectionModel().getSelectedItem();
        if (paymentTM != null) {
            payID.setText(paymentTM.getPaymentId());
            payTime.setText(paymentTM.getPaymentTime());
            payMethod.setText(paymentTM.getPaymentMethodd());
            payDate.setText(paymentTM.getPaymentDate());

            delete.setDisable(false);
        }


    }

    private void refreshPage() throws Exception {

        loadTableData();
        delete.setDisable(true);

        payID.setText("");
        payDate.setText("");
        payTime.setText("");
        payMethod.setText("");
    }
    private void loadTableData() throws Exception {

        ArrayList<PaymentDto> paymentDtos = paymentBO.getAllPayment();
        ObservableList<PaymentTM> paymentTMS = FXCollections.observableArrayList();

        for (PaymentDto paymentDto : paymentDtos) {
            PaymentTM paymentTM = new PaymentTM(
                    paymentDto.getPaymentId(),
                    paymentDto.getPaymentDate().toString(),
                    paymentDto.getPaymentMethodd(),
                    paymentDto.getPaymentTime()
            );
            paymentTMS.add(paymentTM);
        }

        table.setItems(paymentTMS);
    }

}
