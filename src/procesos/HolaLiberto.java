package procesos;

public class HolaLiberto {

    public static void main(String[] args) throws Exception {

        System.out.println("Hola Liberto");
        String primerParam = args[0];
        System.out.println("primerParam = " + primerParam);

        if(primerParam.equalsIgnoreCase("fiesta")){
            throw new Exception("Fiesta not allowed");
        }

    }
}
