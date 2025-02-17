package ui;

import model.*;

import java.util.Scanner;

public class Main {
    public static int size;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Boolean run = true;

        System.out.println("what size simulation do you want?");
        size = scanner.nextInt();

        Thread simulationThread = new Thread(() -> runSimulation(run));
        simulationThread.start();

        //Thread simulationThreadColor = new Thread(() -> runSimulationColor(run));
        //simulationThreadColor.start();

        Thread inputThread = new Thread(() -> handleUserInput(scanner));
        inputThread.start();
    }


    public static void runSimulation(Boolean run) {
        QuantumCellularAutomaton automata = new QuantumCellularAutomaton(size);
        GridRenderer renderer = new GridRenderer(automata);

        while (run) {
            automata.updateGrid();
            renderer.printGrid();
            try {
                Thread.sleep(100);    // Adjust speed of simulation
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
                System.out.println("Exiting simulation...");
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
}
