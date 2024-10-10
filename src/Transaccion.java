import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.nio.*;

public class Transaccion {

    public static void main(String[] args)  {

        String fichero = args[0];
        int numproc = Integer.parseInt(args[1]);
        int numerodelproceso = Integer.parseInt(args[2]);


      ArrayList<Cliente> cli = new ArrayList<>();
      List<String> lineas=new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fichero))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if(line.contains("ID")){
                }else {
                    lineas.add(line);
                }
            }
            int posicion=0;
            int c=lineas.size()/numproc;

            posicion=numLineas(c,numproc,numerodelproceso,posicion,lineas,cli);
            sobrantes(c,numerodelproceso,numproc,posicion,lineas,cli);
            System.out.println("Numero del proceso: "+(numerodelproceso+1)+" | Numero de lineas: "+cli.size()+"\nPrimer ID: "+cli.getFirst().getId()+"\nUltimo ID: "+cli.getLast().getId());
            String ficheroProceso = "ficheroProceso"+(numerodelproceso+1);
            Path path = Paths.get(ficheroProceso+".csv");
            Files.deleteIfExists(path);
            Files.createFile(path);


            for(Cliente cl:cli){
                Files.write(path, cl.toString().getBytes(), StandardOpenOption.APPEND);
                Files.write(path, "\n".getBytes(), StandardOpenOption.APPEND);

            }


            cli.clear();



        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }

    }
    public static int numLineas(int c,int numproc,int numerodelproceso,int posicion,List<String> lineas,ArrayList<Cliente> cli) {

        for(int b=0;b<numerodelproceso;b++){
            posicion = posicion+lineas.size()/numproc;
            c=c+lineas.size()/numproc;
        }
        String transpaso[];

        for(;posicion<c&&posicion<lineas.size();posicion++){
            transpaso=lineas.get(posicion).split(",");
            int id=Integer.parseInt(transpaso[0]);
            String nombre=transpaso[1];
            String fecha=transpaso[2];
            Double monto=Double.parseDouble(transpaso[3]);
            String moneda=transpaso[4];
            cli.add(new Cliente(id,nombre,fecha,monto,moneda));
        }
        for(Cliente cliente:cli){
            convertirMoneda(cliente);
            comprobarFraude(cliente);
        }

        return posicion;
    }
    public static  void comprobarFraude(Cliente cl)  {

                if (cl.getMonto() > 50000.00) {
                    cl.setMoneda(cl.getMoneda()+"-ALERTA");
                    System.err.println("[ERROR] Fraude detectado en la transaccion ID: "+cl.getId()+" Monto: "+cl.getMonto());
                }
        }


    public static void convertirMoneda(Cliente cl){

            if(cl.getMoneda().equalsIgnoreCase("USD")){
                cl.setMonto(redondear(cl.getMonto()*0.85,2));
                cl.setMoneda("EUR");
            }else if(cl.getMoneda().equalsIgnoreCase("GBP")){
                cl.setMonto(redondear(cl.getMonto()*1.17,2));
                cl.setMoneda("EUR");
            }


    }
    public static double redondear(double valor, int decimales) {
        BigDecimal bd = new BigDecimal(valor);
        bd = bd.setScale(decimales, RoundingMode.HALF_UP);  // Redondeo hacia el número más cercano
        return bd.doubleValue();
    }
    public static void sobrantes(int c,int numerodelproceso,int numproc,int posicion,List<String> lineas,ArrayList<Cliente> cli) {

        String transpaso[];
        if(numproc==numerodelproceso+1) {
            for (; posicion < lineas.size(); posicion++) {
                transpaso = lineas.get(posicion).split(",");
                int id = Integer.parseInt(transpaso[0]);
                String nombre = transpaso[1];
                String fecha = transpaso[2];
                Double monto = Double.parseDouble(transpaso[3]);
                String moneda = transpaso[4];
                cli.add(new Cliente(id, nombre, fecha, monto, moneda));
            }
            for(Cliente cliente:cli){
                if(cliente.getMoneda().contains("ALERTA")){
                }else if(!cliente.getMoneda().contains("ALERTA")&&cliente.getMonto()>50000.00) {
                    cliente.setMoneda(cliente.getMoneda() + "-ALERTA");
                    System.err.println("[ERROR] Fraude detectado en la transaccion ID: " + cliente.getId() + " Monto: " + cliente.getMonto());
                }
            }
        }
    }

}