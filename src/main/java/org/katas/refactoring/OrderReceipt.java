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

        receiptStr.append(buildMainOrderReceipt());

        return receiptStr.toString();
    }

    private StringBuilder buildMainOrderReceipt() {
        StringBuilder mainOrderReceipt = new StringBuilder();

        mainOrderReceipt.append(buildOrderBaseInfo());

        mainOrderReceipt.append(appendOrderItems());

        mainOrderReceipt.append("Sales Tax").append('\t').append(caculateTotalTax());

        mainOrderReceipt.append("Total Amount").append('\t').append(caculateTotalAmount());
        return mainOrderReceipt;
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
        StringBuilder orderItemsReceipt = new StringBuilder();
        for (LineItem lineItem : order.getLineItems()) {
            orderItemsReceipt.append(lineItem.getDescription());
            orderItemsReceipt.append('\t');
            orderItemsReceipt.append(lineItem.getPrice());
            orderItemsReceipt.append('\t');
            orderItemsReceipt.append(lineItem.getQuantity());
            orderItemsReceipt.append('\t');
            orderItemsReceipt.append(lineItem.totalAmount());
            orderItemsReceipt.append('\n');
        }
        return orderItemsReceipt;
    }

    private StringBuilder buildOrderBaseInfo() {
        StringBuilder orderInfpReceipt = new StringBuilder();
        orderInfpReceipt.append(order.getCustomerName());
        orderInfpReceipt.append(order.getCustomerAddress());
        return orderInfpReceipt;
    }

}