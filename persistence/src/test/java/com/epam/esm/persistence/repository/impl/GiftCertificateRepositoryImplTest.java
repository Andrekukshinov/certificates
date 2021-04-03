package com.epam.esm.persistence.repository.impl;

import com.epam.esm.persistence.config.TestConfiguration;
import com.epam.esm.persistence.entity.GiftCertificate;
import com.epam.esm.persistence.model.SearchSpecification;
import com.epam.esm.persistence.model.SortSpecification;
import com.epam.esm.persistence.model.enums.SortDirection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@ActiveProfiles("test")
class GiftCertificateRepositoryImplTest {
    private static final Long EXPECTED_SAVED_ID = 5L;
    private static final int EXPECTED_AMOUNT = 1;
    private static final LocalDateTime DATE = LocalDateTime.parse("2021-03-25T00:00:00");
    private static final String SPA = "spa";
    private static final String FAMILY_CERTIFICATE = "family certificate";
    private static final String GYM = "gym";
    private static final String GYM_CERTIFICATE = "for boss of the gym";
    private static final String POOL = "pool";
    private static final String CONNECTION_POOL = "for better connection";
    private static final String CLUP = "clup";
    private static final String CLUP_GIFT = "for leatherman";

    private static final GiftCertificate FOR_SAVING =
            new GiftCertificate(null, DATE, DATE, SPA, FAMILY_CERTIFICATE, new BigDecimal(754), Short.valueOf("3"), null);

    private static final GiftCertificate FIRST =
            new GiftCertificate(1L, DATE, DATE, SPA, FAMILY_CERTIFICATE, new BigDecimal(754), Short.valueOf("3"), null);

    private static final GiftCertificate SECOND =
            new GiftCertificate(2L, DATE, DATE, GYM, GYM_CERTIFICATE, new BigDecimal(300), Short.valueOf("14"),null);

    private static final GiftCertificate THIRD =
            new GiftCertificate(3L, DATE, DATE, POOL, CONNECTION_POOL, new BigDecimal(354), Short.valueOf("23"), null);

    private static final GiftCertificate FOURTH =
            new GiftCertificate(4L, DATE, DATE, CLUP, CLUP_GIFT, new BigDecimal(150), Short.valueOf("9"), null);

    private static final GiftCertificate FOR_UPDATING =
            new GiftCertificate(3L, DATE, DATE, SPA, FAMILY_CERTIFICATE, new BigDecimal(755), Short.valueOf("3"), null);



    private static Stream<Arguments> dataProvider() {
         SearchSpecification findFirstAndFourth = new SearchSpecification("people", "e", "p");
         SearchSpecification findFirstThirdAndFourth = new SearchSpecification(null, "e", "p");
         SearchSpecification findFirstThirdAndFourthByDescription = new SearchSpecification(null, null, "p");
         SearchSpecification findAll = new SearchSpecification(null, null, null);
         SortSpecification nameSortAsc = new SortSpecification(null, SortDirection.ASC);
         SortSpecification nameSortDesc = new SortSpecification(null, SortDirection.DESC);
         SortSpecification nameDescriptionSortAsc = new SortSpecification(SortDirection.ASC, SortDirection.ASC);
         return Stream.of(
                  Arguments.of(findFirstAndFourth, nameSortAsc, List.of(FOURTH, FIRST)),
                  Arguments.of(findFirstThirdAndFourth, nameSortAsc, List.of(FOURTH, THIRD, FIRST)),
                  Arguments.of(findFirstThirdAndFourthByDescription, nameSortDesc, List.of(FIRST, THIRD, FOURTH )),
                  Arguments.of(findAll, nameDescriptionSortAsc, List.of(FOURTH, SECOND, THIRD, FIRST))
          );
     }

    @Autowired
    private GiftCertificateRepositoryImpl repository;

    @Test
    void testFindByIdShouldReturnObjectWhenFound() {
        Optional<GiftCertificate> certificateOptional = repository.findById(1L);

        GiftCertificate giftCertificate = certificateOptional.get();
        assertThat(giftCertificate, is(FIRST));
    }

    @Test
    void testSaveShouldReturnObjectIdWhenSaved() {
        Long id = repository.save(FOR_SAVING);

        assertThat(id, is(EXPECTED_SAVED_ID));
    }

    @Test
    void testDeleteShouldReturnDeletedObjectsAmountWhenDeleted() {
         int amount  = repository.delete(2L);

        assertThat(amount, is(EXPECTED_AMOUNT));
    }

    @Test
    void testUpdateShouldReturnUpdatedObjectsAmountWhenUpdated() {
         int amount  = repository.update(FOR_UPDATING);

        assertThat(amount, is(EXPECTED_AMOUNT));
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void testFindBySpecificationShouldReturnListOfObjects(
                SearchSpecification searchSpecification,
                SortSpecification sortSpecification,
                List<GiftCertificate> expectedCertificates) {
        List<GiftCertificate> giftCertificates = repository.findBySpecification(searchSpecification, sortSpecification);

        assertThat(giftCertificates, is(expectedCertificates));
    }
}
