package com.ssn.oms.controllers;

import com.ssn.oms.OMSApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import com.ssn.oms.models.User;
import com.ssn.oms.services.UserService;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorMessage;

    private UserService userService = new UserService();

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        User user = userService.validateUser(username, password);

        if (user != null) {
            // Redirect to respective dashboard based on user role
            switch (user.getRole()) {
                case "Administrator":
                    loadAdminDashboard();
                    break;
                case "Manager":
                    loadManagerDashboard();
                    break;
                case "User":
                    loadEmployeeDashboard();
                    break;
            }
        } else {
            errorMessage.setText("Invalid username or password.");
            errorMessage.setVisible(true);
        }
    }

    private void loadAdminDashboard() {
        try {
            // Load the FXML file for the Administrator Dashboard
            FXMLLoader loader = new FXMLLoader(OMSApplication.class.getResource("administrator_dashboard.fxml"));
            Parent root = loader.load();

            // Create a new stage for the Administrator Dashboard
            Stage stage = new Stage();
            stage.setTitle("Administrator Dashboard");

            // Set the scene with the loaded FXML and apply a CSS stylesheet
            Scene scene = new Scene(root);
            scene.getStylesheets().add(OMSApplication.class.getResource("administrator_dashboard.css").toExternalForm());

            // Set the scene to the stage
            stage.setScene(scene);
            //stage.setResizable(false);  // Optional: make the stage non-resizable
            Stage currentStage = (Stage) usernameField.getScene().getWindow();
            currentStage.close();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void loadManagerDashboard() {
        // Load manager dashboard FXML
        try {
            // Load the FXML file for the Manager Dashboard
            FXMLLoader loader = new FXMLLoader(OMSApplication.class.getResource("manager_dashboard.fxml"));
            Parent root = loader.load();

            // Create a new stage for the Manager Dashboard
            Stage stage = new Stage();
            stage.setTitle("Manager Dashboard");

            // Set the scene with the loaded FXML and apply a CSS stylesheet
            Scene scene = new Scene(root);
            scene.getStylesheets().add(OMSApplication.class.getResource("manager_dashboard.css").toExternalForm());

            // Set the scene to the stage
            stage.setScene(scene);
            //stage.setResizable(false);  // Optional: make the stage non-resizable
            Stage currentStage = (Stage) usernameField.getScene().getWindow();
            currentStage.close();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadEmployeeDashboard() {
        try {
            // Load the FXML file for the Employee Dashboard
            FXMLLoader loader = new FXMLLoader(OMSApplication.class.getResource("employee_dashboard.fxml"));
            Parent root = loader.load();

            // Create a new stage for the Employee Dashboard
            Stage stage = new Stage();
            stage.setTitle("Employee Dashboard");

            // Set the scene with the loaded FXML and apply a CSS stylesheet
            Scene scene = new Scene(root);
            scene.getStylesheets().add(OMSApplication.class.getResource("employee_dashboard.css").toExternalForm());

            // Set the scene to the stage
            stage.setScene(scene);
            // stage.setResizable(false); // Optional: make the stage non-resizable
            Stage currentStage = (Stage) usernameField.getScene().getWindow();
            currentStage.close();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
