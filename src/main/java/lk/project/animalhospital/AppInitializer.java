package lk.project.animalhospital;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppInitializer extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(AppInitializer.class.getResource("/view/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("/Css/Login.css").toExternalForm());
        stage.setTitle("Pets & Vets Animal Hospital");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
