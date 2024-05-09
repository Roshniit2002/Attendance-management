package com.example.demo.Controller;

public class EmployeeRequest {

    private String name;
    private String role;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private Long manager_id;
    
    public EmployeeRequest() {
    }

    public EmployeeRequest(String name, String role, String username, String password, String email, String phoneNumber, Long manager_id) {
        this.name = name;
        this.role = role;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.manager_id = manager_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

   
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

        public Long getManager_id() {
        return manager_id;
    }

    public void setManagerId(Long manager_id) {
        this.manager_id = manager_id;
    }
}

