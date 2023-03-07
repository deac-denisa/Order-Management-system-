package bussinesLogic;

import dao.ClientDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import model.Order;
import model.Product;
import presentation.OrderGUI;
import javax.swing.*;

/**
 * @author  Deac Denisa Bianca
 * The OrderBLL class contains all the business logic methods for Orders. It uses the methods from the OrderDAO and Validators
 * classes, taken as attributes, performes the operations if all the data is validated
 */
public class OrderBLL {


    private OrderDAO orderDAO;
    private ClientDAO clientDAO;
    private ProductDAO productDAO;
    private Validators validators;

    public OrderBLL(){
        this.orderDAO = new OrderDAO();
        this.clientDAO = new ClientDAO();
        this.productDAO = new ProductDAO();
        this.validators = new Validators();
    }

    /**
     * This method checks if the data taken from the fields in the GUI is valid. In a positive case,
     * it inserts the order created with the read data in the tables
     * @param orderGUI - the graphical interface from which the information is read
     */
    public void checkAndInsert(OrderGUI orderGUI){
        //get data from gui
        JTextField[] tf = orderGUI.getInsertFields();

        //validate for insertion
        int ok = 0;
        if( !validators.validateInteger(tf[0].getText(), "Client ID") ){
            ok = 1;
        }
        else if( !validators.validateInteger(tf[1].getText(), "Product ID") ){
            ok = 1;
        }
        else if( ! validators.validateInteger(tf[2].getText(), "Quantity") ){
            ok = 1;
        }

        if( ok == 0 ) {
            int lastId = orderDAO.getLastId();
            int cid = Integer.parseInt(tf[0].getText());
            int pid = Integer.parseInt(tf[1].getText());
            int quantity = Integer.parseInt(tf[2].getText());
            int stock;
            ClientBLL clientBLL = new ClientBLL();
            ProductBLL productBLL = new ProductBLL();

            if( productBLL.findProductById(pid) && clientBLL.findClientById(cid)) {
                ProductDAO productDAO = new ProductDAO();
                Product p = productDAO.findById(pid);
                stock = p.getQuantity();
                if(validators.validateStock(quantity, stock)) {
                    Order o = new Order(lastId, cid, pid, quantity);
                    orderDAO.createInsertQuery(o);
                    p.setQuantity( stock - quantity);
                    productDAO.createEditQuery(p);
                    orderDAO.createBill(o);
                }

            }
        }
    }


    /**
     * This method creates the tables with all the data from the Client and Product tables
     * @param orderGUI - the graphical interface to which the tables are added
     */
    public void view(OrderGUI orderGUI){
        JTable tableClients = clientDAO.createViewAll();
        JTable tableProducts = productDAO.createViewAll();
        orderGUI.addClientsTable(tableClients);
        orderGUI.addProductTable(tableProducts);
    }

}
