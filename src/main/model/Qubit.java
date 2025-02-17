package model;

import java.util.Random;

import javax.swing.*;
import java.awt.*;

 // This class should contain the properties of a Qubit as well 
 //as the methods that simulate classical and quantum gate operations
 
public class Qubit {
   

    //probability amplitude is a complex number that determines the 
    //probability of a qubit being in a certain state before measurement.

    private ComplexNumber alpha; // this is the probability amplitude of  |0> 
    private ComplexNumber beta;
    private Color color; // this is the probability amplitude of |1> 

    //REQUIRES alpha^2 + beta^2 must = 1 to ensure that the state is normalized 
    public Qubit() {
        this.alpha = new ComplexNumber(1, 0);
        this.beta = new ComplexNumber(0, 0);
        this.color = new Color(255, 0, 0);
    }

    public Qubit(ComplexNumber alpha, ComplexNumber beta) {
        this.alpha = alpha;
        this.beta = beta;
    }

    //MODIFIES: this
    //EFFECTS: iterates through the rows(that are max two because only a 2x2 matrix is allowed), and multiples 
    //the rows by the Gate that is defined in the Gate class
    public void applyGate(Gate gate) {
        ComplexNumber[] newState = new ComplexNumber[2];
        for (int i = 0; i < 2; i++) {
            newState[i] = gate.multiplyMatrixRow(i, alpha, beta);
        }
        this.alpha = newState[0];
        this.beta = newState[1];
    }

    public ComplexNumber getAlpha() {
        return this.alpha;
    }

    public ComplexNumber getBeta() {
        return this.beta;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    //MODIFIES: this 
    //EFFECTS: this method collapsese the qubit into either |0> or |1> by comparing
    //a random number with the square of the probability amplitude of |0>, if the random 
    //is smaller, then it collapses to |0>, else |1>
    public void measure(Random randomDouble) {
        double probability = this.alpha.magnitudeSquared();
        if (randomDouble.nextDouble() < probability) {
            this.alpha = new ComplexNumber(1, 0);
            this.beta = new ComplexNumber(0, 0);
        } else {
            this.alpha = new ComplexNumber(0,0);
            this.beta = new ComplexNumber(1, 0);
        }
    }

    //MODIFIES: this
    //EFFECTS: applies entanglement to the Qubit's neighbour
    public void entangle(Qubit neighbour) {
        if (Math.random() < 0.3) {
            applyGate(Gate.HADAMARD);
        } else if (Math.random() < 0.6) {
            applyGate(Gate.BITFLIP);
        } else {
            applyGate(Gate.PHASEFLIP);
        }
    }

    //EFFECTS: returns a string as ASCII for the console ui to print out
    public String toAscii() {
        double probZero = alpha.magnitudeSquared();
        if (probZero > 0.7) {
            return "0"; 
        } else if (probZero < 0.3) {
            return "1"; 
        } else {
            return "?"; 
        }
    }

}
