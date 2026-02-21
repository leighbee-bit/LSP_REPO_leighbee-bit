package org.howard.edu.lsp.assignment3;

/**
 * After the Product object was created and the data was parsed into
 * models, the list of Products can then be transformed with the discount
 * outlined in the assignment. 
 */

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;


public class ProductTransformer {

    public List<Product> transformAll(List<Product> products) {
        return products.stream()
                .map(this::transform)
                .collect(Collectors.toList());
    }

    private Product transform(Product product) {
        BigDecimal price = product.getPrice();
        String category = product.getCategory();

        // Apply 10% discount if Electronics
        if (category.equals("Electronics")) {
            price = price.multiply(new BigDecimal("0.9"));
        }

        // Round HALF_UP
        price = price.setScale(2, RoundingMode.HALF_UP);

        // Upgrade to Premium Electronics if price > 500 after discount
        if (price.compareTo(new BigDecimal("500.00")) > 0 && category.equals("Electronics")) {
            category = "Premium Electronics";
        }

        // Determine price range
        String priceRange;
        if (price.compareTo(new BigDecimal("10.00")) <= 0) {
            priceRange = "Low";
        } else if (price.compareTo(new BigDecimal("100.00")) <= 0) {
            priceRange = "Medium";
        } else if (price.compareTo(new BigDecimal("500.00")) <= 0) {
            priceRange = "High";
        } else {
            priceRange = "Premium";
        }

        product.setPrice(price);
        product.setCategory(category);
        product.setPriceRange(priceRange);

        return product;
    }
}