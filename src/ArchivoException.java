public class ArchivoException extends Exception{
    String mensaje;

    public ArchivoException() {
        this.mensaje = "Hola";
    }

    public ArchivoException(String message, String mensaje) {
        super(message);
        this.mensaje = mensaje;
    }

    public ArchivoException(String message, Throwable cause, String mensaje) {
        super(message, cause);
        this.mensaje = mensaje;
    }

    public ArchivoException(Throwable cause, String mensaje) {
        super(cause);
        this.mensaje = mensaje;
    }

    public ArchivoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String mensaje) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.mensaje = mensaje;
    }
}
