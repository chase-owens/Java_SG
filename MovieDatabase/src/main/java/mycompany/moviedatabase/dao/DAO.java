/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycompany.moviedatabase.dao;

import mycompany.moviedatabase.dto.DVD;
import mycompany.moviedatabase.dto.MovieDAOException;

/**
 *
 * @author chaseowens
 */
public interface DAO {

    DVD makeDVD(String title, String releaseDate, String MPAArating, String directorsName, String studio, String userRating);

    public void addMovieToFile(DVD newDVD) throws MovieDAOException;
    
    public void marshallMovie(DVD newDVD) throws MovieDAOException;

    public void removeMovie(String movieToRemove);

    public void editRating(String title, String newRating);

    public String[] getAllMovies();

    public String[] getMovieInfo(String movie);

    public String[] findMoviesMatching(String query);
    
}
