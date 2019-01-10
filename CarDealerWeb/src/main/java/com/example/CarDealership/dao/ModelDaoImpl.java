/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.dao;

import com.example.CarDealership.entity.Make;
import com.example.CarDealership.entity.Model;
import com.example.CarDealership.entity.User;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author chaseowens
 */
@Repository
public class ModelDaoImpl implements ModelDao {

    JdbcTemplate jdbc;
    UserDao userDao;
    MakeDao makeDao;

    @Autowired
    public ModelDaoImpl(JdbcTemplate jdbc, UserDao userDao, MakeDao makeDao) {
        this.jdbc = jdbc;
        this.userDao = userDao;
        this.makeDao = makeDao;
    }

    @Override
    public Model createModel(int makeId, String modelName, int userId) {
        Model model = new Model();
        Make make = makeDao.readMakeById(makeId);
        User user = userDao.readUserById(userId);
        model.setCreatedBy(user);
        model.setMake(make);
        model.setModelName(modelName);

        Timestamp dateAdded = Timestamp.valueOf(model.getDateAdded());

        final String CREATE_MODEL = "INSERT INTO model(makeId, modelName, dateAdded, userId) VALUES (?,?,?,?)";
        jdbc.update(CREATE_MODEL, makeId, modelName, dateAdded, userId);

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        model.setId(newId);

        return model;

    }

    @Override
    public List<Model> readAllModels() {
        final String READ_ALL_MODELS = "SELECT * FROM model";
        List<Model> models = jdbc.query(READ_ALL_MODELS, new ModelMapper());
        models.stream().forEach(model -> {
            model.setCreatedBy(userDao.readUserById(model.getCreatedBy().getUserId()));
            model.setMake(makeDao.readMakeById(model.getMake().getMakeId()));
        });
        return models;
    }

    @Override
    public Model readModelById(int id) {
        final String READ_MODEL_BY_ID = "SELECT * FROM model WHERE id = ?";
        Model model = null;
        
        try {
            model = jdbc.queryForObject(READ_MODEL_BY_ID, new ModelMapper(), id);
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
        
        model.setCreatedBy(userDao.readUserById(model.getCreatedBy().getUserId()));
        model.setMake(makeDao.readMakeById(model.getMake().getMakeId()));
        return model;
    }

    @Override
    public void updateModel(Model model) {
        final String UPDATE_MODEL = "UPDATE model SET modelName = ? WHERE id = ?";
        jdbc.update(UPDATE_MODEL, model.getModelName(), model.getId());
    }

}
