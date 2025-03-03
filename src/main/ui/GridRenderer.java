package ui;

import model.QuantumCellularAutomaton;
import model.Qubit;

public class GridRenderer {
    private QuantumCellularAutomaton automata;

    //EFFECTS: initilizes the automata that is going to run in the simulation
    public GridRenderer(QuantumCellularAutomaton automata) {
        this.automata = automata;
    }


    //EFFECTS: prints out the grid in terms of its 1, ? or 0 value given the probability state of the qubit
    public void printGrid() {
        Qubit[][] grid = automata.getGrid();
        int size = automata.getSize();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sb.append(grid[i][j].toAscii()).append(" ");
            }
            sb.append("\n");
        }

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println(sb);
    }

    //EFFECTS: prints out the grid but with the assigned color values based on probability
    public void printGridColor() {
        Qubit[][] grid = automata.getGrid();
        int size = automata.getSize();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sb.append(grid[i][j].getColor());
            }
            sb.append("\n");
        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println(sb);
    }
}
