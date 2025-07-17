package com.ssn.oms.controllers;


import com.ssn.oms.OMSApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AdministratorDashboardController {

    @FXML
    private Button manageEmployeesButton;

    @FXML
    private Button manageDepartmentsButton;

    @FXML
    private Button viewReportsButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button addEmployeeButton;

    @FXML
    private Button removeEmployeeButton;

    @FXML
    private Button generateReportButton;

    @FXML
    private TextField txtTemp;

    @FXML
    public void handleManageEmployees() {
        try {
            // Load the FXML file for the Administrator Dashboard
            FXMLLoader loader = new FXMLLoader(OMSApplication.class.getResource("employee.fxml"));
            Parent root = loader.load();

            // Create a new stage for the Administrator Dashboard
            Stage stage = new Stage();
            stage.setTitle("Dashboard");

            // Set the scene with the loaded FXML and apply a CSS stylesheet
            Scene scene = new Scene(root);
            scene.getStylesheets().add(OMSApplication.class.getResource("employee.css").toExternalForm());

            // Set the scene to the stage
            stage.setScene(scene);
            //stage.setResizable(false);  // Optional: make the stage non-resizable
            Stage currentStage = (Stage) txtTemp.getScene().getWindow();
            currentStage.close();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void handleManageDepartments() {
        try {
            System.out.println("123");
            System.out.println(OMSApplication.class.getResource("department_management.fxml"));

            // Load the FXML file for the Department Management interface
            FXMLLoader loader = new FXMLLoader(OMSApplication.class.getResource("department_management.fxml"));
            Parent root = loader.load();
            // Create a new stage for the Department Management interface
            Stage stage = new Stage();
            stage.setTitle("Manage Departments");

            // Set the scene with the loaded FXML and apply a CSS stylesheet
            Scene scene = new Scene(root);
            scene.getStylesheets().add(OMSApplication.class.getResource("department_management.css").toExternalForm());

            // Set the scene to the stage
            stage.setScene(scene);
            // Optional: make the stage non-resizable
            // stage.setResizable(false);
            Stage currentStage = (Stage) txtTemp.getScene().getWindow(); // Ensure txtTemp is defined in your class
            currentStage.close(); // Close the current stage
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void handleViewReports() {
        // Logic to view reports
        showAlert("View Reports clicked!");
    }

    @FXML
    public void handleSettings() {
        // Logic for settings
        showAlert("Settings clicked!");
    }

    @FXML
    public void handleLogout() {
        // Logic to log out
        showAlert("Logged out successfully!");
    }

    @FXML
    public void handleAddEmployee() {
        // Logic to add an employee
        showAlert("Add Employee clicked!");
    }

    @FXML
    public void handleRemoveEmployee() {
        // Logic to remove an employee
        showAlert("Remove Employee clicked!");
    }

    @FXML
    public void handleGenerateReport() {
        // Logic to generate a report
        showAlert("Generate Report clicked!");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
