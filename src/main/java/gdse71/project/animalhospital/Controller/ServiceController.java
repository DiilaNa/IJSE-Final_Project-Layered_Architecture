package gdse71.project.animalhospital.Controller;

import gdse71.project.animalhospital.db.DBConnection;
import gdse71.project.animalhospital.dto.PetTm.ServiceTM;
import gdse71.project.animalhospital.dto.Servicedto;
import gdse71.project.animalhospital.model.ServiceModel;
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

    ServiceModel serviceModel = new ServiceModel();

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
        } catch (SQLException | ClassNotFoundException e) {
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
        String Id = serviceID.getText();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = serviceModel.deleteService(Id);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "  Record deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete ...!").show();
            }
        }

    }


    @FXML
    void saveAction(ActionEvent event) throws SQLException, ClassNotFoundException {
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
        boolean isSaved = serviceModel.saveService(servicedto);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Pet Record saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save Pet Reccord...!").show();
        }
    }

    @FXML
    void updateAction(ActionEvent event) throws SQLException, ClassNotFoundException {
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
        boolean isSaved = serviceModel.updateService(servicedto);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, " Record updated...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update  Reccord...!").show();
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
            PETid.setItems(data);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadTableData() {
        try {
            ArrayList<Servicedto> servicedtos = serviceModel.getAllService();
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

    private void refreshPage() throws SQLException, ClassNotFoundException {

        loadTableData();
        getNextServeID();

        save.setDisable(false);
        update.setDisable(true);
        delete.setDisable(true);

        serviceType.setText("");
        duration.setText("");

    }
    public void getNextServeID(){
        String servisiD = serviceModel.getNextID();
        serviceID.setText(servisiD);
    }

    @FXML
    void restAction(ActionEvent event) {
        getNextServeID();
       // loadTableData();
        serviceType.setText("");
        duration.setText("");
    }
}
