public class MontoException extends Exception{
    String mensaje;

    public MontoException() {
        this.mensaje = "Hola";
    }

    public MontoException(String message, String mensaje) {
        super(message);
        this.mensaje = mensaje;
    }

    public MontoException(String message, Throwable cause, String mensaje) {
        super(message, cause);
        this.mensaje = mensaje;
    }

    public MontoException(Throwable cause, String mensaje) {
        super(cause);
        this.mensaje = mensaje;
    }

    public MontoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String mensaje) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.mensaje = mensaje;
    }
}
