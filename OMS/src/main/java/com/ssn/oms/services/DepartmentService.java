package com.ssn.oms.services;

import com.ssn.oms.database.DatabaseConnection;
import com.ssn.oms.models.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentService {

    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        String query = "SELECT * FROM department";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Department dept = new Department();
                dept.setDeptId(rs.getLong("deptId"));
                dept.setDeptName(rs.getString("deptName"));
                dept.setLocation(rs.getString("location"));
                departments.add(dept);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return departments;
    }

    public void insertDepartment(Department department) {
        String query = "INSERT INTO department (deptName, location) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, department.getDeptName());
            pstmt.setString(2, department.getLocation());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDepartment(Department department) {
        String query = "UPDATE department SET deptName=?, location=? WHERE deptId=?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, department.getDeptName());
            pstmt.setString(2, department.getLocation());
            pstmt.setLong(3, department.getDeptId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDepartment(long deptId) {
        String query = "DELETE FROM department WHERE deptId=?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setLong(1, deptId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
