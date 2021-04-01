package com.epam.esm.service.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GiftCertificatesNoTagDto {
    private Long id;
    private LocalDate createDate;
    private LocalDate lastUpdateDate;
    private String name;
    private String description;
    private BigDecimal price;
    private Short duration;

    public GiftCertificatesNoTagDto(Long id, LocalDate createDate, LocalDate lastUpdateDate, String name, String description, BigDecimal price, Short duration) {
        this.id = id;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
    }

    public GiftCertificatesNoTagDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDate lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Short getDuration() {
        return duration;
    }

    public void setDuration(Short duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GiftCertificatesNoTagDto that = (GiftCertificatesNoTagDto) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) {
            return false;
        }
        if (getCreateDate() != null ? !getCreateDate().equals(that.getCreateDate()) : that.getCreateDate() != null) {
            return false;
        }
        if (getLastUpdateDate() != null ? !getLastUpdateDate().equals(that.getLastUpdateDate()) : that.getLastUpdateDate() != null) {
            return false;
        }
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) {
            return false;
        }
        if (getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null) {
            return false;
        }
        if (getPrice() != null ? !getPrice().equals(that.getPrice()) : that.getPrice() != null) {
            return false;
        }
        return getDuration() != null ? getDuration().equals(that.getDuration()) : that.getDuration() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getCreateDate() != null ? getCreateDate().hashCode() : 0);
        result = 31 * result + (getLastUpdateDate() != null ? getLastUpdateDate().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getDuration() != null ? getDuration().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GiftCertificatesNoTagDto{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", lastUpdateDate=" + lastUpdateDate +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", duration=" + duration +
                '}';
    }
}
