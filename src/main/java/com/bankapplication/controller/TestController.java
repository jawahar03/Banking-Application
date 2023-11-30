package com.bankapplication.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    private final String apiUrl = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=create";
    private final String authId = "test@sunbasedata.com";
    private final String authPassword = "Test@123";

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody Map<String, String> customerData) {
        // Set up headers with Basic Authentication and content type
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", getBasicAuthHeader(authId, authPassword));
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Set up the request entity with headers and customer data
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(customerData, headers);

        // Create a RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        try {
            // Make the POST request
            ResponseEntity<String> response = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );

            // Handle the response
            if (response.getStatusCode().is2xxSuccessful()) {
                String responseBody = response.getBody();
                return ResponseEntity.ok("Customer Created. Response: " + responseBody);
            } else {
                return ResponseEntity.status(response.getStatusCode()).body("Error: " + response.getStatusCodeValue());
            }
        } catch (HttpServerErrorException e) {
            System.out.println("Error Response: " + e.getResponseBodyAsString());
            return ResponseEntity.status(e.getStatusCode()).body("Error: " + e.getResponseBodyAsString());
        }
    }

    private String getBasicAuthHeader(String username, String password) {
        String credentials = username + ":" + password;
        byte[] credentialsBytes = credentials.getBytes(StandardCharsets.UTF_8);
        String encodedCredentials = Base64.getEncoder().encodeToString(credentialsBytes);
        System.out.println("Generated Authorization Header: " + "Basic " + encodedCredentials);
        return "Bearer " + encodedCredentials;
    }

}
