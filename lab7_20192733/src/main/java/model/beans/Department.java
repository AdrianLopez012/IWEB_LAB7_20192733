package model.beans;

public class Department {

    private Integer departmentId;
    private Integer managerId;
    private Integer locationId;
    private String departmentName;

    public Department(Integer departmentId, Integer managerId, Integer locationId, String departmentName) {
        this.departmentId = departmentId;
        this.managerId = managerId;
        this.locationId = locationId;
        this.departmentName = departmentName;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }



}
