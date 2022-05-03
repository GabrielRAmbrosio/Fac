package Graph;

/**
 *Classe usada para criar uma exceção quando é usado um vertex invalido
 * @author Gabriel Ambrósio - 160221013
 */
public class InvalidVertexException extends RuntimeException{

    /**
     *Construtor default, com mensagem predefinida
     */
    public InvalidVertexException() {
        super("The vertex is invalid or does not belong to this graph.");
    }
    
    /**
     *Construtor com mensagem customizável
     * @param exception - mensagem
     */
    public InvalidVertexException(String exception){
        super(exception);
    }
}
