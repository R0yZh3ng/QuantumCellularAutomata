package model;


// this class will contain all the predetermined gates(operations/static matrices
// that will be operated with the Qubits) in a 2 x 2 format.
// this will also provide a method that multiplies the constant matrix by the current state of the Qubit

public class Gate {
    private ComplexNumber[][] data;

    //REQUIRES: the Gate that is provided must be 2 x 2, meaning that there must be two basis vectors each of size 2;
    public Gate(ComplexNumber[][] data) {
        this.data = data;
    }

    //MODIFIES: this
    //EFFECTS: multiples the vectors of the Qubit by the Gate/gate provided
    public ComplexNumber multiplyMatrixRow(int row, ComplexNumber alpha, ComplexNumber beta) {
        return data[row][0].multiply(alpha).add(data[row][1].multiply(beta));
    }

    // hadamard gate introduces a superposition state for the Qubit \
    //(when there is a equal probability of both the real and imaginary number)
    public static final Gate HADAMARD = new Gate(new ComplexNumber[][]{
        {new ComplexNumber(1 / Math.sqrt(2), 0), new ComplexNumber(1 / Math.sqrt(2), 0)},
        {new ComplexNumber(1 / Math.sqrt(2), 0), new ComplexNumber(-1 / Math.sqrt(2), 0)}
    });

    // classical not gate, changes 0 to 1 and changes 1 to 0
    public static final Gate BITFLIP = new Gate(new ComplexNumber[][]{
        {new ComplexNumber(0, 0), new ComplexNumber(1, 0)},
        {new ComplexNumber(1, 0), new ComplexNumber(0, 0)}
    });

    //introduces a phase shift of the imaginary
    public static final Gate PHASEFLIP = new Gate(new ComplexNumber[][]{
        {new ComplexNumber(1, 0), new ComplexNumber(0, 0)},
        {new ComplexNumber(0, 0), new ComplexNumber(-1, 0)}
    });

}
