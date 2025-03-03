package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;

public class TestQuanumCellularAutomaton {
    private QuantumCellularAutomaton testGrid;
    private int size;
    private Qubit[][] grid;
    private Qubit[][] gridReplica;
    private Qubit[][] sampleGrid2;

    @BeforeEach
    void runBefore() {
        size = 10;
        testGrid = new QuantumCellularAutomaton(size);
        grid = new Qubit[size][size];

        for (int i = 0; i < size; i++) { 
            for (int j = 0; j < size; j++) { 
                grid[i][j] = new Qubit(); 
            }
        }

        gridReplica = new Qubit[size][size];
        for (int i = 0; i < size; i++) { 
            for (int j = 0; j < size; j++) { 
                gridReplica[i][j] = new Qubit(); 
            }
        }

        sampleGrid2 = new Qubit[size][size];
        for (int i = 0; i < size; i++) { 
            for (int j = 0; j < size; j++) { 
                sampleGrid2[i][j] = new Qubit(new ComplexNumber(1 / Math.sqrt(2), 0),
                                             new ComplexNumber(1 / Math.sqrt(2), 0),
                                             new Color(255, 0, 0)); 
            }
        }

    }

    @Test
    void testConstructor() {
        assertEquals(10, size);

        for (int i = 0; i < size; i++) { 
            for (int j = 0; j < size; j++) { 
                assertEquals(grid[i][j].getAlpha(), testGrid.getGrid()[i][j].getAlpha());
                assertEquals(grid[i][j].getBeta(), testGrid.getGrid()[i][j].getBeta());
            }
        }
    }
    
    @Test
    void testGetGrid() {
        for (int i = 0; i < size; i++) { 
            for (int j = 0; j < size; j++) { 
                assertEquals(grid[i][j].getAlpha(), testGrid.getGrid()[i][j].getAlpha());
                assertEquals(grid[i][j].getBeta(), testGrid.getGrid()[i][j].getBeta());
            }
        }
    }

    @Test
    void testGetSize() {
        assertEquals(testGrid.getSize(), 10);
    }


    @Test
    void testUpdateGrid() {
        testGrid.updateGrid();
        Boolean changed = false;
        for (int i = 0; i < size; i++) { 
            for (int j = 0; j < size; j++) { 
                if (!sampleGrid2[i][j].getAlpha().equals(testGrid.getGrid()[i][j].getAlpha()) 
                        || sampleGrid2[i][j].getBeta().equals(testGrid.getGrid()[i][j].getBeta())) { 
                    changed = true;
                }

            }
        }
        assertTrue(changed);
    }

    @Test
    void paintGrid() {
        testGrid.paintGrid();
        for (int i = 0; i < size; i++) { 
            for (int j = 0; j < size; j++) { 
                assertEquals(new Color(255, 0, 0), testGrid.getGrid()[i][j].getColor());
            }
        }
    }

    @Test
    void testGateAppliedToAllQubitsEqual() {
        QuantumCellularAutomaton.applyGate(testGrid, Gate.HADAMARD);
        Boolean changed = true;

        // Check if all qubits have been updated
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                assertTrue(sampleGrid2[i][j].getAlpha().equals(testGrid.getGrid()[i][j].getAlpha()));
                assertTrue(sampleGrid2[i][j].getBeta().equals(testGrid.getGrid()[i][j].getBeta()));
                
            }
        }
    }

}
