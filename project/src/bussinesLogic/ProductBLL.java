package bussinesLogic;

import dao.ProductDAO;
import dao.OrderDAO;
import model.Product;
import presentation.ProductGUI;
import javax.swing.*;

/**
 * @author  Deac Denisa Bianca
 * The ProductBLL class contains all the business logic methods for the products. It uses the methods from the ProductDAO and Validators
 * classes, taken as attributes, and performes the CRUD operations if all the data is valid
 */
public class ProductBLL {


    private ProductDAO productDAO;
    private Validators validators;

    public ProductBLL(){
        this.productDAO = new ProductDAO();
        this.validators = new Validators();
    }

    /**
     * Uses the "FindById" method and displays an error message if the product with the given id doesn't exist
     * @param id - the id of the product needed to be found
     * @return - boolean value showing if the client exists
     */
    public boolean findProductById(int id) {
        Product c = productDAO.findById(id);
        if (c == null) {
            JOptionPane.showMessageDialog(null, "The Product with id =" + id + " was not found!" , "INTEGER ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }


    /**
     * This method checks if the data taken from the fields in the GUI is valid. In a positive case,
     * it inserts the product created with the read data in the table
     * @param productGUI - the graphical interface from which the information is read
     */
    public void checkAndInsert(ProductGUI productGUI){
        //get data from gui
        JTextField[] tf = productGUI.getInsertFields();

        //validate for insertion
        int ok = 0;
        if( !validators.validateName(tf[0].getText()) ){
            ok = 1;
        }
        else if( ! validators.validateInteger(tf[2].getText(), "Quantity") ){
            ok = 1;
        }
        else if( !validators.validateDouble(tf[1].getText(), "Price") ){
            ok = 1;
        }


        if( ok == 0 ) {
            int lastId = productDAO.getLastId();
            float price = Float.parseFloat(tf[1].getText());
            int quantity = Integer.parseInt(tf[2].getText());

            Product c = new Product(lastId, tf[0].getText(),price, quantity);
            productDAO.createInsertQuery(c);
        }
    }

    /**
     * This method checks if the data taken from the fields in the GUI is valid. In a positive case,
     * it edits the product having the id read with the new read data
     * @param productGUI - the graphical interface from which the information is read
     */
    public void checkAndEdit(ProductGUI productGUI){
        JTextField[] tf = productGUI.getEditFields();
        int id = 0;
        int ok = 0;

        //validate
        if(validators.validateInteger(tf[0].getText(), "Id") ){
            id = Integer.parseInt(tf[0].getText());
            if( !findProductById(id)){
                ok = 1;
            }
        }else if( id == 0){
            ok = 1;
        }
        else if( !validators.validateName(tf[1].getText()) ){
            ok = 1;
        }
        else if( !validators.validateDouble(tf[2].getText(), "Price") ){
            ok = 1;
        }
        else if( ! validators.validateInteger(tf[3].getText(), "Quantity") ){
            ok = 1;
        }

        if( ok == 0 ) {
            float price = Float.parseFloat(tf[2].getText());
            int quantity = Integer.parseInt(tf[3].getText());

            Product p = new Product(id, tf[1].getText(), price, quantity);
            productDAO.createEditQuery(p);
        }
    }

    /**
     * This method checks if the id taken from the GUI is valid. In a positive case,
     * it deletes the client with the id read from the database and all the orders containing this product
     * @param productGUI - the graphical interface from which the information is read
     */
    public void checkAndDelete(ProductGUI productGUI){
        int id = productGUI.getDeleteField();
        if(findProductById(id)) {
            //delete all orders made with this Product

            OrderDAO orderDAO = new OrderDAO();
            orderDAO.deleteOrders(id, "pid");
            productDAO.createDeleteQuery(id);
        }
    }


    /**
     * This method creates the table with all the data from the Product table
     * @param productGUI -the graphical interface to which the table is added
     */
    public void view(ProductGUI productGUI){
        JTable table = productDAO.createViewAll();
        productGUI.addTable(table);
    }

}
