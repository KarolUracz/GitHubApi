package uracz.karol.githubapi.controller;

import org.apache.http.HttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uracz.karol.githubapi.model.RepositoryDetails;
import uracz.karol.githubapi.service.GitHubApiService;

import java.io.IOException;


@RestController
public class ConnectionController {

    private GitHubApiService gitHubApiService;

    public ConnectionController(GitHubApiService gitHubApiService) {
        this.gitHubApiService = gitHubApiService;
    }

    @GetMapping("/repositories/{owner}/{repository-name}")
    public RepositoryDetails getDataFromUrl(@PathVariable String owner, @PathVariable("repository-name") String repositoryName) throws IOException {
        HttpResponse dataFromGitHubApi = gitHubApiService.getDataFromGitHubApi(owner, repositoryName);
        RepositoryDetails dataFromUrl = gitHubApiService.getDetailsDataFromUrl(dataFromGitHubApi);
        return dataFromUrl;
    }
}
