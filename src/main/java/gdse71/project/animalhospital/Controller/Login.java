package gdse71.project.animalhospital.Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {

    @FXML
    private ImageView image;

    @FXML
    private Button logbtn;

    @FXML
    private TextField passWord;

    @FXML
    private TextField userName;


    public void initialize() {
        Image loginImage = new Image(getClass().getResourceAsStream("/images/v.jpeg"));
        image.setImage(loginImage);
    }

    @FXML
    void logbtnAction(ActionEvent event) throws IOException {
        if (userName.getText().equals("admin") && passWord.getText().equals("1234")) {
            try {
                Stage stage = (Stage) logbtn.getScene().getWindow();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"))));
                stage.setTitle("Pets & Vets Animal Hospital");
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password");
            alert.showAndWait();
        }

    }

}
