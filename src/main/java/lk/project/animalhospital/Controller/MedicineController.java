package lk.project.animalhospital.Controller;

import lk.project.animalhospital.bo.BOFactory;
import lk.project.animalhospital.bo.Custom.MedicineBO;
import lk.project.animalhospital.model.Med_detailDto;
import lk.project.animalhospital.model.MedicineDto;
import lk.project.animalhospital.model.PetTm.MedicineTM;
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
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MedicineController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image loginImage = new Image(getClass().getResourceAsStream("/images/Medicinee.png"));
        image.setImage(loginImage);

        tablemediID.setCellValueFactory(new PropertyValueFactory<>("MedicineId"));
        tableMedNAme.setCellValueFactory(new PropertyValueFactory<>("MedicineName"));
        tableCondition.setCellValueFactory(new PropertyValueFactory<>("MedicineCondition"));
        tableWeight.setCellValueFactory(new PropertyValueFactory<>("MedicineWeight"));
        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load id").show();
        }

    }

    @FXML
    private TextField Mcoondition;

    @FXML
    private Label Mid;

    @FXML
    private TextField Mname;

    @FXML
    private TextField Mweight;

    @FXML
    private Button back;

    @FXML
    private Button delete;

    @FXML
    private ImageView image;

    @FXML
    private Button save;

    @FXML
    private TableView<MedicineTM> table;

    @FXML
    private TableColumn<MedicineTM, String> tableCondition;


    @FXML
    private TableColumn<MedicineTM, String> tableMedNAme;


    @FXML
    private TableColumn<MedicineTM, Double> tableWeight;

    @FXML
    private TableColumn<MedicineTM, String> tablemediID;

    @FXML
    private Button update;

    @FXML
    private Button reset;

    @FXML
    private ComboBox<String> petId;

    @FXML
    private Label petNname;


    MedicineBO medicineBO = (MedicineBO) BOFactory.getInstance().getBO(BOFactory.BOType.MEDICINE);

    @FXML
    void backAction(ActionEvent event) {
        try {
            Stage stage = (Stage) back.getScene().getWindow();
            Scene scene =new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard.fxml")));
            scene.getStylesheets().add(getClass().getResource("/Css/Login.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Pets & Vets Animal Hospital");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void deleteAction(ActionEvent event) {
        String selectedMedicine = Mid.getText();
        String selectedPetIDValue = petId.getSelectionModel().getSelectedItem();

        if (selectedMedicine == null || selectedPetIDValue == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a medicine id to delete.").show();
            return;
        }

        try {
            boolean isDeleted = medicineBO.deleteMedicine(selectedMedicine,selectedPetIDValue);

            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Medicine deleted successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete medicine ko.").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An error occurred while deleting the medicine.").show();
        }
    }

    @FXML
    void saveAction(ActionEvent event) {
            String medId = Mid.getText();
            String medName = Mname.getText();
            String medCondition = Mcoondition.getText();
            Double mWeight = Double.valueOf(Mweight.getText());
            String petids = petId.getValue();

            String idPattern = "^[A-Za-z0-9]+$";
            String weightPattern = "^[0-9]*\\.?[0-9]+$"; // Accepts positive numbers with optional decimal
            String quantityPattern = "^[1-9][0-9]*$"; // Accepts positive integers only

            boolean isValidID = medId.matches(idPattern);
            boolean isValidWeight = Mweight.getText().matches(weightPattern);

            if (!isValidID) {
                Mid.setStyle(Mid.getStyle() + ";-fx-border-color: red;");
                System.out.println("Invalid Medicine ID: " + medId);
            }
            if (!isValidWeight) {
                Mweight.setStyle(Mweight.getStyle() + ";-fx-border-color: red;");
                System.out.println("Invalid Weight: " + Mweight.getText());
            }

            if (isValidID  && isValidWeight ) {
                MedicineDto medicineDto = new MedicineDto(medId, medName, medCondition, mWeight);
                Med_detailDto med_detailDto = new Med_detailDto(medId,petids);

                List<MedicineDto> medicineDtos = new ArrayList<>();
                medicineDtos.add(medicineDto);

                List<Med_detailDto> medDetailDtos = new ArrayList<>();
                medDetailDtos.add(med_detailDto);

                try {
                    boolean isSaved = medicineBO.saveMedicine(medicineDtos, medDetailDtos); // Pass the lists
                    if (isSaved) {
                        refreshPage();
                        new Alert(Alert.AlertType.INFORMATION, "Medicine saved successfully!").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to save medicine.").show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    new Alert(Alert.AlertType.ERROR, "An error occurred while saving the medicine.").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Please fix the highlighted errors before saving.").show();
            }
    }
    @FXML
    void tableMouseClicked(MouseEvent event) throws Exception {
        MedicineTM  medicineTM = table.getSelectionModel().getSelectedItem();

        if (medicineTM != null) {
            Mid.setText(medicineTM.getMedicineId());
            Mname.setText(medicineTM.getMedicineName());
            Mcoondition.setText(medicineTM.getMedicineCondition());
            Mweight.setText(String.valueOf(medicineTM.getMedicineWeight()));

            String meid = Mid.getText();
            String medgetID = medicineBO.getPetID(meid);
            petId.setValue(medgetID);
            save.setDisable(false);
            delete.setDisable(false);
            update.setDisable(false);
        }
    }
    @FXML
    void updateAction(ActionEvent event) {
        String medId = Mid.getText();
        String medName = Mname.getText();
        String medCondition = Mcoondition.getText();
        Double mWeight = Double.valueOf(Mweight.getText());

        // Regex patterns
        String idPattern = "^[A-Za-z0-9]+$";
        String weightPattern = "^[0-9]*\\.?[0-9]+$"; // Accepts positive numbers with optional decimal
        String quantityPattern = "^[1-9][0-9]*$"; // Accepts positive integers only

        boolean isValidID = medId.matches(idPattern);
        boolean isValidWeight = Mweight.getText().matches(weightPattern);

        if (!isValidID) {
            Mid.setStyle(Mid.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid Medicine ID: " + medId);
        }
        if (!isValidWeight) {
            Mweight.setStyle(Mweight.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid Weight: " + Mweight.getText());
        }

        if (isValidID && isValidWeight ) {
            MedicineDto medicineDto = new MedicineDto(medId, medName, medCondition,mWeight);

            try {
                boolean isSaved = medicineBO.updateMedicine(medicineDto);
                if (isSaved) {
                    refreshPage();
                    new Alert(Alert.AlertType.INFORMATION, "Medicine updated successfully!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to update medicine.").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "An error occurred while updating the medicine.").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Please fix the highlighted errors before updating.").show();
        }

    }
    private void refreshPage() throws Exception {
        loadTableData();
        getNextMedId();
        petNname.setText("");
        loadPetIds();
        save.setDisable(false);
        update.setDisable(true);
        delete.setDisable(true);
        Mname.setText("");
        Mcoondition.setText("");
        Mweight.setText("");

    }
    private void loadTableData() throws Exception {

        ArrayList<MedicineDto> medicineDtos = medicineBO.getALLMedicine();
        ObservableList<MedicineTM> medicineTMS = FXCollections.observableArrayList();

        for (MedicineDto medicineDto: medicineDtos) {
            MedicineTM medicineTM = new MedicineTM(
                    medicineDto.getMedicineId(),
                    medicineDto.getMedicineName(),
                    medicineDto.getMedicineCondition(),
                    medicineDto.getMedicineWeight()
            );
            medicineTMS.add(medicineTM);
        }

        table.setItems(medicineTMS);
    }
    public void getNextMedId() throws Exception {
        String NextMedID = medicineBO.getNextMedID();
        Mid.setText(NextMedID);
    }
    @FXML
    void reseTAction(ActionEvent event) throws Exception {
        getNextMedId();
        Mname.setText("");
        Mcoondition.setText("");
        Mweight.setText("");
        loadPetIds();
        delete.setDisable(true);
        petNname.setText("");
    }
    private void loadPetIds() throws Exception {
        petId.getItems().clear();
        ArrayList<String> data = medicineBO.getMedIdComboBox();
        petId.getItems().addAll(data);
    }
    @FXML
    void petIDAction(ActionEvent event) throws SQLException, ClassNotFoundException {
            String petID = petId.getValue();
            if (petID != null) {
                try {
                    String petName = medicineBO.getPetName(petID);
                    petNname.setText(petName != null ? petName : "No name found");
                } catch (Exception e) {
                    e.printStackTrace();
                    new Alert(Alert.AlertType.ERROR, "Error fetching pet name: " + e.getMessage()).show();
                }
            }
        }

    }
