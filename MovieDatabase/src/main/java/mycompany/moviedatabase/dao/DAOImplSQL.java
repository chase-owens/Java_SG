/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycompany.moviedatabase.dao;


import java.util.ArrayList;
import java.util.List;
import mycompany.moviedatabase.dto.DVD;
import mycompany.moviedatabase.dto.MovieDAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author chaseowens
 */
public class DAOImplSQL implements DAO {

    @Autowired
    private JdbcTemplate jdbc;

    List<DVD> movieLibrary = new ArrayList<>();

    /**
     *
     * @param title
     * @param releaseDate
     * @param MPAArating
     * @param directorsName
     * @param studio
     * @param userRating
     * @return DVD
     */
    @Override
    public DVD makeDVD(String title, String releaseDate, String MPAArating, String directorsName, String studio, String userRating) {
        DVD dvd = new DVD(title, releaseDate, MPAArating, directorsName, studio, userRating);
        return dvd;
    }

    @Override
    public void marshallMovies(List<DVD> dvds) throws MovieDAOException {
        // Not using that anymore
    }

    @Override
    public void addMovieToList(DVD newDVD) {
        jdbc.update("INSERT INTO dvdLibrary(title, releaseDate, MPAARating, director, studio, userRating) VALUES (?,?,?,?,?,?)",
                newDVD.getTitle(), newDVD.getDate().toString(), newDVD.getMPAArating(), newDVD.getDirectorsName(), newDVD.getStudio(), newDVD.getRating());
    }

    @Override
    public void removeMovie(String movieToRemove) {
        jdbc.update("DELETE FROM dvdLibrary WHERE title = ?", movieToRemove);
    }

    @Override
    public void editRating(String title, String newRating) {
        jdbc.update("UPDATE dvdLibrary SET MPAARating = ? WHERE title = ?", newRating, title);
    }

    @Override
    public String[] getAllMovies() {
        String[] movieList = new String[movieLibrary.size()];
        int count = 0;
        for (DVD movie : movieLibrary) {
            String title = movie.getTitle();
            movieList[count] = title;
            count += 1;
        }
        return movieList;
    }

    @Override
    public String[] getMovieInfo(String movie) {
        String[] information = new String[6];
        DVD movieOfInterest = null;
        for (DVD dvd : movieLibrary) {
            String movieTitle = dvd.getTitle();
            if (movieTitle.equals(movie)) {
                movieOfInterest = dvd;
            }
        }
        information[0] = movieOfInterest.getTitle();
        information[1] = movieOfInterest.getDate().toString();
        information[2] = movieOfInterest.getStudio();
        information[3] = movieOfInterest.getDirectorsName();
        information[4] = movieOfInterest.getMPAArating();
        information[5] = movieOfInterest.getRating();

        return information;
    }

    @Override
    public String[] findMoviesMatching(String query) {
        int numberOfMatches = 0;
        for (DVD movie : movieLibrary) {
            String title = movie.getTitle();
            if (title.contains(query)) {
                numberOfMatches += 1;
            }
        }

        String[] moviesMatching = new String[numberOfMatches];
        int count = 0;

        for (DVD movie : movieLibrary) {
            String title = movie.getTitle();
            if (title.contains(query)) {
                moviesMatching[count] = title;
                count += 1;
            }
        }
        return moviesMatching;
    }

    @Override
    public void loadMovies() throws MovieDAOException {
        //movieLibrary = jdbc.query("SELECT * FROM dvdLibrary", new DVDMapper());

    }

    @Override
    public void deleteList() {
        movieLibrary.clear();
    }

    @Override
    public List<DVD> getMovieList() {
        List<DVD> dvds = jdbc.query("SELECT * FROM dvdLibrary", new DVDMapper());
        return dvds;
    }
}
