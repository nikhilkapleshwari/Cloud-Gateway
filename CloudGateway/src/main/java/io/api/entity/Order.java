package io.api.entity;


public class Order {

    public Order() {
	super();
    }

    private int id;
    private String name;
    private int qty;
    private double price;
    
    public Order(int id, String name, int qty, double price) {
	super();
	this.id = id;
	this.name = name;
	this.qty = qty;
	this.price = price;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getQty() {
        return qty;
    }
    public void setQty(int qty) {
        this.qty = qty;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
