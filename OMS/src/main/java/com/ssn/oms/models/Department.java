package com.ssn.oms.models;

import javafx.beans.property.*;

public class Department {
    private LongProperty deptId;
    private StringProperty deptName;
    private StringProperty location;

    // Default Constructor
    public Department() {
        this.deptId = new SimpleLongProperty();
        this.deptName = new SimpleStringProperty();
        this.location = new SimpleStringProperty();
    }

    public long getDeptId() {
        return deptId.get();
    }

    public void setDeptId(long deptId) {
        this.deptId.set(deptId);
    }

    public LongProperty deptIdProperty() {
        return deptId;
    }

    public String getDeptName() {
        return deptName.get();
    }

    public void setDeptName(String deptName) {
        this.deptName.set(deptName);
    }

    public StringProperty deptNameProperty() {
        return deptName;
    }

    public String getLocation() {
        return location.get();
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    public StringProperty locationProperty() {
        return location;
    }

    @Override
    public String toString() {
        return "Department{" +
                "deptId=" + deptId.get() +
                ", deptName='" + deptName.get() + '\'' +
                ", location='" + location.get() + '\'' +
                '}';
    }
}
