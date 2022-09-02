package com.SamPage.controller;

import com.SamPage.entity.Ticket;
import com.SamPage.service.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TicketServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        PrintWriter out = resp.getWriter();
        TicketService ticketService = new TicketService();

        ObjectMapper mapper = new ObjectMapper();
        Ticket ticket;
        try{
            ticket = mapper.readValue(req.getReader(), Ticket.class);
        }catch(Exception e){
            e.printStackTrace();
            resp.sendError(400, "Invalid Ticket Format");
            return;
        }
        String user = ticket.getUsername();
        String status = ticket.getStatus();

        List<Ticket> tickets = ticketService.getEmployeeTickets(user, status);
        for(Ticket ticket1: tickets){
            out.println(ticket1);
        }


    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        PrintWriter out = resp.getWriter();
        TicketService ticketService = new TicketService();

        ObjectMapper mapper = new ObjectMapper();
        Ticket ticket;

        try{
            ticket = mapper.readValue(req.getReader(), Ticket.class);
        }catch(Exception e){
            e.printStackTrace();
            resp.sendError(400, "Invalid Ticket Format");
            return;
        }
        ticket = ticketService.createTicket(ticket);
        out.println(ticket);

    }
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        PrintWriter out = resp.getWriter();
        TicketService ticketService = new TicketService();

        ObjectMapper mapper = new ObjectMapper();
        Ticket ticket;

        try{
           ticket = mapper.readValue(req.getReader(), Ticket.class);
        }catch(Exception e){
            e.printStackTrace();
            resp.sendError(400, "Invalid ticket format");
            return;
        }
        Ticket ticketdb = ticketService.getByTicketID(ticket.getTicketid());
        if(ticketdb.getStatus().equals("pending")){
            ticket = ticketService.updateTicket(ticket);
            out.println(ticket);
        }else{
            out.println("Ticket cannot be updated");
        }

    }
}
