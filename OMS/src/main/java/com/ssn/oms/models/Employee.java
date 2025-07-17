package com.ssn.oms.models;

import javafx.beans.property.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class Employee {
    private LongProperty empId;
    private StringProperty firstName;
    private StringProperty middleName;
    private StringProperty lastName;
    private ObjectProperty<Date> dob;
    private StringProperty address;
    private StringProperty email;
    private StringProperty phone;
    private StringProperty bloodGroup;
    private StringProperty userName;
    private StringProperty password;
    private StringProperty role;
    private StringProperty designation;
    private DoubleProperty basicPay;
    private StringProperty credits;
    private ObjectProperty<Employee> reportingTo;
    private StringProperty status;

    // Default Constructor
    public Employee() {
        this.empId = new SimpleLongProperty();
        this.firstName = new SimpleStringProperty();
        this.middleName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.dob = new SimpleObjectProperty<>();
        this.address = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.phone = new SimpleStringProperty();
        this.bloodGroup = new SimpleStringProperty();
        this.userName = new SimpleStringProperty();
        this.password = new SimpleStringProperty();
        this.role = new SimpleStringProperty();
        this.designation = new SimpleStringProperty();
        this.basicPay = new SimpleDoubleProperty();
        this.credits = new SimpleStringProperty();
        this.reportingTo = new SimpleObjectProperty<>();
        this.status = new SimpleStringProperty();
    }

    // Constructor for existing records (including empId)
    public Employee(long empId) {
        this();
        this.empId.set(empId);
    }

    // Getters and Property Methods
    public long getEmpId() { return empId.get(); }
    public LongProperty empIdProperty() { return empId; }
    public void setEmpId(long empId) { this.empId.set(empId); }

    public String getFirstName() { return firstName.get(); }
    public StringProperty firstNameProperty() { return firstName; }
    public void setFirstName(String firstName) { this.firstName.set(firstName); }

    public String getMiddleName() { return middleName.get(); }
    public StringProperty middleNameProperty() { return middleName; }
    public void setMiddleName(String middleName) { this.middleName.set(middleName); }

    public String getLastName() { return lastName.get(); }
    public StringProperty lastNameProperty() { return lastName; }
    public void setLastName(String lastName) { this.lastName.set(lastName); }

    public Date getDob() { return dob.get(); }
    public ObjectProperty<Date> dobProperty() { return dob; }
    public void setDob(Date dob) { this.dob.set(dob); }

    public String getAddress() { return address.get(); }
    public StringProperty addressProperty() { return address; }
    public void setAddress(String address) { this.address.set(address); }

    public String getEmail() { return email.get(); }
    public StringProperty emailProperty() { return email; }
    public void setEmail(String email) { this.email.set(email); }

    public String getPhone() { return phone.get(); }
    public StringProperty phoneProperty() { return phone; }
    public void setPhone(String phone) { this.phone.set(phone); }

    public String getBloodGroup() { return bloodGroup.get(); }
    public StringProperty bloodGroupProperty() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup.set(bloodGroup); }

    public String getUserName() { return userName.get(); }
    public StringProperty userNameProperty() { return userName; }
    public void setUserName(String userName) { this.userName.set(userName); }

    public String getPassword() { return password.get(); }
    public StringProperty passwordProperty() { return password; }
    public void setPassword(String password) { this.password.set(password); }

    public String getRole() { return role.get(); }
    public StringProperty roleProperty() { return role; }
    public void setRole(String role) { this.role.set(role); }

    public String getDesignation() { return designation.get(); }
    public StringProperty designationProperty() { return designation; }
    public void setDesignation(String designation) { this.designation.set(designation); }

    public double getBasicPay() { return basicPay.get(); }
    public DoubleProperty basicPayProperty() { return basicPay; }
    public void setBasicPay(double basicPay) { this.basicPay.set(basicPay); }

    public String getCredits() { return credits.get(); }
    public StringProperty creditsProperty() { return credits; }
    public void setCredits(String credits) { this.credits.set(credits); }

    public Employee getReportingTo() { return reportingTo.get(); }
    public ObjectProperty<Employee> reportingToProperty() { return reportingTo; }
    public void setReportingTo(Employee reportingTo) { this.reportingTo.set(reportingTo); }

    public String getStatus() { return status.get(); }
    public StringProperty statusProperty() { return status; }
    public void setStatus(String status) { this.status.set(status); }

    // Method for formatted DOB
    public StringProperty formattedDobProperty() {
        SimpleStringProperty formattedDob = new SimpleStringProperty();
        if (dob.get() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            formattedDob.set(sdf.format(dob.get()));
        }
        return formattedDob;
    }

    // Overriding toString for debugging/logging
    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId.get() +
                ", firstName='" + firstName.get() + '\'' +
                ", lastName='" + lastName.get() + '\'' +
                ", email='" + email.get() + '\'' +
                '}';
    }
}
