package com.lania.mca18.service;

import android.os.AsyncTask;
import android.util.Log;

import com.lania.mca18.model.Equipo;
import com.lania.mca18.model.Item;
import com.lania.mca18.model.Computadora;
import com.lania.mca18.model.Persona;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DataAsync extends AsyncTask<Item, Void, String>
{
    @Override
    protected String doInBackground(Item... params)
    {
        Item item = params[0];
        String line = "";
        int response = 0;
        StringBuilder result = null;
        String type = "";

        try
        {
            // IP fija
            // String strUrl = "http://192.168.0.9:8080/";

            // Localhost
            String strUrl = "http://10.0.2.2:8080/";

            String id = "";
            if(!(item.getId() == 0))
                id = String.valueOf(item.getId());

            String action = item.getAction();
            if(!(item.getAction() == ""))
                action +=  "/";

                action += id;
            type = "";

            if (item instanceof Computadora)
                type += "computadora";
            else if (item instanceof Equipo)
                type += "equipo";
            else if (item instanceof Persona)
                type += "persona";

            strUrl += type + "/" + action;

            URL url = new URL(strUrl);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("Content-Type", "application/json");
            DataOutputStream dStream;

            if(item.getAction() == Item.CREATE)
            {
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                dStream = new DataOutputStream(connection.getOutputStream());

                dStream.writeBytes(item.toJSON()); //Writes out the string to the underlying output stream as a sequence of bytes
                dStream.flush(); // Flushes the data output stream.
                dStream.close(); // Closing the output stream.
            }
            else
                connection.setRequestMethod("GET");

            connection.connect();

            result = new StringBuilder();
            try
            {
                response = connection.getResponseCode();
            }
            catch (Exception e)
            {
                Log.e("Conexion", e.getMessage());
                return null;
            }

            if(response == HttpURLConnection.HTTP_OK)
            {
                InputStream in = new BufferedInputStream(connection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                while((line = reader.readLine()) != null)
                {
                    result.append(line);
                }
            }
        }
        catch (Exception ex)
        {
            Log.d("D", ex.getMessage().toString());
            return null;
        }

        //String jsonResult = "{'type' : '" + type +
                //"', 'object' : " + result.toString() +"}";

        JSONObject jsonResult = new JSONObject();

        try {
            jsonResult.put("type", type);
            jsonResult.put("object", new JSONArray(result.toString()));
        } catch (JSONException e) {
            e.printStackTrace();
            try {
                jsonResult.put("object", new JSONArray("[" + result.toString() + "]"));
            } catch (JSONException e1) {
                e1.printStackTrace();
                return null;
            }
        }
        return jsonResult.toString();
    }
}