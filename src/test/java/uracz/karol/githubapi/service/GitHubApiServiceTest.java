package uracz.karol.githubapi.service;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHttpResponse;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import uracz.karol.githubapi.model.RepositoryDetails;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


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
        response.setEntity(new StringEntity("{\"fullName\":\"KarolUracz/GymProject\",\"description\":\"Ending project that allows to manage a gym.\"" +
                ",\"cloneUrl\":\"https://github.com/KarolUracz/GymProject.git\",\"stars\":0,\"createdAt\":\"2020-05-27T18:11:41Z\"}"));
        RepositoryDetails repositoryDetails = new RepositoryDetails();

    }
}