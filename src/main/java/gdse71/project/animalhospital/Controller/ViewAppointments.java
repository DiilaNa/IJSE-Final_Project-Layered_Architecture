package gdse71.project.animalhospital.Controller;

import gdse71.project.animalhospital.dto.PetTm.ViewAppointmentTM;
import gdse71.project.animalhospital.dto.ViewAppointmentDto;
import gdse71.project.animalhospital.model.ViewAppointmentModel;
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

public class ViewAppointments implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image loginImage = new Image(getClass().getResourceAsStream("/images/ALL PET.png"));
        image.setImage(loginImage);

        tableAptID.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        tableDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        tableTime.setCellValueFactory(new PropertyValueFactory<>("Time"));
        tablePetId.setCellValueFactory(new PropertyValueFactory<>("petId"));
        tablePayId.setCellValueFactory(new PropertyValueFactory<>("payID"));
        tableStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));

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
    private TableView<ViewAppointmentTM> table;

    @FXML
    private TableColumn<ViewAppointmentTM, String> tableAptID;

    @FXML
    private TableColumn<ViewAppointmentTM, String> tableDate;

    @FXML
    private TableColumn<ViewAppointmentTM, String> tablePayId;

    @FXML
    private TableColumn<ViewAppointmentTM, String> tablePetId;

    @FXML
    private TableColumn<ViewAppointmentTM, String> tableStatus;

    @FXML
    private TableColumn<ViewAppointmentTM, String> tableTime;

    ViewAppointmentModel viewAppointmentModel = new ViewAppointmentModel();

    @FXML
    void TableMouseClick(MouseEvent event) {
        ViewAppointmentTM viewAppointmentTM = table.getSelectionModel().getSelectedItem();
        if (viewAppointmentTM != null) {
            tableAptID.setText(viewAppointmentTM.getAppointmentId());
            delete.setDisable(false);
        }

    }

    @FXML
    void backAction(ActionEvent event) {
        try {
            Stage stage = (Stage) back.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"))));
            stage.setTitle("Pets & Vets Animal Hospital");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void deleteAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String AptDeleteid = tableAptID.getText();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure You Want to Delete this Appointment?",ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {

            boolean isDeleted = viewAppointmentModel.delete(AptDeleteid);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "  Record deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete ...!").show();
            }
        }


    }
    private void refreshPage() throws SQLException, ClassNotFoundException {

        loadTableData();
        delete.setDisable(true);

    }
    private void loadTableData() throws SQLException, ClassNotFoundException {

        ArrayList<ViewAppointmentDto> viewAppointmentDtos = viewAppointmentModel.getAll();
        ObservableList<ViewAppointmentTM> viewAppointmentTMS = FXCollections.observableArrayList();

        for (ViewAppointmentDto viewAppointmentDto : viewAppointmentDtos) {
            ViewAppointmentTM viewAppointmentTM = new ViewAppointmentTM(
                    viewAppointmentDto.getAppointmentId(),
                    viewAppointmentDto.getDate(),
                    viewAppointmentDto.getTime(),
                    viewAppointmentDto.getPetId(),
                    viewAppointmentDto.getPayID(),
                    viewAppointmentDto.getStatus()
            );
            viewAppointmentTMS.add(viewAppointmentTM);
        }

        table.setItems(viewAppointmentTMS);
    }
}
