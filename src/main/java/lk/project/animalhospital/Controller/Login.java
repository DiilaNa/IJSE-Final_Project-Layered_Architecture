package lk.project.animalhospital.Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.project.animalhospital.AppInitializer;

import java.io.IOException;

public class Login {


    @FXML
    private Label textTopic;

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
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard.fxml")));
                scene.getStylesheets().add(getClass().getResource("/Css/Login.css").toExternalForm());
                stage.setTitle("Pets & Vets Animal Hospital");
                stage.setScene(scene);
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
