package model;
/**
 * @author Deac Denisa Bianca
 * Product is the entity representing the products that a warehouse has, being described by an id, name, its price and a qantity
 * */

public class Product {
    private int id;
    private String pname;
    private  double price;
    private int quantity;

    public Product(int id, String pname, double price, int quantity) {
        this.id = id;
        this.pname = pname;
        this.price = price;
        this.quantity = quantity;
    }

    public Product() {
        this.id = 0;
        this.pname = null;
        this.price = 0;
        this.quantity = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
