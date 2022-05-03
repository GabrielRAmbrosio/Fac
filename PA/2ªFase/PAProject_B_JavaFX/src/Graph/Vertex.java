package Graph;

public interface Vertex<V> {
    
    /**
     * Returns the element (object reference) stored in this vertex.
     * @return stored element
     */
    public V element() throws InvalidVertexException;
}