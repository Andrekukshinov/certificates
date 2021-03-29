package com.epam.esm.persistence.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class QueryCreatorTest {
     @Test
     public void test () {
         Map<String, Object> values = new HashMap<>();
         String id = "id";
         values.put(id, 1);
         values.put("name", 1);
         QueryCreator queryCreator = new QueryCreator();
         String table = queryCreator.getUpdateQuery("table", values.keySet(), id);
         Assertions.assertEquals("UPDATE table SET name=? WHERE id=?", table);

     }

}
