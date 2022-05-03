package Graph;

/**
 * Classe usada para criar uma exceção quando é usado um edge invalido
 * @author Gabriel
 */
public class InvalidEdgeException extends RuntimeException{

    /**
     * Construtor default, com mensagem predefinida
     */
    public InvalidEdgeException() {
        super("The edge is invalid or does not belong to this graph.");
    }
    
    /**
     * Construtor com mensagem customizável
     * @param exception - mensagem
     */
    public InvalidEdgeException(String exception){
        super(exception);
    }
}
