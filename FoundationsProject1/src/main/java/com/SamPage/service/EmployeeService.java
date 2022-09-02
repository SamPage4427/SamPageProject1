package com.SamPage.service;
import com.SamPage.data.DAOFactory;
import com.SamPage.data.ReimbursementDAO;
import com.SamPage.data.EmployeeDAO;
import com.SamPage.entity.Employee;
import com.SamPage.entity.Ticket;

import java.util.List;

public class  EmployeeService {


    public Employee register(Employee employee){
        //declare a user dao and give it the temp implementation that we have:
        EmployeeDAO userDAO = DAOFactory.getEmployeeDAO();
        //insert a username and return the return value:
        Employee user1 = userDAO.insert(employee);
        return user1;
    }
    public Employee login(String username, String password){
        EmployeeDAO employeeDAO = DAOFactory.getEmployeeDAO();
        Employee employee = employeeDAO.getByUsername(username);
        if(password.equals(employee.getPassword())){
            return employee;
        }
        return null;

    }


    public Employee update(Employee user){
        EmployeeDAO employeeDAO = DAOFactory.getEmployeeDAO();
        return employeeDAO.update(user);
    }

    public List<Employee> getAllUsers(int userID){
        EmployeeDAO userDao = DAOFactory.getEmployeeDAO();
        return userDao.getAllUsers();
    }

}