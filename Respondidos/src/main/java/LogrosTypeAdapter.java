import Game.Logros;
import Game.LogrosPorPuntos;
import Game.LogrosPorRacha;
import Game.LogrosRachaCatgoria;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LogrosTypeAdapter implements JsonSerializer<ArrayList<Logros>>, JsonDeserializer<Logros> {
    public JsonElement serialize1(Logros logro, Type typeOfSrc, JsonSerializationContext context) {
        System.out.println("estoy serializando carajo");
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", logro.getClass().getSimpleName());
        jsonObject.add("properties", context.serialize(logro));
        return jsonObject;
    }
    @Override
    public JsonElement serialize(ArrayList<Logros> logrosList, Type typeOfSrc, JsonSerializationContext context) {
        JsonArray jsonArray = new JsonArray();
        for (Logros logro : logrosList) {
            jsonArray.add(serialize1(logro, logro.getClass(), context));
        }
        return jsonArray;
    }

    @Override
    public Logros deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("tipo").getAsString();
        //JsonElement properties = jsonObject.get("properties");

        try {
            switch (type) {
                case "LogrosPorRacha":
                    return context.deserialize(jsonObject, LogrosPorRacha.class);
                case "LogrosRachaCatgoria":
                    return context.deserialize(jsonObject, LogrosRachaCatgoria.class);
                case "LogrosPorPuntos":
                    return context.deserialize(jsonObject, LogrosPorPuntos.class);
                // Añadir otros tipos específicos de Logros aquí
                default:
                    throw new JsonParseException("Unknown type: " + type);
            }
        } catch (Exception e) {
            throw new JsonParseException("Failed to deserialize Logros", e);
        }
    }
}
