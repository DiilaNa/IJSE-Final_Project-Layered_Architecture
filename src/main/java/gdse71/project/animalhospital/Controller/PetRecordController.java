package gdse71.project.animalhospital.Controller;

import gdse71.project.animalhospital.db.DBConnection;
import gdse71.project.animalhospital.dto.PetRecorddto;
import gdse71.project.animalhospital.dto.PetTm.PetRecordTM;
import gdse71.project.animalhospital.model.PetRecordModel;
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
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PetRecordController implements Initializable {

    @FXML
    private Button back;

    @FXML
    private Button delete;

    @FXML
    private TextField desc;

    @FXML
    private Button grn;

    @FXML
    private ImageView image;

    @FXML
    private ComboBox<String> prtID;

    @FXML
    private TextField recID;

    @FXML
    private Button save;

    @FXML
    private TextField status;

    @FXML
    private TableView<PetRecordTM> table;

    @FXML
    private TableColumn<PetRecordTM, Double> tableWeight;

    @FXML
    private TableColumn<PetRecordTM, String> tablepetid;

    @FXML
    private TableColumn<PetRecordTM, String> tablerecid;

    @FXML
    private TableColumn<PetRecordTM, String> tablestatus;

    @FXML
    private TableColumn<PetRecordTM, String> tableDescription;

    @FXML
    private Button update;

    @FXML
    private TextField weight;

    @FXML
    private Button reset;

    PetRecordModel petRecordModel = new PetRecordModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image loginImage = new Image(getClass().getResourceAsStream("/images/ALL PET.png"));
        image.setImage(loginImage);

        tablerecid.setCellValueFactory(new PropertyValueFactory<>("recordId"));
        tableDescription.setCellValueFactory(new PropertyValueFactory<>("status"));
        tablestatus.setCellValueFactory(new PropertyValueFactory<>("description"));
        tableWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        tablepetid.setCellValueFactory(new PropertyValueFactory<>("petID"));

        try {
            refreshPage();
            loadPetIds(); // Call this method to populate ComboBox during initialization
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void MouseClicked(MouseEvent event) {
        PetRecordTM petRecordTM = table.getSelectionModel().getSelectedItem();
        if (petRecordTM != null) {
            recID.setText(petRecordTM.getRecordId());
            desc.setText(petRecordTM.getDescription());
            status.setText(petRecordTM.getStatus());
            weight.setText(String.valueOf(petRecordTM.getWeight()));
            prtID.setValue(petRecordTM.getPetID());

            save.setDisable(false);

            delete.setDisable(false);
            update.setDisable(false);
        }

    }

    @FXML
    void backAction(ActionEvent event) {
        try {
            Stage stage = (Stage) back.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/pet.fxml"))));
            stage.setTitle("Pets & Vets Animal Hospital");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void deleteAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String Id = recID.getText();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = petRecordModel.deletePetRec(Id);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Pet  Record deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Record...!").show();
            }
        }
    }

    @FXML
    void grnAction(ActionEvent event) {
        PetRecordTM petRecordTM = table.getSelectionModel().getSelectedItem();

        if (petRecordTM == null) {
            return;
        }
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/Report/petRecord.jrxml"));
            Connection connection = DBConnection.getInstance().getConnection();
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("P_Date", ""); // or an actual date if needed
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load  Report").show();
            e.printStackTrace();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "DB eror").show();
        }

    }

    @FXML
    void prtIDACTION(ActionEvent event) throws SQLException {

    }

    @FXML
    void saveAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String recId = recID.getText();
        String DESC = desc.getText();
        String Status = status.getText();
        Double Weight = Double.parseDouble(weight.getText());
        String petId = prtID.getValue();

        recID.setStyle(recID.getStyle() + ";-fx-border-color: #7367F0;");
        status.setStyle(status.getStyle() + ";-fx-border-color: #7367F0;");
        weight.setStyle(weight.getStyle() + ";-fx-border-color: #7367F0;");

        String Pattern1 = "^[a-zA-Z0-9-]+$";
        String Pattern3 = "^[a-zA-Z0-9, -]+$";


        boolean isVali1 = recId.matches(Pattern1);
        boolean isValid3 = Status.matches(Pattern3);


        if (!isVali1) {
            recID.setStyle(recID.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid record ID.");
        }

        if (!isValid3) {
            status.setStyle(status.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid owner ID.");
        }



        if (isVali1 && isValid3 ) {
            PetRecorddto petRecorddto = new PetRecorddto(
                    recId,
                    DESC,
                    Status,
                    Weight,
                    petId
            );

            boolean isSaved = petRecordModel.savePetRec(petRecorddto);
            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Pet Record saved...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save Pet Reccord...!").show();
            }
        }

    }

    @FXML
    void updateAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String recId = recID.getText();
        String DESC = desc.getText();
        String Status = status.getText();
// Parse the weight as a Double, assuming it's a number
        Double Weight = Double.parseDouble(weight.getText());

        // Get selected pet ID from ComboBox
        String petId = prtID.getValue();

        recID.setStyle(recID.getStyle() + ";-fx-border-color: #7367F0;");
        status.setStyle(status.getStyle() + ";-fx-border-color: #7367F0;");
        weight.setStyle(weight.getStyle() + ";-fx-border-color: #7367F0;");



        String Pattern1 = "^[a-zA-Z0-9-]+$"; // Matches alphabetic characters and spaces
        String Pattern3 = "^[a-zA-Z0-9, -]+$";  // Matches integer or decimal with two decimal places


        boolean isVali1 = recId.matches(Pattern1);
        boolean isValid3 = Status.matches(Pattern3);


        if (!isVali1) {
            recID.setStyle(recID.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid record ID.");
        }

        if (!isValid3) {
            status.setStyle(status.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid owner ID.");
        }



        if (isVali1 && isValid3 ) {
            PetRecorddto petRecorddto = new PetRecorddto(
                    recId,
                    DESC,
                    Status,
                    Weight,
                    petId
            );

            boolean isSaved = petRecordModel.updatePetRec(petRecorddto);
            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Pet Record Updated...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update Pet Reccord...!").show();
            }
        }

    }
    private void loadPetIds() throws SQLException {

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet rs = connection.createStatement().executeQuery("SELECT pet_id FROM pet");
            ObservableList<String> data = FXCollections.observableArrayList();

            while (rs.next()) {
                data.add(rs.getString("pet_id"));
            }
            prtID.setItems(data);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadTableData() throws SQLException, ClassNotFoundException {

        ArrayList<PetRecorddto> petRecorddtos = petRecordModel.getAllpetRec();
        ObservableList<PetRecordTM> petRecordTMS = FXCollections.observableArrayList();

        for (PetRecorddto petRecorddto : petRecorddtos) {
            PetRecordTM petRecordTM = new PetRecordTM(
                    petRecorddto.getRecordId(),
                    petRecorddto.getStatus(),
                    petRecorddto.getDescription(),
                    petRecorddto.getWeight(),
                    petRecorddto.getPetID()
            );
            petRecordTMS.add(petRecordTM);
        }

        table.setItems(petRecordTMS);
    }
    private void refreshPage() throws SQLException, ClassNotFoundException {

        loadTableData();

        save.setDisable(false);

        update.setDisable(true);
        delete.setDisable(true);

        recID.setText("");
        desc.setText("");
        status.setText("");
        weight.setText("");

    }

    @FXML
    void reserAction(ActionEvent event) {
        recID.setText("");
        desc.setText("");
        status.setText("");
        weight.setText("");
        try {
            loadPetIds();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
