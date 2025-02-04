module gdse71.project.animalhospital {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.sql;
    requires net.sf.jasperreports.core;
    requires java.mail;

    opens lk.project.animalhospital to javafx.fxml;
    exports lk.project.animalhospital;
    exports lk.project.animalhospital.Controller;
    opens lk.project.animalhospital.Controller to javafx.fxml;
    opens lk.project.animalhospital.dto.PetTm to javafx.base;


}