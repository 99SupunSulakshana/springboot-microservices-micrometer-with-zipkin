package com.example.employee_service.dto;

public class EmployeeDTO {
    private String employeeId;
    private String email;
    private String address;
    private String salary;
    private String departmentCode;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String employeeId, String email, String address, String salary, String departmentCode) {
        this.employeeId = employeeId;
        this.email = email;
        this.address = address;
        this.salary = salary;
        this.departmentCode = departmentCode;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
