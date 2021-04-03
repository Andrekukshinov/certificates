package com.epam.esm.persistence.util;

import com.epam.esm.persistence.model.SearchSpecification;
import com.epam.esm.persistence.model.SortSpecification;
import com.epam.esm.persistence.model.enums.SortDirection;
import com.epam.esm.persistence.util.creator.QueryCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

class QueryCreatorTest {
    @Test
    public void test() {
        Map<String, Object> values = new LinkedHashMap<>();
        String id = "id";
        values.put("name", 1);
        values.put(id, 1);
        QueryCreator queryCreator = new QueryCreator();
        String table = queryCreator.getUpdateQuery("table", values.keySet(), id);
        Assertions.assertEquals("UPDATE table SET name=?, id=? WHERE id=?", table);

    }

    @Test
    public void testSelect() {
        SearchSpecification searchSpecification = new SearchSpecification("true", null, "");
        SortSpecification sortSpecification = new SortSpecification(SortDirection.DESC, null);
        QueryCreator queryCreator = new QueryCreator();
        String expected = " SELECT *  FROM gift_certificates  WHERE TRUE  AND name LIKE CONCAT('%', ?, '%') AND  id IN (\n" +
                "           SELECT tgc.gift_certificate_id FROM tags_gift_certificates AS tgc\n" +
                "            INNER JOIN tags AS t ON tgc.tag_id = t.id \n" +
                "            WHERE t.name LIKE ?\n" +
                ")   ORDER BY NULL  ,create_date DESC ";
        String query = queryCreator.getFindCertificateByCondition(searchSpecification, sortSpecification);
        Assertions.assertEquals(expected, query);
    }

}
