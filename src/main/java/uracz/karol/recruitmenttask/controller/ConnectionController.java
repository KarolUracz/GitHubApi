package uracz.karol.recruitmenttask.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uracz.karol.recruitmenttask.service.GitHubApiService;

import java.net.http.HttpResponse;
import java.util.Map;


@RestController
public class ConnectionController {

    private GitHubApiService gitHubApiService;

    public ConnectionController(GitHubApiService gitHubApiService) {
        this.gitHubApiService = gitHubApiService;
    }

    @GetMapping("/repositories/{owner}/{repository-name}")
    public String getDataFromUrl(@PathVariable String owner, @PathVariable("repository-name") String repositoryName) {
        HttpResponse<String> dataFromGitHubApi = gitHubApiService.getDataFromGitHubApi(owner, repositoryName);
        Map<String, Object> detailsDataFromUrl = gitHubApiService.getDetailsDataFromUrl(dataFromGitHubApi);
        return detailsDataFromUrl.toString(); // You can prepare String response using data from map
    }
}
