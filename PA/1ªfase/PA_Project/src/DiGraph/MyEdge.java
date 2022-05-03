package DiGraph;

/**
 * @author Gabriel Ambrósio 160221013 e Hugo Ferreira 160221039
 * @param <E>
 * @param <V>
 */
public class MyEdge<E, V> implements Edge<E, V>{
    private E elem;
    private Vertex<V> vertex1,vertex2;
    
    /**
     *
     * @param elem - elemento do tipo E da aresta
     * @param vertex1
     * @param vertex2
     */
    public MyEdge(E elem, Vertex<V> vertex1, Vertex<V> vertex2){
        this.elem = elem;
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }
    
    @Override
    public E element() throws InvalidEdgeException {
        if(elem == null){
            throw new InvalidEdgeException("Edge Is Null!");
        }
        return elem;
    }

    /**
     *Retorna os vertices que a aresta liga
     * @return vertices
     */
    @Override
    public Vertex<V>[] vertices() {
        Vertex[] vertices = new Vertex[2];
        vertices[0] = vertex1;
        vertices[1] = vertex2;
        return vertices;
    }

    @Override
    public String toString(){
        return elem.toString() + vertex1.element() + vertex2.element();
    }
}
