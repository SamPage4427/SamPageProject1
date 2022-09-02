package com.SamPage.entity;

public class Ticket {
    private int ticketid;
    private String username;
    private double amount;
    private String description;
    private String status = "pending";

    public Ticket(){

    }
    public Ticket(int ticketid, String username, double amount, String description, String status) {
        this.ticketid = ticketid;
        this.username = username;
        this.amount = amount;
        this.description = description;
        this.status = status;
    }
    public Ticket(String username, double amount, String description){
        this.username = username;
        this.amount = amount;
        this.description = description;
    }
    public Ticket(double amount, String description){
        this.amount = amount;
        this.description = description;
    }

    public Ticket(int ticketid, double amount, String description) {
        this.ticketid = ticketid;
        this.amount = amount;
        this.description = description;
    }



    public Ticket(int ticketid, String status) {
        this.ticketid = ticketid;
        this.status = status;
    }

    public Ticket(int ticketid) {
        this.ticketid = ticketid;
    }


    public int getTicketid() {
        return ticketid;
    }

    public void setTicketid(int ticketid) {
        this.ticketid = ticketid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketid=" + ticketid +
                ", username='" + username + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }


}
