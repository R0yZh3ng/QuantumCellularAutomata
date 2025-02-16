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
    }

    //MODIFIES: this, Qubit
    //EFFECTS: updates all the Qubits in the grid (apply entaglement on to neighbours)
    public void updateGrid() {
        for (int i = 0; i < size; i++) { 
            for (int j = 0; j < size; j++) { 
                Qubit current = grid[i][j];
                
                if (i > 0) {
                    current.entangle(grid[i-1][j]);
                }
                if (j > 0) {
                    current.entangle(grid[i][j - 1]);
                }
                if (i < size -1) {
                    current.entangle(grid[i + 1][j]);
                }
                if (j < size -1) {
                    current.entangle(grid[i][j + 1]);
                }
            }
        }
    }

    //MODIFIES this, Qubit
    //EFFECTS: applies colors to all the Qubits in the grid (more red or more blue of more |0> or |1> respectively)
    public void paintGrid() {
        for (int i = 0; i < size; i++) { 
            for (int j = 0; j < size; j++) { 
                Qubit qubit = grid[i][j];
                
                double prob0 = qubit.getAlpha().magnitudeSquared();
                double prob1 = qubit.getBeta().magnitudeSquared();

                int red = (int) (prob0 * 255);
                int blue = (int) (prob1 * 255);
    
                qubit.setColor(new Color(red, 0, blue)); 
            }
        }
    }
    
}
