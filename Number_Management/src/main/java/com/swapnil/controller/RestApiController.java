package com.swapnil.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@CrossOrigin("*")
public class RestApiController {
    @GetMapping("/numbers")
    public ResponseEntity<Map<String, List<Integer>>> getMergedNumbers(
            @RequestParam List<String> url) {

        List<Integer> mergedNumbers = new ArrayList<>();

        for (String urlString : url) {
            try {
                List<Integer> numbers = fetchDataFromUrl(urlString);
                mergedNumbers.addAll(numbers);
            } catch (Exception e) {
            }
        }

        Set<Integer> uniqueNumbers = new HashSet<>(mergedNumbers);
        List<Integer> sortedNumbers = new ArrayList<>(uniqueNumbers);
        Collections.sort(sortedNumbers);

        Map<String, List<Integer>> response = new HashMap<>();
        response.put("numbers", sortedNumbers);

        return ResponseEntity.ok(response);
    }

    private List<Integer> fetchDataFromUrl(String urlString) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(urlString, String.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                String responseBody = responseEntity.getBody();

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(responseBody);

                if (jsonNode.has("numbers")) {
                    JsonNode numbersNode = jsonNode.get("numbers");
                    if (numbersNode.isArray()) {
                        List<Integer> numbers = new ArrayList<>();
                        for (JsonNode numberNode : numbersNode) {
                            numbers.add(numberNode.asInt());
                        }
                        return numbers;
                    }
                }
            }
        } catch (Exception e) {
        }

        return Collections.emptyList();
    }

}
