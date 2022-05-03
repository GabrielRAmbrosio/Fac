package Graph;

import Memento.Memento;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;

/**
 * @author Gabriel Ambrósio - 160221013
 * @param <V>
 * @param <E>
 * 
 * Classe que é a implementação da interface Graph, e que é usada como Model no padrao MVC.
 */
public class MyGraph<V,E> extends Observable implements Graph<V, E> {
    
    private Map<V, Vertex<V>> vertices;
    private Map<E, Edge<E,V>> edges;
    
    public MyGraph(){
        vertices = new HashMap<>();
        edges = new HashMap<>();
        
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
    public Iterable<Edge<E, V>> incidentEdges(Vertex<V> v) throws InvalidEdgeException{

        checkVertex(v);

        List<Edge<E, V>> incidentEdges = new ArrayList<>();
        for (Edge<E, V> edge : edges()){
            if (((MyEdge) edge).contains(v)){
                incidentEdges.add(edge);
            }
        }
        return incidentEdges;
    }
    
    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E, V> e) throws InvalidVertexException, InvalidEdgeException{
        checkVertex(v);
        MyEdge edge = checkEdge(e);
        
        Vertex<V>[] aux = edge.vertices();
        
        if(!edge.contains(v)) return null;
        
        if(aux[0] == v) return aux[1];
        else return aux[0];
    }

    @Override
    public boolean areAdjacent(Vertex<V> v, Vertex<V> u) throws InvalidVertexException {
        checkVertex(v);
        checkVertex(u);
        
        for(Edge<E,V> edge : edges()){
            if(((MyEdge)edge).contains(v) && ((MyEdge)edge).contains(u)) 
                return true;
        }
        return false;
    }
    
    public List<Vertex<V>> getAdjacents(Vertex<V> v){
        List<Vertex<V>> adjacents = new ArrayList<>();
        
        for(Vertex<V> vertex : vertices()){
            if(areAdjacent(v, vertex) && v != vertex) adjacents.add(vertex);
        }
        
        return adjacents;
    }
    
    @Override
    public Vertex<V> insertVertex(V elem) throws InvalidVertexException{
        if(vertices.containsKey(elem)){
            throw new InvalidVertexException(elem + " Already Exists!");
        }
        
        MyVertex vertex = new MyVertex(elem);
        vertices.put(elem, vertex);
        
        setChanged();
        notifyObservers(this);
        
        return vertex;
    }
    
    @Override
    public Edge<E, V> insertEdge(Vertex<V> u, Vertex<V> v, E elem) throws InvalidEdgeException {
        if(edges.containsKey(elem)){
            throw new InvalidEdgeException(elem + " Already Exists!");
        }
        
        MyVertex outVertex = checkVertex(u);
        MyVertex inVertex = checkVertex(v);
        
        MyEdge edge = new MyEdge(elem, outVertex, inVertex);
        edges.put(elem, edge);
        
        setChanged();
        notifyObservers(this);
        
        return edge;
    }
    
    @Override
    public Edge<E, V> insertEdge(V elem1, V elem2, E o) throws InvalidVertexException, InvalidEdgeException {
        if (edges.containsKey(o)) {
            throw new InvalidEdgeException(o + " Already Exists!");
        }
        if (!vertices.containsKey(elem1)) {
            throw new InvalidVertexException("No vertex contains " + elem1);
        }
        if (!vertices.containsKey(elem2)) {
            throw new InvalidVertexException("No vertex contains " + elem2);
        }
        
        MyVertex outVertex = vertexOf(elem1);
        MyVertex inVertex = vertexOf(elem2);

        Edge<E, V> ed = insertEdge(outVertex, inVertex, o);
        
        setChanged();
        notifyObservers(this);
        
        return ed;
    }

    @Override
    public V removeVertex(Vertex<V> v) throws InvalidVertexException {
        checkVertex(v);
        
        Iterable<Edge<E, V>> incidentEdges = incidentEdges(v);
        for (Edge<E, V> edge : incidentEdges) {
            removeEdge(edge);
        }
        
        vertices.remove(v.element());
        
        setChanged();
        notifyObservers(this);
        
        return v.element();
    }
    
    @Override
    public E removeEdge(Edge<E, V> e) throws InvalidEdgeException {
        checkEdge(e);
        
        edges.remove(e.element());
        
        setChanged();
        notifyObservers(this);
        
        return e.element();
    }
    
    private MyVertex checkVertex(Vertex<V> p) throws InvalidVertexException{
        if(p == null){
            throw new InvalidVertexException("");
        }
        
        if(!this.vertices.containsValue(p)){
            throw new InvalidVertexException("Vertex Does Not Exist!");
        }
        try{
            return (MyVertex) p;
        } catch (ClassCastException e){
            throw new InvalidVertexException();
        }
    }
    private MyEdge checkEdge(Edge<E,V> ed) throws InvalidEdgeException{
        if(ed == null){
            throw new InvalidEdgeException();
        }
        if(!this.edges.containsValue(ed)){
            throw new InvalidEdgeException("Edge Does Not Exist!");
        }
        try{
            return (MyEdge) ed;
        } catch (ClassCastException e){
            throw new InvalidEdgeException();
        }
    }
    
    public MyVertex vertexOf(V vElement) {
        for (Vertex<V> v : vertices.values()) {
            if (v.element().equals(vElement)) {
                return (MyVertex) v;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(
                String.format("Graph with %d vertices and %d edges:\n", numVertices(), numEdges())
        );

        sb.append("--- Vertices: \n");
        for (Vertex<V> v : vertices.values()) {
            sb.append("\t").append(v.toString()).append("\n");
        }
        sb.append("\n--- Edges: \n");
        for (Edge<E, V> e : edges.values()) {
            sb.append("\t").append(e.toString()).append("\n");
        }
        return sb.toString();
    }
    
    //Added
    
    //Metodo que copia o conteudo de um Iterable para um HashSet e retorna-o
    private HashSet<Vertex<V>> copyIterable(Iterable<Vertex<V>> iterable) {
        Iterator<Vertex<V>> iter = iterable.iterator();
        HashSet<Vertex<V>> newSet = new HashSet<>();
        while (iter.hasNext()) {
            newSet.add(iter.next());
        }
        return newSet;
    }
    
    /**
     * Metodo que retorna o número de vertices isolados
     * Vertices que não contêm edges
     * @return Integer - numero de vertices isolados
     */
    public int isolatedVertices(){
        HashSet<Vertex<V>> aux = copyIterable(vertices());

        Iterator<Vertex<V>> it = aux.iterator();

        while (it.hasNext()) {
            Vertex<V> v = it.next();
            
            for (Edge<E, V> ed : edges()) {
                if (ed.vertices()[0] == v || ed.vertices()[1] == v) {
                    it.remove();
                    break;
                }
            }
        }
        return aux.size();
    }

    /**
     * Metodo que retorna um Set com os edges de um determinado vertice
     * @param vertex - Vertex
     * @return list - set de edges
     */
    public HashSet<Edge<E, V>> getEdgesOfVertex(Vertex<V> vertex){
        HashSet<Edge<E, V>> list = new HashSet<>();
        Vertex<V> v1 = checkVertex(vertex);
        for(Edge<E, V> e : edges()){
            if(e.vertices()[0] == v1 || e.vertices()[1] == v1){
                list.add(e);
            }
        }
        return list;
    }
    
    /**
     * Metodo que usa o metodo getEdgesOfVertex() para calcular o grau de um vertice
     * @param vertex - vertice a calcular
     * @return Integer - grau, numero de edges do vertice
     */
    public int vertexDegree(Vertex<V> vertex){
        return getEdgesOfVertex(vertex).size();
    }
    
    //Memento

    /**
     * Metodo que cria um novo memento
     * @return
     */
    public Memento createMemento(){
        return new Memento(vertices, edges);
    }

    /**
     * Metodo que restaura o estado dos atributos
     * @param memento - memento
     */
    public void setMemento(Memento memento){
        vertices = memento.getVertices();
        edges = memento.getEdges();
        setChanged();
        notifyObservers(this);
    }
}