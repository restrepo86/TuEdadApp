package co.com.uco.tuedadahora.excepciones;



public class AniosException extends Exception {

    private static final long serialVersionUID = 1L;

    public AniosException(Throwable throwable){
        super(throwable);
    }

    public AniosException(String mensaje) {
        super(mensaje);
    }

    public AniosException(String mensaje, Throwable throwable){
        super(mensaje, throwable);
    }

}
