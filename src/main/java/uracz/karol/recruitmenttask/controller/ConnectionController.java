package uracz.karol.recruitmenttask.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uracz.karol.recruitmenttask.service.GitHubApiService;

@RestController
public class ConnectionController {

    private GitHubApiService gitHubApiService;

    public ConnectionController(GitHubApiService gitHubApiService) {
        this.gitHubApiService = gitHubApiService;
    }

    @GetMapping("/repositories/{owner}/{repository-name}")
    public Object getDataFromUrl(@PathVariable String owner, @PathVariable("repository-name") String repositoryName) {
        String dataFromGitHubApi = gitHubApiService.getDataFromGitHubApi(owner, repositoryName);
        return /*gitHubApiService.getDetailsDataFromUrl(dataFromGitHubApi);*/ dataFromGitHubApi;
    }
}
