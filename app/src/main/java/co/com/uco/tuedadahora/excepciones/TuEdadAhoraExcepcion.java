package co.com.uco.tuedadahora.excepciones;

public class TuEdadAhoraExcepcion extends Exception {

    private static final long serialVersionUID = 1L;

    public TuEdadAhoraExcepcion(String mensaje) {
        super(mensaje);
    }

    public TuEdadAhoraExcepcion(String mensaje, Throwable throwable){
        super(mensaje, throwable);
    }
}
