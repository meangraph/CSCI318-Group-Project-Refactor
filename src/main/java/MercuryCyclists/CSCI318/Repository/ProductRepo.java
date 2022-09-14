package MercuryCyclists.CSCI318.Repository;

import MercuryCyclists.CSCI318.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
}
