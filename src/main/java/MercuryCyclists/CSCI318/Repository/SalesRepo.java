package MercuryCyclists.CSCI318.Repository;

import MercuryCyclists.CSCI318.Model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface SalesRepo extends JpaRepository<Sale, Long> {
}
