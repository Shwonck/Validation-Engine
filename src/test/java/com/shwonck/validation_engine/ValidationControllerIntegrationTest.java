package com.shwonck.validation_engine;

import com.shwonck.validation_engine.model.UserData;
import com.shwonck.validation_engine.model.ValidationResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ValidationControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetRequestBodyTemplate() {
        ResponseEntity<UserData> response = restTemplate.getForEntity("/api/body", UserData.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("123.456.789-09", response.getBody().getCpf());
        assertEquals("Fulano de Tal", response.getBody().getFullName());
        assertEquals("(11) 98765-4321", response.getBody().getPhone());
        assertEquals("fulano@example.com", response.getBody().getEmail());
        assertEquals("31-12-2000", response.getBody().getBirthDate());
        assertEquals("Rua Exemplo, 123", response.getBody().getAddress());
        assertEquals("Mãe de Fulano", response.getBody().getMotherName());

    }

    @Test
    public void testGetValidationScoreValid() {
        UserData userData = new UserData();
        userData.setCpf("123.456.789-09");
        userData.setFullName("Fulano de Tal");
        userData.setPhone("(11) 98765-4321");
        userData.setEmail("fulano@example.com");
        userData.setBirthDate("31-12-2000");
        userData.setAddress("Rua Exemplo, 123");
        userData.setMotherName("Mãe de Fulano");

        ResponseEntity<ValidationResult> response = restTemplate.postForEntity(
                "/api/validate", userData, ValidationResult.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isValid());
    }

    @Test
    public void testGetValidationScoreInvalidCPF() {
        UserData userData = new UserData();
        userData.setCpf("111.222.333-44"); // Invalid CPF
        userData.setFullName("Fulano de Tal");
        userData.setPhone("(11) 98765-4321");
        userData.setEmail("fulano@example.com");
        userData.setBirthDate("31-12-2000");
        userData.setAddress("Rua Exemplo, 123");
        userData.setMotherName("Mãe de Fulano");

        ResponseEntity<ValidationResult> response = restTemplate.postForEntity(
                "/api/validate", userData, ValidationResult.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isValid());
        assertEquals(0, response.getBody().getConfidenceScore());
    }

    @Test
    public void testGetValidationScoreInvalidPhone() {
        UserData userData = new UserData();
        userData.setCpf("111.222.333-44");
        userData.setFullName("Fulano de Tal");
        userData.setPhone("(11) 98765-43211111"); // Invalid phonenumber
        userData.setEmail("fulano@example.com");
        userData.setBirthDate("31-12-2000");
        userData.setAddress("Rua Exemplo, 123");
        userData.setMotherName("Mãe de Fulano");

        ResponseEntity<ValidationResult> response = restTemplate.postForEntity(
                "/api/validate", userData, ValidationResult.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetValidationScoreInvalidEmail() {
        UserData userData = new UserData();
        userData.setCpf("111.222.333-44");
        userData.setFullName("Fulano de Tal");
        userData.setPhone("(11) 98765-43211111");
        userData.setEmail("fulano-example.com"); // Invalid email
        userData.setBirthDate("31-12-2000");
        userData.setAddress("Rua Exemplo, 123");
        userData.setMotherName("Mãe de Fulano");

        ResponseEntity<ValidationResult> response = restTemplate.postForEntity(
                "/api/validate", userData, ValidationResult.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetValidationScoreInvalidBirthDate() {
        UserData userData = new UserData();
        userData.setCpf("111.222.333-44");
        userData.setFullName("Fulano de Tal");
        userData.setPhone("(11) 98765-43211111");
        userData.setEmail("fulano-example.com");
        userData.setBirthDate("31-12-2010"); // Invalid birth date
        userData.setAddress("Rua Exemplo, 123");
        userData.setMotherName("Mãe de Fulano");

        ResponseEntity<ValidationResult> response = restTemplate.postForEntity(
                "/api/validate", userData, ValidationResult.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetValidationScoreMissingCPF() {
        UserData userData = new UserData();
        userData.setCpf("");
        userData.setFullName("Fulano de Tal");
        userData.setPhone("(11) 98765-4321");
        userData.setEmail("fulano@example.com");
        userData.setBirthDate("31-12-2000");
        userData.setAddress("Rua Exemplo, 123");
        userData.setMotherName("Mãe de Fulano");

        ResponseEntity<ValidationResult> response = restTemplate.postForEntity(
                "/api/validate", userData, ValidationResult.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetValidationScoreInvalidDateFormat() {
        UserData userData = new UserData();
        userData.setCpf("123.456.789-09");
        userData.setFullName("Fulano de Tal");
        userData.setPhone("(11) 98765-4321");
        userData.setEmail("fulano@example.com");
        userData.setBirthDate("31/12/2000"); // Invalid format
        userData.setAddress("Rua Exemplo, 123");
        userData.setMotherName("Mãe de Fulano");

        ResponseEntity<ValidationResult> response = restTemplate.postForEntity(
                "/api/validate", userData, ValidationResult.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetValidationScoreInvalidAddress() {
        UserData userData = new UserData();
        userData.setCpf("123.456.789-09");
        userData.setFullName("Fulano de Tal");
        userData.setPhone("(11) 98765-4321");
        userData.setEmail("fulano@example.com");
        userData.setBirthDate("31-12-2000");
        userData.setAddress(""); // Empty address
        userData.setMotherName("Mãe de Fulano");

        ResponseEntity<ValidationResult> response = restTemplate.postForEntity(
                "/api/validate", userData, ValidationResult.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetValidationScoreInvalidMotherName() {
        UserData userData = new UserData();
        userData.setCpf("123.456.789-09");
        userData.setFullName("Fulano de Tal");
        userData.setPhone("(11) 98765-4321");
        userData.setEmail("fulano@example.com");
        userData.setBirthDate("31-12-2000");
        userData.setAddress("Rua Exemplo, 123");
        userData.setMotherName(""); // Empty mother name

        ResponseEntity<ValidationResult> response = restTemplate.postForEntity(
                "/api/validate", userData, ValidationResult.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}