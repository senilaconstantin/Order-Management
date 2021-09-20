package dao;

import connection.ConnectionFactory;
import model.Clienti;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * In aceasta clasa se implementeaza toate metodele comune din clasele ClientDao, OrderDao si respectiv ProductDao,
 * astfel se vor implementa doar o singura data aceste metode. Pentru acest lucru am folosit reflexia facnd astfel posibila
 * implementarea doar o singura data a acestora.
 * @param <T>
 */
public class AbstractC<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractC.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractC() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private String createSelectQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        return sb.toString();
    }

    public ResultSet rezSelect() {
        Connection connection = ConnectionFactory.getConnection();
        String rezSelect = createSelectQuery();
        try {
            Statement statement = connection.createStatement();
            statement.execute(rezSelect);
            ResultSet rs = statement.getResultSet();
            return rs;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getSimpleName() + "Dao (select): " + e.getMessage());
        }
        return null;
    }

    private String createDeleteQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("Delete ");
        sb.append("from ");
        sb.append(type.getSimpleName());
        sb.append(" where id" + type.getSimpleName() + " = ? and nume" + type.getSimpleName() + " = ?");
        return sb.toString();
    }

    public void delete(int id, String nume) {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        String rezDelete = createDeleteQuery();

        //System.out.println(rezDelete);
        try {
            statement = connection.prepareStatement(rezDelete);
            statement.setString(1, String.valueOf(id));
            statement.setString(2, nume);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getSimpleName() + "Dao (delete): " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    private String insertQuery(Field[] fields) {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into ");
        sb.append(type.getSimpleName() + " (");
        for(int i=1;i<fields.length;i++){
            sb.append(fields[i].getName());
            if(i+1< fields.length)
                sb.append(", ");
        }
        sb.append(") values(");
        for(int i=1;i<fields.length;i++) {
            sb.append("?");
            if(i+1< fields.length)
                sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }

    public void insert(T t) {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement insertS = null;
        ResultSet rS = null;
        try {
            Field[] fields = type.getDeclaredFields();
            //System.out.println(insertQuery(fields));
            insertS = connection.prepareStatement(insertQuery(fields));
           // insertS.setInt(1, ++id);
            for (int i = 1; i < fields.length; i++) {
                fields[i].setAccessible(true);
//                System.out.println(fields[i].getName() + " " + fields[i].get(t));
                insertS.setObject(i, fields[i].get(t));
            }
//           System.out.println(insertS);
            insertS.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getSimpleName() + "Dao (insert): " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertS);
            ConnectionFactory.close(connection);
        }
    }

    private String updateQuery(Field[] fields) {
        StringBuilder sb = new StringBuilder();
        sb.append("update ");
        sb.append(type.getSimpleName() + " set ");
        for(int i=1;i<fields.length;i++){
            sb.append(fields[i].getName());
            sb.append("=?");
            if(i+1< fields.length)
                sb.append(", ");
        }
        sb.append(" where id"+type.getSimpleName()+"=?");
        return sb.toString();
    }

    public void edit(T t) {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        try {
            Field[] fields = type.getDeclaredFields();
            //System.out.println(updateQuery(fields));
            statement = connection.prepareStatement(updateQuery(fields));
            for (int i = 1; i < fields.length; i++) {
                fields[i].setAccessible(true);
                //System.out.println(fields[i].getName() + " " + fields[i].get(t));
                statement.setObject(i, fields[i].get(t));
            }
            fields[0].setAccessible(true);
            //System.out.println(fields[0].get(t));
            statement.setObject(fields.length, fields[0].get(t));
          //  System.out.println(statement);

            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getSimpleName()+"Dao (edit): " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    public List<T> createObjects() {
        ResultSet resultSet=rezSelect();
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
                //System.out.println(method.toString());
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
        return list;
    }



}
