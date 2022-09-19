package MercuryCyclists.CSCI318.API;

import MercuryCyclists.CSCI318.Model.InStoreSale;
import MercuryCyclists.CSCI318.Model.Store;
import MercuryCyclists.CSCI318.Service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("api/v1/store")//localhost:8080/api/v1/store
@RestController
public class StoreController {

    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping
    public void createStore(@Valid @RequestBody Store store) {
        storeService.addStore(store);
    }

    @GetMapping
    public List<Store> getAllStores() {return storeService.getAllStores();}

    @GetMapping(path = "{ID}")
    public Store getStoreById(@PathVariable("ID") Long id) { return storeService.getStoreById(id);}

    @GetMapping(path = "/{storeID}/sales")
    public List<InStoreSale> getSaleFromStore(@PathVariable("storeID") Long store){return storeService.getSaleFromStore(store);}


}
