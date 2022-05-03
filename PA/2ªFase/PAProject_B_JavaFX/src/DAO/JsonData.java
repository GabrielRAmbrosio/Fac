package DAO;

import Data.EdgeData;
import Data.VertexData;
import Graph.Edge;
import Graph.MyGraph;
import Graph.Vertex;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que guarda a informaçãoa a ser usada na classe DAOOneJson
 * Criei esta classe porque não estava a conseguir guardar o objeto principal MyGraph
 * @author Gabriel Ambrósio - 160221013
 */
public class JsonData {
    private List<Vertex<VertexData>> vertices;
    private List<Edge<EdgeData, VertexData>> edges;
    private MyGraph<VertexData, EdgeData> graph;
    
    /**
     * Constutor da classe JsonData, recebe um MyGraph, para guardar os seus atributos (vertives e edges)
     * @param graph - MyGraph, graph a guardar
     */
    public JsonData(MyGraph<VertexData, EdgeData> graph){
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        this.graph = graph;
        copyArrays();
    }
    
    //copia os atributos do MyGraph recebido (graph), para esta classe
    private void copyArrays(){
        for(Vertex<VertexData> v : graph.vertices()){
            vertices.add(v);
        }
        
        for(Edge<EdgeData, VertexData> ed : graph.edges()){
            edges.add(ed);
        }
    }
    
    /**
     * Metodo que retorna os vertices guardados
     * @return vertices - Lista de vertices
     */
    public List<Vertex<VertexData>> getVertices(){
        return vertices;
    }

    /**
     * Metodo que retorna os edges guardados
     * @return edges - Lista de edges
     */
    public List<Edge<EdgeData, VertexData>> getEdges(){
        return edges;
    }
}
