package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for member customers. Applies a 10% discount.
 *
 * @author Leighla-Marie Dantes
 */
public class MemberPricingStrategy implements PricingStrategy {

    /**
     * Returns the price after applying a 10% member discount.
     *
     * @param price the original price
     * @return the price with 10% discount applied
     */
    @Override
    public double calculatePrice(double price) {
        return price * 0.90;
    }
}