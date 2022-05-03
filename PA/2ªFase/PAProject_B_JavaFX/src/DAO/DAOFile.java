package DAO;

import Data.EdgeData;
import Data.VertexData;
import Graph.Edge;
import Graph.MyGraph;
import Graph.Vertex;
import Logger.Logger;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Classe DAOFile usada para guardar e ler informação em formato txt
 * @author Gabriel Ambrósio - 160221013
 */
public class DAOFile implements DAOInterface{

    private final String path = System.getProperty("user.dir") + "\\src\\Input\\";
    private Logger logger = Logger.getInstance();
    
    /**
     * Metodo que guarda um MyGraph num ficheiro txt, recebido por parametro
     * @param graph - graph a guardar
     * @param filename - nome do ficheiro (.txt not necessary)
     */
    @Override
    public void saveGraph(MyGraph graph, String filename){
        try (PrintWriter writer = new PrintWriter(path + filename + ".txt", "UTF-8")) {
            writer.println(graph.numVertices());
            
            for (Iterator it = graph.vertices().iterator(); it.hasNext();) {
                Vertex<VertexData> vertex = (Vertex<VertexData>) it.next();
                writer.println(vertex.element().getString());
            }
            
            writer.println(graph.numEdges());
            
            for (Iterator it = graph.edges().iterator(); it.hasNext();) {
                Edge<EdgeData, VertexData> edge = (Edge<EdgeData, VertexData>) it.next();
                writer.print(edge.element().getString().charAt(0)+ ",");
                writer.print(edge.element().getString().charAt(1) + ",");
                writer.print(edge.element().getString() + ",");
                writer.print(edge.element().getCost() + "\n");
            }
            writer.close();
            logger.saveLog("Graph Saved In " + filename + ".txt.");
            
        } catch (IOException ex){
        }
    }

    /**
     * Metodo que le um ficheiro txt importa a informação para a uma classe MyGraph
     * @param filename - nome do ficheiro (.txt not necessary)
     * @return graph - graph importado
     */
    @Override
    public MyGraph loadGraph(String filename){
        try{
            Scanner read = new Scanner(new File(path + filename + ".txt"));
            MyGraph graph = new MyGraph();
            read.useDelimiter(",|\\n");

            int vertexNum, edgeNum, cost;
            String vertexStr, edgeVert1, edgeVert2, edgeStr3;
            
            vertexNum = Integer.parseInt(read.next().trim());
            
            int i = 0;
            while (read.hasNext() && i < vertexNum){
                vertexStr = read.next().trim();
                graph.insertVertex(new VertexData(vertexStr));
                i++;
            }
            
            edgeNum = Integer.parseInt(read.next().trim());
            
            i = 0;
            while (read.hasNext() && i < edgeNum){
                edgeVert1 = read.next().trim();
                edgeVert2 = read.next().trim();
                edgeStr3 = read.next().trim();
                cost = Integer.parseInt(read.next().trim());
                
                Iterable<Vertex<VertexData>> vertices = graph.vertices();
                Iterator<Vertex<VertexData>> iterator = vertices.iterator();
                
                Vertex<VertexData> verticeA = null;
                Vertex<VertexData> verticeB = null;
                
                while(iterator.hasNext()){
                    Vertex<VertexData> aux = iterator.next();
                    if(aux.element().getString().equals(edgeVert1)){
                        verticeA = aux;
                    }
                    if(aux.element().getString().equals(edgeVert2)){
                        verticeB = aux;
                    }
                }
                graph.insertEdge(verticeA, verticeB, new EdgeData(edgeStr3, cost));
                i++;
            }
            logger.saveLog("Graph Loaded From " + filename + ".txt.");
            return graph;
        }catch(FileNotFoundException ex){
            
        }
        return null;
    }
}