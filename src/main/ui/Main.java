package ui;

import model.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import persistence.*;

public class Main {
    private static int size;
    private static QuantumCellularAutomaton automata;  // Shared reference
    private static GridRenderer renderer;
    private static JsonReader jsonReader;
    private static JsonWriter jsonWriter;
    private static final String JSON_STORE = "src/data/quantumcellularautomaton.json";
    private static volatile boolean run = true;  // Shared flag, volatile for thread visibility

    public static void main(String[] args) {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Would you like to load a previous simulation or start a new simulation?");
        System.out.println("A. Start new simulation");
        System.out.println("B. Load previous simulation");
        String option = scanner.nextLine();

        if (option.equalsIgnoreCase("b")) {
            loadQuantumCellularAutomaton();
        } else {
            System.out.println("What size simulation do you want?");
            size = scanner.nextInt();
            scanner.nextLine();  // Consume the newline
            automata = new QuantumCellularAutomaton(size);  // Initialize here for new simulation
        }

        // Start simulation thread
        Thread simulationThread = new Thread(() -> runSimulation());
        simulationThread.start();

        // Start input thread (main thread can handle this)
        handleUserInput(scanner);
        
        // Wait for simulation thread to finish (optional)
        try {
            simulationThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void runSimulation() {
        renderer = new GridRenderer(automata);
        while (run) {
            automata.paintGrid();
            renderer.printGrid();
            automata.updateGrid();
            try {
                Thread.sleep(5000);  // Reduced to 500ms for better responsiveness
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("Simulation terminated");
    }

    public static void handleUserInput(Scanner scanner) {
        while (run) {  // Changed to use the shared run flag
            String command = scanner.nextLine().trim().toLowerCase();
            switch (command) {
                case "apply hadamard":
                    applyGate(automata, Gate.HADAMARD);
                    System.out.println("Hadamard gate applied!");
                    break;
                case "apply bitflip":
                    applyGate(automata, Gate.BITFLIP);
                    System.out.println("Bitflip gate applied!");
                    break;
                case "apply phaseflip":
                    applyGate(automata, Gate.PHASEFLIP);
                    System.out.println("Phaseflip gate applied!");
                    break;
                case "exit":
                    System.out.println("Exiting simulation...");
                    System.out.println("Would you like to save the current Automaton? (Y/N)");
                    String saveChoice = scanner.nextLine();
                    if (saveChoice.equalsIgnoreCase("Y")) {
                        saveQuantumCellularAutomata();
                    }
                    run = false;  // This will stop the simulation thread
                    System.out.println("Have a good day!");
                    return;  // Exit the input handling
                default:
                    System.out.println("Unknown command");
            }
        }
    }

    public static void applyGate(QuantumCellularAutomaton automata, Gate gate) {
        Qubit[][] grid = automata.getGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j].applyGate(gate);
            }
        }
    }

    private static void loadQuantumCellularAutomaton() {
        try {
            automata = jsonReader.read();
            System.out.println("Loaded " + automata.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    private static void saveQuantumCellularAutomata() {
        try {
            jsonWriter.open();
            jsonWriter.write(automata);
            jsonWriter.close();
            System.out.println("Saved " + automata.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }
}