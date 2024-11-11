/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import dao.Order_Dao;
import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Admin
 */
public class UI_ThongKe extends javax.swing.JPanel {

    private Order_Dao orderDao;

    public UI_ThongKe() {
        initComponents();
        orderDao = new Order_Dao();
        jComboBoxLoai.addActionListener(e -> {
            String selectedPeriod = (String) jComboBoxLoai.getSelectedItem();
            if (selectedPeriod != null) {
                loadChart(selectedPeriod);
            }
        });
        loadStatistics();
    }
    private void loadStatistics() {
        try {
            // Tổng doanh thu hôm nay
            double revenueToday = orderDao.getRevenueToday();
            tientxt.setText(String.format("%.2f VNĐ", revenueToday));

            // Doanh thu hôm qua và tính phần trăm tăng trưởng
            double revenueYesterday = orderDao.getRevenueYesterday();
            if (revenueYesterday > 0) {
                double percentageIncreaseYesterday = ((revenueToday - revenueYesterday) / revenueYesterday) * 100;
                phanTram2txt.setText(String.format("%.2f%% so với hôm qua", percentageIncreaseYesterday));
            } else {
                phanTram2txt.setText("Không có dữ liệu hôm qua");
            }

            // Doanh thu cùng kỳ tháng trước và tính phần trăm tăng trưởng
            double revenueSamePeriodLastMonth = orderDao.getRevenueSamePeriodLastMonth();
            if (revenueSamePeriodLastMonth > 0) {
                double percentageIncreaseLastMonth = ((revenueToday - revenueSamePeriodLastMonth) / revenueSamePeriodLastMonth) * 100;
                phanTram3txt1.setText(String.format("%.2f%% so với cùng kỳ tháng trước", percentageIncreaseLastMonth));
            } else {
                phanTram3txt1.setText("Không có dữ liệu cùng kỳ tháng trước");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi truy vấn dữ liệu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void loadChart(String period) {
    try {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        switch (period) {
            case "7 ngày qua": {
                Map<LocalDate, Double> revenueByDate = orderDao.getRevenueLast7DaysByDate();
                for (Map.Entry<LocalDate, Double> entry : revenueByDate.entrySet()) {
                    dataset.addValue(entry.getValue(), "Doanh thu", entry.getKey().toString());
                }
                break;
            }
            case "Tháng này": {
                Map<LocalDate, Double> dailyRevenueThisMonth = orderDao.getDailyRevenueThisMonth();
                LocalDate today = LocalDate.now();
                int daysInMonth = today.lengthOfMonth();

                for (int day = 1; day <= daysInMonth; day++) {
                    LocalDate date = today.withDayOfMonth(day);
                    double revenue = dailyRevenueThisMonth.getOrDefault(date, 0.0);
                    String dayLabel = String.valueOf(day);
                    dataset.addValue(revenue, "Doanh thu", dayLabel);
                }
                break;
            }
            case "Tháng trước": {
                Map<LocalDate, Double> dailyRevenueLastMonth = orderDao.getDailyRevenueLastMonth();
                LocalDate firstDayLastMonth = LocalDate.now().minusMonths(1).withDayOfMonth(1);
                int daysInLastMonth = firstDayLastMonth.lengthOfMonth();

                for (int day = 1; day <= daysInLastMonth; day++) {
                    LocalDate date = firstDayLastMonth.withDayOfMonth(day);
                    double revenue = dailyRevenueLastMonth.getOrDefault(date, 0.0);
                    String dayLabel = String.valueOf(day);
                    dataset.addValue(revenue, "Doanh thu", dayLabel);
                }
                break;
            }
            case "Hôm nay": {
                double revenue = orderDao.getRevenueToday();
                dataset.addValue(revenue, "Doanh thu", "Hôm nay");
                break;
            }
            case "Hôm qua": {
                double revenue = orderDao.getRevenueYesterday();
                dataset.addValue(revenue, "Doanh thu", "Hôm qua");
                break;
            }
            case "Năm nay": {
                Map<Integer, Double> monthlyRevenueThisYear = orderDao.getMonthlyRevenueThisYear();
                for (int month = 1; month <= 12; month++) {
                    double revenue = monthlyRevenueThisYear.getOrDefault(month, 0.0);
                    String monthLabel = "T" + month;
                    dataset.addValue(revenue, "Doanh thu", monthLabel);
                }
                break;
            }
            case "Năm trước": {
                Map<Integer, Double> monthlyRevenueLastYear = orderDao.getMonthlyRevenueLastYear();
                for (int month = 1; month <= 12; month++) {
                    double revenue = monthlyRevenueLastYear.getOrDefault(month, 0.0);
                    String monthLabel = "T" + month;
                    dataset.addValue(revenue, "Doanh thu", monthLabel);
                }
                break;
            }
            default:
                JOptionPane.showMessageDialog(this, "Khoảng thời gian không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Doanh thu theo " + period,
                "Thời gian",
                "Doanh thu (VNĐ)",
                dataset
        );

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(787, 367));
        chartPanel.setBackground(Color.WHITE);

        chart.removeAll();
        chart.setLayout(new BorderLayout());
        chart.add(chartPanel, BorderLayout.CENTER);
        chart.validate();

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Lỗi khi truy vấn dữ liệu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
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
        tieuDetxt = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        info1 = new javax.swing.JPanel();
        icon1 = new javax.swing.JLabel();
        tientxt = new javax.swing.JLabel();
        soLuongHoaDontxt = new javax.swing.JLabel();
        info2 = new javax.swing.JPanel();
        icon2 = new javax.swing.JLabel();
        tieuDe2txt = new javax.swing.JLabel();
        phanTram2txt = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        info3 = new javax.swing.JPanel();
        icon3 = new javax.swing.JLabel();
        tieuDe3txt1 = new javax.swing.JLabel();
        phanTram3txt1 = new javax.swing.JLabel();
        Chart = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxLoai = new javax.swing.JComboBox<>();
        chart = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(1000, 694));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tieuDetxt.setText("Kết quả bán hàng hôm nay");

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(2, 100));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 96, Short.MAX_VALUE)
        );

        info1.setBackground(new java.awt.Color(255, 255, 255));

        icon1.setIcon(new javax.swing.ImageIcon("D:\\nam_4\\HSK\\QuanLiBanHang\\icon\\dollar.png")); // NOI18N

        soLuongHoaDontxt.setText("Doanh thu");

        javax.swing.GroupLayout info1Layout = new javax.swing.GroupLayout(info1);
        info1.setLayout(info1Layout);
        info1Layout.setHorizontalGroup(
            info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(info1Layout.createSequentialGroup()
                .addComponent(icon1)
                .addGap(18, 18, 18)
                .addGroup(info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(soLuongHoaDontxt, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tientxt, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 6, Short.MAX_VALUE))
        );
        info1Layout.setVerticalGroup(
            info1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(icon1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, info1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(soLuongHoaDontxt, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tientxt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        info2.setBackground(new java.awt.Color(255, 255, 255));

        icon2.setIcon(new javax.swing.ImageIcon("D:\\nam_4\\HSK\\QuanLiBanHang\\icon\\growth.png")); // NOI18N

        tieuDe2txt.setText("So với hôm qua");

        javax.swing.GroupLayout info2Layout = new javax.swing.GroupLayout(info2);
        info2.setLayout(info2Layout);
        info2Layout.setHorizontalGroup(
            info2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(info2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(icon2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(info2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tieuDe2txt, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(phanTram2txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 6, Short.MAX_VALUE))
        );
        info2Layout.setVerticalGroup(
            info2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(icon2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, info2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(phanTram2txt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tieuDe2txt, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setPreferredSize(new java.awt.Dimension(2, 100));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 96, Short.MAX_VALUE)
        );

        info3.setBackground(new java.awt.Color(255, 255, 255));

        icon3.setIcon(new javax.swing.ImageIcon("D:\\nam_4\\HSK\\QuanLiBanHang\\icon\\growth.png")); // NOI18N

        tieuDe3txt1.setText("So với cùng kì tháng trước");

        javax.swing.GroupLayout info3Layout = new javax.swing.GroupLayout(info3);
        info3.setLayout(info3Layout);
        info3Layout.setHorizontalGroup(
            info3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(info3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(icon3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(info3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tieuDe3txt1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(phanTram3txt1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 6, Short.MAX_VALUE))
        );
        info3Layout.setVerticalGroup(
            info3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(icon3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, info3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(phanTram3txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tieuDe3txt1, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tieuDetxt)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(info1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(info2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(info3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(info3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(info2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tieuDetxt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(info1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        Chart.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Doanh thu");

        jComboBoxLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hôm nay", "Hôm qua", "7 ngày qua", "Tháng này", "Tháng trước", "Năm nay", "Năm trước" }));
        jComboBoxLoai.setSelectedIndex(-1);

        chart.setBackground(new java.awt.Color(102, 255, 102));

        javax.swing.GroupLayout chartLayout = new javax.swing.GroupLayout(chart);
        chart.setLayout(chartLayout);
        chartLayout.setHorizontalGroup(
            chartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 787, Short.MAX_VALUE)
        );
        chartLayout.setVerticalGroup(
            chartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 367, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout ChartLayout = new javax.swing.GroupLayout(Chart);
        Chart.setLayout(ChartLayout);
        ChartLayout.setHorizontalGroup(
            ChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChartLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(ChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ChartLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(610, 610, 610)
                        .addComponent(jComboBoxLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        ChartLayout.setVerticalGroup(
            ChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChartLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(ChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Chart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Chart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Chart;
    private javax.swing.JPanel chart;
    private javax.swing.JLabel icon1;
    private javax.swing.JLabel icon2;
    private javax.swing.JLabel icon3;
    private javax.swing.JPanel info1;
    private javax.swing.JPanel info2;
    private javax.swing.JPanel info3;
    private javax.swing.JComboBox<String> jComboBoxLoai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel phanTram2txt;
    private javax.swing.JLabel phanTram3txt1;
    private javax.swing.JLabel soLuongHoaDontxt;
    private javax.swing.JLabel tientxt;
    private javax.swing.JLabel tieuDe2txt;
    private javax.swing.JLabel tieuDe3txt1;
    private javax.swing.JLabel tieuDetxt;
    // End of variables declaration//GEN-END:variables
}
