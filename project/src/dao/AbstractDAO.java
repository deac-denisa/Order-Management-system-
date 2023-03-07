package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import connection.ConnectionFactory;

import javax.swing.*;

/**
 * @author Deac Denisa Bianca
 * AbstractDAO is a generic class that uses Connectionfactory to extract data from the database and to alter the tables
 *              depanding on the needed operation
 * */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Method that takes the id of an object and finds in the database the record having that id
     * @param id - the identifier of the desired record
     * @return object having that id
     */
    public T findById(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery("id");
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

            List<T> l = createObjects(resultSet);
            if(l == null){
                return null;
            }
            return l.get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

    /**
     * This method creates a list of object by taking the data from the table and transforming every record into an object
     * @param resultSet - result set with the records from the table that have to be transformed into objects
     * @return - the list of object
     */
	public List<T> createObjects(ResultSet resultSet) {
		List<T> list = new ArrayList<T>();
		Constructor[] ctors = type.getDeclaredConstructors();
		Constructor ctor = null;
		for (int i = 0; i < ctors.length; i++) {
			ctor = ctors[i];
			if (ctor.getGenericParameterTypes().length == 0)
				break;
		}
		try {
			while (resultSet.next()) {
				ctor.setAccessible(true);
				T instance = (T)ctor.newInstance();
				for (Field field : type.getDeclaredFields()) {
					String fieldName = field.getName();
					Object value = resultSet.getObject(fieldName);
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(instance, value);
				}
				list.add(instance);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
        }

        if(list.isEmpty()){
            return null;
        }
        return list;
	}



    /***
     * Creates the query needed to be executed to select a certain line from the current table for the given field
     * @param field - the column name for which we want to select the specific line
     * @return - string representing the query
     */
    public String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * ");
        sb.append(" FROM \"");
        sb.append(type.getSimpleName());
        sb.append("\" WHERE " + field + " =?");
        return sb.toString();
    }

    /***
     * Generic Method that creates a query that will return all the data from the current table when executed
     * @return - string representing the query
     */
    public String createSelectAllQuery(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * ");
        sb.append(" FROM \"");
        sb.append(type.getSimpleName()+"\"");
        return String.valueOf(sb);
    }

    /**
     * Generic Method that finds the index at which the next entity has to be inserted in the table.
     * It creates a query that selects the maximum number from all the primary keys ids from the current table and
     * considers the needed index to be the maximum id incremented by one
     * @return - the id of the next possible entity
     */


    /**
     *
     * @return
     */
    public int getLastId(){
        StringBuilder query = new StringBuilder();
        String table = type.getSimpleName();
        Field field = type.getDeclaredFields()[0];
        field.setAccessible(true);

        query.append("select MAX(");
        query.append(field.getName()+") as id from \""+ table + "\"");

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(String.valueOf(query));

           while(resultSet.next()) {
               int index = resultSet.getInt("id");
               return index+1;
           }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return 0;
    }

    /***
     * Generic Method that creates the query for inserting a new row in the table and executes it. It uses reflection techniques to
     * take the data from the given object
     * @param obj - represents the entity that must be inserted in the table
     */
    public void createInsertQuery(Object obj) {
        String table = obj.getClass().getSimpleName();
        Field[] fields = obj.getClass().getDeclaredFields();
        StringBuilder values = new StringBuilder();
        
        StringBuilder query = new StringBuilder();
        query.append("Insert into \"" + table + "\"(" );
       
        try {
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                query.append(fields[i].getName());

                if(i< fields.length - 1) {
                    query.append(", ");
                }

                Object value = fields[i].get(obj);
                String fieldType = fields[i].getType().getSimpleName();

                if (fieldType.equals("String")) {
                    values.append("\'" + value + "\'");
                }
                else {
                    values.append(value);
                }
                if(i< fields.length - 1) {
                    values.append(", ");
                }
            }
            query.append(") values (" + values + " )");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error when creating the query");
            e.printStackTrace();
        }
        try {
            Connection con = ConnectionFactory.getConnection();
            Statement insertStatement = con.createStatement();
            insertStatement.executeUpdate(query.toString());
            con.close();
            insertStatement.close();
            JOptionPane.showMessageDialog(null, table+" inserted with succes in the table!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Exception when executing insert query");
            e.printStackTrace();
        }
    }

    /**
     * Generic Method that creates and executes a query for deleting a row, identified by its id, from the table.
     * It uses reflection techniques to take the current table and the name of the column representing the id
     * @param id - the value representing the identifier for the row that must be deleted from the database
     */
    public void createDeleteQuery(int id){
        String table = type.getSimpleName();
        Field field0 = type.getDeclaredFields()[0];
        field0.setAccessible(true);

        StringBuilder query = new StringBuilder();
        query.append("Delete from \"" + table + "\" where "+field0.getName() + " = "+ id);

        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement deleteStatement = con.prepareStatement(query.toString());
            deleteStatement.executeUpdate();
            con.close();
            deleteStatement.close();
            JOptionPane.showMessageDialog(null, table+" with id = "+ id +" was deleted with succes from the table!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Exception when executing delete query");
            e.printStackTrace();
        }
    }

    /**
     * Generic Method that creates and executes a query that updates the current table when a certain object must be modified.
     * It uses reflection techniques to take the necessary information and data from the given oject in order to be able to
     * update the entity with the new data
     * @param obj - object representing the new modified entity, having the same id as the old one in the table for which the
     *            data will be updated
     */
    public void createEditQuery(Object obj) {
        String table = obj.getClass().getSimpleName();
        Field[] fields = obj.getClass().getDeclaredFields();
        StringBuilder query = new StringBuilder();
        query.append("Update \"" + table + "\" set ");
        StringBuilder query2 = new StringBuilder();
        query2.append(" where ");
        int idToEdit = 0;

        try {
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                query.append(fields[i].getName() + " = ");

                Object value = fields[i].get(obj);
                String fieldType = fields[i].getType().getSimpleName();

                if (fieldType.equals("String")) {
                    query.append("\'" + value + "\'");
                }
                else {
                    query.append(value);
                }
                if(i< fields.length - 1) {
                    query.append(", ");
                }

                if( i == 0){
                    idToEdit = (int) value;
                    query2.append(fields[i].getName()+ " = ");
                    query2.append(value);
                }
            }

            query.append(query2);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement editStatement = con.prepareStatement(query.toString());
            editStatement.executeUpdate();
            con.close();
            editStatement.close();
            JOptionPane.showMessageDialog(null, table+" with id = "+ idToEdit +" was edited with succes in the table!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Exception when executing delete query", "EDIT ERROR", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * This Method creates a result set with all the data in the table, transforms it into list of objects and then calls
     * the  generic method  "CreateTable" to create a JTable with all that data from the list of objects
     *
     * @return the table containing the information about all the clients in  the database
     */
    public JTable createViewAll(){
        //create a result set with all the data in the table, then transform it into list of objects
        Connection con = ConnectionFactory.getConnection();
        String query = createSelectAllQuery();
        Statement statement = null;
        ResultSet resultSet = null;
        List<T> objList = new ArrayList<T>();

        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery(query);
            objList = createObjects(resultSet);

            return createTable(objList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(con);
        }
        return null;
    }


    /**
     * This method uses reflection techniques to create the header of the table and iterates through all the objects
     * in the list and stores all the data into a 2D array needed to create the table
     * @param objList - list containing all the data from the current table
     * @return - a table containg all the data from the current table
     */
    public JTable createTable(List<?> objList){

        Field[] fields = objList.get(0).getClass().getDeclaredFields();
        int size = fields.length;
        String[] columns = new String[size];
        Object [][] rows = new Object[20][size];
        Iterator<?> i = objList.iterator();
        int c = 0, r = 0;

        //create the header of the table
        for (Field f : fields ) {
            f.setAccessible(true);
            columns[c] = f.getName();
            c++;
        }

        //create the matrix with the data from the table
        try {
            while(i.hasNext()) {
                Object object = i.next();
                c = 0;
                for (Field f : object.getClass().getDeclaredFields()) {
                    f.setAccessible(true);
                    rows[r][c] = f.get(object);
                    c++;
                }
                r++;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        JTable table = new JTable(rows, columns);
        return table;
    }



}