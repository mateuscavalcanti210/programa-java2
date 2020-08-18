package model.entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.enums.OrderStatus;

public class Order {
	
	private static SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private static SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
	
	private Date moment;
	private OrderStatus status;
	
	private Client client;
	private List<OrderItem> items = new ArrayList<OrderItem>();
	
	public Order() {
	}
	
	public Order(Date moment, OrderStatus status, Client client) {
		this.moment = moment;
		this.status = status;
		this.client = client;
	}

	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<OrderItem> getItems() {
		return items;
	}
	
	public void addItem(OrderItem item) {
		items.add(item);
	}
	
	public void removeItem(OrderItem item) {
		items.remove(item);
	}

	public Double total() {
		double sum = 0.0;
		for (OrderItem x : items) {
			sum += x.subTotal();
		}
		return sum;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Order moment: ");
		sb.append(sdf1.format(moment));
		sb.append("\nOrder status: ");
		sb.append(status + "\n");
		sb.append("Client: ");
		sb.append(client.getName() + " (");
		sb.append(sdf2.format(client.getBirthDate()) + ") - ");
		sb.append(client.getEmail());
		sb.append("\nOrder items:\n");
		for (OrderItem x : items) {
			sb.append(x.getProduct().getName() + ", $");
			sb.append(String.format("%.2f", x.getPrice()));
			sb.append(", Quantity: ");
			sb.append(x.getQuantity());
			sb.append(", Subtotal: $");
			sb.append(String.format("%.2f", x.subTotal()) + "\n");
		}
		sb.append("Total price: $");
		sb.append(String.format("%.2f", total()));
		return sb.toString();
	}

}