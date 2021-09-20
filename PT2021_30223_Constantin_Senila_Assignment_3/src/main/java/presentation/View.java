package presentation;

import bll.ClientBll;
import bll.OrderBll;
import bll.ProdusBll;

import model.Clienti;
import model.Orders;
import model.Product;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 * Aceasta clasa reprezinta clasa in care s-a construit interfata grafica si actiunea butoanelor. Tot aici este implementata si partea de
 * editare, adaugare si stergere a produselor si a clientilor. Pentru clienti si pentru produse este facuta afisarea acestora in tabel.
 * De asemenea se poate efectua si comanda in panelul Orders.
 */
public class View {

    private JFrame frame;

    JTabbedPane tabbedPane;

    JPanel panelC;
    JPanel panelClienti;
    JLayeredPane layeredPane;
    JPanel panelClientiEdit;
    JPanel panelClientiNew;
    JPanel panelClientiDelete;
    JPanel panelClientiAfis;

    JPanel panelP;
    JPanel panelProduse;
    JLayeredPane layeredPane_1;
    JPanel panelEditProdus;
    JPanel panelNewProdus;
    JPanel panelDeleteProdus;
    JPanel panelAfisProduse;

    JPanel panelO;
    JPanel panelOrders;
    JLayeredPane layeredPane_2;
    JPanel panelNewOrder;
    private JTextField txtNume;
    private JTextField txtAdresa;
    private JTextField txtEmail;
    private JLabel lblNewProdus;
    private JLabel lblNumeProdus;
    private JLabel lblCantitate;
    private JLabel lblPret;
    private JTextField txtNumeProdus;
    private JTextField txtCantitateProdus;
    private JTextField txtPretProdus;
    private JButton btnAddProdus;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JLabel lblNewLabel;
    private JLabel lblIdCD;
    private JLabel LblNumeCD;
    private JComboBox<String> comboBoxIdClient;
    private JTextField txtNumeCD;
    private JButton btnDeleteC;
    private JLabel lblDeleteProdus;
    private JLabel lblIdProdus;
    private JLabel lblNumeProdus_1;
    private JComboBox<String> comboBoxIdProdus;
    private JTextField txtNumePD;
    private JButton btnDeleteP;
    private JLabel lblNewLabel_1;
    private JLabel lblIdCE;
    private JLabel lblNumeCE;
    private JLabel lblAdresaE;
    private JLabel lblEmailE;
    private JComboBox<String> comboBoxIdCE;
    private JTextField txtNumeE;
    private JTextField txtAdresaE;
    private JTextField txtEmailE;
    private JButton btnEditButton;
    private JLabel lblNewLabel_2;
    private JLabel lblIdProdus_1;
    private JLabel lblNumeProdus_3;
    private JLabel lblCantitate_2;
    private JLabel lblPret_1;
    private JComboBox<String> comboBoxPE;
    private JTextField txtNumePE;
    private JTextField txtCantitatePE;
    private JTextField txtPretPE;
    private JButton btnEdittProdus;
    private JLabel lblNewLabel_4;
    private JTable tableClient;
    private JScrollPane scrollPaneClient;
    private JTable tableProdus;
    private JScrollPane scrollPaneProdus;

    public View() throws SQLException, IllegalAccessException {
        frame = new JFrame();
        frame.setBounds(100, 100, 925, 615);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().setLayout(null);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(10, 44, 889, 519);
        frame.getContentPane().add(tabbedPane);

        initializeClienti();
        initializeProduse();
        initializeOrder();
        butoaneClienti();
        butoaneProduse();

        JLabel lblTitlu = new JLabel("Order Management");
        lblTitlu.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
        lblTitlu.setForeground(Color.BLACK);
        lblTitlu.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitlu.setBounds(0, 0, 921, 44);
        frame.getContentPane().add(lblTitlu);
        frame.setVisible(true);

    }

    private void butoaneProduse() {
        JButton btnEditProdus = new JButton("Edit Produs");
        btnEditProdus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchPanels(layeredPane_1, panelEditProdus);
            }
        });
        btnEditProdus.setForeground(Color.BLACK);
        btnEditProdus.setFont(new Font("Times New Roman", Font.BOLD, 15));
        btnEditProdus.setBounds(143, 0, 139, 31);
        panelP.add(btnEditProdus);

        JButton btnNewProdus = new JButton("New Produs");
        btnNewProdus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchPanels(layeredPane_1, panelNewProdus);
            }
        });
        btnNewProdus.setForeground(Color.BLACK);
        btnNewProdus.setFont(new Font("Times New Roman", Font.BOLD, 15));
        btnNewProdus.setBounds(286, 0, 139, 31);
        panelP.add(btnNewProdus);

        JButton btnDeleteProdus = new JButton("Delete Produs");
        btnDeleteProdus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchPanels(layeredPane_1, panelDeleteProdus);
            }
        });
        btnDeleteProdus.setForeground(Color.BLACK);
        btnDeleteProdus.setFont(new Font("Times New Roman", Font.BOLD, 15));
        btnDeleteProdus.setBounds(427, 0, 139, 31);
        panelP.add(btnDeleteProdus);

        JButton btnProduse = new JButton("Produse");
        btnProduse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchPanels(layeredPane_1, panelAfisProduse);
            }
        });
        btnProduse.setForeground(Color.BLACK);
        btnProduse.setFont(new Font("Times New Roman", Font.BOLD, 15));
        btnProduse.setBounds(569, 0, 139, 31);
        panelP.add(btnProduse);

    }

    private void butoaneClienti() {
        JButton btnEditClient = new JButton("Edit Client");
        btnEditClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchPanels(layeredPane, panelClientiEdit);

            }
        });
        btnEditClient.setForeground(Color.BLACK);
        btnEditClient.setFont(new Font("Times New Roman", Font.BOLD, 15));
        btnEditClient.setBounds(208, 0, 123, 31);
        panelC.add(btnEditClient);

        JButton btnNewClient = new JButton("New Client");
        btnNewClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchPanels(layeredPane, panelClientiNew);
            }
        });
        btnNewClient.setForeground(Color.BLACK);
        btnNewClient.setFont(new Font("Times New Roman", Font.BOLD, 15));
        btnNewClient.setBounds(334, 0, 123, 31);
        panelC.add(btnNewClient);

        JButton btnDeleteClient = new JButton("Delete Client");
        btnDeleteClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchPanels(layeredPane, panelClientiDelete);
            }
        });
        btnDeleteClient.setForeground(Color.BLACK);
        btnDeleteClient.setFont(new Font("Times New Roman", Font.BOLD, 15));
        btnDeleteClient.setBounds(460, 0, 123, 31);
        panelC.add(btnDeleteClient);

        JButton btnAfisareClienti = new JButton("Clienti");
        btnAfisareClienti.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchPanels(layeredPane, panelClientiAfis);
            }
        });
        btnAfisareClienti.setForeground(Color.BLACK);
        btnAfisareClienti.setFont(new Font("Times New Roman", Font.BOLD, 15));
        btnAfisareClienti.setBounds(586, 0, 123, 31);
        panelC.add(btnAfisareClienti);
    }

    private void switchPanels(JLayeredPane jlpanel, JPanel panel) {
        jlpanel.removeAll();
        jlpanel.add(panel);
        jlpanel.repaint();
        jlpanel.revalidate();
    }

    public <T> void tabel(List<T> list, JTable table) throws IllegalAccessException {
        DefaultTableModel model = new DefaultTableModel();
        String[] col = new String[4];

        if (list.size() != 0) {
            Field[] field = list.get(0).getClass().getDeclaredFields();
            for (int i = 0; i < field.length; i++) {
                col[i] = field[i].getName();
            }
            Object[] column = col;
            final Object[] row = new Object[4];
            model.setColumnIdentifiers(column);
            table.setModel(model);

            for (T lst : list) {
                Field[] fieldL = lst.getClass().getDeclaredFields();
                for (int i = 0; i < fieldL.length; i++) {
                    fieldL[i].setAccessible(true);
                    row[i] = fieldL[i].get(lst);
                }
                model.addRow(row);
            }
        } else {
//            T t = null;
//            Field[] f=t.getClass().getDeclaredFields();
//            for(int i=1;i<f.length;i++)
//                col[i-1]=f[i].getName();
//            model.setColumnIdentifiers(col);
            table.setModel(model);
            model.addRow(new Object[4]);
        }
    }

    public void comboSet(ResultSet r, JComboBox<String> combo, String s) throws SQLException {
        combo.removeAllItems();
        String cmbE;
        while (r.next()) {
            cmbE = r.getString(s);
            combo.addItem(cmbE);
        }
    }

    private void initializeClienti() throws SQLException, IllegalAccessException {
        ClientBll c = new ClientBll();
        ResultSet r = c.rezSelect();

        panelC = new JPanel();
        tabbedPane.addTab("Client", null, panelC, null);
        tabbedPane.setBackgroundAt(0, Color.LIGHT_GRAY);
        panelC.setLayout(null);

        panelClienti = new JPanel();
        panelClienti.setBounds(0, 34, 884, 458);
        panelC.add(panelClienti);
        panelClienti.setLayout(new CardLayout(0, 0));

        layeredPane = new JLayeredPane();
        panelClienti.add(layeredPane, "name_8351013508100");
        layeredPane.setLayout(new CardLayout(0, 0));

        panelClientiEdit = new JPanel();
        panelClientiEdit.setBackground(Color.WHITE);
        layeredPane.add(panelClientiEdit, "name_8383220025900");
        panelClientiEdit.setLayout(null);

        lblNewLabel_1 = new JLabel("Edit Client");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 19));
        lblNewLabel_1.setForeground(Color.BLACK);
        lblNewLabel_1.setBounds(10, 56, 864, 42);
        panelClientiEdit.add(lblNewLabel_1);

        lblIdCE = new JLabel("Id Client:");
        lblIdCE.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lblIdCE.setForeground(Color.BLACK);
        lblIdCE.setBounds(80, 150, 114, 29);
        panelClientiEdit.add(lblIdCE);

        lblNumeCE = new JLabel("Nume Client:");
        lblNumeCE.setForeground(Color.BLACK);
        lblNumeCE.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lblNumeCE.setBounds(80, 189, 114, 29);
        panelClientiEdit.add(lblNumeCE);

        lblAdresaE = new JLabel("Adresa:");
        lblAdresaE.setForeground(Color.BLACK);
        lblAdresaE.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lblAdresaE.setBounds(80, 228, 114, 29);
        panelClientiEdit.add(lblAdresaE);

        lblEmailE = new JLabel("Email:");
        lblEmailE.setForeground(Color.BLACK);
        lblEmailE.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lblEmailE.setBounds(80, 267, 114, 29);
        panelClientiEdit.add(lblEmailE);

        comboBoxIdCE = new JComboBox<>();
        comboSet(r, comboBoxIdCE, "idClienti");
        comboBoxIdCE.setBounds(217, 150, 43, 27);
        panelClientiEdit.add(comboBoxIdCE);

        txtNumeE = new JTextField();
        txtNumeE.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        txtNumeE.setBounds(215, 189, 309, 29);
        panelClientiEdit.add(txtNumeE);
        txtNumeE.setColumns(10);

        txtAdresaE = new JTextField();
        txtAdresaE.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        txtAdresaE.setColumns(10);
        txtAdresaE.setBounds(217, 235, 309, 29);
        panelClientiEdit.add(txtAdresaE);

        txtEmailE = new JTextField();
        txtEmailE.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        txtEmailE.setForeground(Color.BLACK);
        txtEmailE.setColumns(10);
        txtEmailE.setBounds(215, 274, 309, 29);
        panelClientiEdit.add(txtEmailE);

        btnEditButton = new JButton("Edit");
        btnEditButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // System.out.println(comboBoxIdCE.getSelectedItem()+" "+txtNumeE.getText());
                if (txtNumeE.getText().equals("") || txtEmailE.getText().equals("") || txtAdresaE.getText().equals(""))
                    showMsg("Introduceti date in toate campurile!");
                else {
                    Clienti cEdit = new Clienti(Integer.valueOf(Integer.parseInt((String) comboBoxIdCE.getSelectedItem())), txtNumeE.getText(), txtAdresaE.getText(), txtEmailE.getText());

                    ClientBll cDao = new ClientBll();
                    cDao.edit(cEdit);
                }
                txtNumeE.setText("");
                txtAdresaE.setText("");
                txtEmailE.setText("");
                try {
                    tabel(c.createObjects(), tableClient);

                } catch (IllegalAccessException illegalAccessException) {
                    illegalAccessException.printStackTrace();
                }
            }
        });
        btnEditButton.setForeground(Color.BLACK);
        btnEditButton.setFont(new Font("Times New Roman", Font.BOLD, 17));
        btnEditButton.setBounds(304, 323, 87, 29);
        panelClientiEdit.add(btnEditButton);

        panelClientiNew = new JPanel();
        layeredPane.add(panelClientiNew, "name_8411809919100");
        panelClientiNew.setLayout(null);

        JLabel lblNewClient = new JLabel("New Client");
        lblNewClient.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewClient.setFont(new Font("Times New Roman", Font.BOLD, 19));
        lblNewClient.setForeground(Color.BLACK);
        lblNewClient.setBounds(10, 39, 864, 42);
        panelClientiNew.add(lblNewClient);

        JLabel lblNume = new JLabel("Nume");
        lblNume.setForeground(Color.BLACK);
        lblNume.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lblNume.setBounds(124, 118, 118, 33);
        panelClientiNew.add(lblNume);

        JLabel lblAdresa = new JLabel("Adresa:");
        lblAdresa.setForeground(Color.BLACK);
        lblAdresa.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lblAdresa.setBounds(124, 161, 118, 33);
        panelClientiNew.add(lblAdresa);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setForeground(Color.BLACK);
        lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lblEmail.setBounds(124, 208, 118, 33);
        panelClientiNew.add(lblEmail);

        txtNume = new JTextField();
        txtNume.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        txtNume.setForeground(Color.BLACK);
        txtNume.setBounds(252, 118, 418, 33);
        panelClientiNew.add(txtNume);
        txtNume.setColumns(10);

        txtAdresa = new JTextField();
        txtAdresa.setForeground(Color.BLACK);
        txtAdresa.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        txtAdresa.setColumns(10);
        txtAdresa.setBounds(252, 161, 418, 33);
        panelClientiNew.add(txtAdresa);

        txtEmail = new JTextField();
        txtEmail.setForeground(Color.BLACK);
        txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        txtEmail.setColumns(10);
        txtEmail.setBounds(252, 208, 418, 33);
        panelClientiNew.add(txtEmail);

        JButton btnAddClient = new JButton("Add");
        btnAddClient.addActionListener(e -> {
            if (txtNume.getText().equals("") || txtEmail.getText().equals("") || txtAdresa.getText().equals(""))
                showMsg("Introduceti date in toate campurile!");
            else {
                Clienti cEdit = new Clienti(txtNume.getText(), txtAdresa.getText(), txtEmail.getText());

                ClientBll cDao = new ClientBll();
                cDao.insert(cEdit);
            }
            txtNume.setText("");
            txtAdresa.setText("");
            txtEmail.setText("");
            try {
                tabel(c.createObjects(), tableClient);
                comboSet(c.rezSelect(), comboBoxIdClient, "idClienti");
                comboSet(c.rezSelect(), comboBoxIdCE, "idClienti");
            } catch (IllegalAccessException | SQLException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        });
        btnAddClient.setForeground(Color.BLACK);
        btnAddClient.setFont(new Font("Times New Roman", Font.BOLD, 17));
        btnAddClient.setBounds(401, 278, 98, 33);
        panelClientiNew.add(btnAddClient);

        panelClientiDelete = new JPanel();
        layeredPane.add(panelClientiDelete, "name_8415530312499");
        panelClientiDelete.setLayout(null);

        lblNewLabel = new JLabel("Delete Client");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 19));
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(10, 66, 864, 44);
        panelClientiDelete.add(lblNewLabel);

        lblIdCD = new JLabel("Id Client:");
        lblIdCD.setForeground(Color.BLACK);
        lblIdCD.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lblIdCD.setBounds(213, 149, 106, 31);
        panelClientiDelete.add(lblIdCD);

        LblNumeCD = new JLabel("Nume Client:");
        LblNumeCD.setForeground(Color.BLACK);
        LblNumeCD.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        LblNumeCD.setBounds(213, 190, 106, 31);
        panelClientiDelete.add(LblNumeCD);

        comboBoxIdClient = new JComboBox<>();
        r = c.rezSelect();
        comboSet(r, comboBoxIdClient, "idClienti");

        comboBoxIdClient.setForeground(Color.BLACK);
        comboBoxIdClient.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        comboBoxIdClient.setBounds(329, 149, 51, 31);
        panelClientiDelete.add(comboBoxIdClient);

        txtNumeCD = new JTextField();
        txtNumeCD.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        txtNumeCD.setForeground(Color.BLACK);
        txtNumeCD.setBounds(328, 190, 330, 31);
        panelClientiDelete.add(txtNumeCD);
        txtNumeCD.setColumns(10);

        btnDeleteC = new JButton("Delete");
        btnDeleteC.addActionListener(e -> {
            if (comboBoxIdClient.getSelectedItem().equals("") || txtNumeCD.getText().equals(""))
                showMsg("Introduceti date in toate campurile!");
            else {
                ClientBll cDao = new ClientBll();
                cDao.delete(Integer.valueOf(Integer.parseInt((String) comboBoxIdClient.getSelectedItem())), txtNumeCD.getText());
            }
            txtNumeCD.setText("");
            try {
                tabel(c.createObjects(), tableClient);
                comboSet(c.rezSelect(), comboBoxIdClient, "idClienti");
                comboSet(c.rezSelect(), comboBoxIdCE, "idClienti");
            } catch (IllegalAccessException | SQLException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        });
        btnDeleteC.setForeground(Color.BLACK);
        btnDeleteC.setFont(new Font("Times New Roman", Font.BOLD, 17));
        btnDeleteC.setBounds(381, 253, 98, 31);
        panelClientiDelete.add(btnDeleteC);

        panelClientiAfis = new JPanel();
        layeredPane.add(panelClientiAfis, "name_8418528201800");
        panelClientiAfis.setLayout(null);

        lblNewLabel_4 = new JLabel("Clienti");
        lblNewLabel_4.setForeground(Color.BLACK);
        lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 19));
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4.setBounds(10, 10, 864, 39);
        panelClientiAfis.add(lblNewLabel_4);

        scrollPaneClient = new JScrollPane();
        scrollPaneClient.setBounds(10, 48, 864, 400);
        panelClientiAfis.add(scrollPaneClient);

        tableClient = new JTable();
        tableClient.setEnabled(false);
        tableClient.setForeground(Color.BLACK);
        tableClient.setFont(new Font("Times New Roman", Font.PLAIN, 15));

        scrollPaneClient.setViewportView(tableClient);
        tabbedPane.setForegroundAt(0, Color.BLACK);
        tabbedPane.setEnabledAt(0, true);

        tabel(c.createObjects(), tableClient);
    }

    public static void showMsg(String s) {
        JLabel allertMsg = new JLabel(s);
        allertMsg.setFont(new Font("Tahoma", Font.PLAIN, 20));
        JOptionPane.showMessageDialog(null, allertMsg);
    }

    private void initializeProduse() throws SQLException, IllegalAccessException {
        panelP = new JPanel();
        tabbedPane.addTab("Produs", null, panelP, null);
        tabbedPane.setBackgroundAt(1, Color.LIGHT_GRAY);
        panelP.setLayout(null);

        panelProduse = new JPanel();
        panelProduse.setBounds(0, 34, 884, 458);
        panelP.add(panelProduse);
        panelProduse.setLayout(new CardLayout(0, 0));

        layeredPane_1 = new JLayeredPane();
        panelProduse.add(layeredPane_1, "name_8898674160900");
        layeredPane_1.setLayout(new CardLayout(0, 0));

        panelEditProdus = new JPanel();
        layeredPane_1.add(panelEditProdus, "name_8910918328400");
        panelEditProdus.setLayout(null);

        lblNewLabel_2 = new JLabel("Edit Produs");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setForeground(Color.BLACK);
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 19));
        lblNewLabel_2.setBounds(10, 57, 864, 42);
        panelEditProdus.add(lblNewLabel_2);

        lblIdProdus_1 = new JLabel("Id Produs:");
        lblIdProdus_1.setForeground(Color.BLACK);
        lblIdProdus_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lblIdProdus_1.setBounds(80, 151, 114, 29);
        panelEditProdus.add(lblIdProdus_1);

        lblNumeProdus_3 = new JLabel("Nume Produs:");
        lblNumeProdus_3.setForeground(Color.BLACK);
        lblNumeProdus_3.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lblNumeProdus_3.setBounds(80, 190, 114, 29);
        panelEditProdus.add(lblNumeProdus_3);

        lblCantitate_2 = new JLabel("Cantitate:");
        lblCantitate_2.setForeground(Color.BLACK);
        lblCantitate_2.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lblCantitate_2.setBounds(80, 229, 114, 29);
        panelEditProdus.add(lblCantitate_2);

        lblPret_1 = new JLabel("Pret:");
        lblPret_1.setForeground(Color.BLACK);
        lblPret_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lblPret_1.setBounds(80, 268, 114, 29);
        panelEditProdus.add(lblPret_1);

        comboBoxPE = new JComboBox<>();
        ProdusBll p = new ProdusBll();
        ResultSet r = p.rezSelect();
        comboSet(r, comboBoxPE, "idProduct");

        comboBoxPE.setBounds(217, 151, 43, 27);
        panelEditProdus.add(comboBoxPE);

        txtNumePE = new JTextField();
        txtNumePE.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        txtNumePE.setColumns(10);
        txtNumePE.setBounds(215, 190, 309, 29);
        panelEditProdus.add(txtNumePE);

        txtCantitatePE = new JTextField();
        txtCantitatePE.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        txtCantitatePE.setColumns(10);
        txtCantitatePE.setBounds(217, 236, 104, 29);
        panelEditProdus.add(txtCantitatePE);

        txtPretPE = new JTextField();
        txtPretPE.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        txtPretPE.setForeground(Color.BLACK);
        txtPretPE.setColumns(10);
        txtPretPE.setBounds(215, 275, 106, 29);
        panelEditProdus.add(txtPretPE);

        btnEdittProdus = new JButton("Edit");
        btnEdittProdus.addActionListener(e -> {
            if (txtNumePE.getText().equals("") || txtCantitatePE.getText().equals("") || txtPretPE.getText().equals(""))
                showMsg("Introduceti date in toate campurile!");
            else {
                Product cEdit = new Product(Integer.valueOf(Integer.parseInt((String) comboBoxPE.getSelectedItem())), txtNumePE.getText(), Integer.valueOf(Integer.parseInt((String) txtCantitatePE.getText())), Double.valueOf(Double.parseDouble((String) txtPretPE.getText())));
                //System.out.println(cEdit.getIdClienti());
                ProdusBll cDao = new ProdusBll();
                cDao.edit(cEdit);
            }
            txtNumePE.setText("");
            txtCantitatePE.setText("");
            txtPretPE.setText("");

            try {
                tabel(p.createObjects(), tableProdus);
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        });
        btnEdittProdus.setForeground(Color.BLACK);
        btnEdittProdus.setFont(new Font("Times New Roman", Font.BOLD, 17));
        btnEdittProdus.setBounds(304, 324, 87, 29);
        panelEditProdus.add(btnEdittProdus);

        panelNewProdus = new JPanel();
        layeredPane_1.add(panelNewProdus, "name_8928154963200");
        panelNewProdus.setLayout(null);

        lblNewProdus = new JLabel("New Produs");
        lblNewProdus.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewProdus.setForeground(Color.BLACK);
        lblNewProdus.setFont(new Font("Times New Roman", Font.BOLD, 19));
        lblNewProdus.setBounds(10, 67, 864, 42);
        panelNewProdus.add(lblNewProdus);

        lblNumeProdus = new JLabel("Nume Produs:");
        lblNumeProdus.setForeground(Color.BLACK);
        lblNumeProdus.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lblNumeProdus.setBounds(124, 146, 118, 33);
        panelNewProdus.add(lblNumeProdus);

        lblCantitate = new JLabel("Cantitate:");
        lblCantitate.setForeground(Color.BLACK);
        lblCantitate.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lblCantitate.setBounds(124, 189, 118, 33);
        panelNewProdus.add(lblCantitate);

        lblPret = new JLabel("Pret:");
        lblPret.setForeground(Color.BLACK);
        lblPret.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lblPret.setBounds(124, 236, 118, 33);
        panelNewProdus.add(lblPret);

        txtNumeProdus = new JTextField();
        txtNumeProdus.setForeground(Color.BLACK);
        txtNumeProdus.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        txtNumeProdus.setColumns(10);
        txtNumeProdus.setBounds(252, 146, 418, 33);
        panelNewProdus.add(txtNumeProdus);

        txtCantitateProdus = new JTextField();
        txtCantitateProdus.setForeground(Color.BLACK);
        txtCantitateProdus.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        txtCantitateProdus.setColumns(10);
        txtCantitateProdus.setBounds(252, 189, 110, 33);
        panelNewProdus.add(txtCantitateProdus);

        txtPretProdus = new JTextField();
        txtPretProdus.setForeground(Color.BLACK);
        txtPretProdus.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        txtPretProdus.setColumns(10);
        txtPretProdus.setBounds(252, 236, 110, 33);
        panelNewProdus.add(txtPretProdus);

        btnAddProdus = new JButton("Add");
        btnAddProdus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtNumeProdus.getText().equals("") || txtCantitateProdus.getText().equals("") || txtPretProdus.getText().equals(""))
                    showMsg("Introduceti date in toate campurile!");
                else {
                    Product cEdit = new Product(txtNumeProdus.getText(), Integer.valueOf(Integer.parseInt((String) txtCantitateProdus.getText())), Double.valueOf(Double.parseDouble((String) txtPretProdus.getText())));
                    //System.out.println(cEdit.getIdClienti());
                    ProdusBll cDao = new ProdusBll();
                    cDao.insert(cEdit);
                }
                txtNumeProdus.setText("");
                txtCantitateProdus.setText("");
                txtPretProdus.setText("");

                try {
                    tabel(p.createObjects(), tableProdus);
                    comboSet(p.rezSelect(), comboBoxIdProdus, "idProduct");
                    comboSet(p.rezSelect(), comboBoxPE, "idProduct");
                } catch (IllegalAccessException | SQLException illegalAccessException) {
                    illegalAccessException.printStackTrace();
                }


            }
        });
        btnAddProdus.setForeground(Color.BLACK);
        btnAddProdus.setFont(new Font("Times New Roman", Font.BOLD, 17));
        btnAddProdus.setBounds(401, 306, 98, 33);
        panelNewProdus.add(btnAddProdus);

        panelDeleteProdus = new JPanel();
        layeredPane_1.add(panelDeleteProdus, "name_8941780980100");
        panelDeleteProdus.setLayout(null);

        lblDeleteProdus = new JLabel("Delete Produs");
        lblDeleteProdus.setHorizontalAlignment(SwingConstants.CENTER);
        lblDeleteProdus.setForeground(Color.BLACK);
        lblDeleteProdus.setFont(new Font("Times New Roman", Font.BOLD, 19));
        lblDeleteProdus.setBounds(10, 84, 864, 44);
        panelDeleteProdus.add(lblDeleteProdus);

        lblIdProdus = new JLabel("Id Produs:");
        lblIdProdus.setForeground(Color.BLACK);
        lblIdProdus.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lblIdProdus.setBounds(213, 167, 106, 31);
        panelDeleteProdus.add(lblIdProdus);

        lblNumeProdus_1 = new JLabel("Nume Produs:");
        lblNumeProdus_1.setForeground(Color.BLACK);
        lblNumeProdus_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lblNumeProdus_1.setBounds(213, 208, 106, 31);
        panelDeleteProdus.add(lblNumeProdus_1);

        comboBoxIdProdus = new JComboBox<>();
        r = p.rezSelect();
        comboSet(r, comboBoxIdProdus, "idProduct");

        comboBoxIdProdus.setForeground(Color.BLACK);
        comboBoxIdProdus.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        comboBoxIdProdus.setBounds(329, 167, 51, 31);
        panelDeleteProdus.add(comboBoxIdProdus);

        txtNumePD = new JTextField();
        txtNumePD.setForeground(Color.BLACK);
        txtNumePD.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        txtNumePD.setColumns(10);
        txtNumePD.setBounds(328, 208, 330, 31);
        panelDeleteProdus.add(txtNumePD);

        btnDeleteP = new JButton("Delete");
        btnDeleteP.addActionListener(e -> {
            if (txtNumePD.getText().equals(""))
                showMsg("Introduceti date in toate campurile!");
            else {
                ProdusBll cDao = new ProdusBll();
                cDao.delete(Integer.valueOf(Integer.parseInt((String) comboBoxIdProdus.getSelectedItem())), txtNumePD.getText());
            }
            txtNumePD.setText("");

            try {
                tabel(p.createObjects(), tableProdus);
                comboSet(p.rezSelect(), comboBoxIdProdus, "idProduct");
                comboSet(p.rezSelect(), comboBoxPE, "idProduct");
            } catch (IllegalAccessException | SQLException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        });
        btnDeleteP.setForeground(Color.BLACK);
        btnDeleteP.setFont(new Font("Times New Roman", Font.BOLD, 17));
        btnDeleteP.setBounds(381, 271, 98, 31);
        panelDeleteProdus.add(btnDeleteP);

        panelAfisProduse = new JPanel();
        layeredPane_1.add(panelAfisProduse, "name_8946608438600");
        panelAfisProduse.setLayout(null);

        JLabel lblNewLabel_4_1 = new JLabel("Produse");
        lblNewLabel_4_1.setBounds(10, 10, 864, 39);
        lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4_1.setForeground(Color.BLACK);
        lblNewLabel_4_1.setFont(new Font("Times New Roman", Font.BOLD, 19));
        panelAfisProduse.add(lblNewLabel_4_1);

        scrollPaneProdus = new JScrollPane();
        scrollPaneProdus.setBounds(10, 48, 864, 400);
        panelAfisProduse.add(scrollPaneProdus);

        tableProdus = new JTable();
        tableProdus.setEnabled(false);
        tableProdus.setForeground(Color.BLACK);
        tableProdus.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        scrollPaneProdus.setViewportView(tableProdus);
        tabbedPane.setForegroundAt(1, Color.BLACK);
        tabbedPane.setEnabledAt(1, true);

        tabel(p.createObjects(), tableProdus);
    }

    private void initializeOrder() {
        panelO = new JPanel();
        tabbedPane.addTab("Order", null, panelO, null);
        panelO.setLayout(null);

        panelOrders = new JPanel();
        panelOrders.setBounds(0, 34, 884, 458);
        panelO.add(panelOrders);
        panelOrders.setLayout(new CardLayout(0, 0));

        layeredPane_2 = new JLayeredPane();
        panelOrders.add(layeredPane_2, "name_9079582801600");
        layeredPane_2.setLayout(new CardLayout(0, 0));

        panelNewOrder = new JPanel();
        layeredPane_2.add(panelNewOrder, "name_9149762602200");
        panelNewOrder.setLayout(null);

        JLabel lblNewOrder = new JLabel("New Order");
        lblNewOrder.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewOrder.setForeground(Color.BLACK);
        lblNewOrder.setFont(new Font("Times New Roman", Font.BOLD, 19));
        lblNewOrder.setBounds(10, 83, 864, 42);
        panelNewOrder.add(lblNewOrder);

        JLabel lblNumeOrder = new JLabel("Nume Produs:");
        lblNumeOrder.setForeground(Color.BLACK);
        lblNumeOrder.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lblNumeOrder.setBounds(124, 162, 118, 33);
        panelNewOrder.add(lblNumeOrder);

        JLabel lblNumeClient = new JLabel("Nume Client:");
        lblNumeClient.setForeground(Color.BLACK);
        lblNumeClient.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lblNumeClient.setBounds(124, 205, 118, 33);
        panelNewOrder.add(lblNumeClient);

        JLabel lblCantitate_1 = new JLabel("Cantitate:");
        lblCantitate_1.setForeground(Color.BLACK);
        lblCantitate_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lblCantitate_1.setBounds(124, 252, 118, 33);
        panelNewOrder.add(lblCantitate_1);

        textField = new JTextField();
        textField.setForeground(Color.BLACK);
        textField.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        textField.setColumns(10);
        textField.setBounds(252, 162, 418, 33);
        panelNewOrder.add(textField);

        textField_1 = new JTextField();
        textField_1.setForeground(Color.BLACK);
        textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        textField_1.setColumns(10);
        textField_1.setBounds(252, 205, 418, 33);
        panelNewOrder.add(textField_1);

        textField_2 = new JTextField();
        textField_2.setForeground(Color.BLACK);
        textField_2.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        textField_2.setColumns(10);
        textField_2.setBounds(252, 252, 110, 33);
        panelNewOrder.add(textField_2);

        JButton btnAddProdus_1 = new JButton("Add");
        btnAddProdus_1.addActionListener(e -> {

            if (textField.getText().equals("") || textField_1.getText().equals("") || textField_2.getText().equals(""))
                showMsg("Introduceti date in toate campurile!");
            else {
                Orders cEdit = new Orders(textField_1.getText(), textField.getText(), Integer.parseInt(textField_2.getText()));
                OrderBll cDao = new OrderBll();
                cDao.insert(cEdit);
                try {
                    if (cDao.nrPoduse(cEdit) < cEdit.getTotal())
                        showMsg("Nu este disponibil numarul de produse selectat!");
                    else {
                        ProdusBll p = new ProdusBll();
                        try {
                            cDao.updt(cEdit);
                            tabel(p.createObjects(), tableProdus);
                        } catch (IllegalAccessException | SQLException illegalAccessException) {
                            illegalAccessException.printStackTrace();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                textField.setText("");
                textField_1.setText("");
                textField_2.setText("");
            }

            });
            btnAddProdus_1.setForeground(Color.BLACK);
            btnAddProdus_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
            btnAddProdus_1.setBounds(401, 322, 98, 33);
            panelNewOrder.add(btnAddProdus_1);
            tabbedPane.setEnabledAt(2, true);
            tabbedPane.setBackgroundAt(2, Color.LIGHT_GRAY);
            tabbedPane.setForegroundAt(2, Color.BLACK);


        }
    }

