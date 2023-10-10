package exercise.mapper;


import exercise.dto.CommentDTO;
import exercise.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CommentMapper {
    public abstract Comment map(CommentDTO comment);
    public abstract CommentDTO map(Comment comment);
}
