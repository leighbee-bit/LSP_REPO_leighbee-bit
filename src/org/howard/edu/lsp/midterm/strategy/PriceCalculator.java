package org.howard.edu.lsp.midterm.strategy;

/**
 * Calculates the final price for a customer purchase using
 * a pluggable pricing strategy. Replaces the original if-chain
 * design with the Strategy Pattern, making it easy to add new
 * discount behaviors without modifying this class.
 *
 * @author Leighla-Marie Dantes
 */

public class PriceCalculator {
    /** The currently active pricing strategy. */
    private PricingStrategy strategy;

    /**
     * Constructs a PriceCalculator with the given pricing strategy.
     *
     * @param strategy the pricing strategy to apply
     */
    public PriceCalculator(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Updates the pricing strategy at runtime.
     *
     * @param strategy the new pricing strategy to apply
     */
    public void setStrategy(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Calculates the final price using the active pricing strategy.
     *
     * @param price the original price before discount
     * @return the final price after the strategy's discount is applied
     */
    public double calculatePrice(double price) {
        return strategy.calculatePrice(price);
    }
}
