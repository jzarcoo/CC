package test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Prueba {

    public static void main(String[] args) {
        BufferedReader in = null;
        int c = 0;
        try {
            in = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream(args[0])));
            String linea = null;
            String[] palabras = null;
            while ((linea = in.readLine()) != null) {
                palabras = linea.split("\\s+");
                for (String palabra : palabras) {
                    palabra = palabra.trim();
                    if (palabra.length() > 0) {
                        c++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Palabras: " + c);
    }

}
