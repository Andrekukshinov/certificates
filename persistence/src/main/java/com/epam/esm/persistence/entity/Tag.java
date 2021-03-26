package com.epam.esm.persistence.entity;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tag implements Serializable {
    private Long id;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Tag tag = (Tag) o;

        return name.equals(tag.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
