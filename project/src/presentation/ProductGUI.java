package presentation;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author Deac Denisa Bianac
 * The ProductGUI class represent the graphical user interface for the product operations. It contains all the labels
 * and fields that the user will use to introduce the data
 */
public class ProductGUI extends JFrame {

  //labels
    private final JLabel title = new JLabel("OPERATIONS ON PRODUCTS");
    //for new product
    private final JLabel name = new JLabel("NAME");
    private final JLabel quantity = new JLabel("QUANTITY");
    private final JLabel price = new JLabel("PRICE");
    //for editing product
    private final JLabel nameE = new JLabel("NEW NAME");
    private final JLabel quantityE = new JLabel("NEW QUANTITY");
    private final JLabel priceE = new JLabel("NEW PRICE");
    private final JLabel idE = new JLabel("EDIT PRODUCT WITH ID: ");
    //for deleting
    private final JLabel idD = new JLabel("DELETE PRODUCT WITH ID: ");

  //fields
    //for new product
    private final JTextField nameField = new JTextField();
    private final JTextField quantityField = new JTextField();
    private final JTextField priceField = new JTextField();
    //for editing product
    private final JTextField nameFieldE = new JTextField();
    private final JTextField quantityFieldE = new JTextField();
    private final JTextField priceFieldE = new JTextField();
    private final JTextField idFieldE= new JTextField();
    //for deleting Product
    private final JTextField idFieldD = new JTextField();

  //buttons
    private final JButton addProduct = new JButton("ADD PRODUCT");
    private final JButton editProduct = new JButton("EDIT PRODUCT");
    private final JButton viewProducts = new JButton("VIEW PRODUCTS");
    private final JButton back = new JButton("BACK");
    private final JButton deleteProduct = new JButton("DELETE PRODUCT");

  //panels
    private static JPanel panel1 = new JPanel();
    private static JPanel panel2 = new JPanel();
    private static JPanel panel3 = new JPanel();
    private static JPanel panel4 = new JPanel();
    
    /**
     * The constructor is used to add all the components
     */
    public ProductGUI(){
        initProduct();
        addTitle();
        addNewProductPanel();
        addEditProductPanel();
        addBackButton();
        addViewProductPanel();
        addDeleteProductPanel();


    }

    /**
     * The initProduct initialises the GUI: the size of the window, visibility, exit policy
     */
    public void initProduct(){
        this.setSize(900, 700);
        this.setTitle("ORDERS MANAGEMENT-products operations");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    /**
     * Method that adds the title of the interface
     */
    private void addTitle(){
        title.setBounds(280,15,300,50);
        title.setVisible(true);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 20));
        this.add(title);
    }


    /**
     * This method creates a panel on which are added all the fields, labels and the button used to insert a new product
     */
    private void addNewProductPanel(){
        //panel
        panel1.setBounds(30,80,820,80);
        panel1.setBackground(new Color(  201,	227,	204));

        Border blackline = BorderFactory.createLineBorder(Color.black);
        TitledBorder t = BorderFactory.createTitledBorder( blackline, "Add New product");
        t.setTitleJustification(TitledBorder.CENTER);
        panel1.setBorder(t);


        panel1.setLayout(null);
        panel1.setVisible(true);
        this.add(panel1);

        //name
        name.setBounds(15,15,50,20);
        name.setVisible(true);
        panel1.add(name);

        nameField.setBounds(15,40,200,25);
        nameField.setVisible(true);
        panel1.add(nameField);

        //quantity
        quantity.setBounds(230,15,70,20);
        quantity.setVisible(true);
        panel1.add(quantity);

        quantityField.setBounds(230,40,200,25);
        quantityField.setVisible(true);
        panel1.add(quantityField);

        //price
        price.setBounds(445,15,50,20);
        price.setVisible(true);
        panel1.add(price);

        priceField.setBounds(445,40,200,25);
        priceField.setVisible(true);
        panel1.add(priceField);

        //product button
        addProduct.setBounds(660, 25, 150,40);
        addProduct.setVisible(true);
        addProduct.setFocusable(false);
        panel1.add(addProduct);
    }


    /**
     * This method creates a panel on which are added all the fields, labels and the button used to edit a product
     */
    private void addEditProductPanel(){
        //panel
        panel2.setBounds(30,200,820,110);
        panel2.setBackground(new Color(  201,	227,	204));
        Border blackline = BorderFactory.createLineBorder(Color.black);
        TitledBorder t = BorderFactory.createTitledBorder( blackline, "Edit product");
        t.setTitleJustification(TitledBorder.CENTER);
        panel2.setBorder(t);
        panel2.setLayout(null);
        panel2.setVisible(true);
        this.add(panel2);

        //id
        idE.setBounds(15,15,180,20);
        idE.setVisible(true);
        panel2.add(idE);

        idFieldE.setBounds(180, 15, 40, 25);
        idFieldE.setVisible(true);
        panel2.add(idFieldE);

        //name
        nameE.setBounds(15,45,90,20);
        nameE.setVisible(true);
        panel2.add(nameE);

        nameFieldE.setBounds(15,70,200,25);
        nameFieldE.setVisible(true);
        panel2.add(nameFieldE);

        //quantity
        quantityE.setBounds(230,45,110,20);
        quantityE.setVisible(true);
        panel2.add(quantityE);

        quantityFieldE.setBounds(230,70,200,25);
        quantityFieldE.setVisible(true);
        panel2.add(quantityFieldE);

        //price
        priceE.setBounds(445,45,90,20);
        priceE.setVisible(true);
        panel2.add(priceE);

        priceFieldE.setBounds(445,70,200,25);
        priceFieldE.setVisible(true);
        panel2.add(priceFieldE);

        //product button
        editProduct.setBounds(660, 30, 150,40);
        editProduct.setVisible(true);
        editProduct.setFocusable(false);
        panel2.add(editProduct);
    }

    /**
     * This method creates a panel on which the button used to view the table with all the products are added
     */
    private void addViewProductPanel(){
        panel4.setBounds(30,350,380,70);
        panel4.setBackground(new Color(  201,	227,	204));
        Border blackline = BorderFactory.createLineBorder(Color.black);
        TitledBorder t = BorderFactory.createTitledBorder( blackline, "View Products in Table");
        t.setTitleJustification(TitledBorder.CENTER);
        panel4.setBorder(t);
        panel4.setLayout(null);
        panel4.setVisible(true);
        this.add(panel4);

        viewProducts.setBounds(100, 22, 150,35);
        viewProducts.setVisible(true);
        viewProducts.setFocusable(false);
        panel4.add(viewProducts);
    }

    /**
     * This method creates a panel on which are added all the fields, labels and the button used to delete a new product
     */
    private void addDeleteProductPanel(){
        //panel
        panel3.setBounds(430,350,420,70);
        panel3.setBackground(new Color(  201,	227,	204));
        Border blackline = BorderFactory.createLineBorder(Color.black);
        TitledBorder t = BorderFactory.createTitledBorder( blackline, "Delete Product");
        t.setTitleJustification(TitledBorder.CENTER);
        panel3.setBorder(t);
        panel3.setLayout(null);
        panel3.setVisible(true);
        this.add(panel3);

        //id
        idD.setBounds(15,25,170,20);
        idD.setVisible(true);
        panel3.add(idD);

        idFieldD.setBounds(190, 25, 40, 25);
        idFieldD.setVisible(true);
        panel3.add(idFieldD);

        //button
        deleteProduct.setBounds(260, 18, 150,35);
        deleteProduct.setVisible(true);
        deleteProduct.setFocusable(false);
        panel3.add(deleteProduct);
    }

    /**
     * This method sets the scroll pane and the bounds of the products table
     * @param table - table with information about all products
     */
    public void addTable(JTable table){

        table.setFillsViewportHeight(true);
        table.setBackground(new Color(  201,	227,	204));
        JScrollPane scrollBar = new JScrollPane(table);
        scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollBar.setBounds(30,450,820,120);
        getContentPane().add(scrollBar);

    }

    /**
     * This method adds on the graphical interface the button that will return the user to the Start Window to choose
     * another category of operations
     */
    private void addBackButton(){
        back.setBounds(30, 610, 80,40);
        back.setVisible(true);
        back.setFocusable(false);
        back.addActionListener( e -> {
            if(e.getSource() == back){
                StartGUI startGUI = new StartGUI();
                startGUI.setVisible(true);
                Controller controller = new Controller(startGUI);
                this.setVisible(false);
            }
        });
        this.add(back);
    }

    /**
     * Method that returns the button used to view the table with all the products
     * @return the JButton that displays the table
     */
    public JButton getViewButton(){ return viewProducts;}

    /**
     * Method that returns the button used to insert a product in the table
     * @return the JButton that inserts the product
     */
    public JButton getInsertButton(){ return addProduct;}

    /**
     * Method that returns the button used to edit a product in the table
     * @return the JButton that edits the product
     */
    public JButton getEditButton(){ return editProduct;}

    /**
     * Method that returns the button used to delete a product in the table
     * @return the JButton that deletes the product
     */
    public JButton getDeleteButton(){ return deleteProduct;}

    /**
     * This Method creates an array of TextFields with all the text fields for inserting a product 
     * @return the list of fields
     */
    public JTextField[] getInsertFields(){
        JTextField[] fields = new JTextField[3];
        fields[0] = nameField;
        fields[1] = priceField;
        fields[2] = quantityField;
        return fields;
    }

    /**
     * This Method creates an array of TextFields with all the text fields for editing a product 
     * @return the list of fields
     */
    public JTextField[] getEditFields(){
        JTextField[] fields = new JTextField[4];
        fields[0] = idFieldE;
        fields[1] = nameFieldE;
        fields[2] = priceFieldE;
        fields[3] = quantityFieldE;
        return fields;
    }
    
    /**
     * This Method is used to return the value of the id that was introduced for deletion 
     * @return the integer value of the id
     */
    public  int getDeleteField(){
        return Integer.parseInt(idFieldD.getText());
    }

    /**
     * Method that adds all the Action Listeners for the buttons to work
     * @param listener - the Action Listener for the buttons
     */
    public void addListeners(ActionListener listener) {
        viewProducts.addActionListener(listener);
        addProduct.addActionListener(listener);
        editProduct.addActionListener(listener);
        deleteProduct.addActionListener(listener);
    }

}

