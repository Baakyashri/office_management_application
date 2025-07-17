package com.ssn.oms.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import com.ssn.oms.models.Employee;
import com.ssn.oms.services.EmployeeService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class EmployeeController {

    @FXML
    private TableView<Employee> tvEmployee;
    @FXML
    private TableColumn<Employee, Long> colId;
    @FXML
    private TableColumn<Employee, String> colFName;
    @FXML
    private TableColumn<Employee, String> colLName;
    @FXML
    private TableColumn<Employee, String> colUsrname;
    @FXML
    private TableColumn<Employee, Date> colDOB;
    @FXML
    private TextField txtID, txtFName, txtLName, txtUsrName, txtPasword, txtdob;

    private EmployeeService employeeService;
    private ObservableList<Employee> employeeList;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Date format

    @FXML
    public void initialize() {
        employeeService = new EmployeeService();
        loadEmployeeData();

        // Configure table columns
        colId.setCellValueFactory(cellData -> cellData.getValue().empIdProperty().asObject());
        colFName.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        colLName.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        colUsrname.setCellValueFactory(cellData -> cellData.getValue().userNameProperty());

        // Set the cell value factory for the date of birth column
        colDOB.setCellValueFactory(cellData -> cellData.getValue().dobProperty());

        // Custom cell factory to format Date display
        colDOB.setCellFactory(column -> new TableCell<Employee, Date>() {
            @Override
            protected void updateItem(Date item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(sdf.format(item)); // Format date to string for display
                }
            }
        });

        // Populate fields when a table row is clicked
        tvEmployee.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 0) {
                populateFields();
            }
        });
    }

    private void loadEmployeeData() {
        employeeList = FXCollections.observableArrayList(employeeService.getAllEmployees());
        tvEmployee.setItems(employeeList);
    }

    private void populateFields() {
        Employee employee = tvEmployee.getSelectionModel().getSelectedItem();
        if (employee != null) {
            txtID.setText(String.valueOf(employee.getEmpId()));
            txtFName.setText(employee.getFirstName());
            txtLName.setText(employee.getLastName());
            txtUsrName.setText(employee.getUserName());
            txtPasword.setText(employee.getPassword());
            if (employee.getDob() != null) {
                txtdob.setText(sdf.format(employee.getDob())); // Format date for text field
            }
        }
    }

    @FXML
    private void insertEmployee() {
        Employee employee = new Employee();
        employee.setEmpId(0);
        employee.setFirstName(txtFName.getText());
        employee.setLastName(txtLName.getText());
        employee.setUserName(txtUsrName.getText());
        employee.setPassword(txtPasword.getText());

        // Parse date from text field
        if (!txtdob.getText().isEmpty()) {
            try {
                java.util.Date utilDate = sdf.parse(txtdob.getText()); // Parse string to java.util.Date
                employee.setDob(new Date(utilDate.getTime())); // Convert to java.sql.Date
            } catch (ParseException e) {
                e.printStackTrace(); // Handle parsing error
            }
        }

        employeeService.insertEmployee(employee);
        loadEmployeeData();
        clearFields();
    }

    @FXML
    private void updateEmployee() {
        Employee employee = new Employee();
        employee.setEmpId(Long.parseLong(txtID.getText()));
        employee.setFirstName(txtFName.getText());
        employee.setLastName(txtLName.getText());
        employee.setUserName(txtUsrName.getText());
        employee.setPassword(txtPasword.getText());

        // Parse date from text field
        if (!txtdob.getText().isEmpty()) {
            try {
                java.util.Date utilDate = sdf.parse(txtdob.getText());
                java.sql.Date sqlDate = new Date(utilDate.getTime()); // Convert to java.sql.Date
                employee.setDob(sqlDate); // Set the SQL date
                System.out.println("Parsed DOB: " + sqlDate); // Debugging line
            } catch (ParseException e) {
                e.printStackTrace(); // Handle parsing error
            }
        }

        // Debugging: Print the Employee DOB before updating
        System.out.println("Updating Employee DOB: " + employee.getDob());

        employeeService.updateEmployee(employee);
        loadEmployeeData();
        clearFields();
    }

    @FXML
    private void deleteEmployee() {
        Employee selectedEmployee = tvEmployee.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            int id = Integer.parseInt(txtID.getText());
            employeeService.deleteEmployee(id); // Call delete without assignment

            // Reload data and clear fields
            loadEmployeeData();
            clearFields();
            tvEmployee.getSelectionModel().clearSelection();
        } else {
            System.out.println("No employee selected.");
        }
    }

    private void clearFields() {
        txtID.clear();
        txtFName.clear();
        txtLName.clear();
        txtUsrName.clear();
        txtPasword.clear();
        txtdob.clear();
    }
}