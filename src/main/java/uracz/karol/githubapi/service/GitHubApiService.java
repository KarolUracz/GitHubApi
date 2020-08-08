package uracz.karol.githubapi.service;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import uracz.karol.githubapi.model.RepositoryDetails;

import java.io.IOException;



@Service
public class GitHubApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GitHubApiService.class);

    public HttpResponse getDataFromGitHubApi (String owner, String repositoryName) {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("https://api.github.com/repos/" + owner + "/" + repositoryName);
        request.setHeader("Content-Type", "application/vnd.github.nebula-preview+json");
        try {
            org.apache.http.HttpResponse response = httpClient.execute(request);
            return response;
        } catch (IOException e) {
            LOGGER.error("Error getting response", e);
            return null;
        }
    }

    public RepositoryDetails getDetailsDataFromUrl(HttpResponse response) {
        String result = null;
        try {
            result = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            LOGGER.error("Error getting entity", e);
        }
        RepositoryDetails details =  new Gson().fromJson(result, RepositoryDetails.class);
        return details;
    }
}
