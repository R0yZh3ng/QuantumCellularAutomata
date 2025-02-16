package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestComplexNumber {
    private double alpha1;
    private double beta1;

    private double alpha2;
    private double beta2;

    private double alpha3;
    private double beta3;

    private ComplexNumber cnum1;
    private ComplexNumber cnum2;
    private ComplexNumber cnum3;
    private ComplexNumber cnum4;

    private Qubit qubit;

    
    @BeforeEach
    void runBefore() {
        alpha1 = 0;
        beta1 = 1;
        cnum1 = new ComplexNumber(alpha1, beta1);

        alpha2 = 0.5;
        beta2 = 0.5;
        cnum2 = new ComplexNumber(alpha2, beta2);

        alpha3 = 1.0;
        beta3 = 0;
        cnum3 = new ComplexNumber(alpha3, beta3);

        cnum4 = new ComplexNumber(alpha1, beta1); //this is set to the same as cnum1 to test the equals override
        qubit = new Qubit(cnum1, cnum2);// another class to test the override equals
    }


    @Test
    void testComplexNumberConstructor() {
        assertEquals(cnum1.real, 0.0);
        assertEquals(cnum1.imaginary, 1.0);
    }
    
    @Test
    void testMagnitudeSquared() {
        assertEquals(cnum1.magnitudeSquared(), 1);
        assertEquals(cnum2.magnitudeSquared(), 0.5);
        assertEquals(cnum3.magnitudeSquared(), 1);
    }

    @Test
    void testMultiply() {
        assertEquals(cnum1.multiply(cnum2), new ComplexNumber(-0.50, 0.50));
        assertEquals(cnum2.multiply(cnum2), new ComplexNumber(0, 0.5));
        assertEquals(cnum3.multiply(cnum2),  new ComplexNumber(0.50, 0.50));
    }

    @Test
    void testAdd() {
        assertEquals(cnum1.add(cnum2), new ComplexNumber(0.50, 1.50));
        assertEquals(cnum2.add(cnum2), new ComplexNumber(1.0, 1.0));
        assertEquals(cnum3.add(cnum2), new ComplexNumber(1.50, 0.50));
    }

    @Test
    void testEqualsOverride() {
        assertTrue(cnum1.equals(cnum1));
        assertTrue(cnum1.equals(cnum4));
        assertFalse(cnum1.equals(cnum2));
        
        assertFalse(cnum1.equals(null));
        assertFalse(cnum1.equals(qubit));

        assertTrue(cnum1.equals(new ComplexNumber(0, 1.00000000001)));
        assertFalse(cnum1.equals(new ComplexNumber(0, 1.0000000001)));
    }

}
