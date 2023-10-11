package exercise.mapper;

import exercise.dto.AuthorDTO;
import exercise.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)

public abstract class AuthorMapper {

    public abstract AuthorDTO put(Author author);

    public abstract List<AuthorDTO> put(List<Author> authors);

    public abstract Author put(AuthorDTO author);
}
