package org.katas.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String generateReceipt() {
        StringBuilder receiptStr = new StringBuilder();

        receiptStr.append("======Printing Orders======\n");

        receiptStr.append(buildOrderMainInfo());

        receiptStr.append(appendOrderItems());

        receiptStr.append("Sales Tax").append('\t').append(caculateTotalTax());

        receiptStr.append("Total Amount").append('\t').append(caculateTotalAmount());

        return receiptStr.toString();
    }

    private double caculateTotalAmount() {
        double totalAmount = 0d;
        for (LineItem lineItem : order.getLineItems()) {
            totalAmount += lineItem.totalAmount();
        }
        totalAmount += caculateTotalTax();
        return totalAmount;
    }

    private double caculateTotalTax() {
        double totalSalesTax = 0d;
        for (LineItem lineItem : order.getLineItems()) {

            double salesTax = lineItem.totalAmount() * .10;
            totalSalesTax += salesTax;
        }
        return totalSalesTax;
    }

    private StringBuilder appendOrderItems() {
        StringBuilder receiptStr = new StringBuilder();
        for (LineItem lineItem : order.getLineItems()) {
            receiptStr.append(lineItem.getDescription());
            receiptStr.append('\t');
            receiptStr.append(lineItem.getPrice());
            receiptStr.append('\t');
            receiptStr.append(lineItem.getQuantity());
            receiptStr.append('\t');
            receiptStr.append(lineItem.totalAmount());
            receiptStr.append('\n');
        }
        return receiptStr;
    }

    private StringBuilder buildOrderMainInfo() {
        StringBuilder receiptStr = new StringBuilder();
        receiptStr.append(order.getCustomerName());
        receiptStr.append(order.getCustomerAddress());
        return receiptStr;
    }

}