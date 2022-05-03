package Graph;

/**
 * @author Gabriel Ambrósio 160221013
 * @param <E>
 * @param <V>
 */
public class MyEdge<E, V> implements Edge<E, V>{
    private E elem;
    private Vertex<V> vertexOutbound,vertexInbound;
    
    /**
     *
     * @param elem - elemento do tipo E da aresta
     * @param v1
     * @param v2
     */
    public MyEdge(E elem, Vertex<V> v1, Vertex<V> v2){
        this.elem = elem;
        this.vertexOutbound = v1;
        this.vertexInbound = v2;
    }
    
    @Override
    public E element() throws InvalidEdgeException {
        if(elem == null){
            throw new InvalidEdgeException();
        }
        return elem;
    }

    public boolean contains(Vertex<V> v){
        return (vertexOutbound == v || vertexInbound == v);
    }
    
    /**
     * Retorna os vertices que a aresta liga
     * @return vertices
     */
    @Override
    public Vertex<V>[] vertices() {
        Vertex[] vertices = new Vertex[2];
        vertices[0] = vertexOutbound;
        vertices[1] = vertexInbound;
        return vertices;
    }

    @Override
        public String toString() {
            return "Edge{{" + elem + "}, vertexOutbound=" + vertexOutbound.toString() + ", vertexInbound=" + vertexInbound.toString() + '}';
        }
}
