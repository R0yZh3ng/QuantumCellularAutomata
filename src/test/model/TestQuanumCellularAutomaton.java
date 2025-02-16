package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;

public class TestQuanumCellularAutomaton {
    private QuantumCellularAutomaton testGrid;
    private int size;
    private Qubit[][] sampleGrid;
    private Qubit[][] sampleGrid2;

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

        sampleGrid2 = new Qubit[size][size];
        for (int i = 0; i < size; i++) { 
            for (int j = 0; j < size; j++) { 
                sampleGrid2[i][j] = new Qubit(new ComplexNumber(1 / Math.sqrt(2), 0),
                                             new ComplexNumber(1 / Math.sqrt(2), 0)); 
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
    void testGetGrid(){
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
        Boolean changed = false;
        for (int i = 0; i < size; i++) { 
            for (int j = 0; j < size; j++) { 
                if (!sampleGrid2[i][j].getAlpha().equals(testGrid.grid[i][j].getAlpha()) || 
                sampleGrid2[i][j].getBeta().equals(testGrid.grid[i][j].getBeta())){
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
                assertEquals(new Color(255, 0, 0), testGrid.grid[i][j].getColor());
            }
        }
    }

    
}
