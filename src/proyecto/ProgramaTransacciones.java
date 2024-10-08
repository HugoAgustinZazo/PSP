package proyecto;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ProgramaTransacciones {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Dime el nombre del archivo");
        String nombre = entrada.next();

        ProcessBuilder processBuilder = new ProcessBuilder("java", Transaccion.class.getName(), nombre);

        processBuilder.directory(new File("out/Production/PSP1"));
        processBuilder.redirectError(ProcessBuilder.Redirect.appendTo(new File("ficheroErrores.txt")));
        processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);

        try {
            processBuilder.start();


        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }
}
