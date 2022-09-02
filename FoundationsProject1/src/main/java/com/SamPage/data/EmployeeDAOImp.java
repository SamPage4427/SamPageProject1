package com.SamPage.data;

import com.SamPage.entity.Employee;
import com.SamPage.entity.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImp implements EmployeeDAO {

    Connection connection;

    public EmployeeDAOImp(){
        connection = ConnectionFactory.getConnection();
    }
    //CRUD Methods:
    @Override
    public Employee insert(Employee user) {
        System.out.println(user.toString());
        String sql = "insert into employeeDB(employeeid, first_name, last_name, username, password, position, role) values (default, ?, ?, ?, ?, ?, 'employee');";

        try{
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql, 1);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2,user.getLastname());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getPosition());

            int count = preparedStatement.executeUpdate();
            if(count == 1){
                System.out.println("User added successfully.");

                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();

                int generatedID = resultSet.getInt(1);

                user.setUserID(generatedID);
            }else{
                System.out.println("Something went wrong.");
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong when preparing the statement");
        }
        return user;
    }

    @Override
    public Employee getByUserID(int userID) {
        String sql = "select * from employeeDB where employeeid = ?;";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //get the desired id
            preparedStatement.setInt(1, userID);
            System.out.println(preparedStatement);
            //print the query
            ResultSet resultSet = preparedStatement.executeQuery();
            //check to see if it is desired result
            if(resultSet.next()){
                int employeeid = resultSet.getInt("employeeid");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String position = resultSet.getString("position");
                String role = resultSet.getString("role");
                Employee user = new Employee(employeeid, first_name, last_name, username, password, position, role);
                return user;
            }else{
                System.out.println("Something went wrong when trying to retrieve user. User might not exist.");
            }

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Something went wrong.");
        }
        return null;
    }

    @Override
    public Employee getByUsername(String username) {
        String sql = "select * from employeeDB where username = ?;";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //get the desired id
            preparedStatement.setString(1, username);
            System.out.println(preparedStatement);
            //print the query
            ResultSet resultSet = preparedStatement.executeQuery();
            //check to see if it is desired result
            if(resultSet.next()){
                int employeeid = resultSet.getInt("employeeid");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String user_name = resultSet.getString("username");
                String password = resultSet.getString("password");
                String position = resultSet.getString("position");
                String role = resultSet.getString("role");
                Employee user = new Employee(employeeid, first_name, last_name, user_name, password, position, role);
                return user;
            }else{
                System.out.println("Something went wrong when trying to retrieve user. User might not exist.");
            }

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Something went wrong.");
        }
        return null;
    }

    @Override
    public List<Employee> getAllUsers() {
        List<Employee> employees = new ArrayList<>();

        String sql = "select * from employeeDB;";
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                int employeeid = resultSet.getInt("employeeid");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String position = resultSet.getString("position");
                String role = resultSet.getString("role");
                Employee employee = new Employee(employeeid, first_name, last_name, username, password, position, role);
                employees.add(employee);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public Employee update(Employee user) {
        String sql = "update employeeDB set first_name = ?, last_name = ?, username = ?, password = ?, position =? where employeeid = ?;";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getPosition());
            preparedStatement.setInt(6, user.getUserID());

            int count = preparedStatement.executeUpdate();

            if(count == 1){
                System.out.println("Update successful");
                return user;
            }else{
                System.out.println("Something went wrong.");
                if(count == 0){
                    System.out.println("No rows were affected");
                }else{
                    System.out.println("Many rows were affected. How Horrible");
                }
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }




}