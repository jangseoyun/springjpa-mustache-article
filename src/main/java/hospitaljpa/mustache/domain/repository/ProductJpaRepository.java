package hospitaljpa.mustache.domain.repository;

import hospitaljpa.mustache.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<Product, Long> {

}
