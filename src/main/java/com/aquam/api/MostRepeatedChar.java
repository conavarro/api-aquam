package com.aquam.api;

import java.util.Arrays;
import java.util.Locale;

public class MostRepeatedChar {

    public static void main(String[] args) {


    }

    public String method(String cadena){
        cadena = cadena.toLowerCase(Locale.ROOT);

        char[] letras = cadena.toCharArray();
        Arrays.sort(letras);

        int longitud = letras.length, i = 0, contador, contadorMax = 0;
        char letra, letraMax = 0;
        while(i < longitud){
            if(Character.isAlphabetic(letras[i])){
                contador = 0;
                letra = letras[i];
                while (i < longitud && letra == letras[i]) {
                    contador++;
                    if(contador > contadorMax){
                        contadorMax = contador;
                        letraMax = letra;
                    }
                    i++;
                }
            } else {
                i++;
            }
        }
//        System.out.println(new String(letras));
        return letraMax + " : " + contadorMax;
    }
}
