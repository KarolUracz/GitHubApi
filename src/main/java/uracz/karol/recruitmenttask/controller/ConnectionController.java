package uracz.karol.recruitmenttask.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uracz.karol.recruitmenttask.service.GitHubApiService;

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

    private GitHubApiService gitHubApiService;

    public ConnectionController(GitHubApiService gitHubApiService) {
        this.gitHubApiService = gitHubApiService;
    }

    @GetMapping("/repositories/{owner}/{repository-name}")
    public String getDataFromUrl(@PathVariable String owner, @PathVariable("repository-name") String repositoryName) {
        Map<String, Object> dataFromGitHubApi = gitHubApiService.getDataFromGitHubApi(owner, repositoryName);
        return dataFromGitHubApi.toString();
    }
}
