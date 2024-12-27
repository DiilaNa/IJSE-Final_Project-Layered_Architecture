package gdse71.project.animalhospital.Controller;

import gdse71.project.animalhospital.CrudUtil.Util;
import gdse71.project.animalhospital.db.DBConnection;
import gdse71.project.animalhospital.dto.EmpSheduleDto;
import gdse71.project.animalhospital.dto.PetTm.ScheduleTM;
import gdse71.project.animalhospital.dto.ScheduleDto;
import gdse71.project.animalhospital.model.ScheduleModel;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class ScheduleController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image loginImage = new Image(getClass().getResourceAsStream("/images/ALL PET.png"));
        image.setImage(loginImage);

        tableId.setCellValueFactory(new PropertyValueFactory<>("ScheduleID"));
        tabledate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        tableTime.setCellValueFactory(new PropertyValueFactory<>("Time"));

        try {
            refreshPage();
            loadNextScheduleId();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Button back;

    @FXML
    private TextField datetxt;

    @FXML
    private Button delete;

    @FXML
    private ImageView image;

    @FXML
    private Button reset;

    @FXML
    private Button save;

    @FXML
    private Label sheduleID;

    @FXML
    private TableView<ScheduleTM> table;

    @FXML
    private TableColumn<ScheduleTM, String> tableId;

    @FXML
    private TableColumn<ScheduleTM, String> tableTime;

    @FXML
    private TableColumn<ScheduleTM, String> tabledate;

    @FXML
    private TextField timeTxt;

    @FXML
    private Button update;


    @FXML
    private ComboBox<String> empIds;

    @FXML
    private Label empName;

   ScheduleModel scheduleModel = new ScheduleModel();

    @FXML
    void ACtionTAble(MouseEvent event) {
        ScheduleTM scheduleTM = table.getSelectionModel().getSelectedItem();
        if (scheduleTM != null) {
            sheduleID.setText(scheduleTM.getScheduleID());
            datetxt.setText(scheduleTM.getDate());
            timeTxt.setText(scheduleTM.getTime());

            save.setDisable(false);

            delete.setDisable(false);
            update.setDisable(false);
            empIds.setValue("");
            empName.setText("");
        }

    }

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
        String Id = sheduleID.getText();
        String id = empIds.getValue();

        if (id == null && Id == null) {
            new Alert(Alert.AlertType.ERROR, "Please select an ID", ButtonType.OK).show();

        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> optionalButtonType = alert.showAndWait();
            if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
                try {
                    boolean isDeleted = scheduleModel.delete(Id,id);
                    if (isDeleted) {
                        refreshPage();
                        new Alert(Alert.AlertType.INFORMATION, "  Record deleted...!").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Fail to delete ...!").show();
                    }


                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }


        }

    }

    @FXML
    void resetAction(ActionEvent event) {
        datetxt.setText("");
        timeTxt.setText("");
        loadNextScheduleId();
    }

    @FXML
    void saveAction(ActionEvent event) {
        String ScheduleId = sheduleID.getText();
        String Date = datetxt.getText();
        String Time = timeTxt.getText();
        String employeeid = empIds.getValue();

       /* String datePattern = ^\d{4}:(0[1-9]|1[0-2]):(0[1-9]|[12]\d|3[01])$

        String timePattern = "^([01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$\n";
*/
     /*   boolean isValidDate = datePattern.matches(datePattern);
        boolean isValidTime = timePattern.matches(timePattern);

        if (!isValidDate) {
            datetxt.setStyle(datetxt.getStyle()+";-fx-border-color: red;");
            System.out.println("Invalid Date "+Date);

        }
        if (!isValidTime) {
            timeTxt.setStyle(timeTxt.getStyle()+";-fx-border-color: red;");
            System.out.println("Invalid Time "+Time);
        }*/

     //   if ( isValidDate && isValidTime) {

            try {
            ScheduleDto scheduleDto = new ScheduleDto(ScheduleId, Date, Time);
            EmpSheduleDto empSheduleDto = new EmpSheduleDto(employeeid,ScheduleId);
            boolean isSaved = scheduleModel.save(scheduleDto,empSheduleDto);
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
      /*  }else {
            new Alert(Alert.AlertType.ERROR, "Fail to save Error Occured While Saving...!").show();
        }*/
    }

    @FXML
    void updateAction(ActionEvent event) {

        String ScheduleId = sheduleID.getText();
        String Date = datetxt.getText();
        String Time = timeTxt.getText();

/*
        String datePattern = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$\n";
        String timePattern = "^([01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$\n";

        boolean isValidDate = datePattern.matches(datePattern);
        boolean isValidTime = timePattern.matches(timePattern);
*/

       /* if (!isValidDate) {
            datetxt.setStyle(datetxt.getStyle()+";-fx-border-color: red;");
            System.out.println("Invalid Date "+Date);

        }
        if (!isValidTime) {
            timeTxt.setStyle(timeTxt.getStyle()+";-fx-border-color: red;");
            System.out.println("Invalid Time "+Time);
        }*/

    //    if (isValidDate && isValidTime) {

            try {
                ScheduleDto scheduleDto = new ScheduleDto(
                        ScheduleId,
                        Date,
                        Time
                );
                boolean isSaved = scheduleModel.update(scheduleDto);
                if (isSaved) {
                    refreshPage();
                    new Alert(Alert.AlertType.INFORMATION, "  Record updated Successfully...!").show();
                }else{
                    new Alert(Alert.AlertType.ERROR, "Fail to update ...!").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

      /*  }else {
            new Alert(Alert.AlertType.ERROR, "Fail to update an Error Occured While Saving...!").show();
        }*/

    }
    public void loadNextScheduleId()  {
        try {
            String nextId = null;
            nextId = scheduleModel.getNextScheduleID();
            sheduleID.setText(nextId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    private void loadTableData() {
        try {
            ArrayList<ScheduleDto> scheduleDtos = scheduleModel.getAll();
            ObservableList<ScheduleTM> scheduleTMS = FXCollections.observableArrayList();

            for (ScheduleDto scheduleDto : scheduleDtos) {
                ScheduleTM scheduleTM = new ScheduleTM(
                        scheduleDto.getScheduleID(),
                        scheduleDto.getDate(),
                        scheduleDto.getTime()
                );
                scheduleTMS.add(scheduleTM);
            }

            table.setItems(scheduleTMS);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed to load data into the table.");
        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {

        loadTableData();
        loadEmpID();

        save.setDisable(false);
        reset.setDisable(false);

        update.setDisable(true);
        delete.setDisable(true);

        sheduleID.setText("");
        datetxt.setText("");
        timeTxt.setText("");

    }
    public void loadEmpID() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet rst = connection.createStatement().executeQuery("select emp_id from employee");
            ObservableList<String> data = FXCollections.observableArrayList();

            while (rst.next()) {
                data.add(rst.getString("emp_id"));
            }
            empIds.setItems(data);
        } catch (ClassNotFoundException |SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void empAction(ActionEvent event) {

        try {
            String Empids = empIds.getValue();
            if (Empids !=null) {
                ResultSet rst = Util.execute("select emp_name from employee where emp_id=?", Empids);
                if (rst.next()) {
                    String aptid = rst.getString("emp_name");
                    empName.setText(aptid);
                }else {
                    empName.setText("no name found");
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
