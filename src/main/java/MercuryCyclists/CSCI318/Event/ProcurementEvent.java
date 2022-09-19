package MercuryCyclists.CSCI318.Event;

import MercuryCyclists.CSCI318.Model.Product;
import MercuryCyclists.CSCI318.Model.Sale;

public class ProcurementEvent {

    private String message;
    private Sale sale;
    private Product product;

    public ProcurementEvent(String message, Sale sale, Product product) {
        this.message = message;
        this.sale = sale;
        this.product = product;
    }
}
