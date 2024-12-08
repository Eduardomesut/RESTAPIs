package apiPokemon.controlador;

import apiPokemon.pojo.Pokemon;
import apirest.Transcript;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class APIConfig {

    public Pokemon obtenerInfoPorNombre(String nombre) throws URISyntaxException, IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://pokeapi.co/api/v2/pokemon/" + nombre.toLowerCase()))
                .GET()
                .build();

        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        System.out.println(getResponse.body());
        if (getResponse.body().equals("Not Found")){
            return null;
        }
        // Parsear la respuesta JSON
        JsonObject jsonResponse = JsonParser.parseString(getResponse.body()).getAsJsonObject();

        // Crear objeto Pokemon con la información relevante
        Pokemon pokemon = new Pokemon();
        pokemon.setName(jsonResponse.get("name").getAsString());
        pokemon.setBase_experience(jsonResponse.get("base_experience").getAsInt());

        // Extraer habilidades
        List<String> abilities = new ArrayList<>();
        jsonResponse.getAsJsonArray("abilities").forEach(ability -> {
            JsonObject abilityObj = ability.getAsJsonObject().getAsJsonObject("ability");
            abilities.add(abilityObj.get("name").getAsString());
        });
        pokemon.setAbilities(abilities);

        // Extraer URL del sonido más reciente
        JsonObject cries = jsonResponse.getAsJsonObject("cries");
        pokemon.setCryUrl(cries.get("latest").getAsString());

        return pokemon;
    }
}
