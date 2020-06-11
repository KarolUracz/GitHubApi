package uracz.karol.recruitmenttask.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ConnectionController {

    @GetMapping("/repositories/{owner}/{repository-name}")
    public String getDataFromUrl(@PathVariable String owner, @PathVariable("repository-name") String repositoryName) {
        try {
            HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("https://api.github.com/repos/" + owner + "/" + repositoryName))
                    .setHeader("Content-Type", "application/vnd.github.nebula-preview+json")
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            Map<String, Object> result = new ObjectMapper().readValue(response.body(), HashMap.class);
            Map<String, Object> collectedData = result.entrySet().stream()
                    .filter(x -> x.getKey().equals("full_name") || x.getKey().equals("clone_url") || x.getKey().equals("stargazers_count")
                            || x.getKey().equals("created_at") || x.getKey().equals("description"))
                    .collect(Collectors.toMap(x -> x.getKey(), x -> Optional.ofNullable(x.getValue()).orElse("")));
            return collectedData.toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "Exception";
        } catch (IOException e) {
            e.printStackTrace();
            return "Exception";
        }
    }
}
