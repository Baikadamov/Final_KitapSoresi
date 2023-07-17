package kz.bitlab.Kitapsoresi.mapper;


import kz.bitlab.Kitapsoresi.dto.BlogDTO;
import kz.bitlab.Kitapsoresi.model.Blog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface BlogMapper {

//    @Mapping(source = "name",target = "title")

    BlogDTO toDto(Blog blog);

//    @Mapping(source = "title",target = "name")
    Blog toModel(BlogDTO blogDTO);

    List<BlogDTO> toDtoList(List<Blog> blogs);
    List<Blog> toModelList(List<BlogDTO> blogDTOS);

}
