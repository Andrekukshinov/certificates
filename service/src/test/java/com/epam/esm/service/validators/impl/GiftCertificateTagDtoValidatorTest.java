package com.epam.esm.service.validators.impl;

import com.epam.esm.service.dto.GiftCertificateTagDto;
import com.epam.esm.service.dto.TagDto;
import com.epam.esm.service.exception.ValidationException;
import com.epam.esm.service.validators.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

class GiftCertificateTagDtoValidatorTest {
    private static final LocalDateTime DATE = LocalDateTime.parse("2021-03-25T00:00:00");

    @Mock
    private Validator<TagDto> tagDtoValidator;

    @InjectMocks
    private GiftCertificateTagDtoValidator validator;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }


    private static Stream<Arguments> dataProvider() {
        GiftCertificateTagDto invalidId =
                new GiftCertificateTagDto(-1L, DATE, DATE, "SPA", "FAMILY_CERTIFICATE", new BigDecimal(754), Short.valueOf("3"), null);
        GiftCertificateTagDto createDateSpecified =
                new GiftCertificateTagDto(1L, DATE, DATE, "SPA", "FAMILY_CERTIFICATE", new BigDecimal(754), Short.valueOf("3"), null);
        GiftCertificateTagDto updateDateDateSpecified =
                new GiftCertificateTagDto(1L, null, DATE, "SPA", "FAMILY_CERTIFICATE", new BigDecimal(754), Short.valueOf("3"), null);
        String tooLongName = "123456789012345678901234567890123456789012345678901";
        GiftCertificateTagDto longName =
                new GiftCertificateTagDto(1L, null, null, tooLongName, "FAMILY_CERTIFICATE", new BigDecimal(754), Short.valueOf("3"), null);
        String tooLongDescription = "1123456789012345678901234567890123456789012345678901123456789012" +
                "3456789012345678901234567890123456789011234567890123456789012345678901234567890123456" +
                "7890112345678901234567890123456789012345678901234567890112345678901234567890123456789" +
                "0123456789012345678901";
        GiftCertificateTagDto longDescr =
                new GiftCertificateTagDto(1L, null, null, "tooLongName", tooLongDescription, new BigDecimal(754), Short.valueOf("3"), null);
        GiftCertificateTagDto invalidPrice =
                new GiftCertificateTagDto(1L, null, null, "tooLongName", "tooLongDescription", new BigDecimal(-754), Short.valueOf("3"), null);
        GiftCertificateTagDto invalidDuration =
                new GiftCertificateTagDto(1L, null, null, "tooLongName", "tooLongDescription", new BigDecimal(754), Short.valueOf("-3"), null);

        return Stream.of(
                Arguments.of(invalidId, "id must be more than 0!"),
                Arguments.of(longName, "name cannot be longer than 50 symbols!"),
                Arguments.of(longDescr, "description cannot be longer than 255 symbols!"),
                Arguments.of(createDateSpecified, "create date cannot be specified!"),
                Arguments.of(updateDateDateSpecified, "last update date cannot be specified!"),
                Arguments.of(invalidDuration, "duration cannot be negative!"),
                Arguments.of(invalidPrice, "price cannot be negative!"),
                Arguments.of(null, "object should exist!")
        );
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void testValidateShouldThrowExceptionWhenInvalidObject(GiftCertificateTagDto dto, String msg) {
        assertThrows(ValidationException.class, () -> validator.validate(dto), msg);
    }
}
