package MercuryCyclists.CSCI318.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Entity(name = "Product")
public class Product {
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productID;
    private double price;
    String comment;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Part> partsList = new ArrayList<>();

    @OneToMany
    @JsonIgnore
    private List<Sale> salesList = new ArrayList<>();

    public Product(@JsonProperty String name, @JsonProperty Long productID, @JsonProperty double price, @JsonProperty String comment) {
        this.name = name;
        this.productID = productID;
        this.price = price;
        this.comment = comment;
    }

    public List<Part> getPartsList() {
        return partsList;
    }

    public void setPartsList(List<Part> partsList) {
        this.partsList = partsList;
    }

    public List<Sale> getSalesList() {
        return salesList;
    }

    public void setSalesList(List<Sale> salesList) {
        this.salesList = salesList;
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public List<Part> getParts() {return partsList;
    }

    public Long getProductID() {
        return productID;
    }

    public double getPrice() {
        return price;
    }

    public String getComment() {
        return comment;
    }

    public void addPart(Part part) {
        partsList.add(part);
    }

    public void removePart(Part part) { partsList.remove(part);}


    public void addSales(Sale sale) {
       salesList.add(sale);
    }

    public void removeSale(Sale sale) {
        salesList.remove(sale);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
