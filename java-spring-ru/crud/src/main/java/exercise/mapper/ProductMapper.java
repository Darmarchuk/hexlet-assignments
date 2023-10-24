package exercise.mapper;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.model.Product;
import org.mapstruct.*;

import java.util.List;


@Mapper(
        uses={ReferenceMapper.class,JsonNullableMapper.class},
        componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
         unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class ProductMapper{

    @Mapping(target = "category",source = "categoryId")
    public abstract Product map(ProductDTO dto);

    @InheritInverseConfiguration
    @Mapping(source = "model.category.name" ,target = "categoryName")
    @Mapping(source = "model.category.id" ,target = "categoryId")
    public abstract ProductDTO map(Product model);

    public abstract List<ProductDTO> map(List<Product> models);
    @Mapping(target = "category" , ignore = true)
    public abstract Product map(ProductCreateDTO dto);

    @Mapping(target = "category" , ignore = true)
    public abstract void  update(ProductUpdateDTO dto,@MappingTarget Product model);


}