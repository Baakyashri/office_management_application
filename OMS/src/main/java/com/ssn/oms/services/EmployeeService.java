package com.ssn.oms.services;

import com.ssn.oms.database.DatabaseConnection;
import com.ssn.oms.models.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employee";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Employee emp = new Employee();
                emp.setEmpId(rs.getInt("empId"));
                emp.setFirstName(rs.getString("firstName"));
                emp.setLastName(rs.getString("lastName"));
                emp.setUserName(rs.getString("username"));
                emp.setPassword(rs.getString("password"));
                emp.setDob(rs.getDate("dob")); // Retrieve DOB

                employees.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    public void insertEmployee(Employee employee) {
        String query = "INSERT INTO employee (firstName, lastName, username, password, dob) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, employee.getFirstName());
            pstmt.setString(2, employee.getLastName());
            pstmt.setString(3, employee.getUserName());
            pstmt.setString(4, employee.getPassword());
            pstmt.setDate(5, employee.getDob()); // Set DOB correctly
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee(Employee employee) {
        String query = "UPDATE employee SET firstName=?, lastName=?, username=?, password=?, dob=? WHERE empId=?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, employee.getFirstName());
            pstmt.setString(2, employee.getLastName());
            pstmt.setString(3, employee.getUserName());
            pstmt.setString(4, employee.getPassword());

            // Get the Date from employee object and convert to java.sql.Date
            Date dobDate = employee.getDob();
            if (dobDate != null) {
                pstmt.setDate(5, new java.sql.Date(dobDate.getTime())); // Convert to java.sql.Date
            } else {
                pstmt.setNull(5, java.sql.Types.DATE); // Handle null case if DOB is not set
            }

            pstmt.setLong(6, employee.getEmpId()); // Use setLong for empId

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteEmployee(int id) {
        String query = "DELETE FROM employee WHERE empId = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}