package org.howard.edu.lsp.assignment3;

/**
 * The first step that came with the refactoring of Assignment 2 would be creating an individual
 * object for a Product. That way, its attributes can be easily referenced and changed in other
 * parts of this program.
 */


import java.math.BigDecimal;

public class Product {

    //All attributes of a product object
    private int productId;
    private String name;
    private BigDecimal price;
    private String category;
    private String priceRange;

    //Constructor
    public Product(int productId, String name, BigDecimal price, String category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    //Getters and setters
    public int getProductId() { return productId; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }
    public String getCategory() { return category; }
    public String getPriceRange() { return priceRange; }

    public void setPrice(BigDecimal price) { this.price = price; }
    public void setCategory(String category) { this.category = category; }
    public void setPriceRange(String priceRange) { this.priceRange = priceRange; }
}