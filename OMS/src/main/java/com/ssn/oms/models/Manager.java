package com.ssn.oms.models;

public class Manager extends User {
    private String department;

    public Manager(String username, String role, String department) {
        super(username, role); // Calls the constructor of User
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void assignTasks() {
        System.out.println(getUsername() + " is assigning tasks.");
    }
}
