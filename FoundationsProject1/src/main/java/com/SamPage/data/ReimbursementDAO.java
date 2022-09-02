package com.SamPage.data;
import com.SamPage.entity.Ticket;

import java.util.List;

public interface ReimbursementDAO {
    public Ticket create(Ticket ticket);

    public Ticket getByTicketID(int ticketID);

    public List<Ticket> getAllTickets();


    public List<Ticket> getEmployeeTickets(String username, String status);


    public Ticket update(Ticket ticket);

    public boolean delete(int ticketID);

}
