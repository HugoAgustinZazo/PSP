import java.io.*;
import java.util.ArrayList;

public class Transaccion {

    public static void main(String[] args)  {
        System.out.println("empezando el programa");
        String fichero = args[0];
        System.out.println(fichero);
        ArrayList<Cliente> cli = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fichero))) {
            System.out.println("Archivo leido");
            String line;
            while ((line = reader.readLine()) != null) {
                if(line.contains("ID")){
                }else {
                    String[] lineas =line.split(",");
                    int id = Integer.parseInt(lineas[0]);
                    String nombre = lineas[1];
                    String fecha = lineas[2];
                    Double monto = Double.parseDouble(lineas[3]);
                    String moneda = lineas[4];

                    cli.add(new Cliente(id,nombre,fecha,monto,moneda));
                }

            }
            convertirMoneda(cli);
            comprobarFraude(cli);
            for(Cliente cl:cli){
                System.out.println(cl.toString());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }

    }
    public static  void comprobarFraude(ArrayList <Cliente> cli)  {
        for(Cliente cl : cli){
                if (cl.getMonto() > 50000) {
                    cl.setMoneda(cl.getMoneda()+"-ALERTA");
                    System.err.println("[ERROR] Fraude detectado en la transacción ID: "+cl.getId()+" Monto: "+cl.getMonto());
                }
        }
    }

    public static void convertirMoneda(ArrayList <Cliente> cli){
        for(Cliente cl : cli){
            if(cl.getMoneda().equalsIgnoreCase("USD")){
                cl.setMonto(cl.getMonto()*0.85);
                cl.setMoneda("EUR");
            }else if(cl.getMoneda().equalsIgnoreCase("GBP")){
                cl.setMonto(cl.getMonto()*1.17);
                cl.setMoneda("EUR");
            }

        }
    }

}
