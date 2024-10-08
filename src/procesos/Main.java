package procesos;

import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String rutaPrograma = "/usr/lib/firefox/firefox.sh";
        ProcessBuilder constructorproceso = new ProcessBuilder(rutaPrograma);
        try {
            constructorproceso.start();
        } catch (IOException e) {
            System.out.println("Excepción al lanzar el proceso = " + e);

        }
    }
}