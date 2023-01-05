package com.ognevoydev.quisell.model;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
    Post postUpdateDTOtoPost(PostUpdateDTO postUpdateDTO);
}
