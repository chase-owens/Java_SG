/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moviedatabase;

import mycompany.moviedatabase.controller.Controller;
import mycompany.moviedatabase.dao.DAO;
import mycompany.moviedatabase.dao.DAOImpl;
import mycompany.moviedatabase.dto.MovieDAOException;
import mycompany.moviedatabase.service.Service;
import mycompany.moviedatabase.service.ServiceImpl;
import mycompany.moviedatabase.view.View;
import mycompany.moviedatabase.view.UserIO;
import mycompany.moviedatabase.view.UserIOImpl;

/**
 *
 * @author chaseowens
 */
public class App {

    public static void main(String[] args) throws MovieDAOException {
        UserIO io = new UserIOImpl();
        View view = new View(io);
        DAO dao = new DAOImpl();
        Service service = new ServiceImpl(dao);
        Controller controller = new Controller(view, service);
        controller.run();
    }
}
