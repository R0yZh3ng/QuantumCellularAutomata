package model;


//This class represents the grid that all the Qubit will be displayed on, 
public class QuantumCellularAutomaton {
    private Qubit[][] grid;
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
    //EFFECTS: updates all the Qubits in the grid using the given gate.
    public void updateGrid(Gate gate) {
        Qubit[][] updatedGrid = new Qubit[size][size];
    }

    //Effects: Runs the simualtion forever in a loop until specified to stop when running = false
    public void runSimulation(Boolean running, Gate gate) {
        while (running) {
            updateGrid(gate); //stub
        }
    }
}
