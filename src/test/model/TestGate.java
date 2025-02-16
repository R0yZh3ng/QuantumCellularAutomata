package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestGate {
    private Gate testGate;
    private ComplexNumber alpha;
    private ComplexNumber beta;
    private ComplexNumber omega;
    private ComplexNumber theta;
    private ComplexNumber[][] testMatrix;
    private ComplexNumber[][] sample;
    
    @BeforeEach
    void runBefore() {
        alpha = new ComplexNumber(1, 0);
        beta = new ComplexNumber(0, 1);
        omega = new ComplexNumber(0, -1);
        theta = new ComplexNumber(1, 0);

        testMatrix = new ComplexNumber[][]{{alpha, beta}, {omega, theta}};

        testGate = new Gate(testMatrix);
    }

    @Test
    void testConstructor() {
        sample = new ComplexNumber[][] 
        {{new ComplexNumber(1, 0),
          new ComplexNumber(0, 1)}, 
         {new ComplexNumber(0, -1),
          new ComplexNumber(1, 0)}};

        assertEquals(testMatrix[0][0], sample[0][0]);
        assertEquals(testMatrix[0][1], sample[0][1]);
        assertEquals(testMatrix[1][0], sample[1][0]);
        assertEquals(testMatrix[1][1], sample[1][1]);
    }
    
    @Test
    public void testMultiplyMatrixRowWithHadamardGate() {
        // Prepare a Hadamard gate
        Gate hadamardGate = Gate.HADAMARD;

        // Define input qubit state |0> (alpha = 1, beta = 0)
        ComplexNumber alpha = new ComplexNumber(1, 0);
        ComplexNumber beta = new ComplexNumber(0, 0);
        
        // Test multiplyMatrixRow for row 0 and row 1
        ComplexNumber resultRow0 = hadamardGate.multiplyMatrixRow(0, alpha, beta);
        ComplexNumber resultRow1 = hadamardGate.multiplyMatrixRow(1, alpha, beta);
        
        // Expected result from Hadamard gate multiplication on |0>
        ComplexNumber expectedRow0 = new ComplexNumber(1 / Math.sqrt(2), 0);
        ComplexNumber expectedRow1 = new ComplexNumber(1 / Math.sqrt(2), 0);
        
        // Assertions
        assertEquals(expectedRow0, resultRow0, "Row 0 should be equal to expected value");
        assertEquals(expectedRow1, resultRow1, "Row 1 should be equal to expected value");
    }
    

    @Test
    public void testMultiplyMatrixRowWithBitflipGate() {
        // Prepare a Bitflip gate
        Gate bitflipGate = Gate.BITFLIP;

        // Define input qubit state |0> (alpha = 1, beta = 0)
        ComplexNumber alpha = new ComplexNumber(1, 0);
        ComplexNumber beta = new ComplexNumber(0, 0);
        
        // Test multiplyMatrixRow for row 0 and row 1
        ComplexNumber resultRow0 = bitflipGate.multiplyMatrixRow(0, alpha, beta);
        ComplexNumber resultRow1 = bitflipGate.multiplyMatrixRow(1, alpha, beta);
        
        // Expected result from Bitflip gate multiplication on |0>
        ComplexNumber expectedRow0 = new ComplexNumber(0, 0);
        ComplexNumber expectedRow1 = new ComplexNumber(1, 0);
        
        // Assertions
        assertEquals(expectedRow0, resultRow0, "Row 0 should be equal to expected value");
        assertEquals(expectedRow1, resultRow1, "Row 1 should be equal to expected value");
    }


    @Test
    public void testMultiplyMatrixRowWithPhaseflipGate() {
        // Prepare a Phaseflip gate
        Gate phaseflipGate = Gate.PHASEFLIP;

        // Define input qubit state |0> (alpha = 1, beta = 0)
        ComplexNumber alpha = new ComplexNumber(1, 0);
        ComplexNumber beta = new ComplexNumber(0, 0);
        
        // Test multiplyMatrixRow for row 0 and row 1
        ComplexNumber resultRow0 = phaseflipGate.multiplyMatrixRow(0, alpha, beta);
        ComplexNumber resultRow1 = phaseflipGate.multiplyMatrixRow(1, alpha, beta);
        
        // Expected result from Phaseflip gate multiplication on |0>
        ComplexNumber expectedRow0 = new ComplexNumber(1, 0);
        ComplexNumber expectedRow1 = new ComplexNumber(0, 0);
        
        // Assertions
        assertEquals(expectedRow0, resultRow0, "Row 0 should be equal to expected value");
        assertEquals(expectedRow1, resultRow1, "Row 1 should be equal to expected value");
    }

    @Test
    public void testMultiplyMatrixRowWithNonStandardState() {
        // Prepare a Hadamard gate
        Gate hadamardGate = Gate.HADAMARD;

        // Define input qubit state |+> (alpha = 1/sqrt(2), beta = 1/sqrt(2))
        ComplexNumber alpha = new ComplexNumber(1 / Math.sqrt(2), 0);
        ComplexNumber beta = new ComplexNumber(1 / Math.sqrt(2), 0);
        
        // Test multiplyMatrixRow for row 0 and row 1
        ComplexNumber resultRow0 = hadamardGate.multiplyMatrixRow(0, alpha, beta);
        ComplexNumber resultRow1 = hadamardGate.multiplyMatrixRow(1, alpha, beta);
        
        // Expected result from Hadamard gate multiplication on |+>
        ComplexNumber expectedRow0 = new ComplexNumber(1, 0);
        ComplexNumber expectedRow1 = new ComplexNumber(0, 0);
        
        // Assertions
        assertEquals(expectedRow0, resultRow0, "Row 0 should be equal to expected value");
        assertEquals(expectedRow1, resultRow1, "Row 1 should be equal to expected value");
    }
}
