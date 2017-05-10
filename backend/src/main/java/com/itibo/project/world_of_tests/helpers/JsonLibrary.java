package com.itibo.project.world_of_tests.helpers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

/**
 * Created by Andrew on 10.05.2017.
 */
public class JsonLibrary {

    /**
     * Check if two JSONArray or JSONObject objects are equal to each other
     * (org.json) library using
     * @param ob1 first object
     * @param ob2 second object
     * @return boolean is it equal or not
     * @throws JSONException if something bad happens
     */
    public static boolean areEqual(Object ob1, Object ob2) throws JSONException {
        Object obj1Converted = convertJsonElement(ob1);
        Object obj2Converted = convertJsonElement(ob2);
        return obj1Converted.equals(obj2Converted);
    }

    /**
     * Convert from JSONObject or JSONArray to JAVA instance
     * @param elem which would be converted
     * @return converted into JAVA instance object
     * @throws JSONException if something bad happens
     */
    private static Object convertJsonElement(Object elem) throws JSONException {
        if (elem instanceof JSONObject) {
            JSONObject obj = (JSONObject) elem;
            Iterator<String> keys = obj.keys();
            Map<String, Object> jsonMap = new HashMap<>();
            while (keys.hasNext()) {
                String key = keys.next();
                jsonMap.put(key, convertJsonElement(obj.get(key)));
            }
            return jsonMap;
        } else if (elem instanceof JSONArray) {
            JSONArray arr = (JSONArray) elem;
            Set<Object> jsonSet = new HashSet<>();
            for (int i = 0; i < arr.length(); i++) {
                jsonSet.add(convertJsonElement(arr.get(i)));
            }
            return jsonSet;
        } else {
            return elem;
        }
    }
}
