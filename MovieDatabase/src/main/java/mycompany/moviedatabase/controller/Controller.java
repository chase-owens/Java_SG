/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycompany.moviedatabase.controller;

import java.util.List;
import mycompany.moviedatabase.dto.DVD;
import mycompany.moviedatabase.dto.MovieDAOException;
import mycompany.moviedatabase.view.View;
import mycompany.moviedatabase.service.Service;

/**
 *
 * @author chaseowens
 */
public class Controller {

    View view;
    Service service;

    public Controller(View injectedView, Service injectedService) {
        this.view = injectedView;
        this.service = injectedService;
    }

    public void run() {
        try {
            boolean keepGoing = true;

            while (keepGoing) {
                loadMovies();
                int selection = displayMenu();
                switch (selection) {
                    case 1:
                        addMovie();
                        break;
                    case 2:
                        removeMovie();
                        break;
                    case 3:
                        editRating();
                        break;
                    case 4:
                        listAllMovies();
                        break;
                    case 5:
                        getMovieInfo();
                        break;
                    case 6:
                        movieSearch();
                        break;
                    case 7:
                        marshallMovies();
                        exitGracefully();
                        keepGoing = false;
                        break;
                    default:
                        break;
                }
            }
        } catch (MovieDAOException e) {
            displayErrorMessage(e);
        }
    }

    private void loadMovies() throws MovieDAOException {
        service.loadMovies();
    }

    private int displayMenu() {
        return view.displayMenu();
    }

    private void addMovie() {
        // Get Movie Info
        String title = view.setTitle();
        String releaseDate = view.getReleaseDate();
        String MPAArating = view.getMPAArating();
        String directorsName = view.getDirector();
        String studio = view.getStudio();
        String userRating = view.getUserRating();

        // Create movie
        DVD newDVD = service.makeDVD(title, releaseDate, MPAArating, directorsName, studio, userRating);

        // Add movie to file
        service.addMovieToList(newDVD);

        // Print confirmation method
        view.confirmMovieAdded();
    }

    private void removeMovie() {
        // Get movie name
        String movieToRemove = view.setTitle();

        // Find remove movie
        service.removeMovie(movieToRemove);

        // Print confirmation message
        view.confirmMovieDeleted(movieToRemove);
    }

    private void editRating() {

        // get movie Title
        String title = view.setTitle();

        // get rating
        String newRating = view.getNewRating();

        // edit rating
        service.editRating(title, newRating);
    }

    private void listAllMovies() {

        // get movie titles
        String[] movies = service.getAllMovies();

        // display movie titles
        view.displayTitles(movies);
    }

    private void getMovieInfo() {
        // get movie title
        String movie = view.setTitle();

        // retrieve information
        String[] movieInfo = service.getMovieInfo(movie);

        // display info
        view.displayInfo(movieInfo);
    }

    private void movieSearch() {
        // get query
        String query = view.getQuery();

        // find movies
        String[] moviesFound = service.findMoviesMatching(query);

        // display movie
        view.printMoviesFound(moviesFound);
    }

    private void exitGracefully() {
        view.exit();
    }

    private void displayErrorMessage(MovieDAOException e) {
        view.displayErrorMessage(e.getMessage());
    }

    private void marshallMovies() throws MovieDAOException {
        // get list of movies
        List<DVD> dvds = service.getMovieList();
        
        // marshall list of movies
        service.marshallMovies(dvds);
    }

}
