package model;
import java.util.Random;

 // This class should contain the properties of a Qubit as well 
 //as the methods that simulate classical and quantum gate operations
 
public class Qubit {
   

    //probability amplitude is a complex number that determines the probability of a qubit being in a certain state before measurement.

    private ComplexNumber alpha; // this is the probability amplitude of  |0> 
    private ComplexNumber beta; // this is the probability amplitude of |1> 

    //REQUIRES alpha^2 + beta^2 must = 1 to ensure that the state is normalized 
    public Qubit(ComplexNumber alpha, ComplexNumber beta) {
        this.alpha = new ComplexNumber(1, 0);
        this.beta = new ComplexNumber(0, 0);
    }


}
