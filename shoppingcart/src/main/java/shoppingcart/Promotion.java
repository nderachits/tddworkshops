package shoppingcart;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/5/14
 */
interface Promotion {
    boolean isApplicable(Cart cart);

    double getAdjustment(Cart cart);

    void apply(Cart cart);
}
