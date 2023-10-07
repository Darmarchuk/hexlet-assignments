package exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

import exercise.model.Product;

import org.springframework.data.domain.Sort;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    Optional<Product> findById(Long aLong);

    @Query(value= "Select * from products p where price >:min and price<:max order by price",nativeQuery = true)
    List<Product> getByMinMaxPrice(@Param("min")int min, @Param("max") int max );


    @Query(value= "Select * from products p where price >:min  order by price",nativeQuery = true)
    List<Product> getByMinPrice(@Param("min")int min );


    @Query(value= "Select * from products p where price <:max  order by price",nativeQuery = true)
    List<Product> getByMaxPrice(@Param("max")int max );
}
