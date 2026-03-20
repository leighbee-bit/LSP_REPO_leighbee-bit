package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for holiday promotions. Applies a 15% discount.
 *
 * @author Leighla-Marie Dantes
 */
public class HolidayPricingStrategy implements PricingStrategy {

    /**
     * Returns the price after applying a 15% holiday discount.
     *
     * @param price the original price
     * @return the price with 15% discount applied
     */
    @Override
    public double calculatePrice(double price) {
        return price * 0.85;
    }
}