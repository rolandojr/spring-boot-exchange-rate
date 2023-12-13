package com.example.mibanco.exchangerate.utils;

public class Utils {

    public static String exchageResult(String exchangeRate,String amount){
       Double result =  Double.valueOf(exchangeRate) * Double.valueOf(amount);
       return result.toString();
    }
}
