package gdse71.project.animalhospital.Controller;


import gdse71.project.animalhospital.dto.PetTm.PetTM;
import gdse71.project.animalhospital.dto.Petdto;
import gdse71.project.animalhospital.model.PetModel;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class Pet  implements Initializable {


    @FXML
    private TableColumn<PetTM, String> ColoumnBreed;

    @FXML
    private TableColumn<PetTM, String> ColoumnName;

    @FXML
    private TableColumn<PetTM, String> ColoumnOwnerId;

    @FXML
    private TableColumn<PetTM, String> columnId;

    @FXML
    private Button backID;

    @FXML
    private TextField breedtxt;

    @FXML
    private Button delete;

    @FXML
    private Button gr;

    @FXML
    private ImageView image;


    @FXML
    private TextField pname;

    @FXML
    private TableView<PetTM> table;

    @FXML
    private Button update;

    @FXML
    private TextField petTypeTxt;

    @FXML
    private TableColumn<PetTM, String> tablePetType;


    @FXML
    private Label petIds;

    @FXML
    private Button reset;

    @FXML
    private Label ownerIds;

    PetModel petModel = new PetModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image loginImage = new Image(getClass().getResourceAsStream("/images/ALL PET.png"));
        image.setImage(loginImage);

        columnId.setCellValueFactory(new PropertyValueFactory<>("petId"));
        ColoumnName.setCellValueFactory(new PropertyValueFactory<>("petName"));
        ColoumnBreed.setCellValueFactory(new PropertyValueFactory<>("petBreed"));
        ColoumnOwnerId.setCellValueFactory(new PropertyValueFactory<>("petOwnerId"));
        tablePetType.setCellValueFactory(new PropertyValueFactory<>("petType"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to refresh Page").show();
        }
    }
    private void refreshPage() throws SQLException, ClassNotFoundException {
            loadTableData();
            petIds.setText("");
            pname.setText("");
            breedtxt.setText("");
            petTypeTxt.setText("");
            ownerIds.setText("");

    }

    private void loadTableData() throws SQLException, ClassNotFoundException {

        ArrayList<Petdto> petDTOS = petModel.getALLPET();
        ObservableList<PetTM> petTMS = FXCollections.observableArrayList();

        for (Petdto petDTO : petDTOS) {
            PetTM petTM = new PetTM(
                    petDTO.getPetId(),
                    petDTO.getPetName(),
                    petDTO.getPetBreed(),
                    petDTO.getPetOwnerId(),
                    petDTO.getPetType()
            );
            petTMS.add(petTM);
        }

        table.setItems(petTMS);
    }

    @FXML
    void ONmouseClicked(MouseEvent event) {
        PetTM petTM = table.getSelectionModel().getSelectedItem();
        if (petTM != null) {
            petIds.setText(petTM.getPetId());
            pname.setText(petTM.getPetName());
            breedtxt.setText(petTM.getPetBreed());
            ownerIds.setText(petTM.getPetOwnerId());
            petTypeTxt.setText(petTM.getPetType());

        }

    }

    @FXML
    void backIDAction(ActionEvent event) {
        try {
            Stage stage = (Stage) backID.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"))));
            stage.setTitle("Pets & Vets Animal Hospital");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void deleteAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        String petId = petIds.getText();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = petModel.deletePet(petId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Pet deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete pet...!").show();
            }
        }

    }

    @FXML
    void genReport(ActionEvent event)  {
        try {
            Stage stage = (Stage) gr.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/petRecord.fxml"))));
            stage.setTitle("Pets & Vets Animal Hospital");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    void updateAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String petId = petIds.getText();
        String petName = pname.getText();
        String petBreed = breedtxt.getText();
        String ownerId = ownerIds.getText();
        String PETtype = petTypeTxt.getText();

        pname.setStyle(pname.getStyle() + ";-fx-border-color: #7367F0;");
        breedtxt.setStyle(breedtxt.getStyle() + ";-fx-border-color: #7367F0;");
        petTypeTxt.setStyle(petTypeTxt.getStyle() + ";-fx-border-color: #7367F0;");

        String pnamePattern = "^[a-zA-Z\\s-]+$"; // Matches alphabetic characters and spaces
        String breedPattern = "^[a-zA-Z\\s-]+$"; // Assuming breed is also alphabetic characters


        boolean isValidName = petName.matches(pnamePattern);
        boolean isValidBreed = petBreed.matches(breedPattern);

        if (!isValidName) {
            pname.setStyle(pname.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid name.");
        }

        if (!isValidBreed) {
            breedtxt.setStyle(breedtxt.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid breed.");
        }

        if (isValidName && isValidBreed) {
            Petdto petdto = new Petdto(
                    petId,
                    petName,
                    petBreed,
                    ownerId,
                    PETtype
            );

            boolean isSaved = petModel.updatePet(petdto);
            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Pet updated...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update Pet...!").show();
            }
        }

    }
    @FXML
    void resetAction(ActionEvent event) {
        petIds.setText("");
        pname.setText("");
        breedtxt.setText("");
        petTypeTxt.setText("");
        ownerIds.setText("");

    }
}
