package com.epam.esm.service.service.impl;

import com.epam.esm.persistence.entity.GiftCertificate;
import com.epam.esm.persistence.entity.Tag;
import com.epam.esm.persistence.model.SearchSpecification;
import com.epam.esm.persistence.model.SortSpecification;
import com.epam.esm.persistence.model.enums.SortDirection;
import com.epam.esm.persistence.repository.GiftCertificateRepository;
import com.epam.esm.service.dto.GiftCertificateTagDto;
import com.epam.esm.service.dto.GiftCertificatesNoTagDto;
import com.epam.esm.service.dto.SpecificationDto;
import com.epam.esm.service.dto.TagDto;
import com.epam.esm.service.exception.EntityNotFoundException;
import com.epam.esm.service.exception.ValidationException;
import com.epam.esm.service.service.TagGiftCertificateService;
import com.epam.esm.service.validators.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
class GiftCertificateServiceImplTest {

    private static final LocalDateTime DATE = LocalDateTime.parse("2021-03-25T00:00:00");
    private static final String SPA = "spa";
    private static final String FAMILY_CERTIFICATE = "family certificate";

    private static final long CERTIFICATE_ID_DEFAULT_ID = 1L;
    private static final Tag PEOPLE_TAG = new Tag(CERTIFICATE_ID_DEFAULT_ID, "PEOPLE");
    private static final Set<Tag> TAGS = Set.of(PEOPLE_TAG);

    private static final TagDto PEOPLE_TAG_DTO = new TagDto("PEOPLE");
    private static final Set<TagDto> TAGS_DTO = Set.of(PEOPLE_TAG_DTO);

    private static final GiftCertificate FIRST =
            new GiftCertificate(CERTIFICATE_ID_DEFAULT_ID, DATE, DATE, SPA, FAMILY_CERTIFICATE, new BigDecimal(754), Short.valueOf("3"), TAGS);

    private static final GiftCertificate EMPTY_TAGS_CERTIFICATE =
            new GiftCertificate(CERTIFICATE_ID_DEFAULT_ID, DATE, DATE, SPA, FAMILY_CERTIFICATE, new BigDecimal(754), Short.valueOf("3"), new HashSet<>());

    private static final GiftCertificate NO_TAGS_CERTIFICATE =
            new GiftCertificate(CERTIFICATE_ID_DEFAULT_ID, DATE, DATE, SPA, FAMILY_CERTIFICATE, new BigDecimal(754), Short.valueOf("3"), null);

    private static final GiftCertificateTagDto DTO =
            new GiftCertificateTagDto(CERTIFICATE_ID_DEFAULT_ID, DATE, DATE, SPA, FAMILY_CERTIFICATE, new BigDecimal(754), Short.valueOf("3"), TAGS_DTO);

    private static final GiftCertificatesNoTagDto NO_TAGS_DTO =
            new GiftCertificatesNoTagDto(CERTIFICATE_ID_DEFAULT_ID, DATE, DATE, SPA, FAMILY_CERTIFICATE, new BigDecimal(754), Short.valueOf("3"));

    @Mock
    private GiftCertificateRepository certificateRepository;
    @Mock
    private Validator<GiftCertificateTagDto> validator;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private TagGiftCertificateService tagCertificateService;

    @InjectMocks
    private GiftCertificateServiceImpl service;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveShouldSaveCertificateWithTagsWhenEntityValid() throws ValidationException {
        when(modelMapper.map(any(), any())).thenReturn(FIRST);
        when(certificateRepository.save(any()))
                .thenAnswer((object) -> object.getArgument(0, GiftCertificate.class).getId());

        service.save(DTO);

        verify(tagCertificateService, times(1)).saveCertificateTags(any(), any());
        verify(validator, times(1)).validate(DTO);
        verify(certificateRepository, times(1)).save(FIRST);
        verify(modelMapper, times(1)).map(DTO, GiftCertificate.class);
    }

    @Test
    public void testSaveShouldSaveCertificateNotTagsWhenEntityValid() throws ValidationException {
        when(modelMapper.map(any(), any())).thenReturn(NO_TAGS_CERTIFICATE);
        when(certificateRepository.save(any()))
                .thenAnswer((object) -> object.getArgument(0, GiftCertificate.class).getId());

        service.save(DTO);

        verify(tagCertificateService, times(0)).saveCertificateTags(any(), any());
        verify(validator, times(1)).validate(DTO);
        verify(certificateRepository, times(1)).save(NO_TAGS_CERTIFICATE);
        verify(modelMapper, times(1)).map(DTO, GiftCertificate.class);
    }

    @Test
    public void testSaveShouldThrowExceptionWhenObjectInvalid() throws ValidationException {
        doThrow(ValidationException.class).when(validator).validate(any());
        when(certificateRepository.save(any()))
                .thenAnswer((object) -> object.getArgument(0, GiftCertificate.class).getId());

        ValidationException validationException = assertThrows(ValidationException.class, () -> service.save(DTO));

        assertThat(validationException.getClass(), is(ValidationException.class));
        verify(tagCertificateService, times(0)).saveCertificateTags(FIRST.getId(), TAGS);
        verify(validator, times(1)).validate(DTO);
        verify(certificateRepository, times(0)).update(FIRST);
        verify(modelMapper, times(0)).map(DTO, GiftCertificate.class);
    }

    @Test
    public void testGetCertificateWithTagsByIdShouldReturnDtoObjectWhenFound() {
        when(certificateRepository.findById(any())).thenReturn(Optional.of(FIRST));
        when(modelMapper.map(FIRST, GiftCertificateTagDto.class)).thenReturn(DTO);
        when(modelMapper.map(PEOPLE_TAG, Tag.class)).thenReturn(PEOPLE_TAG);
        when(tagCertificateService.findCertificateTags(any())).thenReturn(TAGS_DTO);

        GiftCertificateTagDto found = service.getCertificateWithTagsById(CERTIFICATE_ID_DEFAULT_ID);

        assertThat(found, is(DTO));

    }

    @Test
    public void testGetCertificateWithTagsByIdShouldThrowEntityNotFoundExceptionWhenNotFound() {
        when(certificateRepository.findById(CERTIFICATE_ID_DEFAULT_ID)).thenReturn(Optional.empty());

        EntityNotFoundException entityNotFoundException = assertThrows(EntityNotFoundException.class, () -> service.getCertificateWithTagsById(CERTIFICATE_ID_DEFAULT_ID));

        assertThat(entityNotFoundException.getMessage(), is("certificate with id = 1 not found"));
    }

    @Test
    public void testUpdateCertificateShouldInvokeSaveCertificateTagsWhenTagsFound() throws ValidationException {
        when(modelMapper.map(any(), any())).thenReturn(FIRST);
        when(certificateRepository.update(any())).thenReturn(1);

        service.updateCertificate(DTO);

        verify(tagCertificateService, times(1)).saveCertificateTags(FIRST.getId(), TAGS);
        verify(validator, times(1)).validate(DTO);
        verify(certificateRepository, times(1)).update(FIRST);
        verify(modelMapper, times(1)).map(DTO, GiftCertificate.class);
    }

    @Test
    public void testUpdateCertificateShouldInvokeDeleteCertificateTagsWhenTagsNotFound() throws ValidationException {
        when(modelMapper.map(any(), any())).thenReturn(EMPTY_TAGS_CERTIFICATE);
        when(certificateRepository.update(any())).thenReturn(1);

        service.updateCertificate(DTO);

        verify(tagCertificateService, times(1)).deleteCertificateTags(FIRST.getId());
        verify(validator, times(1)).validate(DTO);
        verify(certificateRepository, times(1)).update(EMPTY_TAGS_CERTIFICATE);
        verify(modelMapper, times(1)).map(DTO, GiftCertificate.class);
    }

    @Test
    public void testUpdateCertificateShouldThrowExceptionWhenObjectInvalid() throws ValidationException {
        doThrow(ValidationException.class).when(validator).validate(any());
        when(certificateRepository.update(any()))
                .thenAnswer((object) -> object.getArgument(0, GiftCertificate.class).getId());

        ValidationException validationException = assertThrows(ValidationException.class, () -> service.updateCertificate(DTO));

        assertThat(validationException.getClass(), is(ValidationException.class));
        verify(tagCertificateService, times(0)).saveCertificateTags(FIRST.getId(), TAGS);
        verify(validator, times(1)).validate(DTO);
        verify(certificateRepository, times(0)).update(FIRST);
        verify(modelMapper, times(0)).map(DTO, GiftCertificate.class);
    }

    @Test
    public void testGetBySpecificationShouldReturnListOfDtoEntitiesWhenFound() {
        SpecificationDto searchSpecificationDto = new SpecificationDto();
        SearchSpecification search = new SearchSpecification("people", "e", "a");
        SortSpecification sort = new SortSpecification(SortDirection.ASC, SortDirection.ASC);
        when(modelMapper.map(searchSpecificationDto, SearchSpecification.class)).thenReturn(search);
        when(modelMapper.map(searchSpecificationDto, SortSpecification.class)).thenReturn(sort);
        when(modelMapper.map(FIRST, GiftCertificatesNoTagDto.class)).thenReturn(NO_TAGS_DTO);
        when(certificateRepository.findBySpecification(search, sort)).thenReturn(List.of(FIRST));

        List<GiftCertificatesNoTagDto> actual = service.getBySpecification(searchSpecificationDto);

        assertThat(actual, is(List.of(NO_TAGS_DTO)));
        verify(certificateRepository, times(1)).findBySpecification(search, sort);
        verify(modelMapper, times(3)).map(any(), any());
    }

     @Test
     public void testDeleteCertificateShouldInvokeMethods () {
         service.deleteCertificate(CERTIFICATE_ID_DEFAULT_ID);

         verify(tagCertificateService, times(1)).deleteCertificateTags(1L);
         verify(certificateRepository, times(1)).delete(1L);
     }
}
