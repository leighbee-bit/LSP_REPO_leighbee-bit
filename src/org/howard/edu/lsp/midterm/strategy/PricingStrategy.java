package org.howard.edu.lsp.midterm.strategy;

/**
 * Interface representing a pricing strategy.
 * Each implementation defines a specific discount behavior.
 *
 * @author Leighla-Marie Dantes
 */
public interface PricingStrategy {

    /**
     * Calculates the final price after applying the strategy's discount.
     *
     * @param price the original price before discount
     * @return the final price after discount
     */
    double calculatePrice(double price);
}