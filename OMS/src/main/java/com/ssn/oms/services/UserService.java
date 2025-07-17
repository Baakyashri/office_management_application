package com.ssn.oms.services;


import com.ssn.oms.database.DatabaseConnection;
import com.ssn.oms.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    public User validateUser(String username, String password) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            String query = "SELECT * FROM Employee WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String role = resultSet.getString("role");
                return new User(username, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
