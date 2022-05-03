package DiGraph;

/**
 * @author Gabriel Ambrósio 160221013 e Hugo Ferreira 160221039
 * @param <E>
 * @param <V>
 */
public class MyVertex<E,V> implements Vertex<V>{
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
            throw new InvalidVertexException("Vertex Is Null!");
        }
        return elem;
    }
    
    @Override
    public String toString(){
        return elem.toString();
    }
}
