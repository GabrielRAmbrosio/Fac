package Graph;

public interface Edge<E,V> {
    
    /**
     * Returns the element (object reference) stored in this edge.
     * @return stored element
     */
    public E element() throws InvalidEdgeException;
    /**
     * Returns references of both vertices that this edge connects in the form
     * of an array. 
     * @return an array of length 2, i.e., vertices()[0] and vertices()[1]
     */
    public Vertex<V>[] vertices();
}
