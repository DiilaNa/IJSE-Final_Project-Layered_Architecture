package gdse71.project.animalhospital.Controller;

import gdse71.project.animalhospital.dto.PaymentDto;
import gdse71.project.animalhospital.dto.PetTm.InvoiceTM;
import gdse71.project.animalhospital.dto.PetTm.PaymentTM;
import gdse71.project.animalhospital.model.PaymentModel;
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
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    PaymentModel paymentModel = new PaymentModel();

    @FXML
    void bakAction(ActionEvent event) {
        try {
            Stage stage = (Stage) back.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"))));
            stage.setTitle("Pets & Vets Animal Hospital");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void deleteAction(ActionEvent event) {
        PaymentTM selected = table.getSelectionModel().getSelectedItem();

        if (selected == null) {
            new Alert(Alert.AlertType.WARNING, "Please select an id to delete.").show();
            return;
        }

        try {
            boolean isDeleted = paymentModel.delete(selected.getPaymentId());

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

    private void refreshPage() throws SQLException, ClassNotFoundException {

        loadTableData();
        delete.setDisable(true);

        payID.setText("");
        payDate.setText("");
        payTime.setText("");
        payMethod.setText("");
    }
    private void loadTableData() throws SQLException, ClassNotFoundException {

        ArrayList<PaymentDto> paymentDtos = paymentModel.getAll();
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
