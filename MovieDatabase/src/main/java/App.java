
import mycompany.moviedatabase.controller.Controller;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chaseowens
 */
public class App {

    public static void main(String[] args) {
        // Instantiate Spring Container
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Get controller
        Controller controller = ctx.getBean("controller", Controller.class);

        // Call run
        controller.run();
    }

}
