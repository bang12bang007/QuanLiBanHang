/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.Item_Dao;
import entity.Item;
import utils.AppUtils;

/**
 *
 * @author HP
 */
public class UI_TonKho extends javax.swing.JPanel implements ActionListener, MouseListener {

	private ConnectDB connectDB;
	private Item_Dao itemDao;
	private Item item;;

	/**
	 * Creates new form UI_TonKho
	 */
	public UI_TonKho() {

		connectDB = new ConnectDB();
		itemDao = new Item_Dao();
		item = new Item();
		initComponents();
		
		AppUtils.formatTextField("Bạn có thể nhập Mã Sản Phẩm hoặc Tên Sản Phẩm để tìm.....", search_txt);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {
		connectDB = new ConnectDB();
		// doc du lieu tu database them vao I

//    	showTable();

		jPanel1 = new javax.swing.JPanel();
		jPanel2 = new javax.swing.JPanel();
		search_txt = new javax.swing.JTextField();
		comboxSort = new javax.swing.JComboBox<>();
		btnSearch = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		comboxCategory = new javax.swing.JComboBox<>();
		btnSort = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();

		jPanel1.setBackground(new java.awt.Color(255, 204, 255));

		comboxSort.setModel(new javax.swing.DefaultComboBoxModel<>(
				new String[] { "Số lượng tồn kho tăng dần", "Số lượng tồn kho giảm dần", "Sản phẩm hết hạn" }));
		comboxSort.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				comboxSortActionPerformed(evt);
			}
		});

		btnSearch.setText("Tìm kiếm");
		btnSearch.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnSearchActionPerformed(evt);
			}
		});

		jLabel1.setText("Kho sản phẩm");

		comboxCategory.setModel(new javax.swing.DefaultComboBoxModel<>(
				new String[] { "Tất cả", "Thực phẩm", "Sữa & Sản phẩm từ sữa", "Gia vị & Dầu ăn", "Đồ uống" }));

		btnSort.setText("Sắp xếp");
		btnSort.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnSortActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
						.addGroup(jPanel2Layout.createSequentialGroup()
								.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 99,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(681, Short.MAX_VALUE))
						.addGroup(jPanel2Layout.createSequentialGroup()
								.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(jPanel2Layout.createSequentialGroup().addComponent(search_txt)
												.addGap(23, 23, 23))
										.addGroup(jPanel2Layout.createSequentialGroup()
												.addComponent(comboxCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(comboxSort, javax.swing.GroupLayout.PREFERRED_SIZE, 295,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
								.addGroup(jPanel2Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
										.addComponent(btnSort, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGap(18, 18, 18)))));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup()
						.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(search_txt, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSearch))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(comboxSort, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(comboxCategory, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSort))
						.addGap(0, 17, Short.MAX_VALUE)));

		jTable1.setModel(model);
		Object[] columns = { "ID", "Tên sản phẩm", "Số lượng tồn kho", "Hạn sử dụng" };
		model.setColumnIdentifiers(columns);

		jScrollPane1.setViewportView(jTable1);

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addGroup(jPanel1Layout.createSequentialGroup().addComponent(jScrollPane1).addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
						.addContainerGap()));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		search_txt.addActionListener(this);
		comboxSort.addActionListener(this);
		comboxCategory.addActionListener(this);
		btnSearch.addActionListener(this);
		btnSort.addActionListener(this);
		jTable1.addMouseListener(this);

	}// </editor-fold>//GEN-END:initComponents

	private void comboxSortActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_comboxSortActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_comboxSortActionPerformed

	private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSearchActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_btnSearchActionPerformed

	private void btnSortActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSortActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_btnSortActionPerformed
//public void showTable() throws SQLException
//{
//String sql = "SELECT * FROM Item";
//try {
//	Statement stmt = (Statement) Connection
//	ResultSet rs = stmt.executeQuery(sql);
//} catch (SQLException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}
//
//
//
//
//}
	// Variables declaration - do not modify//GEN-BEGIN:variables

	private javax.swing.JButton btnSearch;
	private javax.swing.JButton btnSort;
	private javax.swing.JComboBox<String> comboxCategory;
	private javax.swing.JComboBox<String> comboxSort;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable1;

	DefaultTableModel model = new DefaultTableModel();
	private javax.swing.JTextField search_txt;

	// End of variables declaration//GEN-END:variables
	@Override
	public void mouseClicked(MouseEvent e) {

		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object o = e.getSource();
		
		
	
		if (o.equals(btnSearch)) {

			// xóa dữ liệu cũ
			model.setRowCount(0);

		
			if (search_txt.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập mã sản phẩm");
			} else {
				itemDao = new Item_Dao();
				Item item = new Item();
                ArrayList<Item> list = null;
                ArrayList<Item> listCategory = null;
               				try {
					item = itemDao.getItemById(search_txt.getText());
					 list = itemDao.getItemByNameFinal(search_txt.getText());
					 listCategory = itemDao.getItemByCategory(search_txt.getText());
					if (item == null && list == null && listCategory == null) {
						JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm");
					return;
					} else if(item != null) {
						model.addRow(new Object[] { item.getId(), item.getName(), item.getQuantity(),
								item.getExpiredDate() });
						return;
					}
					else if (list != null) {
						for (Item s : list) {
							model.addRow(new Object[] { s.getId(), s.getName(), s.getQuantity(), s.getExpiredDate() });
						}
					}
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			
			// kết hợp cả tìm theo mã và theo t
		}

		if (o.equals(btnSort)) {
			model.setRowCount(0);

			search_txt.setText("");
			if (comboxSort.getSelectedItem().equals("Số lượng tồn kho tăng dần")) {
				if (comboxCategory.getSelectedItem().equals("Tất cả")) {
					itemDao = new Item_Dao();
					List<Item> list = null;
					try {
						list = itemDao.getAllItem();
						list.sort(Comparator.comparingInt(Item::getQuantity));

						for (Item s : list) {
							// sắp xếp quantity tăng dần

							model.addRow(new Object[] { s.getId(), s.getName(), s.getQuantity(), s.getExpiredDate() });
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

				if (comboxCategory.getSelectedItem().equals("Thực phẩm")) {
					itemDao = new Item_Dao();
					List<Item> list = null;
					try {
						list = itemDao.getItemByCategory("Thực phẩm");
						list.sort(Comparator.comparingInt(Item::getQuantity));

						for (Item s : list) {

							model.addRow(new Object[] { s.getId(), s.getName(), s.getQuantity(), s.getExpiredDate() });
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				if (comboxCategory.getSelectedItem().equals("Sữa & Sản phẩm từ sữa")) {
					itemDao = new Item_Dao();
					List<Item> list = null;
					try {
						list = itemDao.getItemByCategory("Sữa & Sản phẩm từ sữa");
						list.sort(Comparator.comparingInt(Item::getQuantity));

						for (Item s : list) {

							model.addRow(new Object[] { s.getId(), s.getName(), s.getQuantity(), s.getExpiredDate() });
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				if (comboxCategory.getSelectedItem().equals("Gia vị & Dầu ăn")) {
					itemDao = new Item_Dao();
					List<Item> list = null;
					try {
						list = itemDao.getItemByCategory("Gia vị & Dầu ăn");
						list.sort(Comparator.comparingInt(Item::getQuantity));

						for (Item s : list) {

							model.addRow(new Object[] { s.getId(), s.getName(), s.getQuantity(), s.getExpiredDate() });
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				if (comboxCategory.getSelectedItem().equals("Đồ uống")) {
					itemDao = new Item_Dao();
					List<Item> list = null;
					try {
						list = itemDao.getItemByCategory("Đồ uống");
						list.sort(Comparator.comparingInt(Item::getQuantity));

						for (Item s : list) {

							model.addRow(new Object[] { s.getId(), s.getName(), s.getQuantity(), s.getExpiredDate() });
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}

			if (comboxSort.getSelectedItem().equals("Số lượng tồn kho giảm dần")) {
				if (comboxCategory.getSelectedItem().equals("Tất cả")) {
					itemDao = new Item_Dao();
					List<Item> list = null;
					try {
						list = itemDao.getAllItem();
						list.sort(Comparator.comparingInt(Item::getQuantity).reversed());

						for (Item s : list) {
							// sắp xếp quantity tăng dần

							model.addRow(new Object[] { s.getId(), s.getName(), s.getQuantity(), s.getExpiredDate() });
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

				if (comboxCategory.getSelectedItem().equals("Thực phẩm")) {
					itemDao = new Item_Dao();
					List<Item> list = null;
					try {
						list = itemDao.getItemByCategory("Thực phẩm");
						list.sort(Comparator.comparingInt(Item::getQuantity).reversed());

						for (Item s : list) {

							model.addRow(new Object[] { s.getId(), s.getName(), s.getQuantity(), s.getExpiredDate() });
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				if (comboxCategory.getSelectedItem().equals("Sữa & Sản phẩm từ sữa")) {
					itemDao = new Item_Dao();
					List<Item> list = null;
					try {
						list = itemDao.getItemByCategory("Sữa & Sản phẩm từ sữa");
						list.sort(Comparator.comparingInt(Item::getQuantity).reversed());

						for (Item s : list) {

							model.addRow(new Object[] { s.getId(), s.getName(), s.getQuantity(), s.getExpiredDate() });
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				if (comboxCategory.getSelectedItem().equals("Gia vị & Dầu ăn")) {
					itemDao = new Item_Dao();
					List<Item> list = null;
					try {
						list = itemDao.getItemByCategory("Gia vị & Dầu ăn");
						list.sort(Comparator.comparingInt(Item::getQuantity).reversed());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				// TODO Auto-generated method stub

			}

			if (comboxSort.getSelectedItem().equals("Sản phẩm hết hạn")) {
				if (comboxCategory.getSelectedItem().equals("Tất cả")) {
					itemDao = new Item_Dao();
					List<Item> list = null;
					try {
						list = itemDao.getItemExpired();

						for (Item s : list) {
							// sắp xếp quantity tăng dần

							model.addRow(new Object[] { s.getId(), s.getName(), s.getQuantity(), s.getExpiredDate() });
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

				if (comboxCategory.getSelectedItem().equals("Thực phẩm")) {
					itemDao = new Item_Dao();
					List<Item> list = null;
					try {
						list = itemDao.getItemExpiredByCategory("Thực phẩm");

						for (Item s : list) {

							model.addRow(new Object[] { s.getId(), s.getName(), s.getQuantity(), s.getExpiredDate() });
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				if (comboxCategory.getSelectedItem().equals("Sữa & Sản phẩm từ sữa")) {
					itemDao = new Item_Dao();
					List<Item> list = null;
					try {
						list = itemDao.getItemExpiredByCategory("Sữa & Sản phẩm từ sữa");

						for (Item s : list) {

							model.addRow(new Object[] { s.getId(), s.getName(), s.getQuantity(), s.getExpiredDate() });
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				if (comboxCategory.getSelectedItem().equals("Gia vị & Dầu ăn")) {
					itemDao = new Item_Dao();
					List<Item> list = null;
					try {
						list = itemDao.getItemExpiredByCategory("Gia vị & Dầu ăn");

						for (Item s : list) {

							model.addRow(new Object[] { s.getId(), s.getName(), s.getQuantity(), s.getExpiredDate() });
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		}
	}
}
