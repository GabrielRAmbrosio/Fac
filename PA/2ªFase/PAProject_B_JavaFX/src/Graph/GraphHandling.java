package Graph;

import Data.EdgeData;
import Data.VertexData;
import JavaFX.MainMenu;
import Logger.Logger;
import Memento.GraphCareTaker;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * Classe GraphHandling usada para guardar (Memento), e gerir a informação da classe MyGraph
 * É usada como Controller no padrao MVC
 * @author Gabriel Ambrósio - 160221013
 */
public class GraphHandling extends MyGraph{
    
    private MyGraph<VertexData, EdgeData> graph;
    private MainMenu menu;
    private GraphCareTaker caretaker;
    private Logger logger;
    
    /**
     * Construtor que inicializa os seus atributos com os do MyGraph recebido, também sincroniza o Model (MyGraph) com a View (MainMenu) 
     * e por fim garante que o estado do graph é guardado usando o Memento
     * 
     * @param graph - Model
     * @param menu - View
     */
    public GraphHandling(MyGraph<VertexData, EdgeData> graph, MainMenu menu){
        super();
        this.graph = graph;
        this.menu = menu;
        this.menu.setTriggers(this);
        this.graph.addObserver(this.menu);
        
        logger = Logger.getInstance();
        caretaker = new GraphCareTaker();
        caretaker.save(this.graph);
    }
    
    /**
     * Verifica se o vertice recebido existe no grafo
     * @param vertex - vertexdata
     * @return find - found vertex
     * @throws InvalidVertexException - vertex not found
     */
    public Vertex<VertexData> checkVertexData(VertexData vertex) throws InvalidVertexException {
        if (vertex == null) {
            throw new InvalidVertexException("Vertex cannot be null!");
        }
        Vertex<VertexData> find = null;
        for (Vertex<VertexData> v : graph.vertices()) {
            if (v.element().getString().trim().equals(vertex.getString().trim())) {
                find = v;
            }
        }
        if (find == null) {
            throw new InvalidVertexException("Vertex with String (" + vertex.getString()+ ") does not exist!");
        }
        return find;
    }
    
    /**
     * Verifica se o edge recebido existe no grafo
     * @param edge - EdgeData
     * @return find - found vertex
     * @throws InvalidVertexException - vertex not found
     */
    public Edge<EdgeData, VertexData> checkEdgeData(EdgeData edge) throws InvalidVertexException {
        if (edge == null) {
            throw new InvalidVertexException("Vertex cannot be null!");
        }
        Edge<EdgeData, VertexData> find = null;
        for (Edge<EdgeData, VertexData> ed : graph.edges()) {
            if (ed.element().getString().trim().equals(edge.getString().trim())) {
                find = ed;
            }
        }
        if (find == null) {
            throw new InvalidVertexException("Edge with String (" + edge.getString()+ ") does not exist!");
        }
        return find;
    }
    
    /**
     * Adiciona um novo vertice com a data recebida por parametro
     * Como foi feita uma alteração, é usado o memento e o logger
     * @param vertex - vertexdata
     * @return v - Added vertex
     * @throws InvalidVertexException - vertex not found
     */
    public Vertex<VertexData> addVertexData(VertexData vertex) throws InvalidVertexException {
        caretaker.save(graph);
        
        if (vertex == null) {
            throw new InvalidVertexException("Vertex cannot be null!");
        }

        for(Vertex<VertexData> v : graph.vertices()){
            if(v.element().getString().trim().equals(vertex.getString().trim())){
                throw new InvalidVertexException("Vertex with String (" + vertex.getString() + ") already exists!");
            }
        }
        
        try {
            Vertex<VertexData> v = graph.insertVertex(vertex);
            logger.saveLog("Vertex " + v.element().getString() +" Added.");
            return v;
        } catch (InvalidVertexException e) {
            throw new InvalidVertexException("Vertex with String (" + vertex.getString() + ") already exists!");
        }
    }
    
    /**
     * Remove um vertice com a data pretendida
     * Como foi feita uma alteração, é usado o memento e o logger
     * @param vertex - vertexdata
     * @return v - removed vertex
     * @throws InvalidVertexException - vertex not found
     */
    public Vertex<VertexData> removeVertexData(VertexData vertex) throws InvalidVertexException {
        caretaker.save(graph);
        
        if (vertex == null) {
            throw new InvalidVertexException("Vertex doenst exist!");
        }

        try {
            Vertex<VertexData> v = checkVertexData(vertex);
            graph.removeVertex(v);
            logger.saveLog("Vertex " + v.element().getString() +" Removed.");
            return v;
        } catch (InvalidVertexException e) {
            throw new InvalidVertexException("Vertex with String (" + vertex.getString() + ") doenst exists!");
        }
    }
    
    /**
     * Adiciona um edge com a data recebida, a conectar os dois vertices também recebidos
     * Como foi feita uma alteração, é usado o memento e o logger
     * @param vertex1 - vertexdata
     * @param vertex2 - vertexdata
     * @param edge - edgedata
     * @return added edge
     * @throws InvalidVertexException - vertex not found
     */
    public Edge<EdgeData, VertexData> addEdgeData(VertexData vertex1, VertexData vertex2, EdgeData edge) throws InvalidEdgeException{
        caretaker.save(graph);
        
        if (edge == null) {
            throw new InvalidVertexException("Edge is null!");
        }

        Vertex<VertexData> v1 = checkVertexData(vertex1);
        Vertex<VertexData> v2 = checkVertexData(vertex2);

        try {
            Edge<EdgeData, VertexData> ed = graph.insertEdge(v1, v2, edge);
            logger.saveLog("Edge " + ed.element().getString() +" Added.");
            return ed;
        } catch (InvalidEdgeException e) {
            throw new InvalidEdgeException("The Edge (" + edge.getString() + ") already exists!");
        }
    }
    
     /**
     * Remove um edge com a data recebida
     * Como foi feita uma alteração, é usado o memento e o logger
     * @param edge - dataedge
     * @return v - removed vertex
     * @throws InvalidVertexException - vertex not found
     */
    public Edge<EdgeData, VertexData> removeEdgeData(EdgeData edge) throws InvalidEdgeException {
        caretaker.save(graph);
        
        if (edge == null) {
            throw new InvalidVertexException("Vertex doenst exist!");
        }

        try {
            Edge<EdgeData, VertexData> ed = checkEdgeData(edge);
            graph.removeEdge(ed);
            logger.saveLog("Edge " + ed.element().getString() +" Removed.");
            return ed;
        } catch (InvalidEdgeException e) {
            throw new InvalidEdgeException("Edge with String (" + edge.getString() + ") doenst exists!");
        }
    }
    
    /**
     * Metodo que retorna um Vertex com a data pretendida
     * @param vd - string
     * @return v/null - found vertex
     */
    public Vertex<VertexData> getVertex(String vd){
        for(Vertex<VertexData> v : graph.vertices()){
            if(v.element().getString().trim().equals(vd.trim())){
                return v;
            }
        }
        return null;
    }
    
    /**
     * Metodo que retorna um Edge com a data pretendida
     * @param edge - string
     * @return v/null - found vertex
     */
    public Edge<EdgeData, VertexData> getEdge(String edge){
        for(Edge<EdgeData, VertexData> ed : graph.edges()){
            if(ed.element().getString().trim().equals(edge.trim())){
                return ed;
            }
        }
        return null;
    }
    
    /**
     * Metodo que retorna um Set com todos os edges que ligam os dois vertices recebidos
     * @param vd1 - string of vertexdata
     * @param vd2 - string of vertexdata
     * @return list - list of edges between both vetrexdata
     */
    public HashSet<Edge<EdgeData, VertexData>> getEdgesBetween(String vd1, String vd2){
        HashSet<Edge<EdgeData, VertexData>> list = new HashSet<>();
        Vertex<VertexData> v1 = getVertex(vd1);
        Vertex<VertexData> v2 = getVertex(vd2);
        for(Edge<EdgeData, VertexData> e : graph.edges()){
            if((e.vertices()[0] == v1 && e.vertices()[1] == v2) || (e.vertices()[0] == v2 && e.vertices()[1] == v1)){
                list.add(e);
            }
        }
        return list;
    }
    
    /**
     * Metodo que retorna um Set com todos os edges do vertice recebido
     * @param vertex - vertexdata
     * @return list - set of edges
     */
    public HashSet<Edge<EdgeData, VertexData>> getEdgesOfVertex(VertexData vertex){
        HashSet<Edge<EdgeData, VertexData>> list = new HashSet<>();
        Vertex<VertexData> v1 = checkVertexData(vertex);
        for(Edge<EdgeData, VertexData> e : graph.edges()){
            if(e.vertices()[0] == v1 || e.vertices()[1] == v1){
                list.add(e);
            }
        }
        return list;
    }
    
    //dijkstra

    public double minimumCostPath(VertexData orig, VertexData dst, List<VertexData> places){
            HashMap<Vertex<VertexData>, Vertex<VertexData>> parents = new HashMap();
            HashMap<Vertex<VertexData>, Double> distances = new HashMap();
            places.clear();
            
            Vertex<VertexData> vOrig= checkVertexData(orig);
            
            dijkstra(vOrig, distances, parents);
            
            double cost = distances.get(checkVertexData(dst));
            Vertex<VertexData> vertex = checkVertexData(dst);
            
            do{
                if(getEdgesOfVertex(vertex.element()).isEmpty()){
                    places.clear();
                    return 0;
                }
                places.add(0,vertex.element());
                vertex = parents.get(vertex);
            }while(vertex != vOrig);
            return cost;
    }
    
    private void dijkstra(Vertex<VertexData> orig, Map<Vertex<VertexData>, Double> distances, Map<Vertex<VertexData>, Vertex<VertexData>> parents){

            Set<Vertex<VertexData>> unvisited = new HashSet();
            
            for(Vertex<VertexData> vertex : graph.vertices()) {
                unvisited.add(vertex);
                distances.put(vertex, Double.MAX_VALUE);
                parents.put(vertex, null);
            }
            
            distances.put(orig, 0.0);
            while(!unvisited.isEmpty()) {
                Vertex<VertexData> lowCostVert = findLowerCostVertex(unvisited, distances);
                unvisited.remove(lowCostVert);
                for(Edge<EdgeData, VertexData> edge : graph.incidentEdges(lowCostVert)) {
                    Vertex<VertexData> opposite = graph.opposite(lowCostVert, edge);
                    if(unvisited.contains(opposite)) {
                        double dist = edge.element().getCost() + distances.get(lowCostVert);
                        if(distances.get(opposite) > dist) {
                            distances.put(opposite, dist);
                            parents.put(opposite, lowCostVert);
                        }
                    }
                }
            }
        }
    
    private Vertex<VertexData> findLowerCostVertex(Set<Vertex<VertexData>> unvisited, Map<Vertex<VertexData>, Double> distances){
        double min = Double.MAX_VALUE;
        Vertex<VertexData> minCostVertex = null;
        
        for(Vertex<VertexData> vertex : unvisited) {
            if(distances.get(vertex) <= min) {
                minCostVertex = vertex;
                min = distances.get(vertex);
            }
        }
        
        return minCostVertex;
    }
    
    //percorrer

    /**
     * depth-first search
     * @param v - vertex
     * @return path
     */
    
    public Iterable<VertexData> DFS(Vertex<VertexData> v){
        List<VertexData> path = new ArrayList<>();
        Set<Vertex<VertexData>> visited = new HashSet<>();
        Stack<Vertex> stack = new Stack<>();
        
        visited.add(checkVertexData(v.element()));
        stack.push(v);
        
        while(!stack.isEmpty()){
            Vertex<VertexData> vLook = stack.pop();
            path.add(vLook.element());
            
            for(Vertex<VertexData> vAdj : graph.getAdjacents(vLook)){
                if(!visited.contains(vAdj)){
                    visited.add(vAdj);
                    stack.push(vAdj);
                }
            }
        }
        return path;
    }
    
    /**
     * breadth-first search
     * @param v - vertex
     * @return path
     */
    public Iterable<VertexData> BFS(Vertex<VertexData> v){
        List<VertexData> path = new ArrayList<>();
        Set<Vertex<VertexData>> visited = new HashSet<>();
        Queue<Vertex<VertexData>> queue = new LinkedList<>();
        
        visited.add(checkVertexData(v.element()));
        queue.add(v);
        
        while(!queue.isEmpty()){
            Vertex<VertexData> vLook = queue.remove();
            path.add(vLook.element());
            
            for(Vertex<VertexData> vAdj : graph.getAdjacents(vLook)){
                if(!visited.contains(vAdj)){
                    visited.add(vAdj);
                    queue.add(vAdj);
                }
            }
        }
        
        return path;
    }
    
    /**
     * Retorna o MyGraph
     * @return
     */
    public MyGraph<VertexData, EdgeData> getGaph(){
        return graph;
    }
    
    /**
     *toString()
     * @return
     */
    @Override
    public String toString(){
        return getGaph().toString();
    }
    
    /**
     * Restaura o graph e atualiza o ficheiro do logger
     */
    public void restoreGraph() {
        if (caretaker.restore(graph)) {
            logger.saveLog("Graph Restored.");
        }
    }
}
