package exercise.mapper;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.model.Product;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ProductMapper{

    @Mapping(target ="barcode",source = "dto.vendorCode" )
    @Mapping(target ="cost",source = "dto.price" )
    @Mapping(target = "name",source="dto.title")
    public abstract Product ToModel(ProductDTO dto);

    @InheritInverseConfiguration
    public  abstract ProductDTO toDto(Product dto);

    @InheritConfiguration
    public abstract List<ProductDTO> toDtoList(List<Product> products);

    @Mapping(target ="product.cost",source = "dto.price" )
    @Mapping(target = "product.name",source="dto.title")
    public abstract void  toUpdateDto(ProductUpdateDTO dto, @MappingTarget Product product);

    @Mapping(target ="barcode",source = "dto.vendorCode" )
    @Mapping(target ="cost",source = "dto.price" )
    @Mapping(target = "name",source="dto.title")
    public abstract Product  toModel(ProductCreateDTO dto);
}