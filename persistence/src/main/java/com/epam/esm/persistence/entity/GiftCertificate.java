package com.epam.esm.persistence.entity;
//
//import com.epam.esm.persistence.util.LocalDateDeserializer;
//import com.epam.esm.persistence.util.LocalDateSerializer;
//import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.epam.esm.persistence.util.LocalDateDeserializer;
import com.epam.esm.persistence.util.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GiftCertificate implements Serializable {
    private Long id;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate createDate;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate lastUpdateDate;
    private String name;
    private String description;
    private BigDecimal price;
    private Short duration;
    private Set<Tag> tags;
}
