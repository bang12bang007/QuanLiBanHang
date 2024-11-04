/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.text.DecimalFormat;

/**
 *
 * @author HP
 */
public class AppUtils {
    public static String formatMoney(double a){
          DecimalFormat dc = new DecimalFormat("#,##0 VND");
          String formatter = dc.format(a);
          return formatter;
     }
}
