package com.bankapplication.service;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class TestService 	
{
	    private final String thirdPartyApiUrl = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=create";
	    private final String authCode = "dGVzdEBzdW5iYXNlZGF0YS5jb206VGVzdEAxMjM=";

	   
	    public ResponseEntity<String> callThirdPartyApiPost( Map<String, String> requestData) {
	        // Set up headers with authentication and content type
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Authorization", "Basic " + authCode);
	        headers.setContentType(MediaType.APPLICATION_JSON);  // Assuming JSON data in the request body

	        // Set up the request entity with headers and request data
	        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestData, headers);

	        // Create a RestTemplate
	        RestTemplate restTemplate = new RestTemplate();

	        // Make the POST request
	        ResponseEntity<String> response = restTemplate.exchange(
	                thirdPartyApiUrl,
	                HttpMethod.POST,
	                requestEntity,
	                String.class
	        );

	        // Handle the response
	        if (response.getStatusCode().is2xxSuccessful()) {
	            String responseBody = response.getBody();
	            return ResponseEntity.ok("API Response: " + responseBody);
	        } else {
	            return ResponseEntity.status(response.getStatusCode()).body("Error: " + response.getStatusCodeValue());
	        }
	    }
	

}
