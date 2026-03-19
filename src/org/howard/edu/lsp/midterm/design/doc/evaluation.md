# Evaluating `OrderProcessor.java`

## The Design

The `OrderProcessor` class is meant to aborb customer data, an item being purchased, adnd the original price of an item. The class is then meant to calculate the taxes on the item, print out a receipt with the customer information. The class then saves the order to a file with error handling, sends a confirmation email, and applies a discount at the incorrect moment. Finally, the class publicly logs when the order was made and prints the current date to the terminal. The class is not separated by any methods, and all of these things run in sequence.

## Design Issues

This class violates multiple of Arthur Riel's Design Heuristics, with the largest ones being *H2.3* and *H2.8*; the class simply handles too many operations at once. Furthermore, there are not enough layers of abstraction such as separated classes or even separate functions for the many functions it attempts to perform. This makes processing more prone to crashing, especially since the operations are out of order.