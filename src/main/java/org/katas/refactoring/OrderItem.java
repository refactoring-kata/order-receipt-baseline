package org.katas.refactoring;

public class OrderItem {
    private String description;
    private double price;
    private int quantity;

    public OrderItem(String desc, double p, int qty) {
        super();
        this.description = desc;
        this.price = p;
        this.quantity = qty;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    double totalAmount() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return buildOrderItemReceipt();
    }

    public String buildOrderItemReceipt() {
        StringBuilder orderItemsReceipt = new StringBuilder();
        orderItemsReceipt.append(getDescription());
        orderItemsReceipt.append('\t');
        orderItemsReceipt.append(getPrice());
        orderItemsReceipt.append('\t');
        orderItemsReceipt.append(getQuantity());
        orderItemsReceipt.append('\t');
        orderItemsReceipt.append(totalAmount());
        orderItemsReceipt.append('\n');
        return orderItemsReceipt.toString();
    }
}