/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycompany.moviedatabase.controller;

import java.util.List;
import mycompany.moviedatabase.dto.DVD;
import mycompany.moviedatabase.dto.MovieDAOException;
import mycompany.moviedatabase.service.DVDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author chaseowens
 */
@RestController
@RequestMapping("/api/")
public class DVDRESTController {

    @Autowired
    DVDService service;

    //Add response entity
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public DVD addDVD(String title, String releaseDate, String MPAArating, String directorsName, String studio, String userRating) {
        DVD dvd = service.makeDVD(title, releaseDate, MPAArating, directorsName, studio, userRating);
        service.addMovieToList(dvd);
        return dvd;
    }

    @GetMapping("/movies")
    public List<DVD> getMovies() throws MovieDAOException {
        List<DVD> dvds = service.getMovieList();
        return dvds;
    }

    @GetMapping("/movie")
    public ResponseEntity<DVD> getMovie(String title) throws MovieDAOException {
        DVD dvd = null;
        
        try {
            dvd = service.getMovie(title);
        } catch(MovieDAOException e) {
            return new ResponseEntity(new Error(e.getMessage()), HttpStatus.NOT_FOUND);
        }
        
        return ResponseEntity.ok(dvd);
    }

    @GetMapping("/search")
    public List<DVD> searchMovies(String query) throws MovieDAOException {
        List<DVD> dvds = service.findMoviesMatching(query);
        return dvds;
    }

    @PostMapping("/edit")
    public DVD editDVD(String title, String newRating) throws MovieDAOException {
        DVD updatedDVD = service.editRating(title, newRating);
        return updatedDVD;
    }

    @DeleteMapping("/remove")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeDVD(String title) throws MovieDAOException {
        service.removeMovie(title);
    }
}
