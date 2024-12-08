package apirest;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RestApiTutorial {

    //La idea es mejorar esto para que haya un menú y puedas subir tus archivosnde audios locales y transcribirlos en texto






    public String transcript(String audio) {
        Transcript transcript = new Transcript();
        transcript.setAudio_url(audio);
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(transcript);

        try {
            HttpRequest postRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://api.assemblyai.com/v2/transcript"))
                    .header("Authorization", "aff4300a5ec44316935eecf2bde633b6")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(postResponse.body());
            transcript = gson.fromJson(postResponse.body(), Transcript.class);
            System.out.println(transcript.getId());

            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://api.assemblyai.com/v2/transcript/" + transcript.getId()))
                    .header("Authorization", "aff4300a5ec44316935eecf2bde633b6")
                    .GET()
                    .build();

            boolean acabado = false;
            while (!acabado){
                HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
                transcript = gson.fromJson(getResponse.body(), Transcript.class);
                if (transcript.getStatus().equals("completed")){

                    acabado = true;
                    System.out.println(getResponse.body());
                }
                System.out.println(transcript.getStatus());

            }

            System.out.println("Leido y completado");




        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return transcript.getText();
    }
}
