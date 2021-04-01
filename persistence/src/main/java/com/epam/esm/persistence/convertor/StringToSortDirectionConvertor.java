package com.epam.esm.persistence.convertor;

import com.epam.esm.persistence.model.enums.SortDirection;
import org.springframework.core.convert.converter.Converter;

public class StringToSortDirectionConvertor implements Converter<String, SortDirection> {
    @Override
    public SortDirection convert(String source) {
        return SortDirection.valueOf(source.toUpperCase());
    }
}
