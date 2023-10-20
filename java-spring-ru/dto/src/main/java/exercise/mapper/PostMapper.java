package exercise.mapper;


import exercise.dto.CommentDTO;
import exercise.dto.PostCreateDTO;
import exercise.dto.PostDTO;
import exercise.dto.PostListDTO;
import exercise.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        uses = {JsonNullableMapper.class, ReferenceMapper.class },
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class PostMapper {

    @Mapping(source = "postAuthor.authorId", target = "authorId")
    public abstract PostDTO put(Post post );

    @Mapping(source = "postAuthor.authorId", target = "authorId")
    public abstract List<PostDTO> put(List<Post> map);

    @Mapping(source = "authorId", target = "postAuthor.authorId")
    public abstract Post put(PostDTO postDTO, @MappingTarget Post post);

    @Mapping(target = "id",ignore = true)
    @Mapping(source = "authorId", target = "postAuthor.authorId")
    public abstract Post put(PostCreateDTO post);

}
