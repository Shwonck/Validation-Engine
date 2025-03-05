package com.shwonck.validation_engine.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserData {
    @NotBlank(message = "CPF é obrigatório")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF deve estar no formato 123.456.789-09")
    private String cpf;

    @NotBlank(message = "Nome completo é obrigatório")
    private String fullName;

    @NotBlank(message = "Telefone é obrigatório")
    @Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}", message = "Telefone deve estar no formato (11) 98765-4321")
    private String phone;

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail deve ser válido")
    private String email;

    @NotBlank(message = "Data de nascimento é obrigatória")
    @Pattern(regexp = "\\d{2}-\\d{2}-\\d{4}", message = "Data de nascimento deve estar no formato dd-MM-yyyy")
    private String birthDate;

    @NotBlank(message = "Endereço é obrigatório")
    private String address;

    @NotBlank(message = "Nome da mãe é obrigatório")
    private String motherName;
}