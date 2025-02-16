package model;

//this class contains the definition and operation of ComplexNumbers that will be used in the Qubit class to 
// perform calculations

public class ComplexNumber {
    double real;
    double imaginary;

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;

    }

    //EFFECT: calculates alpha^2 + beta^2 = |z|^2 in probability calculations
    public double magnitudeSquared(){
        return (real * real) + (imaginary * imaginary);

    }

    //MODIFIES: this
    //EFFECT: Multiplies two complex numbers together and return the result as a new Complex number
    public ComplexNumber multiply(ComplexNumber otherNumber){
        double resultReal;
        double resultImaginary;

        resultReal = (real * otherNumber.real - imaginary) * (otherNumber.imaginary);
        resultImaginary = (real * otherNumber.imaginary) + (imaginary * otherNumber.real);
        

        return new ComplexNumber(resultReal, resultImaginary);
    }

    //MODIFIES: this
    //EFFECTS: adds two complex numbers together and returns the result as a new complex number
    public ComplexNumber add(ComplexNumber otherNumber){
        double resultReal;
        double resultImaginary;
        resultReal = real + otherNumber.real;
        resultImaginary = imaginary + otherNumber.imaginary;

        return new ComplexNumber(resultReal, resultImaginary);
    }

}
