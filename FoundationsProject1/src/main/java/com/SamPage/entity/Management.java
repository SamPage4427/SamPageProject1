package com.SamPage.entity;

public class Management {
    private int id;
    private String first_name;
    private String last_name;
    private String username;
    private String password;

    private String position;
    private String role;

    public Management(){

    }
    public Management(int id, String first_name, String last_name, String username, String password, String position, String role) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.password = password;
        this.position = position;
        this.role = role;
    }

    public Management(int id, String first_name, String last_name, String username, String position, String role){
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.position = position;
        this.role = role;
    }

    public Management(String username, String password){
        this.username = username;
        this.password = password;
    }



    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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
        return "Employees{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", username='" + username + '\'' +
                ", position='" + position + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
