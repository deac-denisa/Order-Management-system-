package model;

/**
 * @author Deac Denisa Bianca
 * Order is the entity describing an existing order of a client that contains several products of the same type, identified by their id
 * */

public class Order {
    private int id;
    private int cid;
    private int pid;
    private int quantity;

    public Order(int id, int cid, int pid, int quantity) {
        this.id = id;
        this.cid = cid;
        this.pid = pid;
        this.quantity = quantity;
    }
    
    public Order() {
    	   this.id = 0;
           this.cid = 0;
           this.pid = 0;
           this.quantity = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCid() {
        return cid;
    }

    public int getPid() {
        return pid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
