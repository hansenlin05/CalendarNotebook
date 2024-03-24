package persistence;

import org.json.JSONObject;

//Represent an interface relate to Json Reader and Writer
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
