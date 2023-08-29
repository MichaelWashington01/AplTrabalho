/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.curso.utils;

import java.util.Date;
import java.util.Locale;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.NumberFormat;

/**
 *
 * @author maico
 */
public class Conversao {
    public static Date converDate(String data) throws ParseException{
        SimpleDateFormat fmt = new SimpleDateFormat ("yyyy-mm-dd");
        if (data == null || data.trim().equals("")){
            return null;
        }else {
            Date date = fmt.parse(data);
            return date;
        }
    }
    
    public static String data2String(Date data){
        SimpleDateFormat fmt = new SimpleDateFormat("dd/mm/yyyy");
        String dataFormatada = fmt.format(data);
        return dataFormatada;
    }
    
       public static Date dataAtual(){
        SimpleDateFormat fmt = new SimpleDateFormat("dd/mm/yyyy");
        Date novaData = new Date(System.currentTimeMillis());
        return novaData;
       }
       
       
       public static double valorDinheiro(String valor){
           String conversao = valor.substring(2, valor.length());
           conversao = conversao.replaceAll("[./-]","");
           conversao = conversao.replace(",",".").trim();
           return Double.parseDouble(conversao);
       }
       
       public static String valorDinheiro (double valor, String pais){
           NumberFormat formatter = null;
           if (pais.equals("BR")){
               formatter = NumberFormat.getCurrencyInstance();
           }else if (pais.equals("US")){
               formatter = NumberFormat.getInstance(new Locale("en", "US"));
           }
           String MoneyString = formatter.format(valor);
           return MoneyString;
       }
}