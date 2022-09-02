package com.SamPage.data;

import com.SamPage.entity.Employee;
import com.SamPage.entity.Management;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagementDAOImp implements ManagementDAO {
    Connection connection;

    public ManagementDAOImp(){
        connection = ConnectionFactory.getConnection();
    }


    @Override
    public Management insert(Management manager) {
        return null;
    }

    @Override
    public Management getByID(int id) {
        String sql = "select * from employeeDB where employeeid = ?;";
        try{
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql, 1);
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int employeeid = resultSet.getInt("employeeid");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String position = resultSet.getString("position");
                String role = resultSet.getString("role");
                Management user = new Management(employeeid, first_name, last_name, username, password, position, role);
                return user;
            }else{
                System.out.println("Something went wrong getting the management user");
            }
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Something went wrong(management)");
        }
        return null;
    }
    @Override
    public Management getByUsername(String username) {
        String sql = "select * from employeeDB where username = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //get the desired id
            preparedStatement.setString(1, username);
            System.out.println(preparedStatement);
            //print the query
            ResultSet resultSet = preparedStatement.executeQuery();
            //check to see if it is desired result
            if (resultSet.next()) {
                int employeeid = resultSet.getInt("employeeid");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String user_name = resultSet.getString("username");
                String password = resultSet.getString("password");
                String position = resultSet.getString("position");
                String role = resultSet.getString("role");
                Management manager = new Management(employeeid, first_name, last_name, user_name, password, position, role);
                return manager;
            } else {
                System.out.println("Something went wrong when trying to retrieve user. User might not exist.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong.");
        }
        return null;
    }

    @Override
    public List<Management> getAllUsers() {
        List<Management> employees = new ArrayList<>();
        String sql = "select * from employeeDB;";
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                int employeeid = resultSet.getInt("employeeid");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String username = resultSet.getString("username");
                String position = resultSet.getString("position");
                String role = resultSet.getString("role");
                Management management = new Management(employeeid, first_name, last_name, username, position, role);
                employees.add(management);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public Management update(Management user) {
        String sql = "update employeeDB set first_name = ?, last_name = ?, username = ?, password = ?, position = ?, role = ? where employeeid = ?;";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getFirst_name());
            preparedStatement.setString(2, user.getLast_name());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getPosition());
            preparedStatement.setString(6, user.getRole());
            preparedStatement.setInt(7, user.getId());

            int count = preparedStatement.executeUpdate();
            if(count == 1){
                System.out.println("Update successful");
                return user;
            }else{
                System.out.println("Something went wrong");
                if(count == 0){
                    System.out.println("No rows were affected");
                }else{
                    System.out.println("Many rows were affected");
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteUser(int id) {
        String sql = "delete from employeeBD where employeeid = ?;";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int count = preparedStatement.executeUpdate();
            if(count == 1){
                System.out.println("User deleted successfully.");
                return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }


}
