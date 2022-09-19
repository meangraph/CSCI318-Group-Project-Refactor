package MercuryCyclists.CSCI318.Service;

import MercuryCyclists.CSCI318.Model.InStoreSale;
import MercuryCyclists.CSCI318.Model.Product;
import MercuryCyclists.CSCI318.Model.Store;
import MercuryCyclists.CSCI318.Repository.StoreRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StoreService {

    private final StoreRepo storeRepo;
    private final ProductService productService;

    public StoreService(StoreRepo storeRepo, ProductService productService) {
        this.storeRepo = storeRepo;
        this.productService = productService;
    }

    public void addStore(Store store) { storeRepo.save(store); }

    public List<Store> getAllStores(){return storeRepo.findAll();}

    public Store getStoreById(Long id){return storeRepo.findById(id).orElseThrow(() -> new RuntimeException("Store: " + id + " Not found"));}


    public List<InStoreSale> getSaleFromStore(Long storeId) {return getStoreById(storeId).getSales();}


}
