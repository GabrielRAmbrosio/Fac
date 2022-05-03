package Memento;

import Graph.Edge;
import Graph.Vertex;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe padrao Memento que guarda o estado dos atributos de MyGraph
 * @author Gabriel Ambrósio - 160221013
 * @param <V> - VertexData
 * @param <E> - EdgeData
 */
public class Memento<V,E> {
    private Map<V, Vertex<V>> vertices;
    private Map<E, Edge<E,V>> edges;
    
    /**
     * Construtor da classe Memento, inicializa os atributos com os do graph a guardar
     * @param vertices - map de vertices
     * @param edges - map de edges
     */
    public Memento(Map<V, Vertex<V>> vertices, Map<E, Edge<E,V>> edges){
        this.vertices = new HashMap<>(vertices);
        this.edges = new HashMap<>(edges);
    }
    
    /**
     * Metodo que retona os vertices guardados
     * @return vertices - map de vertices
     */
    public Map<V, Vertex<V>> getVertices(){
        return vertices;
    }

    /**
     * Metodo que retorna os edges guardados
     * @return edges - map de edges
     */
    public Map<E, Edge<E,V>> getEdges(){
        return edges;
    }
}
