/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycompany.classroster.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.time.LocalDateTime;
import mycompany.classroster.dto.ClassRosterPersistenceException;

/**
 *
 * @author chaseowens
 */
public class ClassRosterAuditDAOImpl implements ClassRosterAuditDAO {  
    public static final String AUDIT_FILE = "audit.txt";

    @Override
    public void writeAuditEntry(String entry) throws ClassRosterPersistenceException {
        PrintWriter read;
        
        try {
            read = new PrintWriter(new FileWriter("AUDIT_FILE", true));
        } catch (IOException e ) {
            throw new ClassRosterPersistenceException("Could not persist audit information.", e);
        }
        
        LocalDateTime timestamp = LocalDateTime.now();
        read.println(timestamp.toString() + " : " + entry);
        out.flush();
    }
    
}
