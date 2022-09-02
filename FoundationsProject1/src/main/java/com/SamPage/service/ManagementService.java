package com.SamPage.service;

import com.SamPage.data.DAOFactory;
import com.SamPage.data.ManagementDAO;
import com.SamPage.data.ReimbursementDAO;
import com.SamPage.entity.Management;
import com.SamPage.entity.Ticket;

import java.util.List;

public class ManagementService {
    public Management login(String username, String password){
        ManagementDAO managerDAO = DAOFactory.getManagementDAO();
        Management manager = managerDAO.getByUsername(username);
        if(password.equals(manager.getPassword())){
            return manager;
        }
        return null;
    }
    public Management register(Management manager){
        ManagementDAO managementDAO = DAOFactory.getManagementDAO();
        Management manage = managementDAO.insert(manager);
        return manage;
    }

    public List<Management> getAllUsers(){
        ManagementDAO managerDAO = DAOFactory.getManagementDAO();
        return managerDAO.getAllUsers();
    }
    public List<Ticket> getAllTickets(int employeeid){
        ReimbursementDAO ticketDAO = DAOFactory.getReimbursementDAO();
        return ticketDAO.getAllTickets();
    }

    public Management update(Management user) {
        ManagementDAO managementDAO = DAOFactory.getManagementDAO();
        return managementDAO.update(user);
    }
}
