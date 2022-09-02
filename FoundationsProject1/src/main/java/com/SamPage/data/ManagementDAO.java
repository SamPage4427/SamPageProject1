package com.SamPage.data;

import com.SamPage.entity.Employee;
import com.SamPage.entity.Management;

import java.util.List;

public interface ManagementDAO {

    public Management insert(Management manager);
    public Management getByID(int id);

    public List<Management> getAllUsers();


    public Management update(Management user);

    public boolean deleteUser(int id);

    public Management getByUsername(String username);
}
