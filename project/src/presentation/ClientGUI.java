package presentation;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author Deac Denisa Bianac
 * The ClientGUI class represent the graphical user interface for the client operation. It contains all the labels and
 * fields that the user will use to introduce the data
 */
public class ClientGUI extends JFrame {

    //labels
    private final JLabel title = new JLabel("OPERATIONS ON CLIENTS");
    //for new client
    private final JLabel name = new JLabel("NAME");
    private final JLabel address = new JLabel("ADDRESS");
    private final JLabel email = new JLabel("EMAIL");
    //for editing client
    private final JLabel nameE = new JLabel("NEW NAME");
    private final JLabel addressE = new JLabel("NEW ADDRESS");
    private final JLabel emailE = new JLabel("NEW EMAIL");
    private final JLabel idE = new JLabel("EDIT CLIENT WITH ID: ");
    //for deleting client
    private final JLabel idD = new JLabel("DELETE CLIENT WITH ID: ");

    //fields
    //for new client
    private final JTextField nameField = new JTextField();
    private final JTextField addressField = new JTextField();
    private final JTextField emailField = new JTextField();
    //for editing client
    private final JTextField nameFieldE = new JTextField();
    private final JTextField addressFieldE = new JTextField();
    private final JTextField emailFieldE = new JTextField();
    private final JTextField idFieldE= new JTextField();
    //for deleting client
    private final JTextField idFieldD = new JTextField();
    //buttons
    private final JButton addClient = new JButton("ADD CLIENT");
    private final JButton editClient = new JButton("EDIT CLIENT");
    private final JButton viewClients = new JButton("VIEW CLIENTS");
    private final JButton deleteClient = new JButton("DELETE CLIENT");
    private final JButton back = new JButton("BACK");

    //panels
    private static JPanel panel1 = new JPanel();
    private static JPanel panel2 = new JPanel();
    private static JPanel panel3 = new JPanel();
    private static JPanel panel4 = new JPanel();


    /**
     * The constructor is used to add all the components
     */
    public ClientGUI(){
        initClient();
        addTitle();
        addNewClientPanel();
        addEditClientPanel();
        addBackButton();
        addViewClientsPanel();
        addDeleteClientPanel();

    }

    /**
     * The initClient initialises the GUI: the size of the window, visibility, exit policy
     */
    public void initClient(){
        this.setSize(900, 700);
        this.setTitle("ORDERS MANAGEMENT-clients operations");
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
     * This method creates a panel on which are added all the fields, labels and the button used to insert a new client
     */
    private void addNewClientPanel(){
        //panel
        panel1.setBounds(30,80,820,80);
        panel1.setBackground(new Color(  201,	227,	204));

        Border blackline = BorderFactory.createLineBorder(Color.black);
        TitledBorder t = BorderFactory.createTitledBorder( blackline, "Add New Client");
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

        //address
        address.setBounds(230,15,70,20);
        address.setVisible(true);
        panel1.add(address);

        addressField.setBounds(230,40,200,25);
        addressField.setVisible(true);
        panel1.add(addressField);

        //email
        email.setBounds(445,15,50,20);
        email.setVisible(true);
        panel1.add(email);

        emailField.setBounds(445,40,200,25);
        emailField.setVisible(true);
        panel1.add(emailField);

        //client button
        addClient.setBounds(670, 25, 120,40);
        addClient.setVisible(true);
        addClient.setFocusable(false);
        panel1.add(addClient);
    }

    /**
     * This method creates a panel on which are added all the fields, labels and the button used to edit a client
     */
    private void addEditClientPanel(){
        //panel
        panel2.setBounds(30,200,820,110);
        panel2.setBackground(new Color(  201,	227,	204));
        Border blackline = BorderFactory.createLineBorder(Color.black);
        TitledBorder t = BorderFactory.createTitledBorder( blackline, "Edit Client");
        t.setTitleJustification(TitledBorder.CENTER);
        panel2.setBorder(t);
        panel2.setLayout(null);
        panel2.setVisible(true);
        this.add(panel2);

        //id
        idE.setBounds(15,15,130,20);
        idE.setVisible(true);
        panel2.add(idE);

        idFieldE.setBounds(150, 15, 40, 25);
        idFieldE.setVisible(true);
        panel2.add(idFieldE);

        //name
        nameE.setBounds(15,45,90,20);
        nameE.setVisible(true);
        panel2.add(nameE);

        nameFieldE.setBounds(15,70,200,25);
        nameFieldE.setVisible(true);
        panel2.add(nameFieldE);

        //address
        addressE.setBounds(230,45,110,20);
        addressE.setVisible(true);
        panel2.add(addressE);

        addressFieldE.setBounds(230,70,200,25);
        addressFieldE.setVisible(true);
        panel2.add(addressFieldE);

        //email
        emailE.setBounds(445,45,90,20);
        emailE.setVisible(true);
        panel2.add(emailE);

        emailFieldE.setBounds(445,70,200,25);
        emailFieldE.setVisible(true);
        panel2.add(emailFieldE);

        //button
        editClient.setBounds(670, 30, 120,40);
        editClient.setVisible(true);
        editClient.setFocusable(false);
        panel2.add(editClient);
    }

    /**
     * This method creates a panel on which the button used to view the table with all the clients are added
     */
    private void addViewClientsPanel(){
        panel4.setBounds(30,350,380,70);
        panel4.setBackground(new Color(  201,	227,	204));
        Border blackline = BorderFactory.createLineBorder(Color.black);
        TitledBorder t = BorderFactory.createTitledBorder( blackline, "View Clients in Table");
        t.setTitleJustification(TitledBorder.CENTER);
        panel4.setBorder(t);
        panel4.setLayout(null);
        panel4.setVisible(true);
        this.add(panel4);

        viewClients.setBounds(100, 22, 160,35);
        viewClients.setVisible(true);
        viewClients.setFocusable(false);
        panel4.add(viewClients);
    }

    /**
     * This method creates a panel on which are added all the fields, labels and the button used to delete a new client
     */
    private void addDeleteClientPanel(){
        //panel
        panel3.setBounds(430,350,420,70);
        panel3.setBackground(new Color(  201,	227,	204));
        Border blackline = BorderFactory.createLineBorder(Color.black);
        TitledBorder t = BorderFactory.createTitledBorder( blackline, "Delete Client");
        t.setTitleJustification(TitledBorder.CENTER);
        panel3.setBorder(t);
        panel3.setLayout(null);
        panel3.setVisible(true);
        this.add(panel3);

        //id
        idD.setBounds(45,25,150,20);
        idD.setVisible(true);
        panel3.add(idD);

        idFieldD.setBounds(210, 25, 40, 25);
        idFieldD.setVisible(true);
        panel3.add(idFieldD);

        //button
        deleteClient.setBounds(280, 18, 130,35);
        deleteClient.setVisible(true);
        deleteClient.setFocusable(false);
        panel3.add(deleteClient);
    }

    /**
     * This method sets the scroll pane and the bounds of the clients table
     * @param table - table with information about all clients
     */
    public void addTable( JTable table){
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
     * Method that returns the button used to view the table with all the clients
     * @return the JButton that displays the table
     */
    public JButton getViewButton(){ return viewClients;}

    /**
     * Method that returns the button used to insert a client in the table
     * @return the JButton that inserts the client
     */
    public JButton getInsertButton(){ return addClient;}

    /**
     * Method that returns the button used to edit a client in the table
     * @return the JButton that edits the client
     */
    public JButton getEditButton(){ return editClient;}

    /**
     * Method that returns the button used to delete a client in the table
     * @return the JButton that deletes the client
     */
    public JButton getDeleteButton(){ return deleteClient;}


    /**
     * This Method creates an array of TextFields with all the text fields for inserting a client
     * @return the list of fields
     */
    public JTextField[] getInsertFields(){
        JTextField[] fields = new JTextField[3];
        fields[0] = nameField;
        fields[1] = addressField;
        fields[2] = emailField;
        return fields;
    }

    /**
     * This Method creates an array of TextFields with all the text fields for editing a client
     * @return the list of fields
     */
    public JTextField[] getEditFields(){
        JTextField[] fields = new JTextField[4];
        fields[0] = idFieldE;
        fields[1] = nameFieldE;
        fields[2] = addressFieldE;
        fields[3] = emailFieldE;
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
        viewClients.addActionListener(listener);
        addClient.addActionListener(listener);
        editClient.addActionListener(listener);
        deleteClient.addActionListener(listener);
    }
}

