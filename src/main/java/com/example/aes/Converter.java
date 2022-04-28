package com.example.aes;

public class Converter {
    public static int hexToDecimal(String hex){
        return Integer.parseInt(hex,16);
    }

    public static String decimalToHex(int decimal){
        String hex = Integer.toHexString(decimal);
        if(hex.length() == 1)
            hex = "0"+hex;
        return hex.toUpperCase();
    }
}
