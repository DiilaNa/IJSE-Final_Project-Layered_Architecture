package gdse71.project.animalhospital.Controller;

import gdse71.project.animalhospital.db.DBConnection;
import gdse71.project.animalhospital.dto.PetTm.SmsTM;
import gdse71.project.animalhospital.dto.Smsdto;
import gdse71.project.animalhospital.model.SmsModel;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
        } catch (SQLException | ClassNotFoundException e) {
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
    private Button send;

    @FXML
    private Label smsNo;

    @FXML
    private ComboBox<String> status;

    @FXML
    private TableView<SmsTM> table;

    @FXML
    private TableColumn<SmsTM, String> tableAppointments;

    @FXML
    private TableColumn< SmsTM, String> tableDate;

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

    SmsModel smsModel = new SmsModel();
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
    void deleteAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String Id = smsNo.getText();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

            boolean isDeleted = smsModel.delete(Id);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "  Record deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete ...!").show();
            }
        }

    }

    @FXML
    void resetAction(ActionEvent event) throws SQLException {
        loadNextMailNo();
        loadAppointmentId();
        loadMail();

        maildescription.setText("");
        subject.setText("");
        status.setItems(FXCollections.observableArrayList("SENT","NOT SENT"));
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
            boolean isSaved = smsModel.save(smsdto);
            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "  Record saved...!").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Fail to save ...!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
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
            new Alert(Alert.AlertType.WARNING,"Subject and body are required").show();
            return;
        }

        sendEmailWithGmail(From,CustomerMail,Subject,body);


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
                boolean isSaved = smsModel.update(smsdto);
                if (isSaved) {
                    refreshPage();
                    new Alert(Alert.AlertType.INFORMATION, "  Record updated...!").show();
                }else{
                    new Alert(Alert.AlertType.ERROR, "Fail to update ...!").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
    }
    private void refreshPage() throws SQLException, ClassNotFoundException {

        loadTableData();
        loadAppointmentId();
        loadMail();

        save.setDisable(false);
        reset.setDisable(false);

        update.setDisable(true);
        delete.setDisable(true);

        status.setItems(FXCollections.observableArrayList("SENT","NOT SENT"));


    }
    private void loadAppointmentId() throws SQLException {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet rs = connection.createStatement().executeQuery("SELECT appointment_id FROM appointments");
            ObservableList<String> data = FXCollections.observableArrayList();

            while (rs.next()) {
                data.add(rs.getString("appointment_id"));
            }
            aptIDs.setItems(data);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadTableData() {
        try {
            ArrayList<Smsdto> smsdtos = smsModel.getAll();
            ObservableList<SmsTM> smsTMS = FXCollections.observableArrayList();

            for (Smsdto smsdto :smsdtos) {
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
    public void loadNextMailNo()  {
        try {
            String nextId = null;
            nextId = smsModel.getNextMailNo();
            smsNo.setText(nextId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
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
    private void loadMail() throws SQLException {
        final Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/animal_hospital", "root", "Ijse@1234");

            ResultSet rs = connection.createStatement().executeQuery("SELECT email FROM owner");
            ObservableList<String> data = FXCollections.observableArrayList();

            while (rs.next()) {
                data.add(rs.getString("email"));
            }
            ownerMail.setItems(data);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
