module gdse71.project.animalhospital {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.sql;
    requires net.sf.jasperreports.core;
    requires java.mail;

    opens gdse71.project.animalhospital to javafx.fxml;
    exports gdse71.project.animalhospital;
    exports gdse71.project.animalhospital.Controller;
    opens gdse71.project.animalhospital.Controller to javafx.fxml;
    opens gdse71.project.animalhospital.dto.PetTm to javafx.base;


}