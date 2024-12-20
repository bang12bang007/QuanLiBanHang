/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import dao.Item_Dao;
import dao.Order_Dao;
import entity.Customer;
import entity.Order;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import javax.swing.border.EmptyBorder;

import javax.swing.table.DefaultTableModel;

import utils.AppUtils.*;
import static utils.AppUtils.formatMoney;
import static utils.AppUtils.formatTextField;

import javax.swing.JPanel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;

import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import utils.AppUtils;
import static utils.AppUtils.parseVND;



/**
 *
 * @author HP
 */
public class UI_BanHang extends javax.swing.JPanel {
    private DefaultTableModel productDf,cartDF;
    private Order od = new Order();
    private Order_Dao od_dao = new Order_Dao();
    private Item_Dao dao = new Item_Dao();
    private String status;
    /**
     * Creates new form UI_BanHang
     */
    public UI_BanHang() throws SQLException {
        initComponents();
        // Table sản phẩm
        String[] title = {"Mã sản phẩm", "Tên sản phẩm","Giá","Số lượng tồn","Đơn vị"};
        Item_Dao item = new Item_Dao();
        productDf = new DefaultTableModel(title,0){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
            }
        };
        productTable.setModel(productDf);
        try {
            List<Object[]> data = item.getItemFromSQL();
            for (Object[] row : data) {
                productDf.addRow(row);  
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu từ cơ sở dữ liệu: " + e.getMessage());
        }
        order_ID.setText(generateUniqueOrderID());
        // table order
        String[] title_order = {"Mã sản phẩm", "Tên sản phẩm","Số lượng","Giá"};
        cartDF = new DefaultTableModel(title_order,0){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
            }
        };
        cartTable.setModel(cartDF);
        // format textFied:
        formatTextField("Tìm kiếm theo mã sản phẩm",search_txt);
    }
    private String generateInvoiceCode() {
       return "HD" + (int)(Math.random() * 1000000);
    }
    public String generateUniqueOrderID() throws SQLException {
        String orderID;
        do {
            orderID = generateInvoiceCode();
        } while (od_dao.isOrderIDExist(orderID)); 
        return orderID;
    }
    // update số tiền:
    private void updateTotal(){
        double total = 0;
        for(int i =0 ;i < cartTable.getRowCount();i++){
            double price = Double.parseDouble(cartTable.getValueAt(i, 2).toString()) * Double.parseDouble(cartTable.getValueAt(i, 3).toString()) ;
            total += price;
            money.setText(formatMoney(total));
        }
    }
    public boolean searchOrderID(){
        if(search_txt.getText().trim().equals("")){
            JOptionPane.showConfirmDialog(this,"Không tìm thấy");
        }else{
            boolean isFound = false;
            for(int i = 0;i< productTable.getRowCount();i++){
                if(productTable.getValueAt(i, 0).equals(search_txt.getText())){
                   productTable.setRowSelectionInterval(i, i);
                     isFound = true;
                     return true;
                }
            }
            if(!isFound){
               JOptionPane.showConfirmDialog(this, "Không tìm thấy");
               productTable.clearSelection();
            }else{
            }
        }
        return false;
    }
    public int findRow(String id){
        for (int i = 0; i < productTable.getRowCount(); i++) {
            if (productTable.getValueAt(i, 0).toString().equals(id)) {
                return i;
            }
        }
         return -1;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        HoaDon = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableHoaDon = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tongTientxt = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnXuat = new javax.swing.JButton();
        maHDtxt = new javax.swing.JLabel();
        ngayLaptxt = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnThanhToan = new javax.swing.JButton();
        btnBack1 = new javax.swing.JButton();
        search_txt = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        scrollProduct = new javax.swing.JScrollPane();
        productTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        cartTable = new javax.swing.JTable();
        updateCart = new javax.swing.JButton();
        addCart = new javax.swing.JButton();
        delCart = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        order_ID = new javax.swing.JLabel();
        money = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        customID_txt = new javax.swing.JTextField();

        HoaDon.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setText("HÓA ĐƠN BÁN HÀNG");

        tableHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Số lượng", "Giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tableHoaDon);
        if (tableHoaDon.getColumnModel().getColumnCount() > 0) {
            tableHoaDon.getColumnModel().getColumn(0).setPreferredWidth(15);
            tableHoaDon.getColumnModel().getColumn(1).setPreferredWidth(30);
            tableHoaDon.getColumnModel().getColumn(2).setPreferredWidth(30);
            tableHoaDon.getColumnModel().getColumn(3).setPreferredWidth(15);
            tableHoaDon.getColumnModel().getColumn(4).setPreferredWidth(25);
        }

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Mã hóa đơn:");

        jLabel6.setText("Ngày lập:");

        jLabel7.setText("----------------------------------------------------------------------------------------------------------");

        jLabel8.setText("----------------------------------------------------------------------------------------------------------");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel9.setText("Cửa hàng tiện lợi Hàng Mã");

        jLabel10.setText("45 Tân Lập, phường Đông Hòa, Thành phố Dĩ An, tỉnh Bình Dương");

        jLabel11.setText("-----------------------------------------------------------------------------------------------------------");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel12.setText("Tổng tiền");

        jLabel13.setText("Cảm ơn quý khách đã mua hàng tại của hàng của chúng tôi. ");

        jLabel14.setText("----------------------------------------------------------------------------------------------------------");

        btnXuat.setText("XUẤT");
        btnXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout HoaDonLayout = new javax.swing.GroupLayout(HoaDon);
        HoaDon.setLayout(HoaDonLayout);
        HoaDonLayout.setHorizontalGroup(
            HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HoaDonLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(184, 184, 184))
            .addGroup(HoaDonLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(HoaDonLayout.createSequentialGroup()
                        .addGroup(HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(HoaDonLayout.createSequentialGroup()
                                .addGroup(HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(HoaDonLayout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addGap(18, 18, 18)
                                            .addComponent(ngayLaptxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(HoaDonLayout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(maHDtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(HoaDonLayout.createSequentialGroup()
                        .addGroup(HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HoaDonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HoaDonLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(tongTientxt, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HoaDonLayout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(HoaDonLayout.createSequentialGroup()
                .addGroup(HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HoaDonLayout.createSequentialGroup()
                        .addGap(224, 224, 224)
                        .addComponent(btnXuat))
                    .addGroup(HoaDonLayout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        HoaDonLayout.setVerticalGroup(
            HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HoaDonLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(8, 8, 8)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(maHDtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ngayLaptxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 141, Short.MAX_VALUE)
                .addGroup(HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tongTientxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(btnXuat)
                .addGap(24, 24, 24))
        );

        setPreferredSize(new java.awt.Dimension(288, 645));

        mainPanel.setPreferredSize(new java.awt.Dimension(0, 645));

        jPanel3.setBackground(new java.awt.Color(255, 204, 255));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnBack1.setText("Quay lại");
        btnBack1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBack1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnBack1, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        search_txt.setToolTipText("");
        search_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_txtActionPerformed(evt);
            }
        });

        btnSearch.setText("Tìm kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        productTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollProduct.setViewportView(productTable);

        cartTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã sản phâm", "Tên sản phẩm", "Số lượng", "Giá"
            }
        ));
        jScrollPane2.setViewportView(cartTable);

        updateCart.setText("Cập nhập");
        updateCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateCartActionPerformed(evt);
            }
        });

        addCart.setText("Thêm");
        addCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCartActionPerformed(evt);
            }
        });

        delCart.setText("Xóa");
        delCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delCartActionPerformed(evt);
            }
        });

        jLabel1.setText("Mã hóa đơn ");

        jLabel2.setText("Thành tiền ");

        order_ID.setText("ODER12390");

        money.setText("0 VND");

        jLabel3.setText("Mã khách hàng ");

        customID_txt.setText("CUST001");
        customID_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customID_txtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(order_ID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(money, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(customID_txt))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(order_ID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(customID_txt)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(money, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(search_txt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrollProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(addCart, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(delCart, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateCart, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(search_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollProduct))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(delCart, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateCart, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addCart, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE)

        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents
private void showDialogThanhToan(String maHoaDon){
        JDialog dialog = new JDialog();
        dialog.setSize(600, 600);
        dialog.setLocationRelativeTo(null);
        dialog.setTitle("Thanh Toán - Mã Hóa Đơn: " + maHoaDon);
        
        String[] productColumns = {"Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Đơn Giá"};
        DefaultTableModel productModel = new DefaultTableModel(productColumns, 0);

        for(int i =0 ;i< cartTable.getRowCount();i++){;
            Object[] row = {cartTable.getValueAt(i, 0) , cartTable.getValueAt(i, 1) , cartTable.getValueAt(i, 2),cartTable.getValueAt(i, 3)};
            productModel.addRow(row);
        }

        JTable productTable = new JTable(productModel);

        // Tạo bảng và cuộn bảng
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        tablePanel.add(new JScrollPane(productTable), BorderLayout.CENTER);

        // Tính tổng tiền
        double totalAmount = parseVND(money.getText()); 
        JLabel totalLabel = new JLabel("Tổng tiền: " + formatMoney(totalAmount) , SwingConstants.RIGHT);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Tạo các trường nhập tiền thanh toán
        JPanel paymentPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        paymentPanel.setBorder(new EmptyBorder(20, 10, 20, 10));

        JLabel paymentLabel = new JLabel("Tiền khách đưa: ");
        JTextField paymentField = new JTextField();
        JLabel changeLabel = new JLabel("Tiền thối lại: ");
        JLabel changeAmount = new JLabel("0 VND");

        paymentPanel.add(paymentLabel);
        paymentPanel.add(paymentField);
        paymentPanel.add(changeLabel);
        paymentPanel.add(changeAmount);

        paymentField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double paidAmount = Double.parseDouble(paymentField.getText().replace(",", ""));
                    double change = paidAmount - totalAmount;
                    changeAmount.setText(change >= 0 ? String.format("%.0f ", change) + "VND" : "Không đủ tiền");
                } catch (NumberFormatException ex) {
                    changeAmount.setText("Số tiền không hợp lệ");
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        JButton closeButton = new JButton("Đóng");
        closeButton.addActionListener(e -> dialog.dispose());

        JButton okButton = new JButton("Thanh Toán");
        okButton.addActionListener(e -> {
            try {
                double paidAmount = Double.parseDouble(paymentField.getText().replace(",", ""));
                if (paidAmount >= totalAmount) {
                    JOptionPane.showMessageDialog(dialog, "Thanh toán thành công!\nTiền thối lại: " + changeAmount.getText());
                    status = "Đã thanh toán";
                    for(int i =0; i< cartTable.getRowCount();i++){
                        String id = order_ID.getText();
                        String customer_id_check = customID_txt.getText();
                        String customer_id = (customer_id_check ==null)?"CUST000":customer_id_check;
                        LocalDate order_date = LocalDate.now();
                        od = new Order(customer_id);
                        Customer cs = new Customer();
                        cs.setId(customer_id);
                        Double total_amount = utils.AppUtils.parseVND(money.getText()); 
                        Order od = new Order(id,cs, order_date, total_amount, status);
                        dialog.dispose();
                    try {
                        od_dao.addOrder(od);
                    } catch (SQLException ex) {
               ex.printStackTrace();
            }
        }
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Số tiền khách đưa không đủ để thanh toán.");
                    
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Vui lòng nhập số tiền hợp lệ.");
            }
        });

        JButton waitButton = new JButton("Chờ Thanh Toán");
        waitButton.addActionListener(e->{
            status = "Chờ thanh toán";
            String id = order_ID.getText();
            String customer_id_check = customID_txt.getText();
            String customer_id = (customer_id_check ==null)?"CUST000":customer_id_check;
            LocalDate order_date = LocalDate.now();
            od = new Order(customer_id);
            Customer cs = new Customer();
            cs.setId(customer_id);
            Double total_amount = utils.AppUtils.parseVND(money.getText());
            Order od = new Order(id,cs, order_date, total_amount, status);
            dialog.dispose();
            try {
                od_dao.addOrder(od);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            dialog.dispose();
         });
        buttonPanel.add(closeButton);
        buttonPanel.add(okButton);
        buttonPanel.add(waitButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPanel.add(totalLabel);

        mainPanel.add(tablePanel, BorderLayout.CENTER);
        mainPanel.add(totalPanel, BorderLayout.SOUTH);  
        mainPanel.add(paymentPanel, BorderLayout.NORTH); 

        dialog.add(mainPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
    String totalAmountText = tongTientxt.getText().replaceAll(",", "").replaceAll(" VND", "");
    if (totalAmountText.isEmpty()) {
        totalAmountText = "0"; // Hoặc gán một giá trị mặc định nếu cần thiết
    }
    double totalAmount = Double.parseDouble(totalAmountText);
    String formattedMoney = AppUtils.formatMoney(totalAmount);
    tongTientxt.setText(formattedMoney);


    // Định dạng ngày hiện tại để hiển thị trên hóa đơn
    String formattedDate = AppUtils.formatDate(LocalDate.now());
    ngayLaptxt.setText(formattedDate);
    
    // Tạo một JDialog mới để hiển thị HoaDon
    JDialog hoaDonDialog = new JDialog((JFrame) null, "Hóa Đơn Bán Hàng", true);
    hoaDonDialog.setSize(560, 700);  // Đặt kích thước cho dialog
    hoaDonDialog.setLocationRelativeTo(null); // Hiển thị ở giữa màn hình
    
    DefaultTableModel hoaDonModel = (DefaultTableModel) tableHoaDon.getModel();
    hoaDonModel.setRowCount(0);  // Xóa dữ liệu cũ trong bảng

    // Đặt màu nền cho JTable và JScrollPane
    tableHoaDon.setBackground(Color.WHITE);
    jScrollPane3.getViewport().setBackground(Color.WHITE);
    jScrollPane3.setBorder(null);
    tableHoaDon.setShowGrid(false);
    tableHoaDon.setIntercellSpacing(new Dimension(0, 0));

    // Duyệt qua từng dòng trong cartTable và thêm vào tableHoaDon
    for (int i = 0; i < cartTable.getRowCount(); i++) {
        String orderID = cartTable.getValueAt(i, 0).toString();
        String orderName = cartTable.getValueAt(i, 1).toString();
        int quantity = Integer.parseInt(cartTable.getValueAt(i, 2).toString());
        double price = Double.parseDouble(cartTable.getValueAt(i, 3).toString());

        // Tính tổng tiền cho mỗi dòng và định dạng lại
        double totalPrice = quantity * price;
        String formattedTotalPrice = AppUtils.formatMoney(totalPrice);

        // Thêm vào bảng hóa đơn
        hoaDonModel.addRow(new Object[]{i + 1, orderID, orderName, quantity, formattedTotalPrice});
    }

    // Căn giữa nội dung trong tất cả các ô của bảng
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
    for (int i = 0; i < tableHoaDon.getColumnCount(); i++) {
        tableHoaDon.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
    }

    // Căn giữa tiêu đề cột
    DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer)
            tableHoaDon.getTableHeader().getDefaultRenderer();
    headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
    tableHoaDon.getTableHeader().setDefaultRenderer(headerRenderer);

    // Tính tổng tiền của tất cả các dòng và hiển thị vào `tongTientxt`
    double grandTotal = 0;
    for (int i = 0; i < tableHoaDon.getRowCount(); i++) {
        grandTotal += Double.parseDouble(tableHoaDon.getValueAt(i, 4).toString().replaceAll(",", "").replaceAll(" VND", ""));
    }
    String formattedGrandTotal = AppUtils.formatMoney(grandTotal);
    tongTientxt.setText(formattedGrandTotal);

    // Hiển thị panel HoaDon
    hoaDonDialog.add(HoaDon);
    hoaDonDialog.setVisible(true);


        String id = order_ID.getText();
        showDialogThanhToan(id);
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnBack1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack1ActionPerformed

    }//GEN-LAST:event_btnBack1ActionPerformed

    private void updateCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateCartActionPerformed
            int row = cartTable.getSelectedRow();
            if(row != -1){
             String orderID = cartTable.getValueAt(row, 0).toString();
             int productRow = findRow(orderID);
             if(productRow != -1) {
                 int oldCartQuantity = Integer.parseInt(cartTable.getValueAt(row, 2).toString()); 
                 int newCartQuantity = Integer.parseInt(JOptionPane.showInputDialog("Nhập số lượng mới:")); 
                 if(newCartQuantity > 0) {
                     int productQuantity = Integer.parseInt(productTable.getValueAt(productRow, 3).toString());
                     int quantityDifference = newCartQuantity - oldCartQuantity; // Tính sự chênh lệch

                     if(productQuantity >= quantityDifference) {
                         double price = Double.parseDouble(cartTable.getValueAt(row, 3).toString()) * newCartQuantity; 
                         cartTable.setValueAt(newCartQuantity, row, 2);
                         cartTable.setValueAt(price, row, 3);
                         productTable.setValueAt(productQuantity - quantityDifference, productRow, 3);
                         updateTotal(); 
                     } else {
                         JOptionPane.showMessageDialog(this, "Không đủ số lượng sản phẩm trong kho.");
                     }
                 } else {
                     JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ.");
                 }
             } else {
                 JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm tương ứng.");
             }
         } else {
             JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để sửa.");
         }
    }//GEN-LAST:event_updateCartActionPerformed

    private void delCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delCartActionPerformed
        int row = cartTable.getSelectedRow();
        if(row != -1){
            String orderID = cartTable.getValueAt(row, 0).toString();
            int productRow = findRow(orderID);
            int cartQuantity = Integer.parseInt(cartTable.getValueAt(row, 2).toString());
            int productQuantity = Integer.parseInt(productTable.getValueAt(productRow, 3).toString());
            int newQuantity = productQuantity + cartQuantity ;
            cartDF.removeRow(row);
            productTable.setValueAt(newQuantity, productRow, 3);
            updateTotal();
        }else{
             JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần xóa.");
        }
    }//GEN-LAST:event_delCartActionPerformed

    private void customID_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customID_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_customID_txtActionPerformed

    private void addCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCartActionPerformed

        int row = productTable.getSelectedRow();
        
         if(row != -1){
             String orderID = productTable.getValueAt(row, 0).toString();
             String orderName = productTable.getValueAt(row, 1).toString();
             int quantity = 1 ;
             double price = Double.parseDouble(productTable.getValueAt(row, 2).toString()) * quantity; 
             cartDF.addRow(new Object[]{orderID,orderName,quantity,String.valueOf(price)});
             int newQuantity = Integer.parseInt(productTable.getValueAt(row,3).toString()) - quantity ;
             if(dao.updateQuantity(orderID, newQuantity)){
                 if(Integer.parseInt(productTable.getValueAt(row,3).toString()) >= quantity){
                      productTable.setValueAt(newQuantity, row, 3);
                 }
            }else{
                 JOptionPane.showConfirmDialog(cartTable, "Thêm không thành công ");
             }
              updateTotal(); 
         }else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để thêm vào giỏ.");
        }
    }//GEN-LAST:event_addCartActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        searchOrderID();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void search_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_txtActionPerformed
        
    }//GEN-LAST:event_search_txtActionPerformed

    private void btnXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatActionPerformed
         btnXuat.setVisible(false);
    // Lấy nội dung của panel mauHoaDon
    JPanel panel = HoaDon;
    
    // Tạo một tệp PDF mới
    Document document = new Document();
    try {
        // Đặt đường dẫn và tên cho tệp PDF
        String filePath = "mauHoaDon.pdf";
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();
        
        // Xác định kích thước của trang PDF
        com.itextpdf.text.Rectangle pageSize = document.getPageSize();
        
        // Xác định kích thước của panel
        Dimension panelSize = panel.getPreferredSize();
        
        // Tính toán vị trí để vẽ panel vào trang PDF ở giữa
        float x = (pageSize.getWidth() - panelSize.width) / 2;
        float y = (pageSize.getHeight() - panelSize.height) / 2;
        
        // Tạo đối tượng PdfContentByte để vẽ nội dung của panel vào tệp PDF
        PdfContentByte contentByte = writer.getDirectContent();
        contentByte.saveState();
        
        // Di chuyển ngữ cảnh vẽ đến vị trí đã tính toán
        contentByte.concatCTM(1, 0, 0, 1, x, y);
        
        Graphics2D graphics2d = contentByte.createGraphicsShapes(panelSize.width, panelSize.height);
        panel.print(graphics2d);
        graphics2d.dispose();
        contentByte.restoreState();
        
    } catch (DocumentException | IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi tạo tệp PDF: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    } finally {
        document.close();
    }
    // Mở tệp PDF sau khi tạo xong
    try {
        Desktop.getDesktop().open(new File("mauHoaDon.pdf"));
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Không thể mở tệp PDF: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
     btnXuat.setVisible(true);
    }//GEN-LAST:event_btnXuatActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel HoaDon;
    private javax.swing.JButton addCart;
    private javax.swing.JButton btnBack1;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnXuat;
    private javax.swing.JTable cartTable;
    private javax.swing.JTextField customID_txt;
    private javax.swing.JButton delCart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;

    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel maHDtxt;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel money;
    private javax.swing.JLabel ngayLaptxt;
    private javax.swing.JLabel order_ID;
    private javax.swing.JTable productTable;
    private javax.swing.JScrollPane scrollProduct;
    private javax.swing.JTextField search_txt;
    private javax.swing.JTable tableHoaDon;
    private javax.swing.JLabel tongTientxt;
    private javax.swing.JButton updateCart;
    // End of variables declaration//GEN-END:variables
}
