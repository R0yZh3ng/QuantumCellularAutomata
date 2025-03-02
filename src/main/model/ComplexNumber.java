package model;

//this class contains the definition and operation of ComplexNumbers that will be used in the Qubit class to 
// perform calculations

import persistence.*;
import org.json.*;

public class ComplexNumber implements Serializer{
    private double real;
    private double imaginary;

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;

    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }
    //EFFECT: calculates alpha^2 + beta^2 = |z|^2 in probability calculations
    public double magnitudeSquared() {
        return (real * real) + (imaginary * imaginary);

    }

    //MODIFIES: this
    //EFFECT: Multiplies the current complex numbers together with another and return the result as a new Complex number
    public ComplexNumber multiply(ComplexNumber otherNumber) {
        double resultReal;
        double resultImaginary;

        resultReal = (real * otherNumber.real) - (imaginary * otherNumber.imaginary);
        resultImaginary = (real * otherNumber.imaginary) + (imaginary * otherNumber.real);
        

        return new ComplexNumber(resultReal, resultImaginary);
    }

    //MODIFIES: this
    //EFFECTS: adds the current complex numbers together with an other and returns the result as a new complex number
    public ComplexNumber add(ComplexNumber otherNumber) {
        double resultReal;
        double resultImaginary;
        resultReal = real + otherNumber.real;
        resultImaginary = imaginary + otherNumber.imaginary;

        return new ComplexNumber(resultReal, resultImaginary);
    }

    
    //EFFECTS: allows for a very small margin of error to account of floating point errors
    //overrides the equals comparison to return true when errors is smaller then 1e-10
    //everything else is the same as a regular equals 
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        ComplexNumber that = (ComplexNumber) obj;

        return Math.abs(this.real - that.real) < 1e-10 && Math.abs(this.imaginary - that.imaginary) < 1e-10;
    }
    
    @Override
    //EFFECTS
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("real", getReal());
        json.put("imaginary", getImaginary());
        return json;
    }


}
