package exercise.controller;

import java.util.List;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.exception.BadRequestException;
import exercise.mapper.ProductMapper;
import exercise.model.Category;
import exercise.model.Product;
import exercise.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductMapper productMapper;

    @GetMapping
    List<ProductDTO> show()
    {
        return productMapper.map(productRepository.findAll());
    }
    @GetMapping("/{id}")
    ProductDTO show(@PathVariable Long id)
    {
        return productMapper.map(productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product not found")));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ProductDTO create(@RequestBody ProductCreateDTO dto){
        Product product=productMapper.map(dto);
        Category category=categoryRepository.findById(dto.getCategoryId()).orElseThrow(()->new BadRequestException("Category not found" ));
        product.setCategory(category);
        productRepository.save(product);
        return productMapper.map(product);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody ProductUpdateDTO dto){
        Product product=productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product not found"));
        productMapper.update( dto,product);
        if(dto.getCategoryId().isPresent()) {
            Category category = categoryRepository.findById(dto.getCategoryId().get()).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
            product.setCategory(category);
        }

        productRepository.save(product);

    }

}
