package com.example.deepsurvey.dto;

import javax.validation.constraints.*;
import lombok.Data;

@Data
public class CheckoutRequest {

    private String sessionId;

    // Nome
    @NotBlank(message = "O nome é obrigatório")
    @Pattern(
        regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ\\s\\.'-]{3,60}$",
        message = "Nome inválido"
    )
    private String name;

    // Whatsapp
    @NotBlank(message = "O whatsapp é obrigatório")
    @Pattern(
        regexp = "^\\d{10,11}$",
        message = "Whatsapp deve conter 10 ou 11 dígitos"
    )
    private String phone;

    // Cidade / Região
    @NotBlank(message = "A cidade/região é obrigatória")
    private String region;

    // CEP
    @NotBlank(message = "CEP é obrigatório")
    @Pattern(
        regexp = "^[0-9\\-]{8,9}$",
        message = "CEP inválido"
    )
    private String cep;

    // Rua
    @NotBlank(message = "A rua é obrigatória")
    @Pattern(
        regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ0-9\\s,'\\.-]{3,80}$",
        message = "Rua inválida"
    )
    private String street;

    // Número
    @NotBlank(message = "O número é obrigatório")
    @Pattern(
        regexp = "^[0-9A-Za-z\\-\\/]{1,10}$",
        message = "Número inválido"
    )
    private String number;

    private String complement;

    @Pattern(
        regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ0-9\\s,'\\.-]{0,80}$",
        message = "Referência inválida"
    )
    private String reference;

    private String paymentMethod;
    private Double change;
}