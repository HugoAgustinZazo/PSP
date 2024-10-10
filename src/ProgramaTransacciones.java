import java.io.*;
import java.nio.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ProgramaTransacciones {
    public static void main(String[] args) throws IOException {

        List<Process> procesos = new ArrayList<>();
        Scanner entrada = new Scanner(System.in);
        System.out.println("Dime el nombre del archivo");
        int numproc = 999;
        String nombre = "";
        boolean salir = false;
        boolean salir2 = false;
        while (salir == false) {
            try {
                nombre = entrada.next();
                Path csv = Paths.get("src/" + nombre);
                if (Files.notExists(csv)) {
                    throw new ArchivoException("El archivo introducido no existe.");
                }

                while (salir2 == false) {
                    try {
                        System.out.println("Dime el numero de procesos que quieres");
                        numproc = entrada.nextInt();
                        salir = true;
                        salir2 = true;
                    } catch (InputMismatchException e) {
                        System.out.println("El dato introducido es erroneo tienes que introducir un número");
                        entrada.nextLine();
                    }
                }
            } catch (ArchivoException e) {
                e.printStackTrace();
                System.out.println("El archivo no existe vuelva a introducirlo de nuevo");


            }
        }
            Path error = Paths.get("errores_conversion.csv");
            Files.deleteIfExists(error);
            String ficherofinal = "transacciones_final.csv";
            Path path = Paths.get(ficherofinal);
            Files.deleteIfExists(path);
            Files.createFile(path);
            try {

                ProcessBuilder processBuilder = new ProcessBuilder();
                processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
                processBuilder.redirectError(ProcessBuilder.Redirect.appendTo(new File("errores_conversion.csv")));
                processBuilder.directory(new File("out/production/PRACTICA_UT1_HUGO_AGUSTIN"));


                for (int i = 0; i < numproc; i++) {
                    processBuilder.command("java", Transaccion.class.getName(), nombre, String.valueOf(numproc), String.valueOf(i));
                    Process proceso = processBuilder.start();
                    procesos.add(proceso);
                }
                procesos.getLast().waitFor();


            } catch (IOException e) {
                throw new RuntimeException(e);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            convertirFicheros(path, numproc);
        }
        public static void convertirFicheros (Path path,int numproc) throws IOException {

            for (int i = 0; i < numproc; i++) {
                String ficheroproceso = "ficheroProceso" + (i + 1) + ".csv";
                Path path1 = Paths.get("out/production/PRACTICA_UT1_HUGO_AGUSTIN/" + ficheroproceso);
                List<String> lineas = Files.readAllLines(path1);
                for (String line : lineas) {
                    Files.write(path, line.getBytes(), StandardOpenOption.APPEND);
                    Files.write(path, "\n".getBytes(), StandardOpenOption.APPEND);
                }
                Files.delete(path1);
                lineas.clear();
            }
        }


    }


