package procesos;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Procesos {
    public static void main(String[] args){
        ProcessBuilder constructorProcesos = new ProcessBuilder();
        constructorProcesos.command("bash","-c","ping google.com");


        try{
            constructorProcesos.redirectOutput();
            Process hijo = constructorProcesos.start();

            hijo.waitFor(5, TimeUnit.SECONDS);

            if(hijo.isAlive()) {
                hijo.destroyForcibly();
            }
        }catch (IOException | InterruptedException e){

        }

    }
}
