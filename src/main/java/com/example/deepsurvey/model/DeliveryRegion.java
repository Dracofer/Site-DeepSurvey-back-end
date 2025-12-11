package com.example.deepsurvey.model;

import javax.persistence.*;

import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryRegion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // nome da região
    private String name;

    // valor do frete
    private Double fee;

    // ativa ou não
    private Boolean active = true;
}