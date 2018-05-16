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

	public String generateReceipt() {
		StringBuilder receiptStr = new StringBuilder();

		appendOrderHeader(receiptStr);

		appendOrderMain(receiptStr);

		appendOrderItems(receiptStr);

		appendOrderFinance(receiptStr);

		return receiptStr.toString();
	}

	private void appendOrderFinance(StringBuilder receiptStr) {

		double totalSalesTax = caculateTotalTax();

		appendTotalSalesTax(receiptStr, totalSalesTax);

		appendTotalAmount(receiptStr, caculateTotalAmount(totalSalesTax));
	}

	private double caculateTotalAmount(double totalSalesTax) {
		double totalAmount = 0d;
		for (LineItem lineItem : order.getLineItems()) {
			totalAmount += lineItem.totalAmount();
		}
		totalAmount += totalSalesTax;
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

	private void appendOrderMain(StringBuilder receiptStr) {
		receiptStr.append(order.getCustomerName());
		receiptStr.append(order.getCustomerAddress());
	}

}