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
        Promotion promotionToApply = null;
        double highestDiscount = 0d;
        for (int i = 0; i < promotions.size(); i++) {
            Promotion promotion = promotions.get(i);
            if(promotion.isApplicable(cart)) {
                double discount = promotion.getDiscount(cart);
                if(highestDiscount < promotion.getDiscount(cart)) {
                    promotionToApply = promotion;
                    highestDiscount = discount;
                }
            }
            promotion.cancel(cart);
        }
        if(promotionToApply != null) {
            promotionToApply.apply(cart);
        }
    }
}
