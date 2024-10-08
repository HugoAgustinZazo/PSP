package proyecto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Transaccion {

    static ArrayList<Cliente> cli = new ArrayList<>();

    public static void main(String[] args) throws MontoException {
        String fichero = args[0];
        try (BufferedReader reader = new BufferedReader(new FileReader(fichero))) {
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
            convertirMoneda();
            comprobarFraude();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (MontoException e) {
            throw new RuntimeException(e);
        }
        convertirMoneda();
        comprobarFraude();

    }
    public static  void comprobarFraude() throws MontoException {
        for(Cliente cl : cli){

            try {
                if (cl.getMonto() > 50000) {
                    cl.setMoneda(cl.getMoneda()+"-ALERTA");
                    throw new MontoException();
                }

            }catch (MontoException e){
                System.err.println("[ERROR] Fraude detectado en la transacción ID: "+cl.getId()+" Monto: "+cl.getMonto());
            }
        }
    }

    public static void convertirMoneda(){
        for(Cliente cl : cli){
            if(cl.getMoneda().equalsIgnoreCase("USD")){
                cl.setMonto(cl.getMonto()*0.85);
            }else if(cl.getMoneda().equalsIgnoreCase("GBP")){
                cl.setMonto(cl.getMonto()*1.17);
            }

        }
    }

}
