package com.SamPage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.SamPage.entity.Employee;
import com.SamPage.service.EmployeeService;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class EmployeeServlet extends HttpServlet{
    //login and register
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        PrintWriter out = resp.getWriter();
        EmployeeService employeeService = new EmployeeService();

        ObjectMapper mapper = new ObjectMapper();
        Employee employee;

        try{
            employee = mapper.readValue(req.getReader(), Employee.class);
        }catch(Exception e){
            e.printStackTrace();
            resp.sendError(400, "Invalid Employee Format");
            return;
        }
        String authType = req.getParameter("auth");
        if(authType.equals("login")){
            employee = employeeService.login(employee.getUsername(), employee.getPassword());
        }else if(authType.equals("register")){
            employee = employeeService.register(employee);
        }
        if(employee == null){
            resp.sendError(400, "Invalid Credentials");
            return;
        }
        out.println("Welcome, " + employee.getFirstName());

        req.getSession().setAttribute("employeeid", employee.getUserID());

    }
    //all employees
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        PrintWriter out = resp.getWriter();
        EmployeeService employeeService = new EmployeeService();

        int employeeID;
        try {
            employeeID = (Integer) req.getSession().getAttribute("employeeid");
        }catch(Exception e){
            e.printStackTrace();
            resp.sendError(401, "Must be logged in to view all employees");
            return;
        }
        List<Employee> employees = employeeService.getAllUsers(employeeID);
        for(Employee employee: employees){
            out.println(employee);
        }
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        PrintWriter out = resp.getWriter();
        EmployeeService employeeService = new EmployeeService();

        ObjectMapper objectMapper = new ObjectMapper();
        Employee employee;
        try{
            employee = objectMapper.readValue(req.getReader(), Employee.class);
        }catch(Exception e){
            e.printStackTrace();
            resp.sendError(400, "Invalid employee format");
            return;
        }
        employee = employeeService.update(employee);
        out.println(employee);

    }

}

