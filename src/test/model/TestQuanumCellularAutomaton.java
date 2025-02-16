package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;

public class TestQuanumCellularAutomaton {
    private QuantumCellularAutomaton testGrid;
    private int size;
    private Qubit[][] sampleGrid;

    @BeforeEach
    void runBefore(){
        size = 10;
        testGrid = new QuantumCellularAutomaton(size);
        sampleGrid = new Qubit[size][size];

        for (int i = 0; i < size; i++) { 
            for (int j = 0; j < size; j++) { 
                sampleGrid[i][j] = new Qubit(); 
            }
        }

    }

    @Test
    void testConstructor() {
        assertEquals(10, size);

        for (int i = 0; i < size; i++) { 
            for (int j = 0; j < size; j++) { 
                assertEquals(sampleGrid[i][j].getAlpha(), testGrid.grid[i][j].getAlpha());
                assertEquals(sampleGrid[i][j].getBeta(), testGrid.grid[i][j].getBeta());
            }
        }
    }

    @Test
    void testUpdateGrid() {
        testGrid.updateGrid();
        for (int i = 0; i < size; i++) { 
            for (int j = 0; j < size; j++) { 
                assertNotEquals(sampleGrid[i][j].getAlpha(), testGrid.grid[i][j].getAlpha());
                assertNotEquals(sampleGrid[i][j].getBeta(), testGrid.grid[i][j].getBeta());
            }
        }
    }

    @Test
    void paintGrid() {
        testGrid.paintGrid();
        for (int i = 0; i < size; i++) { 
            for (int j = 0; j < size; j++) { 
                assertEquals(new Color(255, 0, 0), testGrid.grid[i][j].getColor());
            }
        }
    }

    
}
