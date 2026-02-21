# The AI Prompts for Assignment 3

**1. Prompt: I have a program that reads a csv file with products and prices, applies a discount, and writes the new prices on a new csv.  I would like to split my single class that does this into multiple classes. How would I do this?**

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

*While writing these reflection documents, I was unfamiliar with Markdown syntax and docs in general. These resulted in these questions:*

**2. This looks amazing. Can you quickly tell me what is a .md file?**

A `.md` file is a **Markdown** file — a plain text file written in Markdown, which is a lightweight formatting syntax that lets you add styling (like headers, bold text, lists, links, etc.) using simple characters. For example, `# Title` becomes a large heading, and `**bold**` renders as bold.
They're commonly used for documentation, README files in code projects, and notes, because they're easy to write in any text editor but can also be rendered into nicely formatted HTML by tools like GitHub, VS Code, and many others.

**3. Can you give me the syntax for formatting text in these files?**

Here's a quick rundown of the most common Markdown syntax:
**Headings**
```
# Heading 1
## Heading 2
### Heading 3
```
**Bold and Italic**
```
**bold**
*italic*
***bold and italic***
```

**Lists**
```
- Unordered item
- Another item

1. Ordered item
2. Another item
```

**Links and Images**
```
[Link text](https://example.com)
![Alt text](image.png)
```

**Code**
```
`inline code`
```code block```
```
```
**Blockquotes**

This is a quote


**Horizontal Rule**


**Tables**
Column 1Column 2Cell 1Cell 2

**Strikethrough** (supported in most renderers)
strikethrough

That covers the vast majority of what you'll use day to day. The great thing about Markdown is that even if it's not rendered, the raw text is still very readable on its own.
