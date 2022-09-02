package com.SamPage.data;
import com.SamPage.entity.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDAOImp implements ReimbursementDAO {

    Connection connection;

    public ReimbursementDAOImp(){
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public Ticket create(Ticket ticket) {
        System.out.println(ticket.toString());
        String sql = "insert into ticket values(default, ?, ?, ?, 'pending');";

        try{
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql, 1);
            preparedStatement.setString(1, ticket.getUsername());
            preparedStatement.setDouble(2, ticket.getAmount());
            preparedStatement.setString(3, ticket.getDescription());

            int count = preparedStatement.executeUpdate();
            if(count == 1){
                System.out.println("Ticket created successfully.");

                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();

                int generatedID = resultSet.getInt(1);

                ticket.setTicketid(generatedID);
            }else{
                System.out.println("Something went wrong.");
            }
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Something went wrong when creating ticket.");
        }
        return ticket;
    }

    @Override
    public Ticket getByTicketID(int ticketID) {
        String sql = "select * from ticket where ticketid = ?;";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, ticketID);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int ticketid = resultSet.getInt("ticketid");
                String username = resultSet.getString("employeeusername");
                double amount = resultSet.getDouble("amount");
                String description = resultSet.getString("description");
                String status = resultSet.getString("status");
                Ticket ticket = new Ticket(ticketid, username, amount, description, status);
                return ticket;
            }else{
                System.out.println("Something went wrong retrieving the ticket.");
            }
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Something went wrong getting the ticket.");
        }
        return null;
    }

    @Override
    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "select * from ticket;";
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                int ticketid = resultSet.getInt("ticketid");
                String username = resultSet.getString("employeeusername");
                double amount = resultSet.getDouble("amount");
                String description = resultSet.getString("description");
                String status = resultSet.getString("status");

                Ticket ticket = new Ticket(ticketid, username, amount, description, status);
                tickets.add(ticket);
            }
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Something went wrong getting all tickets.");
        }
        return tickets;
    }
    @Override
    public List<Ticket> getEmployeeTickets(String username, String status){
        List<Ticket> tickets = new ArrayList<>();
        String sql = "select * from ticket where employeeusername = ? and status = ?;";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, status);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int ticketid = resultSet.getInt("ticketid");
                username = resultSet.getString("employeeusername");
                double amount = resultSet.getDouble("amount");
                String description = resultSet.getString("description");
                status = resultSet.getString("status");
                Ticket ticket = new Ticket(ticketid, username, amount, description, status);
                tickets.add(ticket);
            }
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Could not get tickets for employee");
        }
        return tickets;
    }



    @Override
    public boolean delete(int ticketID) {
        String sql = "delete from ticket where ticketid = ?;";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, ticketID);

            int count = preparedStatement.executeUpdate();
            if(count == 1){
                System.out.println("Ticket deleted successfully.");
                return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Ticket update(Ticket ticket) {
        String sql = "update ticket set status = ? where ticketid = ?;";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ticket.getStatus());
            preparedStatement.setInt(2, ticket.getTicketid());

            System.out.println(preparedStatement);
            int count = preparedStatement.executeUpdate();
            if(count == 1){
                System.out.println("Ticket updated successfully");
                return ticket;
            }else{
                System.out.println("Could not update ticket");
                if(count == 0){
                    System.out.println("No rows were affected.");
                }else{
                    System.out.println("Many rows were affected.");
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Something went wrong getting the ticket");
        }
        return null;
    }


}

