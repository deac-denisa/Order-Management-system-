package bussinesLogic;

import javax.swing.*;

/**
 * This class contains methods that validate attributes of certain objects using matching pattern. A string is checked
 * if it has a certain form and, in case of wrong data, displays an error message.
 */
public class Validators {

    /**
     * Method that checks if a string is integer and displays an error dialog message in case it's not integer
     * @param nr - the number needed to check if it's integer
     * @param categ - what does that integer represents : Id / Quantity
     * @return boolean value showing if the given string was an integer or not
     */
    public boolean validateInteger( String nr, String  categ){
        if(nr .equals("")){
            JOptionPane.showMessageDialog(null, categ+" missing", "INTEGER ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(!nr.matches("[0-9]+")){
            JOptionPane.showMessageDialog(null, categ+" is not integer", "INTEGER ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Method that checks if a string is a float number and displays an error dialog message in case it's not
     *  @param nr - the number needed to check if it's float
     *  @param categ - what does that integer represents : ex: Price 
     *  @return boolean value showing if the given string was valid
     */
    public boolean validateDouble( String nr, String categ){
        if(nr.equals("")){
            JOptionPane.showMessageDialog(null, categ+" missing", "DECIMAL NUMBER ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(!nr.matches("^[0-9](\\.[0-9]+)?$") && !nr.matches("[0-9]+")){
            JOptionPane.showMessageDialog(null, categ+" is not a valid number", "DECIMAL NUMBER ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Method that checks if a string is a float number and displays an error dialog message in case it's not
     * @param name - string representing the name to be checked to contain only alphabetic characters
     * @return boolean value showing if the given string was valid
     */
    public boolean validateName(String name) {

        if (name.equals("")) {
            JOptionPane.showMessageDialog(null, "Name missing", "NAME ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!name.matches("[ a-zA-Z]+")){
            System.out.println(name);
            JOptionPane.showMessageDialog(null, "Not a valid name", "NAME ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Method that checks if a string has the form needed to be an email( to contain @yahoo.com/ @gmail.com) and if not
     * displays an error dialog message
     * @param email - string that has to have the form of a valid email
     * @return - boolean value showing if the given string was valid
     */
    public boolean validateEmail(String email) {
        if(email.equals("")){
            JOptionPane.showMessageDialog(null, "Email missing", "EMAIL ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!email.contains("@yahoo.com") && !email.contains("@gmail.com") ) {
            JOptionPane.showMessageDialog(null, "Wrong email", "EMAIL ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }


    /**
     * Method that checks if a string has the form needed to be an address( contains alphanumeric charters)  and if not
     *      * displays an error dialog message
     * @param address - the string to be checked
     * @return - boolean value showing if the given string was valid
     */
    public boolean validateAddress(String address){
        if (address .equals("")) {
            JOptionPane.showMessageDialog(null, "Address missing", "ADDRESS ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!address.matches("[a-zA-Z0-9\s]+") ){
            JOptionPane.showMessageDialog(null, "Not a valid address", "ADDRESS ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * This method check if an order is possible: the stock of a certain product is greater or equal with the required
     * number of products
     * @param required - number of products the clients wants to buy
     * @param stock - the cuurent stock of the product : quantity existent in the database
     * @return - boolean value showing if the given string was valid
     */
    public boolean validateStock(int required, int stock){
        if( stock < required){
            JOptionPane.showMessageDialog(null, "Not enough items in stock. Plese select a smaller quantity", "QUANTITY ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

}

