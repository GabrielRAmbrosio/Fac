package DiGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Gabriel Ambrósio 160221013 e Hugo Ferreira 160221039
 */
public class MyGraph<V,E> implements Graph<V, E> {
    private Map<V, Vertex<V>> vertices;
    private Map<E, Edge<E,V>> edges;
    
    public MyGraph(){
        vertices = new HashMap<>();
        edges = new HashMap<>();
    }
    
    private MyVertex checkVertex(Vertex<V> p) throws InvalidVertexException{
        if(p == null){
            throw new InvalidVertexException("Wrong Vertex!");
        }
        if(!this.vertices.containsValue(p)){
            throw new InvalidVertexException("Vertex Does Not Exist!");
        }
        try{
            return (MyVertex) p;
        } catch (ClassCastException e){
            throw new InvalidVertexException("Wrong Vertex!");
        }
    }
    private MyEdge checkEdge(Edge<E,V> ed) throws InvalidEdgeException{
        if(ed == null){
            throw new InvalidEdgeException("Wrong Edge!");
        }
        if(!this.edges.containsValue(ed)){
            throw new InvalidEdgeException("Invalid Edge!");
        }
        try{
            return (MyEdge) ed;
        } catch (ClassCastException e){
            throw new InvalidEdgeException("Wrong Edge!");
        }
    }
    
    @Override
    public int numVertices() {
        return vertices.size();
    }

    @Override
    public int numEdges() {
        return edges.size();
    }

    @Override
    public Iterable<Vertex<V>> vertices() {
        return vertices.values();
    }

    @Override
    public Iterable<Edge<E, V>> edges() {
        return edges.values();
    }

    @Override
    public Iterable<Edge<E, V>> incidentEdges(Vertex<V> vertice) throws InvalidEdgeException {
        MyVertex v1 = checkVertex(vertice);

        List<Edge<E, V>> incidentEdges = new ArrayList<>();
        for (Edge<E, V> edge : edges.values()) {
            if (edge.vertices()[1] == vertice) {
                incidentEdges.add(edge);
            }
        }
        return incidentEdges;
    }
    
    @Override
     public Iterable<Edge<E,V>> accedentEdges(Vertex<V> v){
        MyVertex v1 = checkVertex(v);
        ArrayList<Edge<E,V>> accedents = new ArrayList<>();
        for(Edge<E, V> edge : edges.values()) {
            if (edge.vertices()[0] == v1) {
                accedents.add(edge);
            }
        }
        return accedents;
    }

    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E, V> e) throws InvalidVertexException, InvalidEdgeException {
        Vertex<V>[] vertices1 = checkEdge(e).vertices();
        if(vertices1[0] == v){
            return vertices1[1];
        }
        if(vertices1[1] == v){
            return vertices1[0];
        }
        throw new InvalidVertexException("Invalid Vertex!");
    }

    @Override
    public boolean areAdjacent(Vertex<V> v, Vertex<V> u) throws InvalidVertexException {
        for(Edge<E,V> edg : edges.values()){
            Vertex[] verts = edg.vertices();
            if((verts[0] == v && verts[1] == u) || (verts[0] == u && verts[1] == v)){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public Vertex<V> insertVertex(V elem) throws InvalidVertexException{
        if(vertices.containsKey(elem)){
            throw new InvalidVertexException(elem + " Already Exists ");
        }
        MyVertex vertex = new MyVertex(elem);
        vertices.put(elem, vertex);
        return vertex;
    }
    
    @Override
    public Edge<E, V> insertEdge(Vertex<V> u, Vertex<V> v, E elem) throws InvalidEdgeException {
        if(edges.containsKey(elem)){
            throw new InvalidEdgeException(elem + " Already Exists ");
        }
        
        MyVertex outVertex = checkVertex(u);
        MyVertex inVertex = checkVertex(v);
        
        MyEdge ed = new MyEdge(elem, outVertex, inVertex);
        edges.put(elem, ed);
        
        return ed;
    }
    
    @Override
    public Edge<E, V> insertEdge(V elem1, V elem2, E o) throws InvalidVertexException {
        if (edges.containsKey(o)) {
            throw new IllegalArgumentException("There's already an edge with this element.");
        }

        if (!vertices.containsKey(elem1)) {
            throw new InvalidVertexException("No vertex contains " + elem1);
        }
        if (!vertices.containsKey(elem2)) {
            throw new InvalidVertexException("No vertex contains " + elem2);
        }
        
        MyVertex outVertex = vertexOf(elem1);
        MyVertex inVertex = vertexOf(elem2);

        return insertEdge(outVertex, inVertex, o);
    }

    @Override
    public V removeVertex(Vertex<V> v) throws InvalidVertexException {
        MyVertex vertex = checkVertex(v);
        
        if(hasEdges(v)){
            throw new InvalidVertexException(" Vertex Has Edges!!");
        }
        
        Iterable<Edge<E, V>> incidentEdges = incidentEdges(v);
        for (Edge<E, V> edge : incidentEdges) {
            edges.remove(edge.element());
        }
        
        vertices.remove(v.element());
        return v.element();
    }
    
    private boolean hasEdges(Vertex<V> v) throws InvalidEdgeException {
        return !((List) incidentEdges(v)).isEmpty();
    }
    
    @Override
    public E removeEdge(Edge<E, V> e) throws InvalidEdgeException {
        MyEdge edge = checkEdge(e);
        edges.remove(e.element());
        return e.element();
    }
    
    @Override
    public String toString(){
        String str = "";
        String sb = String.format("Graph with %d vertices and %d edges:\n", numVertices(), numEdges());
        for(Vertex<V> v : vertices.values()){
            str += v.element().toString() + ">[";
            for(Edge e : edges.values()){
                Vertex[] ver = e.vertices();
                if(ver[0] == v || ver[1] == v){
                    str += e.element();
                }
            }
            str += "]\n\n";
        }
        return sb +"\n"+ str;
    }
    
    private MyVertex vertexOf(V vElement) {
        for (Vertex<V> v : vertices.values()) {
            if (v.element().equals(vElement)) {
                return (MyVertex) v;
            }
        }
        return null;
    }
}
