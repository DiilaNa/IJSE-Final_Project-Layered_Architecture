package lk.project.animalhospital.Controller;

import lk.project.animalhospital.bo.BOFactory;
import lk.project.animalhospital.bo.Custom.SalaryBO;
import lk.project.animalhospital.model.PetTm.SalaryTM;
import lk.project.animalhospital.model.Salarydto;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class SalaryController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image loginImage = new Image(getClass().getResourceAsStream("/images/ALL PET.png"));
        image.setImage(loginImage);

        tableSalaryId.setCellValueFactory(new PropertyValueFactory<>("salaryId"));
        tableSalaryDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableSalaryAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        tableSalaryEmployeeId.setCellValueFactory(new PropertyValueFactory<>("EmployeeId"));

        LocalDate date = LocalDate.now();
        String formattedDate = date.format(formatter);
        dateLAbel.setText(formattedDate);


        try {
            refreshPage();
            loadEmpIds();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TextField SalAmount;

    @FXML
    private ComboBox<String> SalEmpId;

    @FXML
    private Button back;

    @FXML
    private Button delete;

    @FXML
    private ImageView image;

    @FXML
    private Button save;


    @FXML
    private Button reset;

    @FXML
    private Label salId;

    @FXML
    private TableView<SalaryTM> table;

    @FXML
    private TableColumn<SalaryTM, Double> tableSalaryAmount;

    @FXML
    private TableColumn<SalaryTM, Date> tableSalaryDate;

    @FXML
    private TableColumn<SalaryTM, String> tableSalaryEmployeeId;

    @FXML
    private TableColumn<SalaryTM, String> tableSalaryId;

    @FXML
    private Button update;

    @FXML
    private Label dateLAbel;

    SalaryBO salaryBO = (SalaryBO) BOFactory.getInstance().getBO(BOFactory.BOType.SALARY);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @FXML
    void deleteAction(ActionEvent event) {
        SalaryTM selectedSalary = table.getSelectionModel().getSelectedItem();

        if (selectedSalary == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a medicine to delete.").show();
            return;
        }

        try {
            boolean isDeleted = salaryBO.deleteSalary(selectedSalary.getSalaryId());

            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Salary Record deleted successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete Salary Record.").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An error occurred while deleting the Salary Record.").show();
        }

    }

    @FXML
    void saveAction(ActionEvent event) {
        // Retrieve input values
        String salaryId = salId.getText();
        String dateText = dateLAbel.getText();
        String amountText = SalAmount.getText();
        String employeeId = SalEmpId.getValue();

        // Regex patterns
        String idPattern = "^[A-Za-z0-9]+$"; // Alphanumeric pattern
        String amountPattern = "^[0-9]*\\.?[0-9]+$"; // Accepts positive numbers with optional decimal

        boolean isValidAmount = amountText.matches(amountPattern);
        boolean isValidEmpID = employeeId != null && employeeId.matches(idPattern);

        if (!isValidAmount) {
            SalAmount.setStyle(SalAmount.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid Amount: " + amountText);
        }
        if (!isValidEmpID) {
            SalEmpId.setStyle(SalEmpId.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid Employee ID: " + employeeId);
        }

        // Proceed if all inputs are valid
        if (isValidAmount && isValidEmpID) {
            try {
                double amount = Double.parseDouble(amountText);

                // Assuming date format "yyyy-MM-dd"
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formatter.parse(dateText);

                Salarydto salarydto = new Salarydto(salaryId, date, amount, employeeId);

                boolean isSaved = salaryBO.saveSalary(salarydto);
                if (isSaved) {
                    refreshPage();
                    new Alert(Alert.AlertType.INFORMATION, "Salary saved successfully!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save salary.").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "An error occurred while saving the salary.").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Please fix the highlighted errors before saving.").show();
        }

    }

    @FXML
    void tableClick(MouseEvent event) {
        SalaryTM salaryTM = table.getSelectionModel().getSelectedItem();
        if (salaryTM != null) {

            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = salaryTM.getDate() != null ? dateFormatter.format(salaryTM.getDate()) : "";

            salId.setText(salaryTM.getSalaryId());
            dateLAbel.setText(formattedDate);
            SalAmount.setText(String.valueOf(salaryTM.getAmount()));
            SalEmpId.setValue(salaryTM.getEmployeeId());

            save.setDisable(false);

            delete.setDisable(false);
            update.setDisable(false);
        }
    }

    @FXML
    void updateAction(ActionEvent event) {
        // Retrieve input values
        String salaryId = salId.getText();
        String dateText = dateLAbel.getText();
        String amountText = SalAmount.getText();
        String employeeId = SalEmpId.getValue();

        // Regex patterns
        String idPattern = "^[A-Za-z0-9]+$"; // Alphanumeric pattern
        String amountPattern = "^[0-9]*\\.?[0-9]+$"; // Accepts positive numbers with optional decimal

        boolean isValidID = salaryId.matches(idPattern);
        boolean isValidAmount = amountText.matches(amountPattern);
        boolean isValidEmpID = employeeId.matches(idPattern);

        // Highlight invalid fields
        if (!isValidAmount) {
            SalAmount.setStyle(SalAmount.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid Amount: " + amountText);
        }
        if (!isValidEmpID) {
            SalEmpId.setStyle(SalEmpId.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid Employee ID: " + employeeId);
        }

        // Proceed if all inputs are valid
        if (isValidID && isValidAmount && isValidEmpID) {
            try {
                double amount = Double.parseDouble(amountText);

                // Assuming date format "yyyy-MM-dd"
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formatter.parse(dateText);

                Salarydto salarydto = new Salarydto(
                        salaryId,
                        date,
                        amount,
                        employeeId
                );

                boolean isSaved = salaryBO.updateSalary(salarydto);
                if (isSaved) {
                    refreshPage();
                    new Alert(Alert.AlertType.INFORMATION, "Salary updated successfully!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to update salary.").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "An error occurred while updating the salary.").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Please fix the highlighted errors before updating.").show();
        }


    }
    private void loadEmpIds() throws Exception {
        SalEmpId.getItems().clear();
        ArrayList<String> empIds = salaryBO.getEmployeeIds();
        SalEmpId.getItems().addAll(empIds);


    }
    private void loadTableData() throws Exception {
        try {
            ArrayList<Salarydto> salarydtos = salaryBO.getAllSalary();
            ObservableList<SalaryTM> salaryTMS = FXCollections.observableArrayList();

            for (Salarydto salarydto : salarydtos) {
                SalaryTM salaryTM = new SalaryTM(
                        salarydto.getSalaryId(),
                        salarydto.getDate(),
                        salarydto.getAmount(),
                        salarydto.getEmployeeId()
                );
                salaryTMS.add(salaryTM);
            }

            table.setItems(salaryTMS);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed to load data into the table.");
        }
    }

    private void refreshPage() throws Exception {

        loadTableData();
        getNextID();
        loadEmpIds();

        save.setDisable(false);
        update.setDisable(true);
        delete.setDisable(true);
        SalAmount.setText("");
      //  SalEmpId.setValue(null);

    }
    @FXML
    void resetAction(ActionEvent event) throws Exception {
        getNextID();
        LocalDate date = LocalDate.now();
        String formattedDate = date.format(formatter);
        dateLAbel.setText(formattedDate);
        SalAmount.setText("");
        SalEmpId.setValue(null);
    }
    public void getNextID() throws Exception {
        String nextID = salaryBO.loadNextSalaryId();
        salId.setText(nextID);
    }

}
