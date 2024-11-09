/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    public static String formatDate(LocalDate time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return time.format(formatter);
    }
    //format textfield chỉnh theo cái search_txt bên UI bán hàng của tao á dòng 70 bên UI_BanHang
    public static void formatTextField(String title_textField, JTextField a){
            String placeholderText = title_textField;
            a.setText(placeholderText);
            a.setForeground(Color.GRAY);
            a.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (a.getText().equals(placeholderText)) {
                    a.setText("");
                    a.setForeground(Color.BLACK);
                  }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (a.getText().isEmpty()) {
                    a.setText(placeholderText); 
                    a.setForeground(Color.GRAY); 
                }
            }
        }
    );
  }    
}
