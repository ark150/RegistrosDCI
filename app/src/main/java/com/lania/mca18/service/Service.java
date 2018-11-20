package com.lania.mca18.service;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.lania.mca18.model.Computadora;
import com.lania.mca18.model.Equipo;
import com.lania.mca18.model.Item;
import com.lania.mca18.model.Persona;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class Service {
    public static boolean isReqEmpty(String response)
    {
        try
        {
            JSONArray json = new JSONArray(response);
            if(json.length() > 0)
                return true;
        }
        catch (Exception e)
        {
            return false;
        }
        return false;
    }

    @Nullable
    public static Item getJsonObject(JSONObject ob, String type)
    {
        Item item = null;
        try {
            item = new Item(type);
            item.setId(Long.parseLong(ob.getString("id")));

            switch (type) {
                case "computadora":
                    Computadora pc = new Computadora(item);

                    pc.setModelo(ob.getString("modelo"));
                    pc.setColor(ob.getString("color"));
                    // pc.setPropietario(new Persona(Long.parseLong(ob.getString("propietario"))));

                    item = pc;
                    break;

                case "equipo":
                    Equipo equipo = new Equipo(item);

                    equipo.setNombre(ob.getString("nombre"));

                    item = equipo;
                    break;

                case "persona":
                    Persona persona = new Persona(item);
                    persona.setNombre(ob.getString("nombre"));
                    persona.setInstitucionDeOrigen(ob.getString("institucionDeOrigen"));
                    persona.setFacebook(ob.getString("facebook"));
                    persona.setCorreo(ob.getString("correo"));
                    // ((Persona) item).setUuid(ob.getString("uuid"));
                    // ((Persona) item).setHash(ob.getString("hash"));
                    // recursión
                    Equipo _equipo = null;
                    try {
                        _equipo = (Equipo)getJsonObject(new JSONObject(ob.getString("equipo")), "equipo");
                    } catch (Exception ex)
                    {
                    }
                    persona.setEquipo(_equipo);

                    Computadora _pc = null;
                    try {
                        _pc = (Computadora) getJsonObject(new JSONObject(ob.getString("computadora")), "computadora");
                    } catch (Exception ex)
                    {
                    }
                    persona.setComputadora(_pc);

                    item = persona;
                    break;

                default:
                    break;
            }
        } catch (Exception ex)
        {

        }
        return item;
    }

    /**
     * Convierte una cadena JSON a una lista de la clase Item.
     * @param strJson Objeto JSON que especifica el tipo de dato y la lista de valores
     * @return Lista de elementos del tipo Item.
     */
    public static List<Item> getItemsList(JSONObject strJson)
    {
        List<Item> items = new ArrayList<>();
        JSONArray json = null;
        JSONObject jsonObject = null;
        String type = "";
        try
        {
            try {
                // json = new JSONArray(strJson);
                //json = new JSONArray(strJson);
                type = strJson.getString("type");
                json = strJson.getJSONArray("object");
            }catch (Exception ex) {
                Log.e("Fallo JSON", "No se pudo obtener el arreglo JSON");
            }

            if(!(json == null))
            {
                Item item;
                for(int i = 0; i < json.length(); i++)
                {
                    JSONObject ob = json.getJSONObject(i);
                    item = getJsonObject(ob, type);
                    items.add(item);
                }
            }
        }
        catch (Exception ex)
        {
            Log.d("Error al deserializar", "Algo salio mal :S -- ");
            Log.e("Deserializar", ex.getMessage().toString());
        }
        return items;
    }

    public static Item getItem(String strJson, String type) {
        Item item = null;
        try
        {
            JSONObject jsonob = new JSONObject(strJson);;

            try {

                if (type == "Computadora")
                {
                    item = new Computadora(Long.parseLong(jsonob.getString("id")));
                    ((Computadora) item).setModelo(jsonob.getString("modelo"));
                    ((Computadora) item).setColor(jsonob.getString("color"));
                }
                else if (type == "Equipo")
                {
                    item = new Equipo(Long.parseLong(jsonob.getString("id")));
                    ((Equipo) item).setNombre(jsonob.getString("nombre"));
                }
                else if (type == "Persona")
                {
                    item = new Persona(Long.parseLong(jsonob.getString("id")));
                    ((Persona) item).setNombre(jsonob.getString("nombre"));

                    // Continuar
                }

            } catch (Exception ex) {
                Log.e("Fallo JSON", "No se pudo convertir a cadena a objeto JSON");
            }
        } catch (Exception ex)
        {
            Log.d("Error al deserializar", "Algo salio mal :S -- ");
            Log.e("Deserializar", ex.getMessage().toString());
        }

        return item;
    }

    @Nullable
    public static List<Item> getData(Context context, String id, Item item)
    {
        List<Item> items = new ArrayList<>();
        Item it;
        if (id == null)
            id = "null";

        String json = "";

        try
        {
            DataAsync da = new DataAsync();
            da.execute(item);
            json = da.get(4, TimeUnit.SECONDS);
            if(da.getStatus() != AsyncTask.Status.FINISHED)
                da.cancel(true);
            if(json.length() > 0)
                Log.d("Estado", "Exito :D");
            else
                Log.d("Estado", "No hay datos en la cadena JSON");
            Log.d("JSON ", json);
        }
        catch (TimeoutException tex)
        {
            Log.e("TimeoutException", "Tiempo excedido al conectar");
        }
        catch (CancellationException cex)
        {
            Log.e("CancellationException", "Error al conectar con el servidor");
        }
        catch (Exception ex)
        {
            Log.e("Exception", "Error con tarea asíncrona");
            Log.e("Error tarea", ex.getMessage());
        }
        finally
        {
            try
            {
                if (json.trim() != "") {
                    //items = getItemsList(json);
                    //it = getItem(json, "Computadora");
                    //Log.d("Test Item", String.valueOf(it));
                    items = getItemsList(new JSONObject(json));
                }
                else
                {
                    Log.d("Datos JSON", "No se encontraron datos");
                    return null;
                }
            } catch (Exception ex)
            {
                Log.e("JSON Exception", "Error al leer JSON/Agregar objetos a la lista de elementos");
            }
            Log.d("Long lista de elementos", String.valueOf(items.size()));

            return items;
        }
    }
}
