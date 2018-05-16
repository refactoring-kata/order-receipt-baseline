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

		// print headers
		appendOrderHeader(receiptStr, "======Printing Orders======\n");

		// print date, bill no, customer name
//        output.append("Date - " + order.getDate();
		appendOrder(receiptStr);
//        output.append(order.getCustomerLoyaltyNumber());

		// prints lineItems
		double totalSalesTax = 0d;
		double totalAmount = 0d;
		for (LineItem lineItem : order.getLineItems()) {
			appendOrderItem(receiptStr, lineItem);

			// calculate sales tax @ rate of 10%
            double salesTax = lineItem.totalAmount() * .10;
            totalSalesTax += salesTax;

            // calculate total amount of lineItem = price * quantity + 10 % sales tax
            totalAmount += lineItem.totalAmount() + salesTax;
		}

		// prints the state tax
		appendTotalSalesTax(receiptStr, totalSalesTax);

		// print total amount
		appendTotalAmount(receiptStr, totalAmount);
		return receiptStr.toString();
	}

	private void appendTotalAmount(StringBuilder receiptStr, double totalAmount) {
		receiptStr.append("Total Amount").append('\t').append(totalAmount);
	}

	private void appendTotalSalesTax(StringBuilder receiptStr, double totalSalesTax) {
		receiptStr.append("Sales Tax").append('\t').append(totalSalesTax);
	}

	private StringBuilder appendOrderHeader(StringBuilder receiptStr, String str) {
		return receiptStr.append(str);
	}

	private void appendOrder(StringBuilder receiptStr) {
		receiptStr.append(order.getCustomerName());
		receiptStr.append(order.getCustomerAddress());
	}

	private void appendOrderItem(StringBuilder receiptStr, LineItem lineItem) {
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