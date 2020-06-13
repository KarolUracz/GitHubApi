package uracz.karol.recruitmenttask.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GitHubApiService {
    public HttpResponse<String> getDataFromGitHubApi (String owner, String repositoryName) {
        try {
            HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("https://api.github.com/repos/" + owner + "/" + repositoryName))
                    .setHeader("Content-Type", "application/vnd.github.nebula-preview+json")
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, Object> getDetailsDataFromUrl(HttpResponse<String> response) {
        Map<String, Object> dataFromResponse = null;
        try {
            dataFromResponse = new ObjectMapper().readValue(response.body(), HashMap.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Map<String, Object> detailsOfRepository = dataFromResponse.entrySet().stream()
                .filter(x -> x.getKey().equals("full_name") || x.getKey().equals("clone_url") || x.getKey().equals("stargazers_count")
                        || x.getKey().equals("created_at") || x.getKey().equals("description"))
                .collect(Collectors.toMap(x -> x.getKey(), x -> Optional.ofNullable(x.getValue()).orElse("")));
        return detailsOfRepository;
    }
}
