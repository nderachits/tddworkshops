package shoppingcart;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/5/14
 */
public class PromotionStub implements Promotion {
    private boolean applied;
    private double adjustment;

    public PromotionStub(boolean isApplied, double adjustment) {
        applied = isApplied;
        this.adjustment = adjustment;
    }

    @Override
    public boolean isApplicable(Cart cart) {
        return applied;
    }

    @Override
    public double getAdjustment(Cart cart) {
        return adjustment;
    }

    @Override
    public void apply(Cart cart) {
        cart.setAdjustment(adjustment);
    }
}
