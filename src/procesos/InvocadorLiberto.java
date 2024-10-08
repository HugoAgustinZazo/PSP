package procesos;

import java.io.File;
import java.io.IOException;

public class InvocadorLiberto {

    public static void main(String[] args) {

        ProcessBuilder constructorProcesos = new ProcessBuilder("java", "procesos.HolaLiberto", "pop", "fiesta");


        //Le digo al ProcessBuilder sobre qué ruta quiero que lance el comando java
        //(básicamente la carpeta donde están los compilados en tu proyecto)
        constructorProcesos.directory(new File("out/production/PSP1"));
        constructorProcesos.redirectError(new File("salidaErroresLiberto.txt"));
        constructorProcesos.redirectOutput(ProcessBuilder.Redirect.INHERIT);

        try {
            constructorProcesos.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
