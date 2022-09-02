package com.SamPage.controller;
import com.SamPage.entity.Employee;
import com.SamPage.service.EmployeeService;
import com.SamPage.service.TicketService;
import com.SamPage.entity.Ticket;

import java.util.List;
import java.util.Scanner;

public class EmployeeCommandLineInterface {
    public static void menu(){
        //initialize both EmployeeService and TicketService
        EmployeeService employeeService = new EmployeeService();
        TicketService ticketService = new TicketService();

        //Stores the current logged in employee
        Employee employee = null;
        //Stores the reimbursement ticket
        Ticket ticket = null;
        //implement scanners to get the user input
        Scanner intScan = new Scanner(System.in);
        Scanner stringScan = new Scanner(System.in);
        while(true){
            printOptions();
            int choice = intScan.nextInt();
            switch (choice){
                case 1:
                    //check to see if the user is logged in or not
                    if(employee != null){
                        System.out.println("Someone is already logged in.");
                        break;
                    }
                    System.out.print("First Name: ");
                    String firstname = stringScan.nextLine();
                    System.out.print("Last Name: ");
                    String lastname = stringScan.nextLine();
                    System.out.print("Create Username: ");
                    String username = stringScan.nextLine();
                    System.out.print("Create Password: ");
                    String password = stringScan.nextLine();
                    System.out.print("Position: ");
                    String position = stringScan.nextLine();
                    Employee newUser = new Employee(firstname, lastname, username, password, position);
                    employee = employeeService.register(newUser);
                    if(newUser != null){
                        System.out.println("User is already in the Database");
                    }else{
                        System.out.println("Account Successfully Created");
                    }
                    break;
                case 2:
                    if(employee != null){
                        System.out.println("You are already logged in.");
                        break;
                    }
                    System.out.print("Username: ");
                    username = stringScan.nextLine();
                    System.out.print("Password: ");
                    password = stringScan.nextLine();
                    employee = employeeService.login(username, password);
                    if(employee == null){
                        System.out.println("Login Unsuccessful.");
                    }
                    break;
                case 3:
                    if(employee == null){
                        System.out.println("You are not logged in");
                        break;
                    }
                    if(ticket != null){
                        System.out.println("There is already a ticket open");
                        break;
                    }
                    System.out.print("Username: ");
                    username = stringScan.nextLine();
                    System.out.print("Amount: ");
                    double amount = intScan.nextDouble();
                    System.out.print("Description: ");
                    String description = stringScan.nextLine();
                    Ticket newTicket = new Ticket(username, amount, description);
                    ticket = ticketService.createTicket(newTicket);
                    break;
                case 4:
                    if(employee == null){
                        System.out.println("You are not logged in.");
                        break;
                    }
                    System.out.println("Here is a list of all submissions: ");
                    List<Ticket> tickets = ticketService.getAllTickets();
                    for(int i = 0; i < tickets.size(); i++){
                        System.out.println(tickets.get(i));
                    }
                    break;
                case 5:
                    if(employee == null){
                        System.out.println("You are not logged in.");
                        break;
                    }
                    System.out.println("Enter your username: ");
                    username = stringScan.nextLine();
                    String status = stringScan.nextLine();
                    List<Ticket> tickets1 = ticketService.getEmployeeTickets(username, status);
                    for(int i = 0; i < tickets1.size(); i++){
                        System.out.println(tickets1.get(i));
                    }
                default:
                    System.out.println("Not a valid option.");
                    break;
            }
        }
    }


    public static void printOptions(){
        System.out.println("What would you like to do?");
        System.out.println("1 - Register");
        System.out.println("2 - Login");
        System.out.println("3 - Create reimbursement ticket");
        System.out.println("4 - View all ticket submissions");
        System.out.println("5 - View user ticket submissions");
        System.out.print("Enter => ");
    }

}