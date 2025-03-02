package persistence;

import org.json.JSONObject;

public interface Writable {
    //EFFECTS: returns this as a Json object (models will implement this to ensure that they can be serialized to JSON)
    JSONObject toJson();
}
