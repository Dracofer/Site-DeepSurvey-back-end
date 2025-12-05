package com.example.deepsurvey.model;

import javax.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double minimumOrderValue = 0.0;
    private Boolean storeOpen = true;

    private String storeName = "Minha Loja";

    @Column(name = "store_subtitle")
    private String storeSubtitle = "Suplementos.";

    private String logoUrl;

    private String themeColor = "#2c2b6e";

    @Column(name = "address_text", length = 500)
    private String addressText;
}