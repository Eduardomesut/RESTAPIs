package apiPokemon.actividad;

import apiPokemon.controlador.APIConfig;
import apiPokemon.pojo.Pokemon;
import apirest.RestApiTutorial;

import java.io.IOException;
import java.net.URISyntaxException;

public class MAIN {

    public static void main(String[] args) {
        APIConfig apiConfig = new APIConfig();
        RestApiTutorial restApiTutorial = new RestApiTutorial();


        try {
            Pokemon busqueda = apiConfig.obtenerInfoPorNombre("pikachu");
            if (busqueda == null){
                System.out.println("No hay pokemón con este nombre!");
            }else {
                String transcripcion = restApiTutorial.transcript(busqueda.getCryUrl());
                System.out.println("Información del Pokémon: " + busqueda);
                System.out.println("Pista de audio transcrita en tiempo real: " + transcripcion);

            }

        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
