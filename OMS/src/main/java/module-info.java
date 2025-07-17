module com.ssn.oms {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.ssn.oms to javafx.fxml;
    exports com.ssn.oms;
    opens com.ssn.oms.controllers to javafx.fxml;
    exports com.ssn.oms.controllers to javafx.fxml;
}