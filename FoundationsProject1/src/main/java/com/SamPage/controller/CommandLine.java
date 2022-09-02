package com.SamPage.controller;
import com.SamPage.entity.Employee;
import java.util.*;

public class CommandLine {
    public static void menu(){
        System.out.println("Which menu do you want to see?");
        System.out.println("1 - Employee Menu");
        System.out.println("2 - Management Menu");
        System.out.print("Enter => ");
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();

        switch(choice){
            case 1:
                EmployeeCommandLineInterface.menu();
                break;
            case 2:
                ManagementCommandLineInterface.managementMenu();
                break;
            default:
                System.out.println("Incorrect choice");
                break;
        }
    }
}
