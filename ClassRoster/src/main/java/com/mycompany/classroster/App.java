/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classroster;

import mycompany.classroster.controller.Controller;
import mycompany.classroster.dao.ClassRosterAuditDAO;
import mycompany.classroster.dao.ClassRosterAuditDAOImpl;
import mycompany.classroster.dao.DAO;
import mycompany.classroster.dao.DAOImpl;
import mycompany.classroster.dto.ClassRosterDataValidationException;
import mycompany.classroster.dto.ClassRosterDuplicateException;
import mycompany.classroster.dto.ClassRosterPersistenceException;
import mycompany.classroster.service.Service;
import mycompany.classroster.service.ServiceImpl;
import mycompany.classroster.ui.View;

/**
 *
 * @author chaseowens
 */
public class App {
    public static void main(String[] args) throws ClassRosterPersistenceException, ClassRosterDuplicateException,
            ClassRosterDataValidationException {
        View view = new View();
        DAO dao = new DAOImpl();
        ClassRosterAuditDAO auditDao = new ClassRosterAuditDAOImpl();
        Service service = new ServiceImpl(dao, auditDao);
        Controller controller = new Controller(view, service);
        controller.run();
    }
}
