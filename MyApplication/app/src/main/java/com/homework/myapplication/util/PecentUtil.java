package com.homework.myapplication.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class PecentUtil {
    public static String percnet(double d){
        DecimalFormat nf = (DecimalFormat) NumberFormat.getPercentInstance();
        nf.applyPattern("0%"); //00表示小数点2位
        nf.setMaximumFractionDigits(2); //2表示精确到小数点后2位
        return nf.format(d);
    }
}
