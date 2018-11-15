package com.mycompany.datetime;


import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chaseowens
 */
public class DateTime {
    public static void main(String[] args) {
        LocalDate ld = LocalDate.now();
        System.out.println("Local Date when instantiated: "+ld);
        
        // Showing passing in a string convert to local date
        // This is creating a new date different from now
        ld = LocalDate.parse("2015-01-01"); 
        
        // Set local date with now or parsing a string
        System.out.println("Parsed time: " + ld);
        
        // This will not work without specifying new format 
        
        // ld = LocalDate.parse("01/01/2001"); ERROR
        
        // If you wish to send anything else in besides default, you must
        // specify the format of the input
        
        ld = LocalDate.parse("02/07/2010", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        System.out.println("Parsed and Formatted: " + ld);
        
        LocalDate past = ld.minusDays(8);
        System.out.println("New variable 8 days ago: " + past);
        
        Period diff = ld.until(past);
        System.out.println("Period type difference, default format: " + diff);
        
        String fullDifference = past.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        System.out.println("Full difference: " + fullDifference);
        
        String shortDifference = past.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        System.out.println("Short difference: " + shortDifference);
        
        String longDifference = past.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        System.out.println("long difference: " + longDifference);
        
        String mediumDifference = past.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        System.out.println("Medium difference: " + mediumDifference);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        // Parameter passed in must match format specified
        LocalDate nd = LocalDate.parse("01-01-2005", formatter);
        String formatted = nd.format(formatter); 
        System.out.println("Formatted Date nd: " + formatted);
        
        formatted = nd.format(DateTimeFormatter.ofPattern("MM==dd==yyyy"));
        System.out.println("New Format: "+ formatted);
        
        formatted = nd.format(DateTimeFormatter.ofPattern("yyyy, MM, dd"));
        System.out.println("New Format: "+ formatted);
        
        
        // Legacy Date Objects
        // Conversion into LocalDate objects - 2 steps
        // 1. LegacyDate -> Instant (MachineTime)
        // 2. Instant -> ZonedDateTime with .ofInstant
        // .ofInstant requires 2 arguments: (date.toInstant(), ZoneId.systemDefault());
        
        Date legacyDate = new Date();
        LocalDate newLocal;
        ZonedDateTime zdt = ZonedDateTime.ofInstant(legacyDate.toInstant(), ZoneId.systemDefault());
        newLocal = zdt.toLocalDate();
        System.out.println("LegacyDateConversion: " + newLocal);
        formatted = newLocal.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        System.out.println("Formatted Legacy Date after conversion: " + formatted);
    }
    
}
