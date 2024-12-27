package gdse71.project.animalhospital.Controller;

import gdse71.project.animalhospital.db.DBConnection;
import gdse71.project.animalhospital.dto.Invoicedto;
import gdse71.project.animalhospital.dto.PetTm.InvoiceTM;
import gdse71.project.animalhospital.model.InvoiceModel;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InvoiceController implements Initializable {

    @FXML
    private Button back;

    @FXML
    private Button delete;

    @FXML
    private ImageView image;

    @FXML
    private TextField paymentInvName;

    @FXML
    private Button reset;

    @FXML
    private Button save;

    @FXML
    private Label invNO;

    @FXML
    private ComboBox<String> paymenttID;

    @FXML
    private TableView<InvoiceTM> table;

    @FXML
    private TableColumn<InvoiceTM, Double> tableAmount;

    @FXML
    private TableColumn<InvoiceTM, String> tableInvoiceNo;

    @FXML
    private TableColumn<InvoiceTM, String> tableName;


    @FXML
    private TableColumn<InvoiceTM, String> tablePaymenID;

    @FXML
    private Label defaultPrice;

    @FXML
    private Label servicePrice;

    @FXML
    private Button update;

    InvoiceModel invoiceModel = new InvoiceModel();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image loginImage = new Image(getClass().getResourceAsStream("/images/ALL PET.png"));
        image.setImage(loginImage);

        tableInvoiceNo.setCellValueFactory(new PropertyValueFactory<>("InvoiceNo"));
        tableName.setCellValueFactory(new PropertyValueFactory<>("InvoiceName"));
        tableAmount.setCellValueFactory(new PropertyValueFactory<>("InvoiceAmount"));
        tablePaymenID.setCellValueFactory(new PropertyValueFactory<>("Paymntid"));


        try {
            refreshPage();

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load id").show();
        }
    }


    @FXML
    void backAction(ActionEvent event) {
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
        InvoiceTM selected = table.getSelectionModel().getSelectedItem();

        if (selected == null) {
            new Alert(Alert.AlertType.WARNING, "Please select an id to delete.").show();
            return;
        }

        try {
            boolean isDeleted = invoiceModel.delete(selected.getInvoiceNo());

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
    void saveAction(ActionEvent event) {
        String invoiceNo = invNO.getText();
        String invName = paymentInvName.getText();
        Double invoiceAmount = Double.valueOf(defaultPrice.getText());
        String paymentIDtxt = paymenttID.getValue();


        // Validation patterns
        String idPattern = "^[A-Za-z0-9]+$";

        boolean isValidID = paymentIDtxt.matches(idPattern);


        // Reset styles
        invNO.setStyle("-fx-border-color: none;");
        defaultPrice.setStyle("-fx-border-color: none;");

        // Apply error styles if validation fails
        if (!isValidID) {
            invNO.setStyle(invNO.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid Payment ID: " + paymentIDtxt);
        }


        if (isValidID  && !invoiceNo.isEmpty() && !invName.isEmpty()) {


            Invoicedto invoicedto = new Invoicedto( invoiceNo, invName, invoiceAmount,paymentIDtxt);
            try {
                boolean isSaved = invoiceModel.save(invoicedto);
                if (isSaved) {
                    refreshPage();
                    new Alert(Alert.AlertType.INFORMATION, "Payment Invoice saved successfully!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save payment invoice.").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "An error occurred while saving the payment invoice.").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Please fix the highlighted errors before saving.").show();
        }

    }

    @FXML
    void tableClick(MouseEvent event) {
        InvoiceTM invoiceTM = table.getSelectionModel().getSelectedItem();
        if (invoiceTM != null) {
            paymentInvName.setText(invoiceTM.getInvoiceName());
            defaultPrice.setText(String.valueOf(invoiceTM.getInvoiceAmount()));
            invNO.setText(invoiceTM.getInvoiceNo());
            paymenttID.setValue(invoiceTM.getPaymntid());

            save.setDisable(false);

            delete.setDisable(false);
            update.setDisable(false);
        }
    }

    @FXML
    void updateAction(ActionEvent event) {
        String invoiceNo = invNO.getText();
        String invName = paymentInvName.getText();
        Double invoiceAmount = Double.parseDouble(defaultPrice.getText());
        String paymentIDtxt = paymenttID.getValue();
        // Validation patterns
        String idPattern = "^[A-Za-z0-9]+$";

        boolean isValidID = paymentIDtxt.matches(idPattern);

        invNO.setStyle("-fx-border-color: none;");
        defaultPrice.setStyle("-fx-border-color: none;");

        if (!isValidID) {
            invNO.setStyle(invNO.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid Payment ID: " + paymentIDtxt);
        }

        if (isValidID && !invoiceNo.isEmpty() && !invName.isEmpty()) {
            Invoicedto invoicedto = new Invoicedto(invoiceNo, invName, invoiceAmount,paymentIDtxt);
            try {
                boolean isSaved = invoiceModel.update(invoicedto);
                if (isSaved) {
                    refreshPage();
                    new Alert(Alert.AlertType.INFORMATION, "Payment Invoice updating successfully!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to update payment invoice.").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "An error occurred while updating the payment invoice.").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Please fix the highlighted errors before saving.").show();
        }

    }


    private void refreshPage() throws SQLException, ClassNotFoundException {

        loadTableData();
        loadPayID();
        loadNextInvoiceID();
        loadAptPrice();

        save.setDisable(false);

        update.setDisable(true);
        delete.setDisable(true);

        paymentInvName.setText("");

    }
    private void loadTableData() throws SQLException, ClassNotFoundException {

        ArrayList<Invoicedto> invoicedtos = invoiceModel.getAll();
        ObservableList<InvoiceTM> invoiceTMS = FXCollections.observableArrayList();

        for (Invoicedto invoicedto : invoicedtos) {
            InvoiceTM invoiceTM = new InvoiceTM(
                    invoicedto.getInvoiceNo(),
                    invoicedto.getInvoiceName(),
                    invoicedto.getInvoiceAmount(),
                    invoicedto.getPaymntid()
            );
            invoiceTMS.add(invoiceTM);
        }

        table.setItems(invoiceTMS);
    }
    public void loadNextInvoiceID()  {
        String nextId = invoiceModel.getNextInvoiceId();
        invNO.setText(nextId);

    }
    private void loadPayID() throws SQLException {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet rs = connection.createStatement().executeQuery("SELECT payment_id FROM payment");
            ObservableList<String> data = FXCollections.observableArrayList();

            while (rs.next()) {
                data.add(rs.getString("payment_id"));
            }
            paymenttID.setItems(data);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void loadAptPrice()  {
        Double aptPrice = 5000.00;
        defaultPrice.setText(aptPrice.toString());
    }
    @FXML
    void resetAction(ActionEvent event) {
        try {
            loadAptPrice();
            loadNextInvoiceID();
            loadPayID();
            paymentInvName.setText("");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
