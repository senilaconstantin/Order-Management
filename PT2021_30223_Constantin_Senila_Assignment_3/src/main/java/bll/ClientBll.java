package bll;

import dao.ClientDao;
import model.Clienti;

import java.sql.ResultSet;
import java.util.List;

/**
 *In aceasta clasa am implementat metodele  insert, delete, edit, rezSelect si createObject care apeleaza metodele
 * corespunzatoare din pachetul dao din clasa ClientDao
 */
public class ClientBll {
    public  void insert(Clienti c) {
        ClientDao d = new ClientDao();
        d.insert(c);
    }

    public  void delete(int id, String nume) {
        ClientDao d = new ClientDao();
        d.delete(id, nume);
    }

    public  void edit(Clienti c) {
        ClientDao d = new ClientDao();
        d.edit(c);
    }

    public ResultSet rezSelect() {
        ClientDao d = new ClientDao();
        return d.rezSelect();
    }

    public List<Clienti> createObjects(){
        ClientDao c=new ClientDao();
        return c.createObjects();
    }

}
