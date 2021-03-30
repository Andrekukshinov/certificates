package com.epam.esm.persistence.extractor.impl;

import com.epam.esm.persistence.extractor.SpecificationExtractor;
import com.epam.esm.persistence.model.SearchSpecification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class SpecificationExtractorImpl implements SpecificationExtractor {
    @Override
    public List<Object> getValues(SearchSpecification specification) {
        List<Object> result = new ArrayList<>();
        addToListIfNotNull(result, specification.getNameCertificateSearch());
        addToListIfNotNull(result, specification.getDescriptionCertificateSearch());
        addToListIfNotNull(result, specification.getTagNameSearch());
        addToListIfNotNull(result, specification.getNameSortConditionDir());
        addToListIfNotNull(result, specification.getCreateDateSortConditionDir());
        return result;
    }

    private void addToListIfNotNull(List<Object> result, Object objectToPut) {
        if (Objects.nonNull(objectToPut)) {
            result.add(objectToPut);
        }
    }
}
