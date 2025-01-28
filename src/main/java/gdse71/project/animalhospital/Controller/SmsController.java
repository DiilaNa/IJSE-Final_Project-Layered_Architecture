package gdse71.project.animalhospital.Controller;

import gdse71.project.animalhospital.bo.BOFactory;
import gdse71.project.animalhospital.bo.Custom.SmsBO;
import gdse71.project.animalhospital.dto.PetTm.SmsTM;
import gdse71.project.animalhospital.dto.Smsdto;
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

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;

public class SmsController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image loginImage = new Image(getClass().getResourceAsStream("/images/ALL PET.png"));
        image.setImage(loginImage);

        tableReminderNo.setCellValueFactory(new PropertyValueFactory<>("smsNo"));
        tableDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tableAppointments.setCellValueFactory(new PropertyValueFactory<>("appID"));
        try {
            refreshPage();
            loadNextMailNo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private ComboBox<String> aptIDs;

    @FXML
    private Button back;

    @FXML
    private DatePicker date;

    @FXML
    private Button delete;

    @FXML
    private ImageView image;

    @FXML
    private Button reset;

    @FXML
    private Button save;

    @FXML
    private Label smsNo;

    @FXML
    private ComboBox<String> status;

    @FXML
    private TableView<SmsTM> table;

    @FXML
    private TableColumn<SmsTM, String> tableAppointments;

    @FXML
    private TableColumn<SmsTM, String> tableDate;

    @FXML
    private TableColumn<SmsTM, String> tableReminderNo;

    @FXML
    private TableColumn<SmsTM, String> tableStatus;

    @FXML
    private Button update;

    @FXML
    private TextArea maildescription;

    @FXML
    private ComboBox<String> ownerMail;

    @FXML
    private TextField subject;

    /*SmsModel smsModel = new SmsModel();*/
    SmsBO smsBO = (SmsBO) BOFactory.getInstance().getBO(BOFactory.BOType.SMS);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
    void deleteAction(ActionEvent event) throws Exception {
        String Id = smsNo.getText();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = smsBO.deleteSms(Id);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "  Record deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete ...!").show();
            }
        }

    }

    @FXML
    void resetAction(ActionEvent event) throws Exception {
        loadNextMailNo();
        loadAppointmentId();
        loadMail();

        maildescription.setText("");
        subject.setText("");
        status.setItems(FXCollections.observableArrayList("SENT", "NOT SENT"));
        aptIDs.setValue("");


    }

    @FXML
    void saveAction(ActionEvent event) {
        String SmsNo = smsNo.getText();
        String Date = date.getValue().format(formatter);
        String Status = status.getValue();
        String AppID = aptIDs.getValue();

        try {
            Smsdto smsdto = new Smsdto(
                    SmsNo,
                    Date,
                    Status,
                    AppID
            );
            boolean isSaved = smsBO.saveSms(smsdto);
            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "  Record saved...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save ...!").show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void sendAction(ActionEvent event) {
        String CustomerMail = ownerMail.getValue();
        if (CustomerMail == null) {
            return;
        }
        final String From = "dilandark602@gmail.com";

        String Subject = subject.getText();
        String body = maildescription.getText();

        if (Subject.isEmpty() || body.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Subject and body are required").show();
            return;
        }

        sendEmailWithGmail(From, CustomerMail, Subject, body);


    }

    @FXML
    void tableAction(MouseEvent event) {
        SmsTM smsTM = table.getSelectionModel().getSelectedItem();
        if (smsTM != null) {
            smsNo.setText(smsTM.getSmsNo());
            date.setValue(LocalDate.parse(smsTM.getDate()));
            aptIDs.setValue(smsTM.getAppID());

            save.setDisable(false);

            delete.setDisable(false);
            update.setDisable(false);
        }

    }

    @FXML
    void updateAction(ActionEvent event) {
        String SmsNo = smsNo.getText();
        String Date = date.getValue().format(formatter);
        String Status = status.getValue();
        String AppID = aptIDs.getValue();

        try {
            Smsdto smsdto = new Smsdto(
                    SmsNo,
                    Date,
                    Status,
                    AppID
            );
            boolean isSaved = smsBO.updateSms(smsdto);
            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "  Record updated...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to update ...!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void refreshPage() throws Exception {

        loadTableData();
        loadAppointmentId();
        loadMail();

        save.setDisable(false);
        reset.setDisable(false);

        update.setDisable(true);
        delete.setDisable(true);

        status.setItems(FXCollections.observableArrayList("SENT", "NOT SENT"));


    }

    private void loadTableData() throws Exception {
        try {
            ArrayList<Smsdto> smsdtos = smsBO.getAllSms();
            ObservableList<SmsTM> smsTMS = FXCollections.observableArrayList();

            for (Smsdto smsdto : smsdtos) {
                SmsTM smsTM = new SmsTM(
                        smsdto.getSmsNo(),
                        smsdto.getDate(),
                        smsdto.getStatus(),
                        smsdto.getAppID()
                );
                smsTMS.add(smsTM);
            }

            table.setItems(smsTMS);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed to load data into the table.");
        }
    }

    public void loadNextMailNo() {
        try {
            String nextId = smsBO.getNextSmsNo();
            smsNo.setText(nextId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void sendEmailWithGmail(String From, String CustomerEmail, String subject, String body) {
        String password = "jkfn whfi vvgb dwcz";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(From, password);
            }
        });
        try {

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(From));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(CustomerEmail));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);

            new Alert(Alert.AlertType.INFORMATION, "Email sent successfully!").show();
        } catch (MessagingException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to send email.").show();
        }
    }

    private void loadMail() throws Exception {
        ownerMail.getItems().clear();
        ArrayList<String> data = smsBO.getEmailList();
        ownerMail.getItems().addAll(data);

    }

    private void loadAppointmentId() throws Exception {
        aptIDs.getItems().clear();
        ArrayList<String> appointmentData = smsBO.getAppointmentId();
        aptIDs.getItems().addAll(appointmentData);
    }
}