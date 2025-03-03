package model;

import javax.swing.*;
import java.awt.*;
import org.json.JSONObject;
import persistence.*;
import org.json.JSONArray;


//This class represents the grid that all the Qubit will be displayed on, 
public class QuantumCellularAutomaton implements Serializer {
    private Qubit[][] grid;
    private int size;
    private String name;

    public QuantumCellularAutomaton(int size) {
        this.size = size;
        this.grid = new Qubit[size][size];
        this.name = "your simulation";

        for (int i = 0; i < size; i++) { 
            for (int j = 0; j < size; j++) { 
                grid[i][j] = new Qubit(); //all qubits start with the |0> state
            }
        }
    }

    public Qubit[][] getGrid() {
        return grid;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //MODIFIES: this, Qubit
    //EFFECTS: updates all the Qubits in the grid (apply entaglement on to neighbours)
    public void updateGrid() {
        for (int i = 0; i < size; i++) { 
            for (int j = 0; j < size; j++) { 
                Qubit current = grid[i][j];
                
                if (i > 0) {
                    current.entangle(grid[i - 1][j]);
                }
                if (j > 0) {
                    current.entangle(grid[i][j - 1]);
                }
                if (i < size - 1) {
                    current.entangle(grid[i + 1][j]);
                }
                if (j < size - 1) {
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

    //MODIFIES: this
    //EFFECTS: applies gate operation on each of the Qubits in the grid
    public static void applyGate(QuantumCellularAutomaton automata, Gate gate) {
        Qubit[][] grid = automata.getGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j].applyGate(gate);
            }
        }
    }

    @Override

    //EFFECTS: serialize for the QCA class
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", getName());
        json.put("size", getSize());
        json.put("Automata", gridArrayToJson());
        return json;
    }

    //EFFECTS: turns the 2d array into json
    private JSONArray gridArrayToJson() {

        JSONArray gridArray = new JSONArray();

        for (int i = 0; i < grid.length; i++) {
            JSONArray rowArray = new JSONArray();

            for (int j = 0; j < grid[i].length; j++) {
                rowArray.put(grid[i][j].toJson());
            }
            gridArray.put(rowArray);
        }
        return gridArray;
    }
    
}
