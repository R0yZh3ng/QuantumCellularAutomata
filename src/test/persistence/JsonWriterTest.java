package persistence;

import model.ComplexNumber;
import model.Qubit;
import model.QuantumCellularAutomaton;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            QuantumCellularAutomaton qca = new QuantumCellularAutomaton(2);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyAutomaton() {
        try {
            QuantumCellularAutomaton qca = new QuantumCellularAutomaton(0); // Empty grid
            qca.setName("your simulation"); // Match your data's name
            JsonWriter writer = new JsonWriter("src/data/testWriterQuantumCellularAutomata.json");
            writer.open();
            writer.write(qca);
            writer.close();

            JsonReader reader = new JsonReader("src/data/testWriterQuantumCellularAutomata.json");
            qca = reader.read();
            assertEquals("your simulation", qca.getName());
            assertEquals(0, qca.getSize());
            assertEquals(0, qca.getGrid().length);
        } catch (IOException e) {
            fail("Exception should not have been thrown: " + e.getMessage());
        }
    }


    @Test
    void testWriterSpecificAutomaton() {
        try {
            QuantumCellularAutomaton qca = new QuantumCellularAutomaton(2); // 2x2 grid
            qca.setName("your simulation");

            // Set up the grid to match your JSON data
            Qubit[][] grid = qca.getGrid();
            grid[0][0] = new Qubit(new ComplexNumber(1.0, 0.0), 
                                   new ComplexNumber(0.0, 0.0), 
                                   new Color(255, 0, 0));
            grid[0][1] = new Qubit(new ComplexNumber(0.7071067811865475, 0.0), 
                                   new ComplexNumber(-0.7071067811865475, 0.0), 
                                   new Color(127, 0, 127));
            grid[1][0] = new Qubit(new ComplexNumber(1.0, 0.0), 
                                   new ComplexNumber(0.0, 0.0), 
                                   new Color(255, 0, 0));
            grid[1][1] = new Qubit(new ComplexNumber(0.9999999999999998, 0.0), 
                                   new ComplexNumber(0.0, 0.0), 
                                   new Color(254, 0, 0));

            JsonWriter writer = new JsonWriter("src/data/testWriterQuantumCellularAutomata.json");
            writer.open();
            writer.write(qca);
            writer.close();

            JsonReader reader = new JsonReader("src/data/testWriterQuantumCellularAutomata.json");
            qca = reader.read();
            assertEquals("your simulation", qca.getName());
            assertEquals(2, qca.getSize());

            Qubit[][] readGrid = qca.getGrid();
            assertEquals(2, readGrid.length);
            assertEquals(2, readGrid[0].length);

            checkQubit(new ComplexNumber(1.0, 0.0), new ComplexNumber(0.0, 0.0), new Color(255, 0, 0), readGrid[0][0]);
            checkQubit(new ComplexNumber(0.7071067811865475, 0.0), new ComplexNumber(-0.7071067811865475, 0.0), 
                       new Color(127, 0, 127), readGrid[0][1]);
            checkQubit(new ComplexNumber(1.0, 0.0), new ComplexNumber(0.0, 0.0), new Color(255, 0, 0), readGrid[1][0]);
            checkQubit(new ComplexNumber(0.9999999999999998, 0.0), new ComplexNumber(0.0, 0.0), 
                       new Color(254, 0, 0), readGrid[1][1]);
        } catch (IOException e) {
            fail("Exception should not have been thrown: " + e.getMessage());
        }
    }
}
