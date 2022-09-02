package com.SamPage.controller;

import com.SamPage.entity.Management;
import com.SamPage.entity.Ticket;
import com.SamPage.service.ManagementService;
import com.SamPage.service.TicketService;

import java.util.List;
import java.util.Scanner;

public class ManagementCommandLineInterface {
    public static void managementMenu(){
        ManagementService managementService = new ManagementService();
        TicketService ticketService = new TicketService();

        Management manager = null;
        Ticket ticket = null;

        Scanner intScan = new Scanner(System.in);
        Scanner stringScan = new Scanner(System.in);
        while(true) {
            printOptionsManage();
            int choice = intScan.nextInt();
            switch (choice) {
                case 1:
                    if (manager != null) {
                        System.out.println("You are already logged in");
                        break;
                    }
                    System.out.print("Username: ");
                    String username = stringScan.nextLine();
                    System.out.print("Password: ");
                    String password = stringScan.nextLine();
                    manager = managementService.login(username, password);
                    if (manager == null) {
                        System.out.println("Login Unsuccessful");
                    }
                    break;
                case 2:
                    if (manager == null) {
                        System.out.println("You must be logged in to view all employees");
                        break;
                    }

                    List<Management> employees = managementService.getAllUsers();
                    System.out.println("Here is a list of all employees");
                    for (Management management : employees) {
                        System.out.println(management);
                    }
                    break;
                case 3:
                    if (manager == null) {
                        System.out.println("You must login to see all submitted tickets");
                        break;
                    }
                    List<Ticket> tickets = managementService.getAllTickets(manager.getId());
                    for (int i = 0; i < tickets.size(); i++) {
                        System.out.println(tickets.get(i));
                    }
                    break;
                case 4:
                    if (manager == null) {
                        System.out.println("You must log in to approve or deny tickets");
                        break;
                    }
                    if (ticket != null) {
                        System.out.println("There is already a ticket open");
                    }
                    System.out.println("Which ticket are you altering?");
                    System.out.print("Ticket ID: ");
                    int ticketid = intScan.nextInt();
                    System.out.print("Status: ");
                    String status = stringScan.nextLine();
                    Ticket ticket1 = new Ticket(ticketid, status);
                    ticket = ticketService.updateTicket(ticket1);
                    break;
                default:
                    System.out.println("Not a valid option");
                    break;

        }
    }

}


    public static void printOptionsManage(){
        System.out.println("What would you like to do?");
        System.out.println("1 - Login");
        System.out.println("2 - View All Employees");
        System.out.println("3 - View Ticket Submissions");
        System.out.println("4 - Approve or Deny Ticket Submissions");
//        System.out.println("6 - Promote to Manager");
//        System.out.println("7 - Demote to Employee");
        System.out.print("Enter => ");

    }
}
