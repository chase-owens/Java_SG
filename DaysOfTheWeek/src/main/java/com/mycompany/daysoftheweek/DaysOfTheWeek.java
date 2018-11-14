/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.daysoftheweek;

/**
 *
 * @author chaseowens
 */
public class DaysOfTheWeek {

    public String toDo(Days days) {
        String message = null;
        switch (days) {
            case MONDAY:
                message = "Case of the Mondays";
                //return "Case of the Mondays";
                break;
            case TUESDAY:
                message = "Be productive or else";
                //return "Be productive or else";
                break;
            case WEDNESDAY:
                message = "Hump Day!!!";
                //return "Hump Day!!!";
                break;
            case THRUSDAY:
                message = "Tomorrow's Friday";
                //return "Tomorrow's Friday";
                break;
            case FRIDAY:
                message = "TGIF";
                //return "TGIF";
                break;
            case SATURDAY:
                message = "Hobbies";
                //return "Hobbies";
                break;
            case SUNDAY:
                message = "Get ready for Monday!";
                //return "Get ready for Monday!";
                break;
            default:
                break;
        }
        return message;
    }
}
