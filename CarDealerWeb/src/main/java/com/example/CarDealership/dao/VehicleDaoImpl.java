/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.dao;

import com.example.CarDealership.entity.Make;
import com.example.CarDealership.entity.Model;
import com.example.CarDealership.entity.User;
import com.example.CarDealership.entity.Vehicle;
import java.math.BigDecimal;
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
public class VehicleDaoImpl implements VehicleDao {

    JdbcTemplate jdbc;
    MakeDao makeDao;
    ModelDao modelDao;
    UserDao userDao;

    @Autowired
    public VehicleDaoImpl(JdbcTemplate jdbc, MakeDao makeDao, ModelDao modelDao, UserDao userDao) {
        this.jdbc = jdbc;
        this.makeDao = makeDao;
        this.modelDao = modelDao;
        this.userDao = userDao;
    }

    @Override
    public Vehicle createVehicle(Make make, Model model, BigDecimal msrp, BigDecimal listPrice, int mileage, int year, String vehicleType, String vehicleDescription, String image, String exteriorColor, String interiorColor, String transmission, String bodyStyle, String vin, int userId) {
        Vehicle vehicle = new Vehicle();
        vehicle.setModel(model);
        vehicle.setMake(make);
        User user = new User();
        user.setUserId(userId);
        vehicle.setCreatedBy(user);
        
        
        vehicle.setExteriorColor(exteriorColor);
        vehicle.setImage(image);
        vehicle.setInteriorColor(interiorColor);
        vehicle.setListPrice(listPrice);
        vehicle.setMsrp(msrp);
        vehicle.setTransmission(transmission);
        vehicle.setVehicleDescription(vehicleDescription);
        vehicle.setVehicleType(vehicleType);
        vehicle.setMileage(mileage);
        vehicle.setVehicleYear(year);
        vehicle.setVin(vin);
        vehicle.setBodyStyle(bodyStyle);
        
        Timestamp timestamp = Timestamp.valueOf(vehicle.getDateAdded());

        final String CREATE_VEHICLE = "INSERT INTO vehicle(makeId, modelId, msrp, listPrice, mileage, vehicleYear, vehicleType, vehicleDescription, image, exteriorColor, interiorColor, transmission, bodyStyle, vin, dateAdded, isAvailable, isFeatured, userId) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        jdbc.update(CREATE_VEHICLE, make.getMakeId(), model.getId(), msrp, listPrice, mileage, year, vehicleType, vehicleDescription, image, exteriorColor, interiorColor, transmission, bodyStyle, vin, timestamp, vehicle.isIsAvailable(), vehicle.isIsAvailable(), userId);
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        vehicle.setVehicleId(newId);

        return vehicle;
    }

    @Override
    public List<Vehicle> readAllVehicles() {
        final String READ_VEHICLES = "SELECT * FROM vehicle";
        List<Vehicle> vehicles = jdbc.query(READ_VEHICLES, new VehicleMapper());
        vehicles.stream().forEach(vehicle -> {
            vehicle.setMake(makeDao.readMakeById(vehicle.getMake().getMakeId()));
            vehicle.setModel(modelDao.readModelById(vehicle.getModel().getId()));
            vehicle.setCreatedBy(userDao.readUserById(vehicle.getCreatedBy().getUserId()));
        });
        return vehicles;
    }

    @Override
    public Vehicle readVehicleById(int id) {
        final String READ_VEHICLE_BY_ID = "SELECT * FROM vehicle WHERE id = ?";
        Vehicle vehicle = null;
        
        try {
            vehicle = jdbc.queryForObject(READ_VEHICLE_BY_ID, new VehicleMapper(), id);
        } catch(EmptyResultDataAccessException e) {
            
        }
        
        vehicle.setMake(makeDao.readMakeById(vehicle.getMake().getMakeId()));
        vehicle.setModel(modelDao.readModelById(vehicle.getModel().getId()));
        vehicle.setCreatedBy(userDao.readUserById(vehicle.getCreatedBy().getUserId()));
        return vehicle;
    }

    @Override
    public void updateVehicle(Vehicle vehicle) {
        Timestamp timestamp = Timestamp.valueOf(vehicle.getDateAdded());
                                                        
        final String UPDATE_VEHICLE = "UPDATE vehicle SET makeId = ?, modelId = ?, msrp = ?, listPrice = ?, mileage = ?, vehicleYear = ?, vehicleType = ?, vehicleDescription = ?, image = ?, exteriorColor = ?, interiorColor = ?, transmission = ?, bodyStyle = ?, vin = ?, dateAdded = ?, isAvailable = ?, isFeatured = ? WHERE id = ?";
        jdbc.update(UPDATE_VEHICLE, vehicle.getMake().getMakeId(), vehicle.getModel().getId(), vehicle.getMsrp(), vehicle.getListPrice(), vehicle.getMileage(), vehicle.getVehicleYear(), vehicle.getVehicleType(), vehicle.getVehicleDescription(), vehicle.getImage(), vehicle.getExteriorColor(), vehicle.getInteriorColor(), vehicle.getTransmission(), vehicle.getBodyStyle(), vehicle.getVin(), timestamp, vehicle.isIsAvailable(), vehicle.isIsFeatured(), vehicle.getVehicleId());

    }

    @Override
    public List<Vehicle> query20VehiclesByTypePriceAndYearDescendingMSRP(String query, String type, BigDecimal minPrice, BigDecimal maxPrice, int minYear, int maxYear) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
