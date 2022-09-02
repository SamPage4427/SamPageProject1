package com.SamPage.data;
import com.SamPage.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    //CRUD
    //Create - inserting a username into the database
    //helps keep track of the userID
    //the reason we return user is, so we can keep track of the ID:
    public Employee insert(Employee user);

    //Read - getting/reading data from the database
    public Employee getByUserID(int userID);
    public Employee getByUsername(String username);
    //get all users with no parameters because we don't specify anything
    public List<Employee> getAllUsers();

    //Update - update some data that is already in the database
    //take an id and some user data
    public Employee update(Employee user);


}
