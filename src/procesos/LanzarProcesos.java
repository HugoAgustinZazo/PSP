package procesos;

import java.io.File;
import java.io.IOException;

public class LanzarProcesos {
        public static void main(String[] args){

            ProcessBuilder constructorProcesos = new ProcessBuilder("bash", "-c","ps -e");


            try{
            constructorProcesos.redirectOutput(new File("salidaListaProcesos.txt"));
            constructorProcesos.start();
            }catch (IOException e){

            }

        }
}
