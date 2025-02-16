package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Random;
import java.awt.*;


public class TestQubit {
    private Qubit testQubit;
    private Qubit testQubit1;
    private Qubit testQubit2;
    private Qubit testQubit3;
    private Qubit testQubit0;
    private ComplexNumber alpha;
    private ComplexNumber alpha1;
    private ComplexNumber alpha2;
    private ComplexNumber alpha3;
    private ComplexNumber beta;
    private ComplexNumber beta1;
    private ComplexNumber beta2;
    private ComplexNumber beta3;
    private Random randomDouble;

    @BeforeEach
    void runBefore() {
        randomDouble = new Random();

        alpha = new ComplexNumber(1, 0);
        beta = new ComplexNumber(0, 0);

        alpha1 = new ComplexNumber(0.7, 0.3);
        beta1 = new ComplexNumber(0.4, 0.6);

        alpha2 = new ComplexNumber(0, 0);
        beta2 = new ComplexNumber(1, 0);
        
        alpha3 = new ComplexNumber(1 / Math.sqrt(2), 0);
        beta3 = new ComplexNumber(0, 1 / Math.sqrt(2));    

        testQubit0 = new Qubit();
        testQubit = new Qubit(alpha, beta);
        testQubit1 = new Qubit(alpha1, beta1);
        testQubit2 = new Qubit(alpha2, beta2);
        testQubit3 = new Qubit(alpha3, beta3);
    }

    @Test 
    void testConstructor() {
        assertEquals(alpha1, testQubit1.getAlpha());
        assertEquals(beta1, testQubit1.getBeta());
        assertEquals(alpha, testQubit0.getAlpha());
        assertEquals(beta, testQubit0.getBeta());
        assertEquals(testQubit0.getColor(), new Color(255, 0, 0));
    }

    @Test
    void testApplyHadamardGate() {
        testQubit.applyGate(Gate.HADAMARD);

        assertEquals(testQubit.getAlpha(), new ComplexNumber(1 / Math.sqrt(2), 0));
        assertEquals(testQubit.getBeta(), new ComplexNumber(1 / Math.sqrt(2), 0));
    }

    @Test
    void testApplyBitFlipGate() {
        testQubit.applyGate(Gate.BITFLIP);

        assertEquals(testQubit.getAlpha(), new ComplexNumber(0, 0));
        assertEquals(testQubit.getBeta(), new ComplexNumber(1, 0));
    }

    @Test
    void testApplyPhaseFlipGate() {
        testQubit.applyGate(Gate.PHASEFLIP);

        assertEquals(testQubit.getAlpha(), new ComplexNumber(1, 0));
        assertEquals(testQubit.getBeta(), new ComplexNumber(0, 0));
    }

    @Test
    void testMeasure() {
        testQubit.measure(randomDouble);

        assertEquals(testQubit.getAlpha(), new ComplexNumber(1, 0));
        assertEquals(testQubit.getBeta(), new ComplexNumber(0, 0));

        Random randomDouble2 = new Random(10);
        testQubit1.measure(randomDouble2);

        assertEquals(testQubit1.getAlpha(), new ComplexNumber(0, 0));
        assertEquals(testQubit1.getBeta(), new ComplexNumber(1, 0));

        Random randomDouble3 = new Random(80);
        testQubit2.measure(randomDouble3);
        assertEquals(testQubit2.getAlpha(), new ComplexNumber(0, 0));
        assertEquals(testQubit2.getBeta(), new ComplexNumber(1, 0));

        Random randomDouble4 = new Random(2156);
        testQubit3.measure(randomDouble4);
        assertEquals(testQubit3.getAlpha(), new ComplexNumber(0, 0));
        assertEquals(testQubit3.getBeta(), new ComplexNumber(1, 0));

    }

}
