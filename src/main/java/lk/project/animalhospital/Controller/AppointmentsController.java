package lk.project.animalhospital.Controller;

import javafx.scene.layout.AnchorPane;
import lk.project.animalhospital.bo.BOFactory;
import lk.project.animalhospital.bo.Custom.AppointmentsBO;
import lk.project.animalhospital.model.Appointmentsdto;
import lk.project.animalhospital.model.Ownerdto;
import lk.project.animalhospital.model.PaymentDto;
import lk.project.animalhospital.model.Petdto;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AppointmentsController implements Initializable {



    @FXML
    private Label AppointmentID;

    @FXML
    private Label PaymentId;

    @FXML
    private Label PeTid;

    @FXML
    private Label TimeLabel;

    @FXML
    private Button backID;

    @FXML
    private ImageView image;

    @FXML
    private TextField ownerAddressTXT;

    @FXML
    private Label ownerid;

    @FXML
    private TextField ownerMailTXT;

    @FXML
    private TextField ownerNAmeTXT;

    @FXML
    private ComboBox<String> paymentMethod;

    @FXML
    private TextField petBreed;

    @FXML
    private TextField petName;

    @FXML
    private ComboBox<String> petType;

    @FXML
    private TextField addTime;

    @FXML
    private Button save;

    @FXML
    private Button servicedetailsView;

    @FXML
    private Button view;

    @FXML
    private Button cancel;

    @FXML
    private ComboBox<String> APTsearch;

    @FXML
    private Button resetDetails;

    @FXML
    private AnchorPane anchorPane;


    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    AppointmentsBO appointmentsBO = (AppointmentsBO) BOFactory.getInstance().getBO(BOFactory.BOType.APPOINTMENTS);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image loginImage = new Image(getClass().getResourceAsStream("/images/ALL PET.png"));
        image.setImage(loginImage);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> updateTime())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load id").show();
        }
    }


    @FXML
    void cancelAPt(ActionEvent event) {
        String appointmentSearchID = APTsearch.getValue();

        try {
            boolean isCancelled = appointmentsBO.CancelApt(appointmentSearchID);

            if (isCancelled) {
                new Alert(Alert.AlertType.INFORMATION, "Appointment successfully cancelled!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to cancel the appointment. Please check the Appointment ID.").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An error occurred while cancelling the appointment.").show();
        }
    }

    @FXML
    void servicedetailsViewAction(ActionEvent event) {
        navigateTo("/view/service.fxml");

    }

    @FXML
    void viewApt(ActionEvent event) {
        navigateTo("/view/View Appointments.fxml");
    }

        @FXML
        void saveAPT(ActionEvent event) throws SQLException {
            String appointmentId = AppointmentID.getText();
            String appointmentTime = addTime.getText();
            String ownerId = ownerid.getText();
            String petId = PeTid.getText();
            String pettype = petType.getValue();
            String paymentId = PaymentId.getText();
            String paymentDate = LocalDate.now().format(formatter);
            String paymentTime = LocalTime.now().format(timeFormatter);
            String ownerMail = ownerMailTXT.getText();
            String ownerName = ownerNAmeTXT.getText();
            String ownerAddress = ownerAddressTXT.getText();
            String petBREED = petBreed.getText();
            String petNAME = petName.getText();
            String paymentMETHOD = paymentMethod.getValue();

            // Reset styles
            ownerMailTXT.setStyle(ownerMailTXT.getStyle() + ";-fx-border-color: #7367F0;");
            ownerNAmeTXT.setStyle(ownerNAmeTXT.getStyle() + ";-fx-border-color: #7367F0;");
            ownerAddressTXT.setStyle(ownerAddressTXT.getStyle() + ";-fx-border-color: #7367F0;");
            petName.setStyle(petName.getStyle() + ";-fx-border-color: #7367F0;");
            petBreed.setStyle(petBreed.getStyle() + ";-fx-border-color: #7367F0;");

            // Validation patterns
            String namePattern = "^[a-zA-Z ]+$";
            String addressPattern = "^[a-zA-Z0-9, -]+$";
            String mailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

            boolean isValidName = ownerName.matches(namePattern);
            boolean isValidAddress = ownerAddress.matches(addressPattern);
           boolean isValidMail = ownerMail.matches(mailPattern);

            if (!isValidName) {
                ownerNAmeTXT.setStyle(ownerNAmeTXT.getStyle() + ";-fx-border-color: red;");
                System.out.println("Invalid name: " + ownerName);
            }
            if (!isValidAddress) {
                ownerAddressTXT.setStyle(ownerAddressTXT.getStyle() + ";-fx-border-color: red;");
                System.out.println("Invalid address: " + ownerAddress);
            }
            if (!isValidMail) {
                ownerMailTXT.setStyle(ownerMailTXT.getStyle() + ";-fx-border-color: red;");
                System.out.println("Invalid mail: " + ownerMail);
            }
            if (isValidName && isValidAddress) {
                Appointmentsdto appointment = new Appointmentsdto(
                        appointmentId,
                        paymentDate,
                        appointmentTime,
                        paymentId,
                        petId

                );
                Ownerdto ownerdto = new Ownerdto(
                        ownerId,
                        ownerName,
                        ownerAddress,
                        ownerMail
                );
                PaymentDto paymentDto = new PaymentDto(
                        paymentId,
                        paymentDate,
                        paymentMETHOD,
                        paymentTime
                );

                Petdto petdto = new Petdto(
                        petId,
                        petNAME,
                        petBREED,
                        ownerId,
                        pettype
                );

                List<Appointmentsdto>appointmentsdtos = new ArrayList<>();
                appointmentsdtos.add(appointment);

                List<Ownerdto>ownerdtos = new ArrayList<>();
                ownerdtos.add(ownerdto);

                List<PaymentDto>paymentdtos = new ArrayList<>();
                paymentdtos.add(paymentDto);

                List<Petdto>petdtos = new ArrayList<>();
                petdtos.add(petdto);

                boolean isSaved = appointmentsBO.saveAppointment(ownerdtos,petdtos,paymentdtos,appointmentsdtos);
                if (isSaved) {
                    refreshPage();
                    new Alert(Alert.AlertType.INFORMATION, "Appointment Saved!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to Save Appointment!").show();
                }
            }
        }
        private void refreshPage() throws SQLException {

        loadNextAppointmentID();
        loadNextPetID();
        loadNextOwnerID();
        loadNextPayID();
        loadAptid();

        save.setDisable(false);
        view.setDisable(false);
        servicedetailsView.setDisable(false);
        cancel.setDisable(false);

        petName.setText("");
        petBreed.setText("");
        petType.setItems(FXCollections.observableArrayList("DOG","CAT","BIRD","OTHER"));
        ownerMailTXT.setText("");
        ownerNAmeTXT.setText("");
        ownerAddressTXT.setText("");
        paymentMethod.setItems(FXCollections.observableArrayList("Cash", "Card"));
        TimeLabel.setText("");


        }

    @FXML
    void resetDetailsAction(ActionEvent event) throws SQLException {
        petType.setValue("");
        paymentMethod.setValue(null);
        refreshPage();
    }
    public void loadNextAppointmentID()  {
        String nextCustomerId = appointmentsBO.getNextAppointmentID();
        AppointmentID.setText(nextCustomerId);
    }
    public void loadNextPetID()  {
        String nextCustomerId =appointmentsBO.getNextPetID();
        PeTid.setText(nextCustomerId);
    }
    public void loadNextOwnerID()  {
        String nextCustomerId = appointmentsBO.getNextOwnerID();
        ownerid.setText(nextCustomerId);
    }
    public void loadNextPayID()  {
        String nextCustomerId = appointmentsBO.getNextPayID();
        PaymentId.setText(nextCustomerId);
    }
    private void updateTime() {
        String currentTime = LocalTime.now().format(timeFormatter);
        TimeLabel.setText(currentTime);
    }
    private void loadAptid() throws SQLException {
        try {
           APTsearch.getItems().clear();
           ArrayList<String> data = appointmentsBO.LoadAppointmentsId();
            APTsearch.getItems().addAll(data);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void navigateTo(String fxmlPath) {
        try {
            anchorPane.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            load.getStylesheets().add(getClass().getResource("/Css/Login.css").toExternalForm());
            load.prefWidthProperty().bind(anchorPane.widthProperty());
            load.prefHeightProperty().bind(anchorPane.heightProperty());
            anchorPane.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load page!").show();
        }
    }
}