package presentation;

import bussinesLogic.ClientBLL;
import bussinesLogic.OrderBLL;
import bussinesLogic.ProductBLL;

/**
 * @author Deac Denisa Bianca
 * The Controller class adds the Action Listeners to the buttons so that they would work properly. The information from
 * GUI with the input data is used and the operations on the database are performed according to the chosen action
 */
public class Controller {

    private final StartGUI startGUI;
    private ClientGUI clientGUI;
    private ProductGUI productGUI;
    private OrderGUI orderGUI;
    private static int okClient = 0, okProduct = 0, okOrder = 0;

    /**
     * The Constructor uses the data from the StartGUI and adds the listeners
     * @param startGUI -the graphical user interface that has started
     */
    public Controller(StartGUI startGUI) {
        this.startGUI = startGUI;
        addListenerStart();
    }

    /**
     * This method represents the way the StartGUI has to work: for each  button pressed, a window with the necessary
     * oaperation will appear on the screen while the start window will disappear. The listeners for the other windows
     * are added
     */
    private void addListenerStart() {
        okClient = 0;
        startGUI.addListeners(e -> {
            if (e.getSource() == startGUI.getClientsButton()) {
                okClient = 1;
                startGUI.setVisible(false);
                this.clientGUI = new ClientGUI();
                this.clientGUI.setVisible(true);
                addListenerClient();
            } else if (e.getSource() == startGUI.getProductsButton()) {
                okProduct = 1;
                startGUI.setVisible(false);
                this.productGUI = new ProductGUI();
                this.productGUI.setVisible(true);
                addListenerProduct();
            } else if (e.getSource() == startGUI.getOrdersButton()) {
                okOrder = 1;
                startGUI.setVisible(false);
                this.orderGUI = new OrderGUI();
                this.orderGUI.setVisible(true);
                OrderBLL orderBLL = new OrderBLL();
                orderBLL.view(this.orderGUI);
                addListenerOrder();
            }

        });

    }

    /**
     * This Method will initiate the required operation on the Client table by instantiating the business logic class that will check
     * and do the required operations
     */
    private void addListenerClient() {

        if(okClient == 1) {

            clientGUI.addListeners(e -> {
                ClientBLL clientBLL = new ClientBLL();
               if(e.getSource() == clientGUI.getInsertButton()){
                   clientBLL.checkAndInsert(clientGUI);
                }
                else if(e.getSource() == clientGUI.getEditButton()){
                   clientBLL.checkAndEdit(clientGUI);
                }
                else if(e.getSource() == clientGUI.getDeleteButton()){
                   clientBLL.checkAndDelete(clientGUI);
                }
                else if (e.getSource() == clientGUI.getViewButton()) {
                  clientBLL.view(clientGUI);
               }

            });
        }
    }

    /**
     * This Method will initiate the required operation on the Product table by instantiating the business logic class that will check
     * and do the required operations
     */
    private void addListenerProduct() {

        if (okProduct == 1) {

            productGUI.addListeners(e -> {
                ProductBLL productBLL = new ProductBLL();
                if (e.getSource() == productGUI.getInsertButton()) {
                    productBLL.checkAndInsert(productGUI);
                }
                else if (e.getSource() == productGUI.getEditButton()) {
                    productBLL.checkAndEdit(productGUI);
                } else if (e.getSource() == productGUI.getDeleteButton()) {
                   productBLL.checkAndDelete(productGUI);
                } else if (e.getSource() == productGUI.getViewButton()) {
                   productBLL.view(productGUI);
                }
            });
        }
    }

    /**
     * This Method will initiate the insertion operation on the Product table by instantiating the business logic class
     * that will check and do the required operation
     */
    private void addListenerOrder() {

        if (okOrder == 1) {

            orderGUI.addListeners(e -> {
                OrderBLL orderBLL = new OrderBLL();
                if (e.getSource() == orderGUI.getAddOrder()) {
                    orderBLL.checkAndInsert(orderGUI);
                }

            });
        }
    }

}
