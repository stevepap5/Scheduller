package com.example.scheduller.Employees;

import androidx.room.PrimaryKey;

import java.util.List;

public class Employee {

//    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String employeeName;

    private String employeeSurname;

    private String employeeHiringDate;

    private String employeeSkills;


    public void setEmployeeSurname(String employeeSurname) {
        this.employeeSurname = employeeSurname;
    }

    public String getEmployeeSkills() {
        return employeeSkills;
    }

    public void setEmployeeSkills(String employeeSkills) {
        this.employeeSkills = employeeSkills;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeHiringDate() {
        return employeeHiringDate;
    }

    public void setEmployeeHiringDate(String employeeHiringDate) {
        this.employeeHiringDate = employeeHiringDate;
    }

    public String getEmployeeSurname() {
        return employeeSurname;
    }

}
