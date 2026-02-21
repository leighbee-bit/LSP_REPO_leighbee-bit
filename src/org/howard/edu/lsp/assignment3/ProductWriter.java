package org.howard.edu.lsp.assignment3;

/**
 * Finally, after the discounts are applied to the Products, these 
 * Products are rewritten to the output file in the same format they were
 * received in.
 */

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class ProductWriter {
    private final Path outputPath;

    public ProductWriter(Path outputPath) {
        this.outputPath = outputPath;
    }

    public void write(List<Product> products) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(outputPath, StandardCharsets.UTF_8)) {
            writer.write("ProductID,Name,Price,Category,PriceRange");
            writer.newLine();

            for (Product product : products) {
                writer.write(
                        product.getProductId() + "," +
                        product.getName() + "," +
                        product.getPrice().toPlainString() + "," +
                        product.getCategory() + "," +
                        product.getPriceRange()
                );
                writer.newLine();
            }
        }
    }
}