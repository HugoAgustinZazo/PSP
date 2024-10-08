package procesos;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ProgramaTransacciones {
    static ArrayList<Cliente> cli = new ArrayList<>();

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        System.out.println("Indica el nombre del archivo que vayas a leer");
        String fichero = teclado.next();

        System.out.println("Indica el número de procesos que quieres que se ejecuten");
        int numproc = teclado.nextInt();


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
    }
    public static  void comprobarFraude() throws MontoException {
        for(Cliente cl : cli){

          try {
              if (cl.getMonto() > 50000) {
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
