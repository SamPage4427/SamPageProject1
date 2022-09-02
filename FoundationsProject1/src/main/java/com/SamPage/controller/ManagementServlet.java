package com.SamPage.controller;


import com.SamPage.entity.Management;
import com.SamPage.service.ManagementService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ManagementServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        ManagementService managementService = new ManagementService();

        ObjectMapper mapper = new ObjectMapper();
        Management manager;

        try {
            manager = mapper.readValue(req.getReader(), Management.class);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(400, "Invalid Employee Format");
            return;
        }
        String authType = req.getParameter("auth");
        if (authType.equals("login")) {
            manager = managementService.login(manager.getUsername(), manager.getPassword());
        }else if(authType.equals("register")){
            manager = managementService.register(manager);
        }
        if (manager == null) {
            resp.sendError(400, "Invalid Credentials");
            return;
        }
        out.println("Welcome, " + manager.getFirst_name());

        req.getSession().setAttribute("employeeid", manager.getId());

    }
        @Override
        protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            PrintWriter out = resp.getWriter();
            ManagementService managementService1 = new ManagementService();

            int employeeID;
            try {
                employeeID = (Integer) req.getSession().getAttribute("employeeid");
            } catch (Exception e) {
                e.printStackTrace();
                resp.sendError(401, "Must be logged in to view employees");
                return;
            }
            List<Management> managers = managementService1.getAllUsers();
            for (Management management : managers) {
                out.println(management);
            }
        }
        @Override
        protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
            PrintWriter out = resp.getWriter();
            ManagementService managementService = new ManagementService();

            ObjectMapper objectMapper = new ObjectMapper();
            Management employee;
            try{
                employee = objectMapper.readValue(req.getReader(), Management.class);
            }catch(Exception e){
                e.printStackTrace();
                resp.sendError(400, "Invalid employee format");
                return;
            }
            employee = managementService.update(employee);
            out.println(employee);
        }
}