package DAO;

import Data.EdgeData;
import Data.VertexData;
import Graph.Edge;
import Graph.MyGraph;
import Graph.Vertex;
import java.io.BufferedReader;
import java.io.FileReader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe DAOJson usada para guardar e ler informação em formato gson
 * @author Gabriel Ambrósio - 160221013
 */
public class DAOOneJson implements DAOInterface{

    private final String path = System.getProperty("user.dir") + "\\src\\Input\\";
    private final String filename = "graphs.json";
    
    private JsonData select() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path + filename));
            Gson gson = new GsonBuilder().create();

            JsonData gh = gson.fromJson(br, new TypeToken<JsonData>() { }.getType());
            //MyGraph<VertexData, EdgeData> gsonGraph = gson.fromJson(br, new TypeToken<MyGraph<VertexData, EdgeData>>() { }.getType());
            
            return gh;

        } catch (IOException ex) {
            
        }
        return null;
    }

    /**
     * Metodo que guarda um MyGraph num ficheiro gson predefinido
     * @param graph - MyGraph a guardar
     * @param filename - filename
     */
    @Override
    public void saveGraph(MyGraph<VertexData, EdgeData> graph, String filename) {
        FileWriter writer = null;
        try {
            Gson gson = new GsonBuilder().create();
            JsonData data = new JsonData(graph);
            writer = new FileWriter(path + this.filename);
            gson.toJson(data, writer);
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            
        }
    }

    /**
     * Metodo que le um ficheiro gson e importa o grafo
     * @param filename - filename
     * @return
     */
    @Override
    public MyGraph loadGraph(String filename) {
        MyGraph<VertexData, EdgeData> graph = new MyGraph<>();
        
        for(Vertex<VertexData> vd : select().getVertices()){
            graph.insertVertex(vd.element());
        }
        for(Edge<EdgeData, VertexData> ed : select().getEdges()){
            graph.insertEdge(ed.vertices()[0], ed.vertices()[1], ed.element());
        }
        return graph;
    }
}