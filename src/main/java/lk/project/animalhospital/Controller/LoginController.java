package lk.project.animalhospital.Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image loginImage = new Image(getClass().getResourceAsStream("/images/v.jpeg"));
        image.setImage(loginImage);
        passWord.setVisible(true);
        passWordText.setVisible(false);
    }

    @FXML
    private Label textTopic;

    @FXML
    private ImageView image;

    @FXML
    private Button logbtn;


    @FXML
    private PasswordField passWord;

    @FXML
    private TextField userName;

    @FXML
    private CheckBox checkBox;

    @FXML
    private TextField passWordText;

    @FXML
    void checkBoxAction(ActionEvent event) {
        if (checkBox.isSelected()) {
            passWordText.setVisible(true);
            passWord.setVisible(false);
            passWordText.setText(passWord.getText());
        }else {
            passWordText.setVisible(false);
            passWord.setVisible(true);
            passWord.setText(passWordText.getText());
        }
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
