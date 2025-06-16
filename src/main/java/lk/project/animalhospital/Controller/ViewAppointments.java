package lk.project.animalhospital.Controller;

import javafx.scene.layout.AnchorPane;
import lk.project.animalhospital.bo.BOFactory;
import lk.project.animalhospital.bo.Custom.ViewAppointmentsBO;
import lk.project.animalhospital.model.PetTm.ViewAppointmentTM;
import lk.project.animalhospital.model.ViewAppointmentDto;
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

    @FXML
    private AnchorPane anchorPane;

    @FXML
    void backIDAction(ActionEvent event) {
        try {
            anchorPane.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/pet.fxml"));
            load.getStylesheets().add(getClass().getResource("/Css/Login.css").toExternalForm());
            load.prefWidthProperty().bind(anchorPane.widthProperty());
            load.prefHeightProperty().bind(anchorPane.heightProperty());
            anchorPane.getChildren().add(load);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    ViewAppointmentsBO viewAppointmentsBO = (ViewAppointmentsBO) BOFactory.getInstance().getBO(BOFactory.BOType.VIEW_APPOINTMENTS);

    @FXML
    void TableMouseClick(MouseEvent event) {
        ViewAppointmentTM viewAppointmentTM = table.getSelectionModel().getSelectedItem();
        if (viewAppointmentTM != null) {
            tableAptID.setText(viewAppointmentTM.getAppointmentId());
            delete.setDisable(false);
        }

    }

    @FXML
    void deleteAction(ActionEvent event) throws Exception {
        String AptDeleteid = tableAptID.getText();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure You Want to Delete this Appointment?",ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {

            boolean isDeleted = viewAppointmentsBO.deleteAppointment(AptDeleteid);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "  Record deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete ...!").show();
            }
        }


    }
    private void refreshPage() throws Exception {

        loadTableData();
        delete.setDisable(true);

    }
    private void loadTableData() throws Exception {

        ArrayList<ViewAppointmentDto> viewAppointmentDtos = viewAppointmentsBO.getAllAppointments();
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
