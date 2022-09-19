package MercuryCyclists.CSCI318.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import java.util.Random;

@Entity
@JsonPropertyOrder({"id"})
public class InStoreSale extends Sale{

    private Long receiptNumber;
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    public InStoreSale(@JsonProperty("productName")String productName,
                       @JsonProperty("quantity") int quantity) {
        super(productName, quantity);
        this.receiptNumber = generateReceiptNumber();
    }

    public InStoreSale() {

    }

    public Long getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(Long receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public Long generateReceiptNumber() {
        Long receiptNumber = new Random().nextLong();
        return receiptNumber;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
