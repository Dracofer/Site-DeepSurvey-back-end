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

    // Configurações gerais
    private Double minimumOrderValue = 0.0;
    private Boolean storeOpen = true;

    private String storeName = "Minha Loja";

    @Column(name = "store_subtitle")
    private String storeSubtitle = "Suplementos.";

    private String logoUrl;

    private String themeColor = "#2c2b6e";

    @Column(name = "address_text", length = 500)
    private String addressText;
    
    private String titleColor;
    private String productTextColor;
    private String pageTextColor;

    // ✅ NOVO — Imagem de fundo da loja
    @Column(name = "background_image")
    private String backgroundImage;
    private String themeMode = "light"; // light | dark | glass
    private String headerTheme; // "light", "dark", "blue", "glass", etc.

    // Configurações de contato
    @Column(name = "whatsapp_number")
    private String whatsappNumber = "5511947935371";

    @Column(name = "whatsapp_number2")
    private String whatsappNumber2;

    @Column(name = "contact_email")
    private String contactEmail = "exemplo@loja.com";

    @Column(name = "contact_text", length = 2000)
    private String contactText =
            "Segunda à sábado: 08:00 às 22:00\nDomingo: Fechado\n\n" +
            "Endereço: informe aqui seu endereço completo.";
}