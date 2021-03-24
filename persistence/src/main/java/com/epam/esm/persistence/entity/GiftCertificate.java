package com.epam.esm.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GiftCertificate {
    private Long id;
    private LocalDate createDate;
    private LocalDate lastUpdateDate;
    private String name;
    private String description;
    private BigDecimal price;
    private Short duration;
    private Set<Tag> tags;
}
