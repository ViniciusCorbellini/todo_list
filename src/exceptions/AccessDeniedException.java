package exceptions;

/**
 *
 * @author Vinicius Corbellini
 */
public class AccessDeniedException extends Exception{
    public AccessDeniedException() {
        super("Acesso negado.");
    }

    public AccessDeniedException(String message) {
        super("Acesso negado: " + message);
    }
}
