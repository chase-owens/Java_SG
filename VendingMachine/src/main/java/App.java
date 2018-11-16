
import com.mycompany.vendingmachine.controller.VendingMachineController;
import com.mycompany.vendingmachine.dao.Dao;
import com.mycompany.vendingmachine.dao.DaoImpl;
import com.mycompany.vendingmachine.dao.InsufficientFundsError;
import com.mycompany.vendingmachine.dao.OutOfStockException;
import com.mycompany.vendingmachine.dao.VendingMachinePersistenceError;
import com.mycompany.vendingmachine.service.Service;
import com.mycompany.vendingmachine.service.ServiceImpl;
import com.mycompany.vendingmachine.view.UserIO;
import com.mycompany.vendingmachine.view.UserIOImpl;
import com.mycompany.vendingmachine.view.View;

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
    public static void main(String[] args)  throws InsufficientFundsError, OutOfStockException, VendingMachinePersistenceError {
        UserIO io = new UserIOImpl();
        View view = new View(io);
        Dao dao = new DaoImpl();
        Service service = new ServiceImpl(dao);
        VendingMachineController VendingMachineControl = new VendingMachineController(view, service);
        VendingMachineControl.run();
        
    }
    
}
