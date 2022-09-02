package com.SamPage.data;

public class DAOFactory {
    private static EmployeeDAO employeeDAO= null;
    private static ManagementDAO managementDAO = null;
    private static ReimbursementDAO reimbursementDAO = null;

    private DAOFactory(){

    }

    public static EmployeeDAO getEmployeeDAO(){
        if(employeeDAO == null){
            employeeDAO = new EmployeeDAOImp();
        }
        return employeeDAO;
    }

    public static ManagementDAO getManagementDAO(){
        if(managementDAO == null){
            managementDAO = new ManagementDAOImp();
        }
        return managementDAO;
    }

    public static ReimbursementDAO getReimbursementDAO(){
        if(reimbursementDAO == null){
            reimbursementDAO = new ReimbursementDAOImp();
        }
        return reimbursementDAO;
    }

}
