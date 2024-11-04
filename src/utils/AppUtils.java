/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.DecimalFormat;
import javax.swing.JTextField;

/**
 *
 * @author HP
 */
public class AppUtils {
    //format money
    public static String formatMoney(double a){
          DecimalFormat dc = new DecimalFormat("#,##0 VND");
          String formatter = dc.format(a);
          return formatter;
     }
    //format date
    
    //format textfield chỉnh theo cái search_txt bên UI bán hàng của tao á 
    public static void formatTextField(String title_textField, JTextField a){
            String placeholderText = title_textField;
            a.setText(placeholderText);
            a.setForeground(Color.GRAY);
            a.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (a.getText().equals(placeholderText)) {
                    a.setText(""); // Xóa placeholder khi người dùng nhập
                    a.setForeground(Color.BLACK); // Đặt lại màu chữ bình thường
                  }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (a.getText().isEmpty()) {
                    a.setText(placeholderText); // Đặt lại placeholder nếu trống
                    a.setForeground(Color.GRAY); // Đặt lại màu placeholder
                }
            }
        }
    );
  }    
}
