package MercuryCyclists.CSCI318.Service;

import MercuryCyclists.CSCI318.Model.*;
import MercuryCyclists.CSCI318.Repository.SalesRepo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    private final SalesRepo inStoreRepo;
    private final SalesRepo onLineRepo;
    private final StoreService storeService;
    private final ProductService productService;

    public SaleService(@Qualifier("inStoreSaleRepo") SalesRepo inStoreRepo, @Qualifier("onlineSaleRepo") SalesRepo onLineRepo, StoreService storeService, ProductService productService) {
        this.inStoreRepo = inStoreRepo;
        this.onLineRepo = onLineRepo;
        this.storeService = storeService;
        this.productService = productService;
    }


    public void createSale(Long productId,OnlineSale sale){
        Product product = productService.getProductById(productId);
        sale.setProduct(product);
        onLineRepo.save(sale);
    }

    @Transactional
    public void createSale(Long productId,Long storeId,InStoreSale sale){
        Store store = storeService.getStoreById(storeId);
        Product product = productService.getProductById(productId);
        sale.setStore(store);
        sale.setProduct(product);
        store.addSales(sale);
        inStoreRepo.save(sale);
    }

    public List<Sale> getAllSales() {return inStoreRepo.findAll();}

    public Store getStoreFromSale(Long orderId) {
        InStoreSale inStoreSale = (InStoreSale) inStoreRepo.findById(orderId).orElseThrow(() -> new RuntimeException("Sale: " + orderId + " Not found"));
        return inStoreSale.getStore();
    }

    public Product getProduct(Long orderId) {
        Sale sale = inStoreRepo.findById(orderId).orElse(onLineRepo.findById(orderId).orElseThrow(() -> new RuntimeException("Sale: " + orderId + " Not found")));

        return sale.getProduct();
    }
}
