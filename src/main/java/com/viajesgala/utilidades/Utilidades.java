package com.viajesgala.utilidades;

public class Utilidades {

	public static String initCap(String givenString) {
	    String[] arr = givenString.toLowerCase().split(" ");
	    StringBuffer sb = new StringBuffer();

	    for (int i = 0; i < arr.length; i++) {
	        sb.append(Character.toUpperCase(arr[i].charAt(0)))
	            .append(arr[i].substring(1)).append(" ");
	    }          
	    return sb.toString().trim();
	}  
	
}
