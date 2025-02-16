package model;

import javax.swing.*;
import java.awt.*;

//This class represents the grid that all the Qubit will be displayed on, 
public class QuantumCellularAutomaton {
    public Qubit[][] grid;
    private int size;

    public QuantumCellularAutomaton(int size) {
        this.size = size;
        this.grid = new Qubit[size][size];

        for (int i = 0; i < size; i++) { 
            for (int j = 0; j < size; j++) { 
                grid[i][j] = new Qubit(); //all qubits start with the |0> state
            }
        }

        Timer timer = new Timer(200, e -> {
            updateGrid();
            paintGrid();
        });
        
        timer.start();
    }

    public Qubit[][] getGrid() {
        return grid;
    }

    public int getSize() {
        return size;
    }

    //MODIFIES: this, Qubit
    //EFFECTS: updates all the Qubits in the grid (apply entaglement on to neighbours)
    public void updateGrid() {
        Qubit[][] updatedGrid = new Qubit[size][size];
    }

    //MODIFIES this, Qubit
    //EFFECTS: applies colors to all the Qubits in the grid (more red or more blue of more |0> or |1> respectively)
    public void paintGrid() {
        Qubit[][] updatedGrid = new Qubit[size][size];
    }

    //Effects: Runs the simualtion forever in a loop until specified to stop when running = false
    public void runSimulation(Boolean running, Gate gate) {
        while (running) {
            updateGrid(); //stub
        }
    }
}
