package uracz.karol.githubapi.service;

import com.google.gson.Gson;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHttpResponse;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import uracz.karol.githubapi.model.RepositoryDetails;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


class GitHubApiServiceTest {

    @Test
    public void givenGetRequestExecuted_whenAnalyzingTheResponse_thenCorrectStatusCode()
            throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(new HttpGet("https://github.com/"));
        int statusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
    }

    @Test
    void getDetailsDataFromUrl() throws UnsupportedEncodingException {
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, 200, "TEST");

        RepositoryDetails repositoryDetails = new RepositoryDetails("karol", "none", "www.test.com", 4, "2020-05-27T18:11:41Z");
        response.setEntity(new StringEntity(new Gson().toJson(repositoryDetails)));
        GitHubApiService gitHubApiService = new GitHubApiService();
        RepositoryDetails detailsDataFromUrl = gitHubApiService.getDetailsDataFromUrl(response);
        Assert.assertEquals(repositoryDetails.getFullName(), detailsDataFromUrl.getFullName());
        Assert.assertEquals(repositoryDetails.getCloneUrl(), detailsDataFromUrl.getCloneUrl());
        Assert.assertEquals(repositoryDetails.getCreatedAt(), detailsDataFromUrl.getCreatedAt());
        Assert.assertEquals(repositoryDetails.getDescription(), detailsDataFromUrl.getDescription());
        Assert.assertEquals(repositoryDetails.getStars(), detailsDataFromUrl.getStars());
    }

    @Test
    public void givenUserDoesNotExists_whenUserInfoIsRetrieved_then404IsReceived()
            throws IOException {
        String name = RandomStringUtils.randomAlphabetic( 8 );
        HttpUriRequest request = new HttpGet( "https://api.github.com/users/" + name );

        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

        assertThat(
                httpResponse.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_NOT_FOUND));
    }
}