package dao;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import connection.ConnectionFactory;
import model.Client;
import model.Order;
import model.Product;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * @author Deac Denisa Bianca
 *  * OrderDAO class implements methods specific for the order data acces operations, including the ones inherited from the AbstractDAO
 */
public class OrderDAO extends AbstractDAO<Order>{

    public OrderDAO() {
        super();
    }
    /**
     * This method creates a pdf representing the bill for the given order
     * @param order - the order for which the bill must be made
     */
    public void createBill(Order order){

        StringBuilder bill = new StringBuilder();
        ClientDAO clientDAO = new ClientDAO();
        ProductDAO productDAO = new ProductDAO();
        Client client = clientDAO.findById(order.getCid());
        Product product = productDAO.findById(order.getPid());

        bill.append("Order number "+ order.getId()+"\n\nCLIENT DETAILS:\n");
        bill.append("Name: "+client.getCname()+"\n"+"Address: "+client.getAddress()+"\nEmail: "+client.getEmail()+"\n\n");
        bill.append("ORDER DETAILS:\n");
        bill.append("Product: "+product.getPname()+"\nProduct Price: "+ product.getPrice()+"\nQuantity: "+order.getQuantity());
        bill.append("\n\nTotal price: "+product.getPrice()*order.getQuantity());

        try {
            String billName = "bill"+order.getId()+".pdf";

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(billName));

            document.open();

            document.add(new Paragraph("Order number "+ order.getId()+"\n\nCLIENT DETAILS:\n"));
            document.add(new Paragraph("Name: "+client.getCname()+"\n"+"Address: "+client.getAddress()+"\nEmail: "+client.getEmail()+"\n\n"));
            document.add(new Paragraph("ORDER DETAILS:\n"));
            document.add(new Paragraph("Product: "+product.getPname()+"\nProduct Price: "+ product.getPrice()+"\nQuantity: "+order.getQuantity()));
            document.add(new Paragraph("\n\nTotal price: "+product.getPrice()*order.getQuantity() ));
            document.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     *  Method that takes the id of the client and finds in the database the record having that id
     * @param id - the identifier of the desired field
     * @param field - the column for which we want to look : client or product id
     * @return - List of orders representing all the orders containing in the given field the id provided
     */


    public List<Order> findElementsById(int id, String field) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery(field);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public void deleteOrders( int cid, String field){
        List<Order> orders = findElementsById( cid, field);

        if(orders != null) {
           for (Order o : orders) {
              createDeleteQuery(o.getId());
          }
        }
    }

}
