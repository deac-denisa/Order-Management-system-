package presentation;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author Deac Denisa Bianac
 * The ProductGUI class represent the graphical user interface for the order operations. It contains all the labels
 * and fields that the user will use to introduce the data
 */
public class OrderGUI extends JFrame {

    //labels
    private final JLabel title = new JLabel("OPERATIONS ON ORDERS");
    private final JLabel idClient = new JLabel("ID OF THE CLIENT");
    private final JLabel idProduct = new JLabel("ID OF THE PRODUCT");
    private final JLabel quantity = new JLabel("QUANTITY");

    //fields
    private final JTextField clientField = new JTextField();
    private final JTextField productField = new JTextField();
    private final JTextField quantityField = new JTextField();

    //buttons
    private final JButton addOrder = new JButton("ADD ORDER");
    private final JButton back = new JButton("BACK");

    //panels
    private static JPanel panel1 = new JPanel();

    /**
     * The constructor is used to add all the components
     */
    public OrderGUI(){
        initOrder();
        addTitle();
        addNewOrderPanel();
        addBackButton();

    }

    /**
     * The initProduct initialises the GUI: the size of the window, visibility, exit policy
     */
    public void initOrder(){
        this.setSize(900, 700);
        this.setTitle("ORDERS MANAGEMENT-Orders operations");
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
     * This method creates a panel on which are added all the fields, labels and the button used to insert a new order
     */
    private void addNewOrderPanel(){
        //panel
        panel1.setBounds(30,100,820,80);
        panel1.setBackground(new Color(  201,	227,	204));

        Border blackline = BorderFactory.createLineBorder(Color.black);
        TitledBorder t = BorderFactory.createTitledBorder( blackline, "Add New Order");
        t.setTitleJustification(TitledBorder.CENTER);
        panel1.setBorder(t);


        panel1.setLayout(null);
        panel1.setVisible(true);
        this.add(panel1);

        //client
        idClient.setBounds(15,15,190,20);
        idClient.setVisible(true);
        panel1.add(idClient);

        clientField.setBounds(15,40,200,25);
        clientField.setVisible(true);
        panel1.add(clientField);

        //product
        idProduct.setBounds(230,15,190,20);
        idProduct.setVisible(true);
        panel1.add(idProduct);

        productField.setBounds(230,40,200,25);
        productField.setVisible(true);
        panel1.add(productField);

        //quantity
        quantity.setBounds(445,15,90,20);
        quantity.setVisible(true);
        panel1.add(quantity);

        quantityField.setBounds(445,40,200,25);
        quantityField.setVisible(true);
        panel1.add(quantityField);

        //Order button
        addOrder.setBounds(670, 25, 120,40);
        addOrder.setVisible(true);
        addOrder.setFocusable(false);
        panel1.add(addOrder);
    }

    /**
     * This method sets the scroll pane, the bounds of the client table and adds it to the interface
     * @param table - table with information about all clients
     */
    public void addClientsTable(JTable table){

        JLabel label = new JLabel("Clients");
        label.setVisible(true);
        label.setBounds(400, 215, 100, 30);
        this.add(label);
        table.setFillsViewportHeight(true);
        table.setBackground(new Color(  201,	227,	204));
        JScrollPane scrollBar = new JScrollPane(table);
        scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollBar.setBounds(30,250,820,120);
        getContentPane().add(scrollBar);

    }

    /**
     * This method sets the scroll pane, the bounds of the product table and adds it to the interface
     * @param table - table with information about all products
     */
    public void addProductTable(JTable table){
        JLabel label = new JLabel("Products");
        label.setVisible(true);
        label.setBounds(400, 395, 100, 30);
        this.add(label);
        table.setFillsViewportHeight(true);
        table.setBackground(new Color(  201,	227,	204));
        JScrollPane scrollBar = new JScrollPane(table);
        scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollBar.setBounds(30,430,820,120);
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
     * This Method creates an array of TextFields with all the text fields for inserting an order
     * @return the list of fields
     */
    public JTextField[] getInsertFields(){
        JTextField[] fields = new JTextField[3];
        fields[0] = clientField;
        fields[1] = productField;
        fields[2] = quantityField;
        return fields;
    }

    /**
     * Method that returns the button used to insert an order in the table
     * @return the JButton that inserts the product
     */
    public JButton getAddOrder(){
        return addOrder;
    }

    /**
     * Method that adds the ActionListener to the button for it to work properly
     * @param listener - action listener that will make the button work
     */
    public void addListeners(ActionListener listener) {
        addOrder.addActionListener(listener);
    }
}

