package com.epam.esm.service.validators.impl;

import com.epam.esm.service.dto.TagDto;
import com.epam.esm.service.exception.ValidationException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TagDtoTest {


    private static Stream<Arguments> dataProvider() {
        String tooLongName = "123456789012345678901234567890123456789012345678901";
        TagDto longName = new TagDto(tooLongName);
        return Stream.of(
                Arguments.of(longName, "name cannot be longer than 50 symbols!"),
                Arguments.of(null, "object should exist!")
        );
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
     void testValidateShouldThrowValidationExceptionWhenInvalid (TagDto tagDto, String msg) {
         assertThrows(ValidationException.class, () -> new TagDtoValidator().validate(tagDto), msg);
     }
}
