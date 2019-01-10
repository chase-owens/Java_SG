/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.dao;

import com.example.CarDealership.entity.Profile;
import com.example.CarDealership.entity.Purchase;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
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
public class PurchaseDaoImpl implements PurchaseDao {

    JdbcTemplate jdbc;
    ProfileDao profileDao;
    UserDao userDao;
    VehicleDao vehicleDao;

    @Autowired
    public PurchaseDaoImpl(JdbcTemplate jdbc, ProfileDao profileDao, UserDao userDao, VehicleDao vehicleDao) {
        this.jdbc = jdbc;
        this.profileDao = profileDao;
        this.userDao = userDao;
        this.vehicleDao = vehicleDao;
    }

    @Override
    public Purchase createPurchase(Profile profile, int vehicleId, BigDecimal salePrice, String purchaseType, int userId) {
        Purchase purchase = new Purchase();
        purchase.setVehicle(vehicleDao.readVehicleById(vehicleId));
        purchase.setCustomerProfile(profile);
        purchase.setCreatedBy(userDao.readUserById(userId));
        purchase.setSalePrice(salePrice);
        purchase.setSaleType(purchaseType);

        Timestamp timestamp = Timestamp.valueOf(purchase.getDateAdded());

        final String CREATE_PURCHASE = "INSERT INTO purchase(profileId, vehicleId, salePrice, saleType, dateAdded, userId) VALUES(?,?,?,?,?,?)";
        jdbc.update(CREATE_PURCHASE, profile.getProfileId(), vehicleId, salePrice, purchaseType, timestamp, userId);

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        purchase.setPurchaseId(newId);

        return purchase;
    }

    @Override
    public List<Purchase> readAllPurchases() {
        final String SELECT_ALL_PURCHASES = "SELECT * FROM purchase";
        List<Purchase> purchases = jdbc.query(SELECT_ALL_PURCHASES, new PurchaseMapper());
        purchases.stream().forEach(purchase -> {
            purchase.setCreatedBy(userDao.readUserById(purchase.getCreatedBy().getUserId()));
            purchase.setCustomerProfile(profileDao.readProfileById(purchase.getCustomerProfile().getProfileId()));
            purchase.setVehicle(vehicleDao.readVehicleById(purchase.getVehicle().getVehicleId()));
        });
        return purchases;
    }

    @Override
    public Purchase readPurchaseById(int id) {
        final String SELECT_PURCHASE_BY_ID = "SELECT * FROM purchase WHERE id = ?";
        Purchase purchase = null;
        try {
            purchase = jdbc.queryForObject(SELECT_PURCHASE_BY_ID, new PurchaseMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        purchase.setCreatedBy(userDao.readUserById(purchase.getCreatedBy().getUserId()));
        purchase.setCustomerProfile(profileDao.readProfileById(purchase.getCustomerProfile().getProfileId()));
        
        purchase.setVehicle(vehicleDao.readVehicleById(purchase.getVehicle().getVehicleId()));
        return purchase;
    }

    @Override
    public void updatePurchase(Purchase purchase) {
        final String UPDATE_PURCHASE = "UPDATE purchase SET salePrice = ?, saleType = ? WHERE id = ?";
        jdbc.update(UPDATE_PURCHASE, purchase.getSalePrice(), purchase.getSaleType(), purchase.getPurchaseId());
    }

    @Override
    public void deletePurchase(int id) {
        final String DELETE_PURCHASE = "DELETE FROM purchase WHERE id = ?";
        jdbc.update(DELETE_PURCHASE, id);
    }

    @Override
    public BigDecimal getSalesSumById(int id, LocalDate startingOn, LocalDate to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getSalesCountById(int id, LocalDate startingOn, LocalDate to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
