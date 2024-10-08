package procesos;

import java.io.File;
import java.io.IOException;

public class LanzarComandosListado {
    public static void main(String[] args){

        ProcessBuilder constructorProcesos = new ProcessBuilder("bash", "-c","ls -al");


        try{
            constructorProcesos.directory(new File("/home/alumno"));
            constructorProcesos.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            constructorProcesos.start();
        }catch (IOException e){

        }

    }
}
