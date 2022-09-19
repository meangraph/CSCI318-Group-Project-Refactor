package MercuryCyclists.CSCI318.Repository;

import MercuryCyclists.CSCI318.Model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepo extends JpaRepository<Store,Long> {
}
