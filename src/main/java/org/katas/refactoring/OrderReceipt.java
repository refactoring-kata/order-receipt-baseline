package org.katas.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 * 
 */
public class OrderReceipt {
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
	}

	public String printReceipt() {
		StringBuilder receiptStr = new StringBuilder();

		appendOrderHeader(receiptStr);

		appendOrder(receiptStr);

		appendOrderItems(receiptStr);
		double totalSalesTax = 0d;
		double totalAmount = 0d;

		for (LineItem lineItem : order.getLineItems()) {

			double salesTax = lineItem.totalAmount() * .10;
			totalSalesTax += salesTax;

			// calculate total amount of lineItem = price * quantity + 10 % sales tax
			totalAmount += lineItem.totalAmount() + salesTax;
		}

		appendTotalSalesTax(receiptStr, totalSalesTax);

		appendTotalAmount(receiptStr, totalAmount);
		return receiptStr.toString();
	}

	private void appendOrderItems(StringBuilder receiptStr) {
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
	}

	private void appendTotalAmount(StringBuilder receiptStr, double totalAmount) {
		receiptStr.append("Total Amount").append('\t').append(totalAmount);
	}

	private void appendTotalSalesTax(StringBuilder receiptStr, double totalSalesTax) {
		receiptStr.append("Sales Tax").append('\t').append(totalSalesTax);
	}

	private StringBuilder appendOrderHeader(StringBuilder receiptStr) {
    	String orderHeaderStr = "======Printing Orders======\n";
		return receiptStr.append(orderHeaderStr);
	}

	private void appendOrder(StringBuilder receiptStr) {
		receiptStr.append(order.getCustomerName());
		receiptStr.append(order.getCustomerAddress());
	}

}