package bll;
import dao.OrderDao;
import model.Orders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

//import java.sql.ResultSet;
//import java.util.List;

/**
 * In aceasta clasa am clasa insert care apeleaza metoda de insert din dao si metodele: updt, nrProduse si afisareFacturaTxt
 * care apeleaza metodele din pachetul dao si anume din clasa OrderDao.
 */
public class OrderBll {
    public  void insert(Orders c) {
        OrderDao d = new OrderDao();
        d.insert(c);
    }
    public void updt(Orders o) throws SQLException, IOException {
        OrderDao odt=new OrderDao();
        odt.scadereProd(o);
        afisareFacturaTxt(o);
       //System.out.println(o.toString());
    }
    public int nrPoduse(Orders s) throws SQLException {
        OrderDao odt=new OrderDao();
        return odt.nrProd(s);
    }
    private void afisareFacturaTxt(Orders o) throws IOException {
        OrderDao odt=new OrderDao();
        odt.maxId(o);
        int nr=o.getIdOrders();
        String s="Factura";
        s=s+nr+".txt";

        File outF=new File(s);
        try {
            outF.createNewFile();
        } catch (Exception e) {
            throw new FileNotFoundException("Nu s-a gasit fisierul si nici nu s-a putut creea!");
        }
        FileWriter fw = null;
        try {
            fw=new FileWriter(outF.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            assert fw != null;
            fw.write(o.toString());
            System.out.println(o.toString());
        } catch (Exception ignored) {
        }

        try {
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("id maxim este: "+o.getIdOrders());
    }

//    public  void delete(int id, String nume) {
//        OrderDao d = new OrderDao();
//        d.delete(id, nume);
//    }
//
//    public  void edit(Orders c) {
//        OrderDao d = new OrderDao();
//        d.edit(c);
//    }
//
//    public ResultSet rezSelect() {
//        OrderDao d = new OrderDao();
//        return d.rezSelect();
//    }
//
//    public List<Orders> createObjects(){
//        OrderDao c=new OrderDao();
//        return c.createObjects();
//    }

}
