/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycompany.moviedatabase.dao;

import mycompany.moviedatabase.dto.DVD;
import mycompany.moviedatabase.dto.MovieDAOException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author chaseowens
 */
public class DAOTest {

    DAO dao = new DAOImpl();
    DVD tester;

    public DAOTest(DAO dao) {
        this.tester = new DVD("a", "a", "a", "a", "a", "a");
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        dao.deleteList();
    }

    @After
    public void tearDown() throws MovieDAOException {
        dao.loadMovies();
    }

    /**
     * Test of makeDVD method, of class DAO.
     */
    @Test
    public void testMakeDVD() {
        DVD newMovie = dao.makeDVD("a", "a", "a", "a", "a", "a");
        assertEquals(newMovie, tester);
    }

    /**
     * Test of addMovieToList method, of class DAO.
     */
    @Test
    public void testAddMovieToList() {
        assertEquals(0, dao.getAllMovies().length);
        assertEquals(0, dao.getMovieList().size());
        dao.addMovieToList(tester);
        assertEquals(1, dao.getAllMovies().length);
        assertEquals(1, dao.getMovieList().size());
    }

    /**
     * Test of removeMovie method, of class DAO.
     */
    @Test
    public void testRemoveMovie() {
        dao.addMovieToList(tester);
        assertEquals(1, dao.getAllMovies().length);
        dao.removeMovie(tester.getTitle());
        assertEquals(0, dao.getAllMovies().length);
    }

    /**
     * Test of editRating method, of class DAO.
     */
    @Test
    public void testEditRating() {
        String ratingBefore = tester.getRating();
        dao.addMovieToList(tester);
        dao.editRating(tester.getTitle(), ratingBefore + ratingBefore);
        String[] info = dao.getMovieInfo(tester.getTitle());
        assertNotEquals(ratingBefore, info[5]);
    }

    /**
     * Test of getMovieInfo method, of class DAO.
     */
    @Test
    public void testGetMovieInfo() {
        String[] information = new String[6];
        information[0] = tester.getTitle();
        information[1] = tester.getDate().toString();
        information[2] = tester.getStudio();
        information[3] = tester.getDirectorsName();
        information[4] = tester.getMPAArating();
        information[5] = tester.getRating();
        
        dao.addMovieToList(tester);
        
        String[] retreivedInformation = dao.getMovieInfo(tester.getTitle());
        assertArrayEquals(information, retreivedInformation);
    }

    /**
     * Test of findMoviesMatching method, of class DAO.
     */
    @Test
    public void testFindMoviesMatching() {
        assertEquals(0, dao.findMoviesMatching("").length);
        dao.addMovieToList(tester);
        assertEquals(1, dao.findMoviesMatching(tester.getTitle()).length);

        DVD tester2 = dao.makeDVD(tester.getTitle() + tester.getTitle(), tester.getDate().toString(), tester.getMPAArating(), tester.getDirectorsName(), tester.getStudio(), tester.getRating());
        dao.addMovieToList(tester2);
        assertEquals(2, dao.findMoviesMatching(tester.getTitle()).length);
    }
}
