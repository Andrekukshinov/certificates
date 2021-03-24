package com.epam.esm.persistence.util;

import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Component
public class QueryCreator {

    private static final String INSERT_INTO = "INSERT INTO ";
    private static final String OPEN_BRACKET = " (";
    private static final String COMMA_SPACE = ", ";
    private static final String CLOSING_BRACKET = ")";
    private static final String VALUES = " VALUES";
    private static final String QUESTION = "?";
    private static final String UPDATE = "UPDATE ";
    private static final String SET = " SET ";
    private static final String ID = "id";
    private static final String WHERE_ID = " WHERE id=?";
    private static final String EQUALS_QUESTION = "=?";


    public String getSaveQuery(String tableName, Map<String, Object> fieldsValues) {
        Set<String> fieldNames = fieldsValues.keySet();
        StringBuilder queryBuilder = buildQuery(fieldNames, tableName);
        return queryBuilder.toString();
    }

    private StringBuilder buildQuery(Set<String> fieldNames, String tableName) {
        StringBuilder insertIntoPart = new StringBuilder(INSERT_INTO).append(tableName).append(OPEN_BRACKET);
        StringBuilder valuesPart = new StringBuilder(VALUES).append(OPEN_BRACKET);
        Iterator<String> fieldNamesIterator = fieldNames.iterator();
        while (fieldNamesIterator.hasNext()) {
            String fieldName = fieldNamesIterator.next();
            insertIntoPart.append(fieldName);
            valuesPart.append(QUESTION);
            if (fieldNamesIterator.hasNext()) {
                insertIntoPart.append(COMMA_SPACE);
                valuesPart.append(COMMA_SPACE);
            }
        }
        insertIntoPart.append(CLOSING_BRACKET);
        valuesPart.append(CLOSING_BRACKET);
        return insertIntoPart.append(valuesPart);
    }

}
