package org.howard.edu.lsp.assignment2;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ETLPipeline {

    public static void main(String[] args) {
        int rowsRead = 0;
        int rowsTransformed = 0;
        int rowsSkipped = 0;

        // Look in src/data instead
        Path dataDir = Paths.get(System.getProperty("user.dir")).resolve("src").resolve("data");
        Path inputPath = dataDir.resolve("products.csv");
        Path outputPath = dataDir.resolve("transformed_products.csv");

        // Check if input file exists
        if (!Files.exists(inputPath)) {
            System.out.println("Error: Input file not found at " + inputPath.toString());
            return;
        }

        // Ensure output directory exists
        try {
            Files.createDirectories(dataDir);
        } catch (IOException e) {
            System.out.println("Error: Could not create data directory.");
            return;
        }

        try (
                BufferedReader reader = Files.newBufferedReader(inputPath, StandardCharsets.UTF_8);
                BufferedWriter writer = Files.newBufferedWriter(outputPath, StandardCharsets.UTF_8)) {
            // Write header row
            writer.write("ProductID,Name,Price,Category,PriceRange");
            writer.newLine();

            String line = reader.readLine(); // skip header
            if (line == null) {
                printSummary(rowsRead, rowsTransformed, rowsSkipped, outputPath);
                return;
            }

            while ((line = reader.readLine()) != null) {
                rowsRead++;

                if (line.trim().isEmpty()) {
                    rowsSkipped++;
                    continue;
                }

                String[] fields = line.split(",");
                if (fields.length != 4) {
                    rowsSkipped++;
                    continue;
                }

                try {
                    int productId = Integer.parseInt(fields[0].trim());
                    String name = fields[1].trim().toUpperCase();
                    BigDecimal price = new BigDecimal(fields[2].trim());
                    String category = fields[3].trim();

                    String originalCategory = category;

                    // 10% discount if Electronics
                    if (category.equals("Electronics")) {
                        price = price.multiply(new BigDecimal("0.9"));
                    }

                    // Round HALF_UP
                    price = price.setScale(2, RoundingMode.HALF_UP);

                    // Premium Electronics rule
                    if (price.compareTo(new BigDecimal("500.00")) > 0 &&
                            originalCategory.equals("Electronics")) {
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

                    writer.write(productId + "," +
                            name + "," +
                            price.toPlainString() + "," +
                            category + "," +
                            priceRange);
                    writer.newLine();

                    rowsTransformed++;

                } catch (NumberFormatException e) {
                    rowsSkipped++;
                }
            }

        } catch (IOException e) {
            System.out.println("Error processing files.");
            return;
        }

        printSummary(rowsRead, rowsTransformed, rowsSkipped, outputPath);
    }

    private static void printSummary(int read, int transformed, int skipped, Path outputPath) {
        System.out.println("Run Summary:");
        System.out.println("Rows read: " + read);
        System.out.println("Rows transformed: " + transformed);
        System.out.println("Rows skipped: " + skipped);
        System.out.println("Output written to: " + outputPath.toString());
    }
}
