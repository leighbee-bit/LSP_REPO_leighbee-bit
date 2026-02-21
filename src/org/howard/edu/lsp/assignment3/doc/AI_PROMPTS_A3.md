# The AI Prompts for Assignment 3

*1. Prompt: I have a program that reads a csv file with products and prices, applies a discount, and writes the new prices on a new csv.  I would like to split my single class that does this into multiple classes. How would I do this?*

Response: Great idea — separating concerns makes the code much easier to test and maintain. Here's how I'd split it:
1. `Product.java` (Data Model)
Holds the fields for a single product row: productId, name, price, category. This is just a plain data class with getters/setters.
2. `ProductReader.java` (Extract)
Responsible only for reading the CSV and returning a list of Product objects. It handles skipped/invalid rows and tracks that count.
3. `ProductTransformer.java` (Transform)
Takes a Product and applies your business logic — the 10% Electronics discount, the Premium Electronics upgrade, and the price range label. Returns the transformed product.
4. `ProductWriter.java` (Load)
Takes a list of transformed Product objects and writes them to the output CSV, including the header row.
5. `ETLPipeline.java` (Orchestrator)
Your main method stays here, but now it just wires everything together: create a reader, read the products, transform each one, write the results, and print the summary. It holds the row counters.

The flow in `main` would look roughly like:

```
ProductReader reader = new ProductReader(inputPath);
List<Product> products = reader.read(); // returns valid rows, tracks skipped

ProductTransformer transformer = new ProductTransformer();
List<Product> transformed = transformer.transformAll(products);

ProductWriter writer = new ProductWriter(outputPath);
writer.write(transformed);

printSummary(reader.getRowsRead(), transformed.size(), reader.getRowsSkipped(), outputPath);
```
The key principle here is the Single Responsibility Principle — each class has one reason to change. If your discount logic changes, you only touch ProductTransformer. If the CSV format changes, you only touch ProductReader or ProductWriter.
