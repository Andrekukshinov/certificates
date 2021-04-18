package com.epam.esm.service.dto;

import java.util.Objects;

public class TagDto {

    private Long id;
    private String name;

    public TagDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public TagDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TagDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TagDto tagDto = (TagDto) o;
        return Objects.equals(getId(), tagDto.getId()) && Objects.equals(getName(), tagDto.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
