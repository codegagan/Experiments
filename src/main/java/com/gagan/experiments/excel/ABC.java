/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gagan.experiments.excel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Iterator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Gagan.mar26
 */
public class ABC {
    //Programme to find the MD5 hash value of a string and to output the value

    public String MD5() throws Exception {
        String a1 = null;
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter string:");
        String s = userInput.readLine();
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(s.getBytes());

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        System.out.println("Digest(in hex format):: " + sb.toString());

        //convert the byte to hex format method 2
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        try {
            a1 = hexString.toString();
            //To print the hash value in hexadecimal
            System.out.println("Digest(in hex format):: " + hexString.toString());

            //To print the hash value in decimal
            //System.out.println("MD5 in decimal: "+new BigInteger(1,m.digest()).toString(10));
            //To print the hash value in binary
            System.out.println("MD5 in binary: " + new BigInteger(1, md.digest()).toString(2));
            //Close the output stream
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }

        return a1;
    }

    public String readXslx(String characters) throws Exception {

        String b1 = null;
        String returnVal = null;
        try (FileInputStream fis = new FileInputStream(new File("/home/gagan/temp/Book2.xlsx"))) {
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0); // the first sheet
            //Refer to a row:
            //HSSFRow row = sheet.getRow(0);.
//Refer to a cell in the row:
            //HSSFCell cell = row.getCell((short)0);.
//Get the values in that cell:
            Iterator ite = sheet.rowIterator();

            while (ite.hasNext()) {
                Row row = (Row) ite.next();
                Iterator<Cell> cite = row.cellIterator();
                String key = cite.next().toString(); // Use the method as expected values. as numeric values will have decimal
                if (cite.hasNext()) {
                    String last = cite.next().toString(); // May throw NPE if there are not two columns in the file.
                    if (characters.equals(last)) {
                        returnVal = key;
                        break;
                    }
                    System.out.println(key + " -> " + last); // only printing values with both both columns
                }

//                while(cite.hasNext()){
//                    Cell c = cite.next();
//                    b1 =  c.toString();
//                    System.out.print(b1 +"  ");
//                }
                
            }
        }

        return returnVal;
    }

    public static void main(String[] args) throws Exception {
        String a;
        String b;
        ABC M1 = new ABC();
        a = M1.MD5();

        System.out.println("the value of a:" + a);
        String value = a;
        String lastTwo = null;
        if (value != null && value.length() >= 2) {
            lastTwo = value.substring(value.length() - 2);
        }
        System.out.println("the last two character of MD5 are:" + lastTwo);
        b = M1.readXslx(lastTwo);
        System.out.println("Got the corresponding key from excel: "+b);
        //String b2 = "ea";
        String key;

        switch (lastTwo) {
            case "ea":
                key = "monkey";
                break;
            /* case "february":
             monthNumber = 2;
             break;
             case "march":
             monthNumber = 3;
             break;
             case "april":
             monthNumber = 4;
             break;
             case "may":
             monthNumber = 5;
             break;
             case "june":
             monthNumber = 6;
             break;
             case "july":
             monthNumber = 7;
             break;
             case "august":
             monthNumber = 8;
             break;
             case "september":
             monthNumber = 9;
             break;
             case "october":
             monthNumber = 10;
             break;
             case "november":
             monthNumber = 11;
             break;
             case "december":
             monthNumber = 12;
             break;
             default: 
             monthNumber = 0;
             break;
             */
        }

        //return monthNumber;
    }

         //if (lastTwo.equals(b))
    //{ 
    //  System.out.println("fetching key from the database");
    //}
}
