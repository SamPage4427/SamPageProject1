package com.SamPage.entity;

public class Employee {
    private int userID;
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private String position;
    private String role;


    public Employee(){

    }


    public Employee(int userID, String firstname, String lastname, String username, String password, String position, String role) {
        this.userID = userID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.position = position;
        this.role = role;
    }


    public Employee(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Employee(String firstname, String lastname, String username, String password, String position){
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.position = position;
        this.role = "Employee";

    }


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName(){
        return firstname;
    }
    public void setFirstName(String firstname){
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", password='" + password + '\'' +
                ", position='" + position + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}