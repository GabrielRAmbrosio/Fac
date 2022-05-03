package DAO;

import Data.EdgeData;
import Data.VertexData;
import Graph.MyGraph;

/**
 * Interface para o padrao DAO
 * @author Gabriel Ambrósio - 160221013
 */
public interface DAOInterface {
    
    public void saveGraph(MyGraph<VertexData, EdgeData> graph, String filename);
    public MyGraph loadGraph(String filename);
}
