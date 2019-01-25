/*
 * SIVOTEK SOLUTIONS LTD COURSE GRADING SYSTEM. 
 * DEVELOPED BY SIVOTEK SOLUTION LTD.
 * ALL RIGHTS RESERVED 2017.
 */
package com.sivotek.school_management_system.app.business.audit;


import com.sivotek.school_management_system.app.pojo.SystemInfo;
import com.sivotek.school_management_system.entities.ActionType;
import com.sivotek.school_management_system.entities.AuditVault;
import com.sivotek.school_management_system.entities.Users;
import com.sivotek.school_management_system.entities.controllers.AuditVaultJpaController;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
/**
 *
 * @author Administrator
 */
public class AuditVaultRegistry {
    private static final Logger log = Logger.getLogger(AuditVaultRegistry.class.getName());
   
    //creating log for login, logout, read/view, search
    public void LogAction(Users users, ActionType actionType, String tablename, SystemInfo systemInfo) throws IOException, Exception
    {
       
        
        System.out.println("Action type :"+actionType.getName());
        System.out.println("User Id :"+users.getId());
        System.out.println("Entity name :"+tablename);
        
        //logging action into the audit vault
        AuditVault auditVault = new AuditVault();
        auditVault.setBranchId(users.getBranchId());
        auditVault.setUserId(users.getId());
        auditVault.setActionTypeId(actionType);
        auditVault.setTableName(tablename);
        auditVault.setSynchStatus(Boolean.FALSE);
        auditVault.setLogTime(new Date());
        
        auditVault.setSystemInfo(convertByteArrayOutputStream(systemInfo));
        AuditVaultJpaController auditVaultJpaController = new AuditVaultJpaController();
        //
       try{
             auditVaultJpaController.create(auditVault);
        }catch(Exception ex){
            System.out.println("Exception :"+ex.getCause().getMessage());
            log.log(Level.ERROR, "----Error occoured during AuditVaultRegistry----:{0} "+new Date());
        }

       
    }
    
    //creating log for update
    public void LogAction(Users users, ActionType actionType, String tablename, Object olddata, Object newdata, SystemInfo systemInfo) throws IOException, Exception
    {
        //logging action into the audit vault
        
        System.out.println("Action type :"+actionType.getName());
        System.out.println("User Id :"+users.getId());
        System.out.println("Entity name :"+tablename);
        
        //logging action into the audit vault
        AuditVault auditVault = new AuditVault();
        auditVault.setBranchId(users.getBranchId());
        auditVault.setUserId(users.getId());
        auditVault.setActionTypeId(actionType);
        auditVault.setTableName(tablename);
        auditVault.setSynchStatus(Boolean.FALSE);
        auditVault.setLogTime(new Date());
        
        auditVault.setRowDataOld(convertByteArrayOutputStream(olddata));
        auditVault.setRowDataNew(convertByteArrayOutputStream(newdata)); 
        auditVault.setSystemInfo(convertByteArrayOutputStream(systemInfo));
        
        AuditVaultJpaController auditVaultJpaController = new AuditVaultJpaController();
        
        //
        try{
             auditVaultJpaController.create(auditVault);
        }catch(Exception ex){
            log.log(Level.ERROR, "----Error occoured during AuditVaultRegistry----:{0} "+new Date());
        }
    }
    
    //creating log for insert, delete
    public void LogAction(Users users, ActionType actionType, String tablename, Object newdata, SystemInfo systemInfo) throws Exception
    {
        //logging action into the audit vault
      
        
        System.out.println("Action type :"+actionType.getName());
        System.out.println("User Id :"+users.getId());
        System.out.println("Entity name :"+tablename);
        
        //logging action into the audit vault
        AuditVault auditVault = new AuditVault();
        auditVault.setBranchId(users.getBranchId());
        auditVault.setUserId(users.getId());
        auditVault.setActionTypeId(actionType);
        auditVault.setTableName(tablename);
        auditVault.setSynchStatus(Boolean.FALSE);
        auditVault.setLogTime(new Date());
        
                
        auditVault.setLogTime(new Date());
        if(actionType.getName().equals("insert")){
          auditVault.setRowDataNew(convertByteArrayOutputStream(newdata));} 
        
        if(actionType.getName().equals("delete")){
          auditVault.setRowDataOld(convertByteArrayOutputStream(newdata));}
        
        auditVault.setSystemInfo(convertByteArrayOutputStream(systemInfo));
        
        AuditVaultJpaController auditVaultJpaController = new AuditVaultJpaController();
        
        //
        try{
             auditVaultJpaController.create(auditVault);
        }catch(Exception ex){
            log.log(Level.ERROR, "----Error occoured during AuditVaultRegistry----:{0} "+new Date());
        }
    }
    
    
   
    private byte[] convertByteArrayOutputStream(Object obj) throws IOException, Exception
    {
        ObjectOutputStream output;
            //write user object and convert to byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            output = new ObjectOutputStream(baos);
            output.writeObject(obj);
            output.close();
        
        return baos.toByteArray();
    }
    
}
