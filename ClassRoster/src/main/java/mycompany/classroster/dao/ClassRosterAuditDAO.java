/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycompany.classroster.dao;

import mycompany.classroster.dto.ClassRosterPersistenceException;

/**
 *
 * @author chaseowens
 */
public interface ClassRosterAuditDAO {
    
    public void writeAuditEntry(String entry) throws ClassRosterPersistenceException;
}
