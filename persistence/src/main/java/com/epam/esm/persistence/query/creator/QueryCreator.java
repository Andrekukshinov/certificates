package com.epam.esm.persistence.query.creator;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Component
public class QueryCreator {

    private static final String INSERT_INTO = "INSERT INTO ";
    private static final String OPEN_BRACKET = " (";
    private static final String COMMA_SPACE = ", ";
    private static final String SEMI_COLUMN = ";";
    private static final String CLOSING_BRACKET = ") ";
    private static final String VALUES = " VALUES";
    private static final String QUESTION = "?";
    private static final String UPDATE = "UPDATE ";
    private static final String SET = " SET ";
    private static final String ID = "id";
    private static final String WHERE_ID = " WHERE id=?";
    private static final String EQUALS_QUESTION = "=?";
    private static final int SINGLE_ENTITY = 1;


    public String getSaveQuery(String tableName, Set<String> fieldNames) {
        StringBuilder insertIntoPart = getInsertIntoPart(fieldNames, tableName);
        StringBuilder secondPart = new StringBuilder(VALUES);
        secondPart.append(getValuesPart(fieldNames));
        secondPart.append(SEMI_COLUMN);
        return insertIntoPart.append(secondPart).toString();
    }

    public String getUpdateQuery(String tableName, Set<String> fieldNames, String updateParam) {
        StringBuilder query = new StringBuilder(UPDATE);
        query.append(tableName);
        query.append(" SET ");
        String setValuesPart = getJoinedString("=?, ", fieldNames);
        query.append(setValuesPart).append("=?").append(" WHERE ").append(updateParam).append("=?");
        return query.toString();
    }

    private StringBuilder getInsertIntoPart(Set<String> fieldNames, String tableName) {
        StringBuilder insertIntoPart = new StringBuilder(INSERT_INTO).append(tableName);
        insertIntoPart.append(OPEN_BRACKET);
        String fieldNamesForInsert = getJoinedString(COMMA_SPACE, fieldNames);
        insertIntoPart.append(fieldNamesForInsert);
        insertIntoPart.append(CLOSING_BRACKET);
        return insertIntoPart;
    }

    private StringBuilder getValuesPart(Set<String> fieldNames) {
        StringBuilder valuesPart = new StringBuilder(OPEN_BRACKET);
        String questionsForValues = getJoinedString(COMMA_SPACE, Collections.nCopies(fieldNames.size(), QUESTION));
        valuesPart.append(questionsForValues);
        valuesPart.append(CLOSING_BRACKET);
        return valuesPart;
    }

    private String getJoinedString(String joinWith, Collection<? extends String> forJoining) {
        return String.join(joinWith, forJoining);
    }
}
