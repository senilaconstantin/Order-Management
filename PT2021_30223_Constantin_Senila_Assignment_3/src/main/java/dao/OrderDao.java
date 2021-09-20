package dao;

import connection.ConnectionFactory;
import model.Orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Acesta clsa extinde clasa AbstractC care implementeaza toatemetodele comune cu celelate clase.
 * Metodele care sunt implementate si aici separat sunt: nrProd care returneaza numarul de produse de un tip anume disponibile, scadereProd care scade
 * numarul de produse plasate in comanda din cel initial in acel numar de produse si metoda maxId care calculeaza id ul maxim al unei
 * comezifiin nevoie de acesta pentru a crea numele fisierului txt care reprezinta factura.
 */
public class OrderDao extends AbstractC<Orders> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractC.class.getName());

    public int nrProd(Orders ord) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        String selc = "Select cantitate, pret from product where numeProduct=?";
        int nr = 0;

        try {
            statement = connection.prepareStatement(selc);
            statement.setString(1, ord.getNumeOrders());
            ResultSet r = statement.executeQuery();
            r.next();
            nr = Integer.parseInt(r.getString("cantitate"));
            double d = Double.parseDouble(r.getString("pret"))* ord.getTotal();
            ord.setPret(d);
            //System.out.println(ord.getPret());
            // System.out.println(nr);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Dao (extract): " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return nr;
    }

    public void scadereProd(Orders ord) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        String s = "update Product set cantitate=? where numeProduct=?";
        int x = nrProd(ord) - ord.getTotal();
        //System.out.println(ord.getTotal());
        try {
            statement = connection.prepareStatement(s);
            statement.setInt(1, x);
            statement.setString(2, ord.getNumeOrders());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Dao (scad): " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    public void maxId(Orders o){
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        String s = "Select max(idOrders) as id from `orders`;";
        try {
            statement= connection.prepareStatement(s);
            ResultSet r=statement.executeQuery();
            r.next();
            o.setIdOrders(Integer.parseInt(r.getString("id")));
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Dao (max): " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}
