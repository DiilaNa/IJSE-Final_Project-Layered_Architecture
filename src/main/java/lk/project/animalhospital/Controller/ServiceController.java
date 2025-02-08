package lk.project.animalhospital.Controller;

import lk.project.animalhospital.bo.BOFactory;
import lk.project.animalhospital.bo.Custom.ServiceBO;
import lk.project.animalhospital.model.PetTm.ServiceTM;
import lk.project.animalhospital.model.Servicedto;
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

public class ServiceController implements Initializable {


    @FXML
    private ComboBox<String> PETid;

    @FXML
    private Button backId;

    @FXML
    private Button delete;

    @FXML
    private TextField duration;

    @FXML
    private ImageView image;

    @FXML
    private Button save;

    @FXML
    private TableView<ServiceTM> table;

    @FXML
    private TableColumn<ServiceTM, String> tableDuration;

    @FXML
    private TableColumn<ServiceTM, String> tablePetId;

    @FXML
    private TableColumn<ServiceTM, String> tableServiceId;

    @FXML
    private TableColumn<ServiceTM, String> tableServiceName;

    @FXML
    private Button update;

    @FXML
    private TextField serviceType;


    @FXML
    private Button reset;

    @FXML
    private Label serviceID;

    ServiceBO serviceBO = (ServiceBO) BOFactory.getInstance().getBO(BOFactory.BOType.SERVICE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image loginImage = new Image(getClass().getResourceAsStream("/images/ALL PET.png"));
        image.setImage(loginImage);

        tableServiceId.setCellValueFactory(new PropertyValueFactory<>("serviceID"));
        tableServiceName.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        tableDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        tablePetId.setCellValueFactory(new PropertyValueFactory<>("PetIdService"));




        try {
            refreshPage();
            loadPetIds();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void MouseClickAction(MouseEvent event) {
        ServiceTM serviceTM = table.getSelectionModel().getSelectedItem();
        if (serviceTM != null) {
            serviceID.setText(serviceTM.getServiceID());
            serviceType.setText(serviceTM.getServiceName());
            duration.setText(serviceTM.getDuration());
            PETid.setValue(serviceTM.getPetIdService());

            save.setDisable(false);

            delete.setDisable(false);
            update.setDisable(false);
        }

    }

    @FXML
    void backIdAction(ActionEvent event) {
        try {
            Stage stage = (Stage) backId.getScene().getWindow();
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
    void deleteAction(ActionEvent event) throws Exception {
        String Id = serviceID.getText();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = serviceBO.deleteService(Id);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "  Record deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete ...!").show();
            }
        }

    }


    @FXML
    void saveAction(ActionEvent event) throws Exception {
        String servId = serviceID.getText();
        String servName = serviceType.getText();
        String durationValue = duration.getText();
        String petServiceId = PETid.getValue();

        Servicedto servicedto = new Servicedto(
                servId,
                servName,
                durationValue,
                petServiceId
        );
        boolean isSaved = serviceBO.saveService(servicedto);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Pet Record saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save Pet Record...!").show();
        }
    }

    @FXML
    void updateAction(ActionEvent event) throws Exception {
        String servId = serviceID.getText();
        String servName = serviceType.getText();
        String durationValue = duration.getText();
        String petServiceId = PETid.getValue();

        Servicedto servicedto = new Servicedto(
                servId,
                servName,
                durationValue,
                petServiceId
        );
        boolean isSaved = serviceBO.updateService(servicedto);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, " Record updated...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update  Record...!").show();
        }
    }
    private void loadPetIds() throws SQLException, ClassNotFoundException {
            PETid.getItems().clear();
            ArrayList<String>petIDS = serviceBO.getPetIdsComboBox();
            PETid.getItems().addAll(petIDS);
    }
    private void loadTableData() throws Exception {
        try {
            ArrayList<Servicedto> servicedtos = serviceBO.getAllServices();
            ObservableList<ServiceTM> serviceTMS = FXCollections.observableArrayList();

            for (Servicedto servicedto : servicedtos) {
                ServiceTM serviceTM = new ServiceTM(
                        servicedto.getServiceID(),
                        servicedto.getServiceName(),
                        servicedto.getDuration(),
                        servicedto.getPetIdService()
                );
                serviceTMS.add(serviceTM);
            }

            table.setItems(serviceTMS);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed to load data into the table.");
        }
    }

    private void refreshPage() throws Exception {

        loadTableData();
        getNextServeID();

        save.setDisable(false);
        update.setDisable(true);
        delete.setDisable(true);

        serviceType.setText("");
        duration.setText("");

    }
    public void getNextServeID() throws Exception {
        String servisiD = serviceBO.getServiceId();
        serviceID.setText(servisiD);
    }

    @FXML
    void restAction(ActionEvent event) throws Exception {
        getNextServeID();
        serviceType.setText("");
        duration.setText("");
    }
}
