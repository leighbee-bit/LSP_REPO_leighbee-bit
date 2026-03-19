# Evaluating `PriceCalculator`

## The Design
The `PriceCalculator` class uses a chain of `if` statements to determine pricing logic based on the customer type. While this works for the current four types, it creates significant maintainability and extensibility problems as the system grows.

## Design Issues
The most immediate issue is that adding a new customer type — say, `"STUDENT"` or `"EMPLOYEE"` — requires directly modifying the `calculatePrice()` method every single time. This violates the Open/Closed Principle, which states that a class should be open for extension but closed for modification. Every change to this method risks introducing bugs that affect the already-working customer types.

The class also violates *H2.8* and *H2.3* (the distribution of behavior across the system). All pricing strategies are crammed into a single method, meaning `PriceCalculator` is responsible for knowing the discount logic of every customer type simultaneously. Ideally, each pricing strategy should be its own abstraction.

Finally, the design is not scalable. If pricing logic becomes more complex — for example, if `VIP` customers get a different discount on weekends, or `MEMBER` discounts stack with holiday sales — the if chain grows into something nearly impossible to read or test reliably. There is no way to test one pricing strategy in isolation without running through the entire method.
