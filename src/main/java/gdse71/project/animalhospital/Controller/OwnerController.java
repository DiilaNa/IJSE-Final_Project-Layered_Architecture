package gdse71.project.animalhospital.Controller;

import gdse71.project.animalhospital.dto.Ownerdto;
import gdse71.project.animalhospital.dto.PetTm.OwnerTM;
import gdse71.project.animalhospital.model.OwnerModel;
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
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class OwnerController implements Initializable {


    @FXML
    private TextField address;

    @FXML
    private Button backbtn;

    @FXML
    private Button deletebtn;

    @FXML
    private TextField mail;

    @FXML
    private TextField name;

    @FXML
    private TableView<OwnerTM> table;

    @FXML
    private TableColumn<OwnerTM,String> tableOmail;

    @FXML
    private TableColumn<OwnerTM,String> tableOname;

    @FXML
    private TableColumn<OwnerTM,String> tableOwnerAddrees;

    @FXML
    private TableColumn<OwnerTM,String> tableownerID;

    @FXML
    private Button updatebtn;

    @FXML
    private ImageView imagefromscenebuilderID;

    @FXML
    private Label ownerIDS;

    @FXML
    private Button reset;

    OwnerModel ownerModel = new OwnerModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image loginImage = new Image(getClass().getResourceAsStream("/images/ALL PET.png"));
        imagefromscenebuilderID.setImage(loginImage);

        tableownerID.setCellValueFactory(new PropertyValueFactory<>("ownerId"));
        tableOname.setCellValueFactory(new PropertyValueFactory<>("ownerName"));
        tableOwnerAddrees.setCellValueFactory(new PropertyValueFactory<>("ownerAddress"));
        tableOmail.setCellValueFactory(new PropertyValueFactory<>("ownerMail"));



        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load owner id").show();
        }

    }

    private void loadTableData() throws SQLException, ClassNotFoundException {

        ArrayList<Ownerdto> ownerdtos = ownerModel.getAllOwner();
        ObservableList<OwnerTM> ownerTMS = FXCollections.observableArrayList();

        for (Ownerdto ownerdto : ownerdtos) {
            OwnerTM ownerTM = new OwnerTM(
                    ownerdto.getOwnerId(),
                    ownerdto.getOwnerName(),
                    ownerdto.getOwnerAddress(),
                    ownerdto.getOwnerMail()
            );
            ownerTMS.add(ownerTM);
        }

        table.setItems(ownerTMS);
    }
    private void refreshPage() throws SQLException, ClassNotFoundException {

        loadTableData();

        updatebtn.setDisable(true);
        deletebtn.setDisable(true);

        ownerIDS.setText("");
        name.setText("");
        address.setText("");
        mail.setText("");
    }

    @FXML
    void TABLEACTION(MouseEvent event) {
        OwnerTM ownerTM = table.getSelectionModel().getSelectedItem();

        if (ownerTM != null) {
            ownerIDS.setText(ownerTM.getOwnerId());
            name.setText(ownerTM.getOwnerName());
            address.setText(ownerTM.getOwnerAddress());
            mail.setText(ownerTM.getOwnerMail());

            deletebtn.setDisable(false);
            updatebtn.setDisable(false);
        }


    }

    @FXML
    void backbtnAction(ActionEvent event) {
        try {
            Stage stage = (Stage) backbtn.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"))));
            stage.setTitle("Pets & Vets Animal Hospital");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void deletebtnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String ownerId = ownerIDS.getText();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = ownerModel.deleteOwner(ownerId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Owner deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Owner...!").show();
            }
        }

    }

    @FXML
    void savebtnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String ownerId = ownerIDS.getText();
        String ownerName = name.getText();
        String ownerAddress = address.getText();
        String ownerMail = mail.getText();

        // Reset styles
        name.setStyle(name.getStyle() + ";-fx-border-color: #7367F0;");
        address.setStyle(address.getStyle() + ";-fx-border-color: #7367F0;");
        mail.setStyle(mail.getStyle() + ";-fx-border-color: #7367F0;");

        // Pattern adjustments
        String namePattern =  "^[a-zA-Z0-9, -]+$";
        String addressPattern = "^[a-zA-Z0-9, -]+$";
        String mailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        boolean isValidName = ownerName.matches(namePattern);
        boolean isValidAddress = ownerAddress.matches(addressPattern);
        boolean isValidMail = ownerMail.matches(mailPattern);

        if (!isValidName) {
            name.setStyle(name.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid name: " + ownerName);
        }
        if (!isValidAddress) {
            address.setStyle(address.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid address: " + ownerAddress);
        }
        if (!isValidMail) {
            mail.setStyle(mail.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid mail: " + ownerMail);
        }

        if (isValidName && isValidAddress && isValidMail) {
            Ownerdto ownerdto = new Ownerdto(
                    ownerId,
                    ownerName,
                    ownerAddress,
                    ownerMail
            );

            boolean isSaved = ownerModel.saveOwner(ownerdto);
            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Owner Updated...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Update Owner...!").show();
            }
        }



    }

    @FXML
    void updatebtnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String ownerId = ownerIDS.getText();
        String ownerName = name.getText();
        String ownerAddress = address.getText();
        String ownerMail = mail.getText();

        // Reset styles
        name.setStyle(name.getStyle() + ";-fx-border-color: #7367F0;");
        address.setStyle(address.getStyle() + ";-fx-border-color: #7367F0;");
        mail.setStyle(mail.getStyle() + ";-fx-border-color: #7367F0;");

        // Pattern adjustments
        String namePattern ="^[a-zA-Z0-9, -]+$";
        String addressPattern = "^[a-zA-Z0-9, -]+$"; // Allow spaces, commas, hyphens
        String mailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        boolean isValidName = ownerName.matches(namePattern);
        boolean isValidAddress = ownerAddress.matches(addressPattern);
        boolean isValidMail = ownerMail.matches(mailPattern);

        if (!isValidName) {
            name.setStyle(name.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid name: " + ownerName);
        }
        if (!isValidAddress) {
            address.setStyle(address.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid address: " + ownerAddress);
        }
        if (!isValidMail) {
            mail.setStyle(mail.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid mail: " + ownerMail);
        }

        if (isValidName && isValidAddress && isValidMail) {
            Ownerdto ownerdto = new Ownerdto(
                    ownerId,
                    ownerName,
                    ownerAddress,
                    ownerMail
            );

            boolean isSaved = ownerModel.updateOwner(ownerdto);
            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Owner Updated...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Update Owner...!").show();
            }
        }
    }
    @FXML
    void resetbtnAction(ActionEvent event) {
        name.setText("");
        address.setText("");
        mail.setText("");

    }

}

