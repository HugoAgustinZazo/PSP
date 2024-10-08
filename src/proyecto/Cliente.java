package proyecto;

public class Cliente {
int id;
String nombre;
String fecha;
Double monto;
String moneda;

    public Cliente(int id, String nombre, String fecha, Double monto, String moneda) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.monto = monto;
        this.moneda = moneda;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }
}
