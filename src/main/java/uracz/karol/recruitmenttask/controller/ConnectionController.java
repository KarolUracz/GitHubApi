package uracz.karol.recruitmenttask.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ConnectionController {

    @GetMapping("/repositories/{owner}/{repository-name}")
    public String getDataFromUrl(@PathVariable String owner, @PathVariable("repository-name") String repositoryName){
        try {
            HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("https://api.github.com/repos/" + owner + "/" + repositoryName))
                    .setHeader("Content-Type", "application/vnd.github.nebula-preview+json")
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return  response.body();
        }  catch (InterruptedException e) {
            e.printStackTrace();
            return "Exception";
        } catch (IOException e) {
            e.printStackTrace();
            return "Exception";
        }
    }
}
