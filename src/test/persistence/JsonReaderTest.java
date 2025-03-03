package persistence;

import model.ComplexNumber;
import model.Qubit;
import model.QuantumCellularAutomaton;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            QuantumCellularAutomaton qca = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderSpecificAutomaton() {
        JsonReader reader = new JsonReader("src/data/testReaderQuantumCellularAutomata.json");
        try {
            QuantumCellularAutomaton qca = reader.read();
            assertEquals("your simulation", qca.getName());
            assertEquals(2, qca.getSize());

            Qubit[][] grid = qca.getGrid();
            assertEquals(2, grid.length);
            assertEquals(2, grid[0].length);

            // Row 0, Col 0
            checkQubit(new ComplexNumber(1.0, 0.0), new ComplexNumber(0.0, 0.0), new Color(255, 0, 0), grid[0][0]);

            // Row 0, Col 1
            checkQubit(new ComplexNumber(0.7071067811865475, 0.0), new ComplexNumber(-0.7071067811865475, 0.0), 
                       new Color(127, 0, 127), grid[0][1]);

            // Row 1, Col 0
            checkQubit(new ComplexNumber(1.0, 0.0), new ComplexNumber(0.0, 0.0), new Color(255, 0, 0), grid[1][0]);

            // Row 1, Col 1
            checkQubit(new ComplexNumber(0.9999999999999998, 0.0), new ComplexNumber(0.0, 0.0), 
                       new Color(254, 0, 0), grid[1][1]);
        } catch (IOException e) {
            fail("Couldn't read from file: " + e.getMessage());
        }
    }
}












