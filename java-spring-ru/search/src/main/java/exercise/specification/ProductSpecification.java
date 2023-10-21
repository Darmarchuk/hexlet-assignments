package exercise.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;

@Component
public class ProductSpecification{

    public Specification<Product> build(ProductParamsDTO parmas){
        return withTitleCont(parmas.getTitleCont())
                .and(withPriceGt(parmas.getPriceGt()))
                .and(withPriceLt(parmas.getPriceLt()))
                .and(withRatingGt(parmas.getRatingGt()))
                .and(withCategoryId(parmas.getCategoryId()));
    }


    public Specification<Product> withCategoryId(Long categoryId){
        return (r,q,cb)-> categoryId==null ? cb.conjunction() : cb.equal( r.get("category").get("id"),categoryId);
    }

    public Specification<Product> withTitleCont(String title){
        return (r,q,cb)-> title==null ? cb.conjunction() : cb.like( r.get("title"),"%"+title+"%");
    }

    public Specification<Product> withPriceGt(Integer price){
        return (r,q,cb)-> price==null ? cb.conjunction() : cb.greaterThan( r.get("price"),price);
    }

    public Specification<Product> withPriceLt(Integer price){
        return (r,q,cb)-> price==null ? cb.conjunction() : cb.lessThan( r.get("price"),price);
    }


    public Specification<Product> withRatingGt(Double rating){
        return (r,q,cb)-> rating ==null ? cb.conjunction() : cb.greaterThan( r.get("price"),rating);
    }
}