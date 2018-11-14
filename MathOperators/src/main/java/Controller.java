
import static java.lang.String.valueOf;






/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chaseowens
 */
class Controller {
    View view = new View();
    Service service = new Service();
    
    public void run() {
        
        int selection = view.displayMenu();
        int[] operands = new int[2];
        Operator operator = null;
        
        
        switch(selection) {
            case 1:
                operands = view.getOperands("plus");
                operator = Operator.valueOf("PLUS");
                break;
            case 2:
                operands = view.getOperands("minus");
                operator = Operator.valueOf("MINUS");
                break;
            case 3:
                operands = view.getOperands("multiply");
                operator = Operator.valueOf("MINUS");
                break;
            case 4:
                operands = view.getOperands("divide");
                operator = Operator.valueOf("MINUS");
                break;
            default:
                break;
        }
        
        int answer = service.doMath(operator, operands);
        
        view.print(answer);
    }
    
}
