package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;
import java.awt.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Automaton from file and returns it;
    // throws IOException if an error occurs reading data from file
    public QuantumCellularAutomaton read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseQuantumCellularAutomaton(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses Automaton from JSON object and returns it
    private QuantumCellularAutomaton parseQuantumCellularAutomaton(JSONObject jsonObject) {
        int size = jsonObject.getInt("size");
        QuantumCellularAutomaton qca = new QuantumCellularAutomaton(size);
        addQubits(qca, jsonObject);
        return qca;
    }

    // MODIFIES: qca
    // EFFECTS: parses thingies from JSON object and adds them to Automaton
    private void addQubits(QuantumCellularAutomaton qca, JSONObject jsonObject) {
        JSONArray gridArray = jsonObject.getJSONArray("Automata");
        for (int i = 0; i < gridArray.length(); i++) {
            JSONArray rowArray = gridArray.getJSONArray(i);
            for (int j = 0; j < rowArray.length(); j++) {
                JSONObject qubitJson = rowArray.getJSONObject(j);
                Qubit qubit = parseQubit(qubitJson);
                qca.getGrid()[i][j] = qubit;
            }
        }
    }


    // EFFECTS: parses Qubit from JSON object and returns it
    private Qubit parseQubit(JSONObject jsonObject) {
        ComplexNumber alpha = ComplexNumber.fromJson(jsonObject.getJSONObject("alpha"));
        ComplexNumber beta = ComplexNumber.fromJson(jsonObject.getJSONObject("beta"));
        Color color = new Color(jsonObject.getInt("red"), jsonObject.getInt("green"), jsonObject.getInt("blue"));
        Qubit qubit = new Qubit(alpha, beta, color);
        return qubit;
    }

}
