package org.howard.edu.lsp.assignment3;

/**
 * This class is used for the sole purpose of allowing each
 * of the created classes to interact with each other and produce the
 * desired result. The class first locates the directory where all data
 * will be, and established the input and output paths for the files, with
 * error handling. Each object is created and utilized in a try-catch system
 * following the error handling, with a final void function printing a 
 * summary of what the program has done when run successfully.
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ETLPipeline {

    public static void main(String[] args) {
        Path dataDir = Paths.get(System.getProperty("user.dir")).resolve("data");
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

        try {
            ProductReader reader = new ProductReader(inputPath);
            List<Product> products = reader.read();

            ProductTransformer transformer = new ProductTransformer();
            List<Product> transformed = transformer.transformAll(products);

            ProductWriter writer = new ProductWriter(outputPath);
            writer.write(transformed);

            printSummary(reader.getRowsRead(), transformed.size(), reader.getRowsSkipped(), outputPath);

        } catch (IOException e) {
            System.out.println("Error processing files.");
        }
    }

    private static void printSummary(int read, int transformed, int skipped, Path outputPath) {
        System.out.println("Run Summary:");
        System.out.println("Rows read: " + read);
        System.out.println("Rows transformed: " + transformed);
        System.out.println("Rows skipped: " + skipped);
        System.out.println("Output written to: " + outputPath.toString());
    }
}