package com.shwonck.validation_engine.controller;

import com.shwonck.validation_engine.model.UserData;
import com.shwonck.validation_engine.service.ValidationService;
import com.shwonck.validation_engine.model.ValidationResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ValidationController {


    private final ValidationService validationService;

    @Autowired
    public ValidationController(ValidationService validationService) {
        this.validationService = validationService;
    }

    @GetMapping("/body")
        @Operation(summary = "Returns a UserData model with default values", description = "This endpoint returns a UserData template with default values to facilitate manual requests.")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Template returned successfully"),
                @ApiResponse(responseCode = "500", description = "Internal server error")
        })
        public UserData getRequestBodyTemplate() {
            // Returns a UserData model with default values
            UserData template = new UserData();
            template.setCpf("123.456.789-09");
            template.setFullName("Fulano de Tal");
            template.setPhone("(11) 98765-4321");
            template.setEmail("fulano@example.com");
            template.setBirthDate("31-12-2000");
            template.setAddress("Rua Exemplo, 123");
            template.setMotherName("Mãe de Fulano");

            return template;
        }


    @PostMapping("/validate")
    public ResponseEntity<ValidationResult> validateUserData(@Valid @RequestBody UserData userData) {
            ValidationResult result = ValidationService.validate(userData);
            return ResponseEntity.ok(result);
    }
}
