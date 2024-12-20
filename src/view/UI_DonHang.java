/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import dao.OrderDetail_Dao;
import dao.Order_Dao;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import static utils.AppUtils.formatTextField;

/**
 *
 * @author HP
 */
public class UI_DonHang extends javax.swing.JPanel {
    private DefaultTableModel tabledf ;
    private CardLayout card;
    private Order_Dao order = new Order_Dao();
    private OrderDetail_Dao detail = new OrderDetail_Dao();
    private void showOrderDetails(String maDonHang) {
        // Tạo dialog để hiển thị chi tiết sản phẩm trong hóa đơn
        JDialog dialog = new JDialog();
        dialog.setSize(500, 500);
        dialog.setLocationRelativeTo(this);
         dialog.setTitle("Chi Tiết Theo Mã Hóa Đơn: " + maDonHang);
        String[] productColumns = {"Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Đơn Giá", "Thành Tiền"};
        DefaultTableModel productModel = new DefaultTableModel(productColumns, 0);

        productModel.addRow(new Object[]{"SP001", "Sản phẩm A", 2, "100,000", "200,000"});
        productModel.addRow(new Object[]{"SP002", "Sản phẩm B", 1, "300,000", "300,000"});
        productModel.addRow(new Object[]{"SP003", "Sản phẩm C", 3, "50,000", "150,000"});

        JTable productTable = new JTable(productModel);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(new JScrollPane(productTable), BorderLayout.CENTER);

        JButton closeButton = new JButton("Đóng");
        closeButton.addActionListener(e -> dialog.dispose());
        panel.add(closeButton, BorderLayout.SOUTH);

        dialog.add(panel);
        dialog.setVisible(true);
    }
    
    
    /**
     * Creates new form UI_DonHang
     */
    public UI_DonHang() {
        initComponents();
        formatTextField("Tìm kiếm theo mã hóa đơn",search_txt);
        String[] title = {"Mã hóa đơn","Mã khách hàng","Tổng hóa đơn","Ngày lập hóa đơn","Trạng thái"};
        tabledf = new DefaultTableModel(title,0);
        order_Table.setModel(tabledf);
        
         try {
            List<Object[]> data = order.getAllOrder();
            for (Object[] row : data) {
                tabledf.addRow(row);  
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu từ cơ sở dữ liệu: " + e.getMessage());
        }
        
        
    }
     public boolean searchOrderID(){
        if(search_txt.getText().trim().equals("")){
            JOptionPane.showConfirmDialog(this,"Không tìm thấy");
        }else{
            boolean isFound = false;
            for(int i = 0;i< order_Table.getRowCount();i++){
                if(order_Table.getValueAt(i, 0).equals(search_txt.getText())){
                   order_Table.setRowSelectionInterval(i, i);
                     isFound = true;
                     return true;
                }
            }
            if(!isFound){
               JOptionPane.showConfirmDialog(this, "Không tìm thấy");
               order_Table.clearSelection();
            }else{
            }
        }
        return false;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        search_txt = new javax.swing.JTextField();
        search_btn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        order_Table = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(255, 204, 255));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Danh sách hóa đơn ");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        search_txt.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        search_txt.setText("jTextField1");

        search_btn.setText("Tìm");
        search_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(search_txt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(search_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(243, 243, 243)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)))
                .addGap(252, 252, 252))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search_txt, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(search_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 9, Short.MAX_VALUE))
        );

        order_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        order_Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                order_TableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(order_Table);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void search_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_btnActionPerformed
        searchOrderID();
    }//GEN-LAST:event_search_btnActionPerformed

    private void order_TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_order_TableMouseClicked
       int selectedRow = order_Table.getSelectedRow();
                if (selectedRow != -1) {
                    String maDonHang = tabledf.getValueAt(selectedRow, 0).toString();
                    String name = tabledf.getValueAt(selectedRow, 1).toString();
                    showOrderDetails(maDonHang);
                }
    }//GEN-LAST:event_order_TableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable order_Table;
    private javax.swing.JButton search_btn;
    private javax.swing.JTextField search_txt;
    // End of variables declaration//GEN-END:variables
}
