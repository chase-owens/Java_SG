/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycompany.moviedatabase.service;

import mycompany.moviedatabase.dao.DAO;
import mycompany.moviedatabase.dto.DVD;

/**
 *
 * @author chaseowens
 */
public class ServiceImpl implements Service {

    DAO dao;

    public ServiceImpl(DAO injectedDAO) {
        this.dao = injectedDAO;
    }

    @Override
    public DVD makeDVD(String title, String releaseDate, String MPAArating, String directorsName, String studio, String userRating) {
        return dao.makeDVD(title, releaseDate, MPAArating, directorsName, studio, userRating);
    }

    @Override
    public void addMovieToFile(DVD newDVD) {
        dao.addMovieToFile(newDVD);
    }

    @Override
    public void removeMovie(String movieToRemove) {
        dao.removeMovie(movieToRemove);
    }

    @Override
    public void editRating(String title, String newRating) {
        dao.editRating(title, newRating);
    }

    @Override
    public String[] getAllMovies() {
        return dao.getAllMovies();
    }

    @Override
    public String[] getMovieInfo(String movie) {
        return dao.getMovieInfo(movie);
    }

    @Override
    public String[] findMoviesMatching(String query) {
        return dao.findMoviesMatching(query);
    }

}
