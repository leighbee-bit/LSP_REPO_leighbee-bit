package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for VIP customers. Applies a 20% discount.
 *
 * @author Leighla-Marie Dantes
 */
public class VIPPricingStrategy implements PricingStrategy {

    /**
     * Returns the price after applying a 20% VIP discount.
     *
     * @param price the original price
     * @return the price with 20% discount applied
     */
    @Override
    public double calculatePrice(double price) {
        return price * 0.80;
    }
}