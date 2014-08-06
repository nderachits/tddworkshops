package shoppingcart;

/**
 * User: nike
 * Date: 8/2/14
 */
public class CartItem{

    private int productCode;
    private int quantity;

    public CartItem(int productCode, int quantity) {
        this.productCode = productCode;
        this.quantity = quantity;
    }

    public int getProductCode() {
        return productCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
