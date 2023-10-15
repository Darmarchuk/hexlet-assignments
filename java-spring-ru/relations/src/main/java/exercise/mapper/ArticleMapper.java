package exercise.mapper;

import exercise.dto.ArticleCreateDto;
import exercise.dto.ArticleDto;
import exercise.model.Article;
import org.mapstruct.*;

@Mapper( uses = { ReferenceMapper.class },
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)


public abstract class ArticleMapper {

   @Mapping(target = "category",source = "dto.categoryId")
    public abstract Article toEntity(ArticleCreateDto dto);

    @InheritInverseConfiguration
    public abstract ArticleDto toDto(Article dto);

    @Mapping(target = "category",source = "dto.categoryId")
    public abstract void toEntity(ArticleCreateDto dto, @MappingTarget Article article );

}
