import Game.Logros;
import Game.LogrosPorPuntos;
import Game.LogrosPorRacha;
import Game.LogrosRachaCatgoria;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * LogrosTypeAdapter es la clase encargada de resolver el problema de la deserializacion (¡No podemos crear objetos de logros abstractos!).
 * @author Villegas Joaquin
 */
public class LogrosTypeAdapter implements JsonDeserializer<Logros> {

    /**
     * Metodo deserializar, la encargada de al recibir un argumento de tipo logro, le busca el tipo exacto que es.
     * @param json el objeto json que se va a deserializar
     * @param typeOfT el tipo de dato (o clase) que se quiere retonar al final
     * @param context la encargada de la deserializacion de los JsonElement
     * @return un objeto del tipo Logro
     * @throws JsonParseException
     */
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
