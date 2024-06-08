package model.beans;

import java.math.BigDecimal;
import java.util.Date;

public class Employee {
    private int employeeId;
    private String fullNameEmployee;
    private String email;
    private String password;
    private String phoneNumber;
    private Date hireDate;
    private String jobId;
    private BigDecimal salary;
    private BigDecimal commissionPct;
    private Integer managerId;
    private Integer departmentId;
    private int enabled;

    // Constructor
    public Employee(int employeeId, String fullNameEmployee, String email, String password, String phoneNumber, Date hireDate, String jobId, BigDecimal salary, BigDecimal commissionPct, Integer managerId, Integer departmentId, int enabled) {
        this.employeeId = employeeId;
        this.fullNameEmployee = fullNameEmployee;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.jobId = jobId;
        this.salary = salary;
        this.commissionPct = commissionPct;
        this.managerId = managerId;
        this.departmentId = departmentId;
        this.enabled = enabled;
    }

    public Employee() {

    }

    // Getters y Setters
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFullNameEmployee() {
        return fullNameEmployee;
    }

    public void setFullNameEmployee(String fullNameEmployee) {
        this.fullNameEmployee = fullNameEmployee;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public BigDecimal getCommissionPct() {
        return commissionPct;
    }

    public void setCommissionPct(BigDecimal commissionPct) {
        this.commissionPct = commissionPct;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
