/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.model;

import com.mycompany.flooringmastery.dao.DataValidationException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author chaseowens
 */
public class DateValidationObject {

    String date;

    public DateValidationObject(String date) {
        this.date = date;
    }

    public boolean validate() throws DataValidationException {
        boolean validEntry = false;
        try {
            String[] formattedDate = this.date.split("-");
            int month = Integer.parseInt(formattedDate[0]);
            int day = Integer.parseInt(formattedDate[1]);
            int year = Integer.parseInt(formattedDate[2]);
            if (month < 1 || month > 12 || day < 1 || day > 31 || formattedDate[0].length() != 2 || formattedDate[1].length() != 2 || formattedDate[2].length() != 4) {
                throw new DataValidationException("This is not in correct format");
            } else if (year < 2000) {
                throw new DataValidationException("We were not in business at that time");
            } else if (year > 2020) {
                throw new DataValidationException("We are not taking orders for that date at this time..");
            }
        } catch (UnsupportedOperationException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DataValidationException("That's not the correct format");
        }
        return validEntry = true;
    }

    public boolean checkNotPast() throws DataValidationException {
        boolean canFulfill;
        LocalDate now = LocalDate.now();
        LocalDate dateEntered = LocalDate.parse(this.date, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        Period diff = now.until(dateEntered);
        if (diff.getDays() >= 0) {
            canFulfill = true;
        } else {
            throw new DataValidationException("We cannot edit past orders or fulfill an order in the past");
        }
        
        return canFulfill;
    }
}
