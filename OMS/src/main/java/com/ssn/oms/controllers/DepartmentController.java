package com.ssn.oms.controllers;

import com.ssn.oms.models.Department;
import com.ssn.oms.services.DepartmentService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class DepartmentController {

    private DepartmentService departmentService = new DepartmentService();
    private ObservableList<Department> departmentList = FXCollections.observableArrayList();

    @FXML
    private TableView<Department> departmentTable;

    @FXML
    private TableColumn<Department, Long> deptIdColumn;

    @FXML
    private TableColumn<Department, String> deptNameColumn;

    @FXML
    private TableColumn<Department, String> locationColumn;

    @FXML
    private TextField deptNameField;

    @FXML
    private TextField locationField;

    @FXML
    private TextField txtEmployeeCount; // Ensure this is declared for employee count input.

    @FXML
    private void initialize() {
        deptIdColumn.setCellValueFactory(cellData -> cellData.getValue().deptIdProperty().asObject());
        deptNameColumn.setCellValueFactory(cellData -> cellData.getValue().deptNameProperty());
        locationColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());

        departmentList.setAll(departmentService.getAllDepartments());
        departmentTable.setItems(departmentList);
    }

    @FXML
    private void handleAddDepartment() {
        Department department = new Department();
        department.setDeptName(deptNameField.getText());
        department.setLocation(locationField.getText());

        departmentService.insertDepartment(department);
        departmentList.setAll(departmentService.getAllDepartments());
    }

    @FXML
    private void handleUpdateDepartment() {
        Department selectedDepartment = departmentTable.getSelectionModel().getSelectedItem();
        if (selectedDepartment != null) {
            selectedDepartment.setDeptName(deptNameField.getText());
            selectedDepartment.setLocation(locationField.getText());

            departmentService.updateDepartment(selectedDepartment);
            departmentList.setAll(departmentService.getAllDepartments());
        }
    }

    @FXML
    private void handleDeleteDepartment() {
        Department selectedDepartment = departmentTable.getSelectionModel().getSelectedItem();
        if (selectedDepartment != null) {
            departmentService.deleteDepartment(selectedDepartment.getDeptId());
            departmentList.setAll(departmentService.getAllDepartments());
        }
    }
}
