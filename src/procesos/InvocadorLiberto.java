package procesos;

import java.io.IOException;

public class InvocadorLiberto {
    public static void main(String[] args) {
        ProcessBuilder constructorproc = new ProcessBuilder("java","procesos.HolaLiberto");

        constructorproc.redirectError(ProcessBuilder.Redirect.INHERIT);
        constructorproc.redirectOutput(ProcessBuilder.Redirect.INHERIT);

        try {
            constructorproc.start();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
