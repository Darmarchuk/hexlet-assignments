package exercise.mapper;


import exercise.dto.CommentDTO;
import exercise.dto.PostCreateDTO;
import exercise.dto.PostDTO;
import exercise.dto.PostListDTO;
import exercise.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class PostMapper {
    public abstract PostDTO put(Post map , List<CommentDTO> comments);
    public abstract PostListDTO put(Post map );
    public abstract Post put(PostDTO post);

    @Mapping(target = "id",ignore = true)
    public abstract Post put(PostCreateDTO post);

}
