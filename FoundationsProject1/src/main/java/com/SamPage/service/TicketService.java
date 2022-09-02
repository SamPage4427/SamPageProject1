package com.SamPage.service;
import com.SamPage.data.DAOFactory;
import com.SamPage.data.ReimbursementDAO;
import com.SamPage.data.ReimbursementDAOImp;
import com.SamPage.entity.Ticket;

import java.util.List;

public class TicketService {
    public Ticket createTicket(Ticket ticket){
        ReimbursementDAO ticketDAO = DAOFactory.getReimbursementDAO();
        Ticket ticket1 = ticketDAO.create(ticket);
        return ticket1;
    }
    public Ticket updateTicket(Ticket ticket){
        ReimbursementDAO ticketDAO = DAOFactory.getReimbursementDAO();
        Ticket ticket1 = ticketDAO.update(ticket);
        return ticket1;
    }

    public Ticket getByTicketID(int ticketid){
        ReimbursementDAO ticketDAO = DAOFactory.getReimbursementDAO();
        return ticketDAO.getByTicketID(ticketid);
    }

    public List<Ticket> getAllTickets(){
        ReimbursementDAO ticketDAO = DAOFactory.getReimbursementDAO();
        return ticketDAO.getAllTickets();
    }

    public List<Ticket> getEmployeeTickets(String username, String status){
        ReimbursementDAO ticketDAO = DAOFactory.getReimbursementDAO();
        return ticketDAO.getEmployeeTickets(username, status);
    }

}
