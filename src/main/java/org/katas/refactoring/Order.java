package org.katas.refactoring;

import java.util.List;

public class Order {
    String customerName;
    String address;
    List<OrderItem> orderItem;

    public Order(String nm, String addr, List<OrderItem> li) {
        this.customerName = nm;
        this.address = addr;
        this.orderItem = li;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return address;
    }

    public List<OrderItem> getLineItems() {
        return orderItem;
    }

    public StringBuilder buildMainOrderReceipt() {
        StringBuilder mainOrderReceipt = new StringBuilder();

        mainOrderReceipt.append(buildOrderBaseInfo());

        mainOrderReceipt.append(buildOrderItems());

        mainOrderReceipt.append("Sales Tax").append('\t').append(caculateTotalTax());

        mainOrderReceipt.append("Total Amount").append('\t').append(caculateTotalAmount());
        return mainOrderReceipt;
    }

    private double caculateTotalTax() {
        double totalSalesTax = 0d;
        for (OrderItem orderItem : getLineItems()) {

            double salesTax = orderItem.totalAmount() * .10;
            totalSalesTax += salesTax;
        }
        return totalSalesTax;
    }

    private double caculateTotalAmount() {
        double totalAmount = 0d;
        for (OrderItem orderItem : getLineItems()) {
            totalAmount += orderItem.totalAmount();
        }
        totalAmount += caculateTotalTax();
        return totalAmount;
    }

    private StringBuilder buildOrderBaseInfo() {
        StringBuilder orderInfpReceipt = new StringBuilder();
        orderInfpReceipt.append(getCustomerName());
        orderInfpReceipt.append(getCustomerAddress());
        return orderInfpReceipt;
    }

    private StringBuilder buildOrderItems() {
        StringBuilder orderItemsReceipt = new StringBuilder();
        for (OrderItem orderItem : getLineItems()) {
            orderItemsReceipt.append(orderItem.toString());
        }
        return orderItemsReceipt;
    }

}
