/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycompany.moviedatabase.view;

/**
 *
 * @author chaseowens
 */
public class View {

    UserIO io = new UserIOImpl();

    public int displayMenu() {
        io.print("Select an option");
        String displayMessage = "1) Add Movie \t2) Remove Movie \t3)Edit Movie Rating \n4) List Movies \t5) Get movie info \t6) Movie Search \n7) Quit";
        return io.readInt(displayMessage, 1, 7);
    }

    public String setTitle() {
        return io.readString("Movie title: ");
    }

    public String getReleaseDate() {
        return io.readString("Release date: ");
    }

    public String getMPAArating() {
        return io.readString("MPAA rating: ");
    }

    public String getDirector() {
        return io.readString("Director's name:");
    }

    public String getStudio() {
        return io.readString("What studio was it filmed in? ");
    }

    public String getUserRating() {
        return io.readString("Custom Rating");
    }

    public void confirmMovieAdded() {
        io.print("The movie was added to your movie library");
    }

    public void exit() {
        io.print("Have fun not watching TV");
    }

    public void confirmMovieDeleted(String movieToRemove) {
        io.print(movieToRemove + " was remvoed from your movie Library");
    }

    public String getNewRating() {
        return io.readString("Enter the new rating: ");
    }

    public void displayTitles(String[] movies) {
        for (String movie : movies) {
            io.print(movie);
        }
    }

    public void displayInfo(String[] movieInfo) {
        for (int i = 0; i < movieInfo.length; i++) {
            switch(i) {
                case 0:
                    io.print("Movie title: " + movieInfo[0]);
                    break;
                case 1:
                    io.print("Release date: " + movieInfo[1]);
                    break;
                case 2:
                    io.print("MPAA rating: " + movieInfo[2]);
                    break;
                case 3:
                    io.print("Director's name: " + movieInfo[3]);
                    break;
                case 4:
                    io.print("Studio: " + movieInfo[4]);
                    break;
                case 5:
                    io.print("Your rating: " + movieInfo[5]);
                    break;
                default:
                    break;
                    
            }
        }
    }

    public String getQuery() {
        return io.readString("Enter search criteria");
    }

    public void printMoviesFound(String[] moviesFound) {
        for (String movie : moviesFound) {
            io.print(movie);
        }
    }

}
