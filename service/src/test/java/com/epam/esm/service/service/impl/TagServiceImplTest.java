package com.epam.esm.service.service.impl;

import com.epam.esm.persistence.entity.Tag;
import com.epam.esm.service.dto.TagDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

class TagServiceImplTest {
    @Test
    public void test() {
        Tag tag = new Tag(1L, "qw");
        TagDto tagDto = new TagDto("qw");
        ModelMapper modelMapper = new ModelMapper();
        TagDto map = modelMapper.map(tag, TagDto.class);
        Assertions.assertEquals(tagDto, map);
    }

}
