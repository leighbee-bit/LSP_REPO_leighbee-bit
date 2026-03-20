package org.howard.edu.lsp.midterm.strategy;

/**
 * Driver class demonstrating the Strategy Pattern implementation
 * of PriceCalculator with different customer pricing strategies.
 *
 * @author Leighla-Marie Dantes
 */
public class Driver {

    /**
     * Main method that tests each pricing strategy with a purchase price of 100.0.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        double price = 100.0;

        PriceCalculator calculator = new PriceCalculator(new RegularPricingStrategy());
        System.out.println("REGULAR: " + calculator.calculatePrice(price));

        calculator.setStrategy(new MemberPricingStrategy());
        System.out.println("MEMBER: " + calculator.calculatePrice(price));

        calculator.setStrategy(new VIPPricingStrategy());
        System.out.println("VIP: " + calculator.calculatePrice(price));

        calculator.setStrategy(new HolidayPricingStrategy());
        System.out.println("HOLIDAY: " + calculator.calculatePrice(price));
    }
}