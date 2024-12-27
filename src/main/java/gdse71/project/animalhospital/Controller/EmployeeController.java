package gdse71.project.animalhospital.Controller;

import gdse71.project.animalhospital.db.DBConnection;
import gdse71.project.animalhospital.dto.Docdto;
import gdse71.project.animalhospital.dto.Employeedto;
import gdse71.project.animalhospital.dto.PetTm.EmployeeTM;
import gdse71.project.animalhospital.model.EmployeeModel;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    @FXML
    private TextField address;

    @FXML
    private Button back;

    @FXML
    private TextField contact;

    @FXML
    private Button delete;

    @FXML
    private TextField duty;

    @FXML
    private TextField empNAme;

    @FXML
    private ImageView image;

    @FXML
    private Button save;

    @FXML
    private TableView<EmployeeTM> table;

    @FXML
    private TableColumn<EmployeeTM, String> tableAddress;

    @FXML
    private TableColumn<EmployeeTM, String> tableContactNo;

    @FXML
    private TableColumn<EmployeeTM, String> tableDuty;

    @FXML
    private TableColumn<EmployeeTM, String> tableEmpID;

    @FXML
    private TableColumn<EmployeeTM, String> tableEmpName;

    @FXML
    private Button update;

    @FXML
    private Label empId;

    @FXML
    private Button reset;

    @FXML
    private ComboBox<String> appt;

    EmployeeModel employeeModel = new EmployeeModel();

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
    void deleteAction(ActionEvent event) {
        String employeeID = empId.getText();
        String selectedAptId = appt.getValue();
        if (selectedAptId == null && employeeID == null) {
            new Alert(Alert.AlertType.WARNING, "Please select an employee to delete.").show();
            return;
        }


        try {
            boolean isDeleted = employeeModel.delete(employeeID,selectedAptId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Employee deleted successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete employee.").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An error occurred while deleting the employee.").show();
        }

    }

    @FXML
    void saveAction(ActionEvent event) {
        String employeeID = empId.getText();
        String employeeName = empNAme.getText();
        String employeeDuty = duty.getText();
        String employeeAddress = address.getText();
        String employeeContact = contact.getText();
        String appointmentid = appt.getValue();

        String namePattern = "^[A-Za-z\\s]+$";
        String dutyPattern = "^[A-Za-z\\s]+$";
        String addressPattern ="^[a-zA-Z0-9, -]+$";
        String contactPattern = "^\\d{10}$";

        boolean isValidName = employeeName.matches(namePattern);
        boolean isValidDuty = employeeDuty.matches(dutyPattern);
        boolean isValidAddress = employeeAddress.matches(addressPattern);
        boolean isValidContact = employeeContact.matches(contactPattern);
        if (!isValidName) {
            empNAme.setStyle("-fx-border-color: red;");
            System.out.println("Invalid Employee Name: " + employeeName);
        }
        if (!isValidDuty) {
            duty.setStyle("-fx-border-color: red;");
            System.out.println("Invalid Duty: " + employeeDuty);
        }
        if (!isValidAddress) {
            address.setStyle("-fx-border-color: red;");
            System.out.println("Invalid Address: " + employeeAddress);
        }
        if (!isValidContact) {
            contact.setStyle("-fx-border-color: red;");
            System.out.println("Invalid Contact Number: " + employeeContact);
        }

        if (isValidName && isValidDuty && isValidAddress && isValidContact) {
            Employeedto employeedto = new Employeedto(employeeID, employeeName, employeeDuty, employeeAddress, employeeContact);
            Docdto docdto = new Docdto(employeeID,appointmentid);

            try {
                boolean isSaved = employeeModel.save(employeedto,docdto);
                if (isSaved) {
                    refreshPage();
                    new Alert(Alert.AlertType.INFORMATION, "Employee saved successfully!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save employee.").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "An error occurred while saving the employee.").show();
            }
        }
    }

    @FXML
    void tableONACTION(MouseEvent event) {
        EmployeeTM employeeTM = table.getSelectionModel().getSelectedItem();

        if (employeeTM != null) {
            empId.setText(employeeTM.getEmployeeId());
            empNAme.setText(employeeTM.getEmployeeName());
            duty.setText(employeeTM.getEmployeeDuty());
            address.setText(employeeTM.getEmployeeAddress());
            contact.setText(employeeTM.getEmployeePhone());

            save.setDisable(false);

            delete.setDisable(false);
            update.setDisable(false);
        }

    }

    @FXML
    void updateAction(ActionEvent event) {
        String employeeID = empId.getText();
        String employeeName = empNAme.getText();
        String employeeDuty = duty.getText();
        String employeeAddress = address.getText();
        String employeeContact = contact.getText();

        String namePattern = "^[A-Za-z\\s]+$";
        String dutyPattern = "^[A-Za-z\\s]+$";
        String addressPattern ="^[a-zA-Z0-9, -]+$";
        String contactPattern = "^\\d{10}$";

        boolean isValidName = employeeName.matches(namePattern);
        boolean isValidDuty = employeeDuty.matches(dutyPattern);
        boolean isValidAddress = employeeAddress.matches(addressPattern);
        boolean isValidContact = employeeContact.matches(contactPattern);

        if (!isValidName) {
            empNAme.setStyle("-fx-border-color: red;");
            System.out.println("Invalid Employee Name: " + employeeName);
        }
        if (!isValidDuty) {
            duty.setStyle("-fx-border-color: red;");
            System.out.println("Invalid Duty: " + employeeDuty);
        }
        if (!isValidAddress) {
            address.setStyle("-fx-border-color: red;");
            System.out.println("Invalid Address: " + employeeAddress);
        }
        if (!isValidContact) {
            contact.setStyle("-fx-border-color: red;");
            System.out.println("Invalid Contact Number: " + employeeContact);
        }

        if (isValidName && isValidDuty && isValidAddress && isValidContact) {
            Employeedto employeedto = new Employeedto(
                    employeeID,
                    employeeName,
                    employeeDuty,
                    employeeAddress,
                    employeeContact
            );

            try {
                boolean isSaved = employeeModel.update(employeedto);
                if (isSaved) {
                    refreshPage();
                    new Alert(Alert.AlertType.INFORMATION, "Employee updated successfully!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to update employee.").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "An error occurred while updating the employee.").show();
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image loginImage = new Image(getClass().getResourceAsStream("/images/ALL PET.png"));
        image.setImage(loginImage);

        tableEmpID.setCellValueFactory(new PropertyValueFactory<>("EmployeeId"));
        tableEmpName.setCellValueFactory(new PropertyValueFactory<>("EmployeeName"));
        tableDuty.setCellValueFactory(new PropertyValueFactory<>("EmployeeDuty"));
        tableAddress.setCellValueFactory(new PropertyValueFactory<>("EmployeeAddress"));
        tableContactNo.setCellValueFactory(new PropertyValueFactory<>("EmployeePhone"));


        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load ").show();
        }

    }
    private void loadTableData() throws SQLException, ClassNotFoundException {

        ArrayList<Employeedto> employeedtos = employeeModel.getAll();
        ObservableList<EmployeeTM> employeeTMS = FXCollections.observableArrayList();

        for (Employeedto employeedto : employeedtos) {
            EmployeeTM employeeTM = new EmployeeTM(
                    employeedto.getEmployeeId(),
                    employeedto.getEmployeeName(),
                    employeedto.getEmployeeDuty(),
                    employeedto.getEmployeeAddress(),
                    employeedto.getEmployeePhone()

            );
            employeeTMS.add(employeeTM);
        }

        table.setItems(employeeTMS);
    }
    private void refreshPage() throws SQLException, ClassNotFoundException {

        loadTableData();

        save.setDisable(false);
        reset.setDisable(false);
        update.setDisable(true);
        delete.setDisable(true);

       loadNextID();
        loadaptIds();
        empNAme.setText("");
        duty.setText("");
        address.setText("");
        contact.setText("");

    }

    @FXML
    void resetAction(ActionEvent event) throws SQLException {
        loadNextID();
        loadaptIds();
        empNAme.setText("");
        duty.setText("");
        address.setText("");
        contact.setText("");

    }
    public void loadNextID()  {
        String nextId = employeeModel.getNextID();
        empId.setText(nextId);

    }
    private void loadaptIds() throws SQLException {

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet rs = connection.createStatement().executeQuery("SELECT appointment_id FROM appointments");
            ObservableList<String> data = FXCollections.observableArrayList();

            while (rs.next()) {
                data.add(rs.getString("appointment_id"));
            }
            appt.setItems(data);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
