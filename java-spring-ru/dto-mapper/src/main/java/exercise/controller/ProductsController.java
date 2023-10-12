package exercise.controller;

import exercise.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;

import exercise.repository.ProductRepository;
import exercise.dto.ProductDTO;
import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.ProductMapper;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper mapper;

    @GetMapping
    public List<ProductDTO> show(){
        return mapper.toDtoList(productRepository.findAll());
    }

    @GetMapping("/{id}")
    public ProductDTO show(@PathVariable Long id){
        return mapper.toDto(productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Prdouct with id "+id+"not found")));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO createProduct(@RequestBody ProductCreateDTO dto){
        return mapper.toDto(productRepository.save(mapper.toModel(dto)));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO updateProduct(@PathVariable Long id,@RequestBody ProductUpdateDTO dto){
        Product product=productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product with id "+id+" not found"));
        mapper.toUpdateDto(dto,product);
        productRepository.save(product);
        return mapper.toDto(product);
    }

}
