package com.epam.esm.persistence.extractor;

import java.util.Map;

public interface FieldsExtractor<T> {
    Map<String, Object> getFieldsValuesMap(T t);
}
