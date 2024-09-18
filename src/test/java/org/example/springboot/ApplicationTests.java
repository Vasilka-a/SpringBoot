package org.example.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestTaskApplicationTests {
    @Container
    private static final GenericContainer<?> myAppDev = new GenericContainer<>("devapp")
            .withExposedPorts(8080);
    @Container
    private static final GenericContainer<?> myAppProd = new GenericContainer<>("prodapp")
            .withExposedPorts(8081);
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void devAppTest() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + myAppDev.getMappedPort(8080) + "/profile", String.class);
        String expected = "Current profile is dev";
        assertEquals(expected, forEntity.getBody());
    }

    @Test
    void prodAppTest() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + myAppProd.getMappedPort(8081) + "/profile", String.class);
        String expected = "Current profile is production";
        assertEquals(expected, forEntity.getBody());
    }
}





