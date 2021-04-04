package com.epam.esm.service.service.impl;

import com.epam.esm.persistence.entity.GiftCertificate;
import com.epam.esm.persistence.entity.Tag;
import com.epam.esm.persistence.repository.GiftCertificateRepository;
import com.epam.esm.service.dto.GiftCertificateTagDto;
import com.epam.esm.service.dto.TagDto;
import com.epam.esm.service.exception.EntityNotFoundException;
import com.epam.esm.service.exception.ValidationException;
import com.epam.esm.service.service.GiftCertificateService;
import com.epam.esm.service.service.TagGiftCertificateService;
import com.epam.esm.service.validators.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GiftCertificateServiceImplTest {

    private static final LocalDateTime DATE = LocalDateTime.parse("2021-03-25T00:00:00");
    private static final String SPA = "spa";
    private static final String FAMILY_CERTIFICATE = "family certificate";

    private static final Tag PEOPLE_TAG = new Tag(1L, "PEOPLE");
    private static final Set<Tag> TAGS = Set.of(PEOPLE_TAG);

    private static final TagDto PEOPLE_TAG_DTO = new TagDto("PEOPLE");
    private static final Set<TagDto> TAGS_DTO = Set.of(PEOPLE_TAG_DTO);

    private static final GiftCertificate FIRST =
            new GiftCertificate(1L, DATE, DATE, SPA, FAMILY_CERTIFICATE, new BigDecimal(754), Short.valueOf("3"), TAGS);

    private static final GiftCertificateTagDto DTO =
            new GiftCertificateTagDto(1L, DATE, DATE, SPA, FAMILY_CERTIFICATE, new BigDecimal(754), Short.valueOf("3"), TAGS_DTO);

    @Mock
    private GiftCertificateRepository certificateRepository;
    @Mock
    private Validator<GiftCertificateTagDto> validator;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private TagGiftCertificateService tagCertificateService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveShouldSaveWhenEntityValid() throws ValidationException {
        when(modelMapper.map(any(), any())).thenReturn(FIRST);
        when(certificateRepository.save(any()))
                .thenAnswer((object) -> object.getArgument(0, GiftCertificate.class).getId());

        GiftCertificateService repository =
                new GiftCertificateServiceImpl(certificateRepository, tagCertificateService, modelMapper, validator);

        repository.save(DTO);

        verify(tagCertificateService, times(1)).saveCertificateTags(any(), any());
        verify(validator, times(1)).validate(DTO);
    }

    @Test
    public void testSaveShouldThrowExceptionWhenObjectInvalid() {
        when(modelMapper.map(any(), any())).thenAnswer((answer) -> {
            throw new ValidationException();
        });
        when(certificateRepository.save(any()))
                .thenAnswer((object) -> object.getArgument(0, GiftCertificate.class).getId());

        GiftCertificateService repository =
                new GiftCertificateServiceImpl(certificateRepository, tagCertificateService, modelMapper, validator);

        ValidationException validationException = assertThrows(ValidationException.class, () -> repository.save(DTO));

        assertThat(validationException.getClass(), is(ValidationException.class));
    }

    @Test
    public void testGetCertificateWithTagsByIdShouldReturnDtoObjectWhenFound() {
        when(certificateRepository.findById(any())).thenReturn(Optional.of(FIRST));
        when(modelMapper.map(FIRST, GiftCertificateTagDto.class)).thenReturn(DTO);
        when(modelMapper.map(PEOPLE_TAG, Tag.class)).thenReturn(PEOPLE_TAG);
        when(tagCertificateService.findCertificateTags(any())).thenReturn(TAGS_DTO);

        GiftCertificateService repository =
                new GiftCertificateServiceImpl(certificateRepository, tagCertificateService, modelMapper, validator);

        GiftCertificateTagDto found = repository.getCertificateWithTagsById(1L);

        assertThat(found, is(DTO));

    }


    @Test
    public void testGetCertificateWithTagsByIdShouldThrowEntityNotFoundExceptionWhenNotFound() {
        when(certificateRepository.findById(1L)).thenReturn(Optional.empty());

        GiftCertificateService repository =
                new GiftCertificateServiceImpl(certificateRepository, tagCertificateService, modelMapper, validator);

        EntityNotFoundException entityNotFoundException = assertThrows(EntityNotFoundException.class, () -> repository.getCertificateWithTagsById(1L));

       assertThat(entityNotFoundException.getMessage(), is("certificate with id = 1 not found"));
    }
// TODO: 04.04.2021 update certificate & getBySpecification


}
