import java.io.*;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProgramaTransacciones {
    public static void main(String[] args) throws IOException {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Dime el nombre del archivo");
        String nombre = entrada.next();
        System.out.println("Dime el numero de procesos que quieres");
        int numproc = entrada.nextInt();

        Path error = Paths.get("errores_conversion.csv");
        Files.deleteIfExists(error);
        String ficherofinal = "transacciones_final.csv";
        Path path = Paths.get(ficherofinal);
        Files.deleteIfExists(path);
        Files.createFile(path);
        try{

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        processBuilder.redirectError(ProcessBuilder.Redirect.appendTo(new File("errores_conversion.csv")));
        processBuilder.directory(new File("out/production/PSP1"));


            for(int i=0;i<numproc;i++) {
                   processBuilder.command("java", Transaccion.class.getName(), nombre,String.valueOf(numproc),String.valueOf(i));
                   processBuilder.start();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);

        }

        convertirFicheros(path,numproc);
    }
    public static void convertirFicheros(Path path, int numproc) throws IOException {

        for(int i=0;i<numproc;i++) {
            String ficheroproceso = "ficheroProceso" + (i + 1) + ".csv";
            Path path1 = Paths.get("out/production/PSP1/" + ficheroproceso);
            List<String> lineas = Files.readAllLines(path1);
            Files.delete(path1);
            for(String line:lineas){
                Files.write(path,line.getBytes(), StandardOpenOption.APPEND);
                Files.write(path,"\n".getBytes(), StandardOpenOption.APPEND);
            }
        }
    }
}
