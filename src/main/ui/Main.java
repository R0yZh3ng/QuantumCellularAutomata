package ui;

import model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import persistence.*;

public class Main {
    private static int size;
    private static QuantumCellularAutomaton automata;
    private static GridRenderer renderer;
    private static JsonReader jsonReader;
    private static JsonWriter jsonWriter;
    private static final String JSON_STORE = "src/data/quantumcellularautomaton.json";
    
    public static void main(String[] args) {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        Scanner scanner = new Scanner(System.in);
        Boolean run = true;

        System.out.println("would you like to load a previous simualtion or start a new simulation?");
        System.out.println("A. start new simulation");
        System.out.println("B. load previous simulation");
        String option = scanner.nextLine();

        if(option.equalsIgnoreCase("b")) {
            loadQuantumCellularAutomaton();
            Thread simulationThread = new Thread(() -> runSimulation(run, true));
            simulationThread.start();
            //Thread simulationThreadColor = new Thread(() -> runSimulationColor(run));
            //simulationThreadColor.start();
            Thread inputThread = new Thread(() -> handleUserInput(scanner));
            inputThread.start();

        } else {
            System.out.println("what size simulation do you want?");
            size = scanner.nextInt();

            Thread simulationThread = new Thread(() -> runSimulation(run, false));
            simulationThread.start();

            //Thread simulationThreadColor = new Thread(() -> runSimulationColor(run));
            //simulationThreadColor.start();

            Thread inputThread = new Thread(() -> handleUserInput(scanner));
            inputThread.start();
        }
    }


    public static void runSimulation(Boolean run, Boolean loaded) {
        if(!loaded){
            automata = new QuantumCellularAutomaton(size);
        }
        renderer = new GridRenderer(automata);

        while (run) {
            automata.updateGrid();
            renderer.printGrid();
            try {
                Thread.sleep(5000);    // Adjust speed of simulation
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


//    public static void runSimulationColor(Boolean run) {
//      QuantumCellularAutomaton automata = new QuantumCellularAutomaton(10);
//       GridRenderer renderer = new GridRenderer(automata);
//       while (run) {
//            automata.updateGrid();
//            renderer.printGridColor();
//            try {
//                Thread.sleep(5000);    // Adjust speed of simulation
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public static void handleUserInput(Scanner scanner) {
        QuantumCellularAutomaton automata = new QuantumCellularAutomaton(10);

        while (true) {
            String command = scanner.nextLine().trim().toLowerCase();
            if (command.equals("apply hadamard")) {
                applyGate(automata, Gate.HADAMARD);
                System.out.println("Hadamard gate applied!");
            } else if (command.equals("apply bitflip")) {
                applyGate(automata, Gate.BITFLIP);
                System.out.println("Bitflip gate applied!");
            } else if (command.equals("apply phaseflip")) {
                applyGate(automata, Gate.PHASEFLIP);
                System.out.println("Phaseflip gate applied!");
            } else if (command.equals("exit")) {
                Scanner save = new Scanner(System.in);
                System.out.println("Exiting simulation...");
                System.out.println("Would you like to save the current Automaton? (Y/N)");
                
                String saved = save.nextLine();

                if(saved.equalsIgnoreCase("Y")) {
                    saveQuantumCellularAutomata();
                } else {
                    System.out.println("Have a good day!");
                }

                break;
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
            System.out.println("Loaded " + automata.getName() + "from " + JSON_STORE);
        } catch(IOException e) {
            System.out.println("umable to read from files: " + JSON_STORE);
        }
    }

    // EFFECTS: saves the workroom to file
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
