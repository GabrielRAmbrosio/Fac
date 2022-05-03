package Graph;

/**
 * @author Gabriel Ambrósio 160221013 */
public class MyVertex<V> implements Vertex<V>{
    private V elem;
    
    /**
     *
     * @param elem - elemento do tipo V do vertice
     */
    public MyVertex(V elem){
        this.elem = elem;
    }
    
    @Override
    public V element() throws InvalidVertexException {
        if(elem == null){
            throw new InvalidVertexException();
        }
        return elem;
    }
    
    @Override
    public String toString() {
        return "Vertex{" + elem + '}';
    }
}
