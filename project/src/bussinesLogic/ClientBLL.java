package bussinesLogic;

import dao.ClientDAO;
import dao.OrderDAO;
import model.Client;
import presentation.ClientGUI;
import javax.swing.*;

/**
 * @author  Deac Denisa Bianca
 * The ClientBLL class contains all the business logic methods. It uses the methods from the ClientDAO and Validators
 * classes, taken as attributes, performes the CRUD operations if all the data is validated
 */
public class ClientBLL {

    private ClientDAO clientDAO;
    private Validators validators;

    public ClientBLL(){
        clientDAO = new ClientDAO();
        validators = new Validators();
    }

    /**
     * Uses the "FindById" method and displays an error message if the client with the given id doesn't exist
     * @param id - the id of the client needed to be found
     * @return - boolean value showing if the client exists
     */
    public boolean findClientById(int id) {
        Client c = clientDAO.findById(id);
        if (c == null) {
            JOptionPane.showMessageDialog(null, "The client with id =" + id + " was not found!" , "INTEGER ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * This method checks if the data taken from the fields in the GUI is valid. In a positive case,
     * it inserts the client created with the read data in the tables
     * @param clientGUI - the graphical interface from which the information is read
     */
    public void checkAndInsert(ClientGUI clientGUI){
        //get data from gui
        JTextField[] tf = clientGUI.getInsertFields();

        //validate for insertion
        int ok = 0;
        if( !validators.validateName(tf[0].getText()) ){
            ok = 1;
        }
        else if( !validators.validateAddress(tf[1].getText()) ){
            ok = 1;
        }
        else if( ! validators.validateEmail(tf[2].getText()) ){
            ok = 1;
        }

        if( ok == 0 ) {
            int lastId = clientDAO.getLastId();
            Client c = new Client(lastId, tf[0].getText(), tf[1].getText(), tf[2].getText());
            clientDAO.createInsertQuery(c);
        }
    }

     /**
      * This method checks if the data taken from the fields in the GUI is valid. In a positive case,
      * it edits the client with the id read with the new read data
      * @param clientGUI - the graphical interface from which the information is read
     */
    public void checkAndEdit(ClientGUI clientGUI){
        JTextField[] tf = clientGUI.getEditFields();
        int id = 0;
        int ok = 0;

        //validate
        if(validators.validateInteger(tf[0].getText(), "Id") ){
            id = Integer.parseInt(tf[0].getText());
            if( !findClientById(id)){
                ok = 1;
            }
        }else if( id == 0){
            ok = 1;
        }
        else if( !validators.validateName(tf[1].getText()) ){
            ok = 1;
        }
        else if( !validators.validateAddress(tf[2].getText()) ){
            ok = 1;
        }
        else if( ! validators.validateEmail(tf[3].getText()) ){
            ok = 1;
        }

        if( ok == 0 ) {
            Client c = new Client(id, tf[1].getText(), tf[2].getText(), tf[3].getText());
            clientDAO.createEditQuery(c);
        }
    }

    /**
     * This method checks if the id taken from the GUI is valid. In a positive case,
     * it deletes the client with the id read from the database and all the orders made by this client
     * @param clientGUI - the graphical interface from which the information is read
     */
    public void checkAndDelete(ClientGUI clientGUI){
        int id = clientGUI.getDeleteField();
        if(findClientById(id)) {
            //delete all orders made by this client
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM \"Order\" where cid ="+ id);

            OrderDAO orderDAO = new OrderDAO();
            orderDAO.deleteOrders(id, "cid");
            clientDAO.createDeleteQuery(id);
        }
    }

    /**
     * This method creates the table with all the data from the Client table
     * @param clientGUI - the graphical interface to which the table is added
     */
    public void view(ClientGUI clientGUI){
        JTable table = clientDAO.createViewAll();
        clientGUI.addTable(table);
    }
}
