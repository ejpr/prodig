package com.challenge.prodig.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.PageRequest;

import com.challenge.prodig.dto.PageRequestDTO;

@Mapper
public interface PageableMapper {

    PageableMapper INSTANCE = Mappers.getMapper(PageableMapper.class);

    default PageRequest mapPageRequestDTO(PageRequestDTO pageRequestDTO) {
        return PageRequest.of(pageRequestDTO.getPage(), pageRequestDTO.getSize());
    }
}
