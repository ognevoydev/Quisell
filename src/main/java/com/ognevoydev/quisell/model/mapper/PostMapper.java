package com.ognevoydev.quisell.model.mapper;

import com.ognevoydev.quisell.model.Post;
import com.ognevoydev.quisell.model.PostDTO;
import org.mapstruct.Mapper;

@Mapper
public interface PostMapper {
    Post postDTOtoPost(PostDTO postDTO);
}
