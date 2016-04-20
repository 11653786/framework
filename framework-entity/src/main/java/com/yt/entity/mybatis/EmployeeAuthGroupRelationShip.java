package com.yt.entity.mybatis;

public class EmployeeAuthGroupRelationShip {
    private Integer id;

    private Integer employeeId;

    private Integer authGroupId;

    private String employeeName;

    private String authGroupName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getAuthGroupId() {
        return authGroupId;
    }

    public void setAuthGroupId(Integer authGroupId) {
        this.authGroupId = authGroupId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName == null ? null : employeeName.trim();
    }

    public String getAuthGroupName() {
        return authGroupName;
    }

    public void setAuthGroupName(String authGroupName) {
        this.authGroupName = authGroupName == null ? null : authGroupName.trim();
    }
}