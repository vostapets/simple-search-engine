package org.osv.sse.server.client;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootApplication
public class ClientApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientApplication.class);

    public static void main(String args[]) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Client application example hard-coded behaviour.");
        RestTemplate restTemplate = new RestTemplateBuilder().build();
        String document = "aaa bbb ccc";
        LOGGER.info("Store document {}.", document);
        URI uri = restTemplate.postForLocation("http://localhost:8080/document", document);
        LOGGER.info("Get document by uri {}.", uri);
        String stored = restTemplate.getForObject(uri, String.class);
        LOGGER.info("Result of get {}.", stored);
        LOGGER.info("Store more documents...");
        restTemplate.postForLocation("http://localhost:8080/document", "aaa bbb");
        restTemplate.postForLocation("http://localhost:8080/document", "aaa ccc");

        search(restTemplate, "aa", "bb");
        search(restTemplate, "cc", "bb");
        search(restTemplate, "dd", "bb");
    }

    private void search(RestTemplate restTemplate, Object... params) {
        LOGGER.info("Search by tokens: {}, {}.", params);
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8080/search")
                .queryParam("token", params);

        String searchResult = restTemplate.getForObject(builder.build().encode().toUri(), String.class);
        LOGGER.info("Search result {}.", searchResult);
    }
}
