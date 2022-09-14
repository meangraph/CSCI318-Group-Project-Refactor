package MercuryCyclists.CSCI318.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;


//Might need to redo this one. To make it easier, i put a bool flag called "online" to denote weather it's online or offline.
@Entity(name = "Sale")
public class Sale {

    String productName;
    int quantity;
    boolean online;

    @NotBlank
    @Id
    Long receiptNumber;
    //instore vars

    @ManyToOne
    @JoinColumn(name = "store_id")
    @JsonIgnore
    Store store;

    //online vars
    String customerName;

    //In person constructor
    public Sale(@JsonProperty String productName, @JsonProperty int quantity, @JsonProperty Long receiptNumber, Store store){
        this.productName = productName;
        this.quantity = quantity;
        this.receiptNumber = receiptNumber;
        this.store = store;
        online = false;
    }

    public Sale(){}

    //Online constructor
    public Sale(@JsonProperty String productName, @JsonProperty int quantity, @JsonProperty String customerName, @JsonProperty Long reciptNumber){
        this.productName = productName;
        this.quantity = quantity;
        this.customerName = customerName;
        this.receiptNumber = reciptNumber;
        online = true;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isOnline() {
        return online;
    }

    public Long getReceiptNumber() {
        return receiptNumber;
    }

    public Store getStore() {
        return store;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getProductName() {
        return productName;
    }
}
