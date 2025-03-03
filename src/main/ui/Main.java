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
    
    //EFFECTS: runs the app, initializes the simulation and all the options
    public static void main(String[] args) {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        Scanner scanner = new Scanner(System.in);
        Boolean run = true;

        System.out.println("would you like to load a previous simualtion or start a new simulation?");
        System.out.println("A. start new simulation");
        System.out.println("B. load previous simulation");
        String option = scanner.nextLine();

        if (option.equalsIgnoreCase("b")) {
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

    //EFFECTS: runs the simulation, either loads or starts off with a new simulation
    public static void runSimulation(Boolean run, Boolean loaded) {
        if (!loaded) {
            automata = new QuantumCellularAutomaton(size);
        }
        renderer = new GridRenderer(automata);

        while (run) {
            automata.updateGrid();
            automata.paintGrid();
            renderer.printGrid();
            try {
                Thread.sleep(5000);    // Adjust speed of simulation
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

//    EFFECTS: runs the simulation but displays the colors instead of the 0,1,?
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

    //EFFECTS: takes in the user input and performs a action of apply gates, exiting or saving the automata.
    @SuppressWarnings("methodlength")
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
                save.close();

                if (saved.equalsIgnoreCase("Y")) {
                    saveQuantumCellularAutomata(); 
                    break;
                } else {
                    System.out.println("Have a good day!");
                    break;
                }
            }
        }
    }

    //EFFECTS: Applies the specific gate given to the current automata (this should be moved to the QCA class soon)
    public static void applyGate(QuantumCellularAutomaton automata, Gate gate) {
        Qubit[][] grid = automata.getGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j].applyGate(gate);
            }
        }
    }

    //EFFECTS: loads the simulation from the last save
    private static void loadQuantumCellularAutomaton() {
        try {
            automata = jsonReader.read();
            System.out.println("Loaded " + automata.getName() + "from " + JSON_STORE);
        } catch (IOException e) {
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
