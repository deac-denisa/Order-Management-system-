package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author Deac Denisa Bianac
 * The ProductGUI class represent the graphical user interface for the order operations. It contains all the labels
 * and fields that the user will use to introduce the data
 */
public class StartGUI extends JFrame {

    private final JLabel title = new JLabel("ORDERS MANAGEMENT");
    private final JLabel instructions = new JLabel("choose the type of operation:");

    private final JButton clients = new JButton("CLIENTS");
    private final JButton products = new JButton("PRODUCTS");
    private final JButton orders = new JButton("ORDERS");


    /**
     * The constructor is used to add all the components
     */
    public StartGUI(){
       initStart();
       addLabels();
       addButtons();
   }

    /**
     * The initProduct initialises the GUI: the size of the window, visibility, exit policy
     */
    public void initStart(){
        this.setSize(400, 400);
        this.setTitle("ORDERS MANAGEMENT");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    /**
     * Method that adds the title of the interface
     */
    public void addLabels(){
        title.setBounds(40,15,300,50);
        title.setVisible(true);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 20));
        this.add(title);

        instructions.setBounds(110,80,450,30);
        instructions.setVisible(true);
        this.add(instructions);
    }

    /**
     * This methods adds on the window the buttons that will start the other interfaces depending on the category
     * of operations chosen: Client, Product or Order operations
     */
    public void addButtons(){
        clients.setBounds(140, 120, 105, 40);
        clients.setVisible(true);
        clients.setFocusable(false);
        this.add(clients);

        orders.setBounds(140, 240, 105, 40);
        orders.setVisible(true);
        orders.setFocusable(false);
        this.add(orders);

        products.setBounds(140, 180, 105, 40);
        products.setVisible(true);
        products.setFocusable(false);
        this.add(products);
    }

    /**
     * Method that adds all the Action Listeners for the buttons to work
     * @param listener - the Action Listener for the buttons
     */
    public void addListeners(ActionListener listener) {
        clients.addActionListener(listener);
        orders.addActionListener(listener);
        products.addActionListener(listener);
    }

    /**
     * Method that gets the button for starting the client interface
     * @return button for client operations
     */
    public JButton getClientsButton(){ return clients;}

    /**
     * Method that gets the button for starting the product interface
     * @return button for client operations
     */
    public JButton getProductsButton(){ return products;}

    /**
     * Method that gets the button for starting the order interface
     * @return button for client operations
     */
    public JButton getOrdersButton(){ return orders;}

}
