package com.hiringdefined;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ResourceApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class ApplicationTests {

    @Value("${local.server.port}")
    private int port;

    private final RestTemplate template = new TestRestTemplate();

    @Test
    public void resourceProtected() throws Exception {
        String url = String.format("http://localhost:%d/", port);
        ResponseEntity<String> response = template.getForEntity(url, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }
}
