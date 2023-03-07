package model;
/**
 * @author Deac Denisa Bianca
 *
 * Client -> is the entities that represent the person enrolled by the manager in the warehouse, with the possibility of having an order
 */

public class Client {
    private int id;
    private String cname;
    private String address;
    private String email;

    public Client(int id, String name, String address, String email) {
        this.id = id;
        this.cname = name;
        this.address = address;
        this.email = email;
    }
    
    public Client() {
    	 this.id = 0;
         this.cname = null;
         this.address = null;
         this.email = null;
    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
