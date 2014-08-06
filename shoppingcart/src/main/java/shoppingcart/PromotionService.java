package shoppingcart;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/5/14
 */
public class PromotionService {

    private List<Promotion> promotions = new ArrayList<>();

    public void addPromotion(Promotion promotion) {
        promotions.add(promotion);
    }

    public void applyToCart(Cart cart) {
        for (int i = 0; i < promotions.size(); i++) {
            Promotion promotion = promotions.get(i);
            if(promotion.isApplicable(cart)) {
                promotion.apply(cart);
                break;
            } else {
                promotion.cancel(cart);
            }
        }
    }
}
