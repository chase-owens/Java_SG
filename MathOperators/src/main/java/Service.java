


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chaseowens
 */
class Service {

    int doMath(Operator operator, int[] operands) {
        switch(operator) {
            case PLUS:
                return operands[0] + operands[1];
            case MINUS:
                return operands[0] - operands[1];
            case MULTIPLY:
                return operands[0] * operands[1];
            case DIVIDE:
                return Math.round(operands[0]/operands[1]);
            default:
                return 0;
        }
    }
    
}
