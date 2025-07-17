package com.ssn.oms.models;

public class Admin extends User {
    private String adminLevel;

    public Admin(String username, String role, String adminLevel) {
        super(username, role); // Calls the constructor of User
        this.adminLevel = adminLevel;
    }

    public String getAdminLevel() {
        return adminLevel;
    }

    public void manageUsers() {
        System.out.println(getUsername() + " is managing users.");
    }
}
