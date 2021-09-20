package bll;

import dao.ProductDao;
import model.Product;

import java.sql.ResultSet;
import java.util.List;

/**
 * In aceasta clasa am implementate metodele insert, delete, edit, rezSelect si create object ccare apeleaza metodele din pachetul
 * dao si anume clasa ProductDao.
 */
public class ProdusBll {
    public  void insert(Product c) {
        ProductDao d = new ProductDao();
        d.insert(c);
    }

    public  void delete(int id, String nume) {
        ProductDao d = new ProductDao();
        d.delete(id, nume);
    }

    public  void edit(Product c) {
        ProductDao d = new ProductDao();
        d.edit(c);
    }

    public ResultSet rezSelect() {
        ProductDao d = new ProductDao();
        return d.rezSelect();
    }

    public List<Product> createObjects(){
        ProductDao c=new ProductDao();
        return c.createObjects();
    }
}
