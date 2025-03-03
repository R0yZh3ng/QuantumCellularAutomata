package persistence;

import model.*;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;

// Assuming JsonTest is a parent class with utility methods
public class JsonTest {
    protected void checkQubit(ComplexNumber expectedAlpha, 
                              ComplexNumber expectedBeta,
                              Color expectedColor, 
                              Qubit qubit) {
        assertEquals(expectedAlpha.getReal(), qubit.getAlpha().getReal());
        assertEquals(expectedAlpha.getImaginary(), qubit.getAlpha().getImaginary());
        assertEquals(expectedBeta.getReal(), qubit.getBeta().getReal());
        assertEquals(expectedBeta.getImaginary(), qubit.getBeta().getImaginary());
        assertEquals(expectedColor.getRed(), qubit.getColor().getRed());
        assertEquals(expectedColor.getGreen(), qubit.getColor().getGreen());
        assertEquals(expectedColor.getBlue(), qubit.getColor().getBlue());
    }
}