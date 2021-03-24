package com.epam.esm.persistence.extractor;

import org.springframework.stereotype.Component;

import java.util.Map;

public interface FieldsExtractor<T> {
    Map<String, Object> getFieldsValuesMap(T t);//todo return set of names
}
