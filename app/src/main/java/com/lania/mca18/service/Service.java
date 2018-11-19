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
    public static JSONObject getJsonObject(String strJson)
    {
        JSONObject object = null;
        try
        {
            object = new JSONObject(strJson);
        } catch(Exception ex)
        {
            return null;
        }

        return object;
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

            // Se necesita conocer qué tipo de objeto es
            // En base del tipo de objeto JSON, agregar objetos
            // a la lista que se regresará.

            if(!(json == null))
            {
                Item item;
                for(int i = 0; i < json.length(); i++)
                {
                    JSONObject ob = json.getJSONObject(i);
                    item = new Item(type);
                    item.setId(Long.parseLong(ob.getString("id")));

                    switch (type)
                    {
                        case "computadora":
                            // Computadora cannot be cast to item type... Fix it!

                            ((Computadora) item).setModelo(ob.getString("modelo"));
                            ((Computadora) item).setColor(ob.getString("color"));
                            //((Computadora) item).setModelo(ob.getString("propietario"));
                            break;

                        case "equipo":
                            ((Equipo) item).setNombre(ob.getString("nombre"));
                            //((Computadora) item).setModelo(ob.getString("propietario"));
                            break;

                        case "persona":
                            ((Persona) item).setNombre(ob.getString("nombre"));
                            ((Persona) item).setInstitucionDeOrigen(ob.getString("institucionDeOrigen"));
                            ((Persona) item).setFacebook(ob.getString("facebook"));
                            ((Persona) item).setCorreo(ob.getString("correo"));
                            // ((Persona) item).setUuid(ob.getString("uuid"));
                            // ((Persona) item).setHash(ob.getString("hash"));

                            //((Persona) item).setEquipo.
                            //private Computadora computadora;
                            break;

                        default:
                            break;
                    }

                    items.add(item);
                }
            }

            /*Item item;
            for (int i = 0; i < json.length(); i++)
            {
                item = new Item();
                JSONObject ob = json.getJSONObject(i);
                event.setId(ob.getString("ev_id"));
                event.setName(ob.getString("ev_name"));
                event.setDescription(ob.getString("ev_des"));
                String strDate = ob.getString("ev_sch");
                strDate = strDate.replace("-", "/");
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US);
                event.setDate(format.parse(strDate));

                try
                {
                    event.getType().setId(Integer.parseInt(ob.getString("tp_id")));
                    event.getType().setName(ob.getString("tp_name"));
                }
                catch (Exception ex)
                {
                    Log.w("Tipo de Ev", "Excepción al agregar el tipo de evento");
                }

                items.add(item);
            }*/
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

        //Item item;
        //ev.setId(id);
        //ev.getType().setId(type);
        String json = "";

        /*if (item instanceof Computadora)
            strUrl += "computadora/";
        else if (item instanceof Equipo)
            strUrl += "equipo/";
        else if (item instanceof Persona)
            strUrl += "persona/";*/

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
                    getItemsList(new JSONObject(json));
                }
                else
                {
                    Log.d("Datos JSON", "No se encontraron datos");
                    return null;
                }
            } catch (Exception ex)
            {
                Log.e("JSON Exception", "Error al leer JSON/Agregar objetos a la lista de eventos");
            }
            Log.d("Long lista de eventos", String.valueOf(items.size()));

            return items;
        }
    }
}
