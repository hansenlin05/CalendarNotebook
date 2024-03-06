package persistence;

import org.json.JSONObject;

//Represent a interface relate to Json Reader and Writer
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
