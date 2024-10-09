import java.io.*;
import java.util.Scanner;

public class ProgramaTransacciones {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Dime el nombre del archivo");
        String nombre = entrada.next();

        ProcessBuilder processBuilder = new ProcessBuilder("java", Transaccion.class.getName(), nombre);
        processBuilder.redirectError(ProcessBuilder.Redirect.appendTo(new File("ficheroErrores.txt")));
        processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        processBuilder.directory(new File("out/production/PSP1"));

        try {
            processBuilder.start();
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }
}
