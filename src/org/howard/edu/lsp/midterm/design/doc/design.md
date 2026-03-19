# Redesigned OrderProcessor — CRC Cards

---

## Class: Order
**Responsibilities:**
- Store customer name, email, item, and original price
- Provide access to order data for other classes

**Collaborators:**
- None

---

## Class: TaxCalculator
**Responsibilities:**
- Calculate tax on a given price
- Return the total price after tax

**Collaborators:**
- Order

---

## Class: DiscountCalculator
**Responsibilities:**
- Check if an order qualifies for a discount
- Apply discount to the total price and return the discounted amount

**Collaborators:**
- Order

---

## Class: ReceiptPrinter
**Responsibilities:**
- Format and print a receipt with customer name, item, and final total

**Collaborators:**
- Order
- TaxCalculator
- DiscountCalculator

---

## Class: OrderFileWriter
**Responsibilities:**
- Save a completed order record to a file
- Handle any file I/O errors

**Collaborators:**
- Order

---

## Class: EmailService
**Responsibilities:**
- Send a confirmation email to the customer

**Collaborators:**
- Order

---

## Class: OrderLogger
**Responsibilities:**
- Log the date and time an order was processed

**Collaborators:**
- None

---

## Class: OrderProcessor
**Responsibilities:**
- Coordinate the order processing workflow in the correct sequence:
  calculate tax → apply discount → print receipt → save to file
  → send email → log order

**Collaborators:**
- Order
- TaxCalculator
- DiscountCalculator
- ReceiptPrinter
- OrderFileWriter
- EmailService
- OrderLogger