/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moviedatabase;

import mycompany.moviedatabase.controller.Controller;
import mycompany.moviedatabase.dao.DAO;
import mycompany.moviedatabase.dao.DAOImpl;
import mycompany.moviedatabase.service.Service;
import mycompany.moviedatabase.service.ServiceImpl;
import mycompany.moviedatabase.view.View;

/**
 *
 * @author chaseowens
 */
public class App {

    public static void main(String[] args) {
        View view = new View();
        DAO dao = new DAOImpl();
        Service service = new ServiceImpl(dao);
        Controller controller = new Controller(view, service);
        controller.run();
    }
}
