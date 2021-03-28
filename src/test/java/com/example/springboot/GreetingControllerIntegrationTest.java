package com.example.springboot;

import static org.assertj.core.api.Assertions.*;

import java.net.URL;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingControllerIntegrationTest {

	@LocalServerPort
	private int port;

	private URL base;

	@Autowired
	private TestRestTemplate template;

    @BeforeEach
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/greeting");
    }

    @Test
    public void getGreetingNoParams() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        JSONObject jsonResponse = new JSONObject(response.getBody());
        assertThat(jsonResponse.get("id")).isNotNull();
        assertThat(jsonResponse.get("content")).isEqualTo("Hello World!");
    }

    @Test
    public void getGreetingWithName() throws Exception {
        final String testName = "Test";
        final URL baseWithParamName = new URL("http://localhost:" + port + "/greeting?name=" + testName);

        ResponseEntity<String> response = template.getForEntity(baseWithParamName.toString(), String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        JSONObject jsonResponse = new JSONObject(response.getBody());
        assertThat(jsonResponse.get("id")).isNotNull();
        assertThat(jsonResponse.get("content")).isEqualTo("Hello Test!");
    }
}