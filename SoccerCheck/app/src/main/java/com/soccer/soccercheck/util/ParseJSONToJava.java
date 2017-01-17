package com.soccer.soccercheck.util;

import android.graphics.Bitmap;
import android.util.Log;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soccer.soccercheck.model.FixturesData;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ederson.js on 10/10/2016.
 */

public class ParseJSONToJava {
    private static final String TAG = "ParseJSONToJava";

    private ObjectMapper mapper;

    public ParseJSONToJava(){
    }

    /**
     *
     * This method parses from JSON format to Java Objects with the Jackson API
     *
     * @param json
     * @return
     * @Original_Base WerbSystique - http://websystique.com/java/json/jackson-convert-java-object-to-from-json/
     */
    public Object convertToJava(String json){
        Log.i(TAG, "ParseJSONToJava.convertToJava() inside method - param value: " + json);
        mapper = new ObjectMapper();
        FixturesData fixturesData = null;

        try {
            Log.i(TAG, "ParseJSONToJava.convertToJava() inside try/catch block");

            // Fill the Car Object list from JSON
            fixturesData = mapper.readValue(json, FixturesData.class);
/**
            for (int i = 0; i < listCarObject.size(); i++){
                Bitmap imageCar = queryRequest.getImageWithQuery(listCarObject.get(i).getUrlPhoto());
                listCarObject.get(i).setCarImageByte(imageCar);
            }
**/

        } catch (JsonGenerationException ex) {
            Log.i(TAG, "ParseJSONToJava.convertToJava() inside catch block: Exception - JsonGenerationException - message: " + ex.getMessage());
            ex.printStackTrace();

        } catch (JsonMappingException ex) {
            Log.i(TAG, "ParseJSONToJava.convertToJava() inside catch block: Exception - JsonMappingException - message: " + ex.getMessage());
            ex.printStackTrace();

        } catch (IOException ex) {
            Log.i(TAG, "ParseJSONToJava.convertToJava() inside catch block: Exception - IOException - message: " + ex.getMessage());
            ex.printStackTrace();

        }

        return fixturesData;
    }
}
