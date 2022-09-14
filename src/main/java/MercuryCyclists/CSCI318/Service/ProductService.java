package MercuryCyclists.CSCI318.Service;

import MercuryCyclists.CSCI318.Model.Part;
import MercuryCyclists.CSCI318.Model.Product;
import MercuryCyclists.CSCI318.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepo productRepo;
    private final PartService partService;

    @Autowired
    public ProductService(ProductRepo productRepo, PartService partService) {
        this.productRepo = productRepo;
        this.partService = partService;
    }

    public void addProduct(Product product) { productRepo.save(product); }

    public Product getProductById(Long id){ return productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product: " + id + " Not found")); }

    public Product updateProductByID(Long id, Product newProduct){
        return productRepo.findById(id).map(product -> {
            product.setName(newProduct.getName());
            product.setPrice(newProduct.getPrice());
            product.setComment(newProduct.getComment());
            product.setPartsList(newProduct.getPartsList());
            product.setSalesList(newProduct.getSalesList());
            return productRepo.save(product);
        }).orElseGet(() ->{
            newProduct.setProductID(id);
            return productRepo.save(newProduct);
        });
    }

    public List<Part> getAllProductParts(Long id) {
        return getProductById(id).getPartsList();
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public void deleteProductById(Long id) { productRepo.deleteById(id);}

    public void addPartToProduct(Long productId, Long partId){
        Product product = getProductById(productId);
        Part part = partService.getPartById(partId);

        part.setProduct(product);
        product.addPart(part);
    }
}
