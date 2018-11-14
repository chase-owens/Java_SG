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
class Controller {
    View view = new View();
    DaysOfTheWeek days = new DaysOfTheWeek();
    public void run() {
        Days favoriteDay = Days.valueOf(view.favoriteDay().toUpperCase());
        String yourDaysMessage = days.toDo(favoriteDay);
        view.print(yourDaysMessage);
    }
}
