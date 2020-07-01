package uracz.karol.recruitmenttask.service;

import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import uracz.karol.recruitmenttask.model.RepositoryDetails;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class GitHubApiService {
    public String getDataFromGitHubApi (String owner, String repositoryName) {
        HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://api.github.com/repos/" + owner + "/" + repositoryName))
                .setHeader("Content-Type", "application/vnd.github.nebula-preview+json")
                .build();
        HttpResponse response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "Interrupted exception";
        } catch (IOException e) {
            e.printStackTrace();
            return "IO Exception";
        }
        return /*response.body().toString();*/ String.valueOf(response.statusCode());
    }

    public RepositoryDetails getDetailsDataFromUrl(String response) {
        JSONObject body = new JSONObject(response);
        RepositoryDetails details =  new Gson().fromJson(body.toString(), RepositoryDetails.class);
        return details;
    }
}
