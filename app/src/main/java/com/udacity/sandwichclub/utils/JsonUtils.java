package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    public static String mainName;
    public static String imageURL;
    public static String placeOforigin;
    public static List<String> alsoKnownAs_list;
    public static String description;
    public static List<String> ingradients_list;

    public static Sandwich parseSandwichJson(String json) {
        alsoKnownAs_list = new ArrayList<>();
        ingradients_list = new ArrayList<>();
        try {
            JSONObject sandwichObject = new JSONObject(json);
            JSONObject sandwichName = sandwichObject.getJSONObject("name");
            mainName = sandwichName.getString("mainName");

            JSONArray jsonArray = sandwichName.getJSONArray("alsoKnownAs");
            for (int i = 0; i < jsonArray.length(); i++) {
                String knownAs = jsonArray.getString(i);
                alsoKnownAs_list.add(knownAs);
            }

            jsonArray = sandwichObject.getJSONArray("ingredients");
            for (int i = 0; i < jsonArray.length(); i++) {
                String ingradientsName = jsonArray.getString(i);
                ingradients_list.add(ingradientsName);
            }


            imageURL = sandwichObject.getString("image");
            placeOforigin = sandwichObject.getString("placeOfOrigin");
            description = sandwichObject.getString("description");


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return new Sandwich(mainName, alsoKnownAs_list, placeOforigin, description, imageURL, ingradients_list);
    }

}
