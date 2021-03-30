package com.epam.esm.persistence.extractor;

import com.epam.esm.persistence.model.SearchSpecification;

import java.util.List;

public interface SpecificationExtractor {
    List<Object> getValues(SearchSpecification specification);
}
