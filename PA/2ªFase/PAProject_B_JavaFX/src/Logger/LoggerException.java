package Logger;

/**
 * Classe usada para criar uma exceção quando não é possivel usar o logger
 * @author Gabriel
 */
public class LoggerException extends RuntimeException {

    /**
     * Construtor com mensagem customizável
     * @param message - mensagem da exceção
     */
    public LoggerException(String message) {
        super(message);
    }
    
}

