package org.howard.edu.lsp.assignment3;

/**
 * The second major change that came to the Assignment 2 project was creating
 * a class specifically for reading through the input file and parsing the data
 * into the Product model that was created, and returns a list of Java
 * translated objects from the input file.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class ProductReader {
    private final Path inputPath;
    private int rowsRead;
    private int rowsSkipped;


    //Constructor
    public ProductReader(Path inputPath) {
        this.inputPath = inputPath;
        this.rowsRead = 0;
        this.rowsSkipped = 0;
    }

    /**Parses through the input file and creates Product objects to be referenced
     * in other classes. 
     * @param: None, utilizes file path from object attributes
     * @return: A list of Product objects
     * @throws: IOException, given the file path does not exist.
     * */
    public List<Product> read() throws IOException {
        List<Product> products = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(inputPath, StandardCharsets.UTF_8)) {
            String line = reader.readLine(); // skip header
            if (line == null) return products;

            while ((line = reader.readLine()) != null) {
                this.rowsRead++;

                if (line.trim().isEmpty()) {
                    this.rowsSkipped++;
                    continue;
                }

                String[] fields = line.split(",");
                if (fields.length != 4) {
                    this.rowsSkipped++;
                    continue;
                }

                try {
                    int productId = Integer.parseInt(fields[0].trim());
                    String name = fields[1].trim().toUpperCase();
                    BigDecimal price = new BigDecimal(fields[2].trim());
                    String category = fields[3].trim();

                    //Actively creating the new Product object
                    products.add(new Product(productId, name, price, category));
                } catch (NumberFormatException e) {
                    this.rowsSkipped++;
                }
            }
        }

        return products;
    }

    //Getters and setters
    public int getRowsRead() { return rowsRead; }
    public int getRowsSkipped() { return rowsSkipped; }
}