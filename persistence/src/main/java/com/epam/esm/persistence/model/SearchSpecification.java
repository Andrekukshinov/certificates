package com.epam.esm.persistence.model;

import com.epam.esm.persistence.model.enums.SortDirection;

public class SearchSpecification {
    private String tagNameSearch;
    private String descriptionCertificateSearch;
    private String nameCertificateSearch;
    private SortDirection createDateSortConditionDir;
    private SortDirection nameSortConditionDir;

    public SearchSpecification(String tagSearch, String descriptionCertificateSearch,
                               String nameCertificateSearch, SortDirection createDateSortConditionDir,
                               SortDirection nameSortConditionDir) {
        this.tagNameSearch = tagSearch;
        this.descriptionCertificateSearch = descriptionCertificateSearch;
        this.nameCertificateSearch = nameCertificateSearch;
        this.createDateSortConditionDir = createDateSortConditionDir;
        this.nameSortConditionDir = nameSortConditionDir;
    }

    public String getTagNameSearch() {
        return tagNameSearch;
    }

    public void setTagNameSearch(String tagNameSearch) {
        this.tagNameSearch = tagNameSearch;
    }

    public String getDescriptionCertificateSearch() {
        return descriptionCertificateSearch;
    }

    public void setDescriptionCertificateSearch(String descriptionCertificateSearch) {
        this.descriptionCertificateSearch = descriptionCertificateSearch;
    }

    public String getNameCertificateSearch() {
        return nameCertificateSearch;
    }

    public void setNameCertificateSearch(String nameCertificateSearch) {
        this.nameCertificateSearch = nameCertificateSearch;
    }

    public SortDirection getCreateDateSortConditionDir() {
        return createDateSortConditionDir;
    }

    public void setCreateDateSortConditionDir(SortDirection createDateSortConditionDir) {
        this.createDateSortConditionDir = createDateSortConditionDir;
    }

    public SortDirection getNameSortConditionDir() {
        return nameSortConditionDir;
    }

    public void setNameSortConditionDir(SortDirection nameSortConditionDir) {
        this.nameSortConditionDir = nameSortConditionDir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SearchSpecification that = (SearchSpecification) o;

        if (getTagNameSearch() != null ? !getTagNameSearch().equals(that.getTagNameSearch()) : that.getTagNameSearch() != null) {
            return false;
        }
        if (getDescriptionCertificateSearch() != null ? !getDescriptionCertificateSearch().equals(that.getDescriptionCertificateSearch()) : that.getDescriptionCertificateSearch() != null) {
            return false;
        }
        if (getNameCertificateSearch() != null ? !getNameCertificateSearch().equals(that.getNameCertificateSearch()) : that.getNameCertificateSearch() != null) {
            return false;
        }
        if (getCreateDateSortConditionDir() != that.getCreateDateSortConditionDir()) {
            return false;
        }
        return getNameSortConditionDir() == that.getNameSortConditionDir();
    }

    @Override
    public int hashCode() {
        int result = getTagNameSearch() != null ? getTagNameSearch().hashCode() : 0;
        result = 31 * result + (getDescriptionCertificateSearch() != null ? getDescriptionCertificateSearch().hashCode() : 0);
        result = 31 * result + (getNameCertificateSearch() != null ? getNameCertificateSearch().hashCode() : 0);
        result = 31 * result + (getCreateDateSortConditionDir() != null ? getCreateDateSortConditionDir().hashCode() : 0);
        result = 31 * result + (getNameSortConditionDir() != null ? getNameSortConditionDir().hashCode() : 0);
        return result;
    }
}
