package com.ssn.oms.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class ManagerDashboardController {
    private int loggedInManagerId; // Declare this variable

    @FXML
    private ListView<String> taskListView; // Added ListView reference

    // Method to set the logged-in manager's ID
    public void setLoggedInManagerId(int managerId) {
        this.loggedInManagerId = managerId;
    }

    @FXML
    private void handleCreateMilestone() {
        showMilestoneDialog();
    }

    @FXML
    private void handleAssignTask() {
        System.out.println("Assign Task button clicked."); // Debug statement
        showTaskDialog();
    }

    @FXML
    private void handleApproveLeave() {
        showLeaveApprovalDialog();
    }

    @FXML
    private void handleScheduleMeeting() {
        showMeetingDialog();
    }

    @FXML
    private void handleGenerateReport() {
        showReportDialog();
    }

    @FXML
    private void handleChangePassword() {
        showChangePasswordDialog();
    }

    @FXML
    private void handleSetTheme() {
        showThemeSelectionDialog();
    }

    private void showThemeSelectionDialog() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Set Theme");
        dialog.setHeaderText("Select a color theme for the application.");

        ComboBox<String> themeComboBox = new ComboBox<>();
        themeComboBox.getItems().addAll("Light", "Dark", "Blue", "Green");
        themeComboBox.setPromptText("Select a theme");

        VBox vbox = new VBox(10, new Label("Select Theme:"), themeComboBox);
        dialog.getDialogPane().setContent(vbox);

        ButtonType setThemeButtonType = new ButtonType("Set Theme", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(setThemeButtonType, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == setThemeButtonType) {
                return themeComboBox.getValue();
            }
            return null;
        });

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(theme -> {
            Stage primaryStage = (Stage) dialog.getDialogPane().getScene().getWindow();
            applyTheme(primaryStage, theme);
            showAlert("Theme Set", "The theme has been changed to " + theme + ".");
        });
    }

    private void applyTheme(Stage primaryStage, String theme) {
        String stylesheet = "";

        switch (theme) {
            case "Light":
                stylesheet = getClass().getResource("/styles/light-theme.css").toExternalForm();
                break;
            case "Dark":
                stylesheet = getClass().getResource("/styles/dark-theme.css").toExternalForm();
                break;
            case "Blue":
                stylesheet = getClass().getResource("/styles/blue-theme.css").toExternalForm();
                break;
            case "Green":
                stylesheet = getClass().getResource("/styles/green-theme.css").toExternalForm();
                break;
            default:
                return; // No valid theme selected
        }

        primaryStage.getScene().getStylesheets().clear();
        primaryStage.getScene().getStylesheets().add(stylesheet);
    }

    private void showMilestoneDialog() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Create Milestone");

        TextField milestoneField = new TextField();
        milestoneField.setPromptText("Enter milestone details");

        VBox vbox = new VBox(10, new Label("Milestone Details:"), milestoneField);
        dialog.getDialogPane().setContent(vbox);

        ButtonType createMilestoneButtonType = new ButtonType("Create", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(createMilestoneButtonType, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == createMilestoneButtonType) {
                return milestoneField.getText();
            }
            return null;
        });

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(milestone -> showAlert("Milestone Created", "Milestone: " + milestone + " has been created."));
    }

    private void showTaskDialog() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Assign Task");

        TextField taskNameField = new TextField();
        taskNameField.setPromptText("Enter task name");
        TextField taskDetailsField = new TextField();
        taskDetailsField.setPromptText("Enter task details");
        TextField assignedToField = new TextField();
        assignedToField.setPromptText("Enter employee ID to assign to");

        VBox vbox = new VBox(10, new Label("Task Name:"), taskNameField,
                new Label("Task Details:"), taskDetailsField,
                new Label("Assign To (Employee ID):"), assignedToField);
        dialog.getDialogPane().setContent(vbox);

        ButtonType assignTaskButtonType = new ButtonType("Assign", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(assignTaskButtonType, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == assignTaskButtonType) {
                String assignedTask = taskNameField.getText() + " - " + taskDetailsField.getText() + " (Assigned to: " + assignedToField.getText() + ")";
                saveTaskToDatabase(taskNameField.getText(), taskDetailsField.getText(), Integer.parseInt(assignedToField.getText()));
                return assignedTask;
            }
            return null;
        });

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(task -> {
            showAlert("Task Assigned", task);
            updateTaskListView(task); // Update the ListView with the newly assigned task
        });
    }

    private void saveTaskToDatabase(String taskName, String taskDetails, int assignedTo) {
        String sql = "INSERT INTO task (taskName, assignedTo, assignedBy, status, description) VALUES (?, ?, ?, ?, ?)";
        int managerId = loggedInManagerId; // Assuming you have this value set when the manager logs in

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "user", "password");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, taskName);
            pstmt.setInt(2, assignedTo);
            pstmt.setInt(3, managerId);
            pstmt.setString(4, "Pending"); // Default status
            pstmt.setString(5, taskDetails);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateTaskListView(String task) {
        taskListView.getItems().add(task); // Add the new task to the ListView
    }

    private void showLeaveApprovalDialog() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Approve Leave");

        TextField employeeNameField = new TextField();
        employeeNameField.setPromptText("Enter employee name");
        TextField leaveDatesField = new TextField();
        leaveDatesField.setPromptText("Enter leave dates (e.g., 2023-10-10 to 2023-10-15)");

        VBox vbox = new VBox(10, new Label("Employee Name:"), employeeNameField, new Label("Leave Dates:"), leaveDatesField);
        dialog.getDialogPane().setContent(vbox);

        ButtonType approveLeaveButtonType = new ButtonType("Approve", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(approveLeaveButtonType, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == approveLeaveButtonType) {
                return employeeNameField.getText() + " - " + leaveDatesField.getText();
            }
            return null;
        });

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(leave -> showAlert("Leave Approved", "Leave approved for: " + leave + "."));
    }

    private void showMeetingDialog() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Schedule Meeting");

        TextField meetingSubjectField = new TextField();
        meetingSubjectField.setPromptText("Enter meeting subject");
        TextField meetingDateField = new TextField();
        meetingDateField.setPromptText("Enter meeting date (YYYY-MM-DD)");
        TextField meetingTimeField = new TextField();
        meetingTimeField.setPromptText("Enter meeting time (HH:MM)");

        VBox vbox = new VBox(10, new Label("Meeting Subject:"), meetingSubjectField,
                new Label("Meeting Date:"), meetingDateField, new Label("Meeting Time:"), meetingTimeField);
        dialog.getDialogPane().setContent(vbox);

        ButtonType scheduleMeetingButtonType = new ButtonType("Schedule", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(scheduleMeetingButtonType, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == scheduleMeetingButtonType) {
                return meetingSubjectField.getText() + " on " + meetingDateField.getText() + " at " + meetingTimeField.getText();
            }
            return null;
        });

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(meeting -> showAlert("Meeting Scheduled", "Meeting scheduled: " + meeting + "."));
    }

    private void showReportDialog() {
        showAlert("Report", "Generating reports is not yet implemented.");
    }

    private void showChangePasswordDialog() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Change Password");

        TextField newPasswordField = new TextField();
        newPasswordField.setPromptText("Enter new password");
        TextField confirmPasswordField = new TextField();
        confirmPasswordField.setPromptText("Confirm new password");

        VBox vbox = new VBox(10, new Label("New Password:"), newPasswordField,
                new Label("Confirm Password:"), confirmPasswordField);
        dialog.getDialogPane().setContent(vbox);

        ButtonType changePasswordButtonType = new ButtonType("Change", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(changePasswordButtonType, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == changePasswordButtonType) {
                return newPasswordField.getText();
            }
            return null;
        });

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(password -> showAlert("Password Changed", "Password successfully changed."));
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, content, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}