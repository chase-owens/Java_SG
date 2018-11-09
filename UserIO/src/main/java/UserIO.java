/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chaseowens
 */
public interface UserIO {
    void ioPrint(String message);
    
    double ioReadDouble();
    
    double ioReadDoubleRange();

    float ioReadFloat();

    float ioReadFloatRange();

    int ioReadInt();

    int ioReadIntRAnge();

    long ioReadLong();

    long ioReadLongRange();

    String ioReadString();
}
