package paproject_b_javafx;

import DAO.DAOFile;
import Data.EdgeData;
import Data.VertexData;
import Graph.GraphHandling;
import Graph.MyGraph;
import JavaFX.MainMenu;
import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

/**
 *
 * @author Gabriel Ambrósio - 160221013
 */
public class PAProject_B_JavaFX extends Application {
    
    private static MainMenu menu;
    private static GraphHandling graphHandling;
    private static MyGraph<VertexData, EdgeData> graph;
    private static DAOFile dao;
    
    @Override
    public void start(Stage primaryStage) {
        
        menu = createApp();
        primaryStage.setTitle("Graph");
        primaryStage.setScene(menu.getCreatedScene());
        
        primaryStage.setResizable(false);
        primaryStage.setIconified(false);
        primaryStage.show();
        
        //pergunta se e para guardar o grafo ao sair da aplicação
        primaryStage.setOnCloseRequest(event -> {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Exit Message");
            alert.setHeaderText("Want To Save?");

            ButtonType buttonTypeOne = new ButtonType("Yes");
            ButtonType buttonTypeTwo = new ButtonType("No");
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            
            if (result.get() == buttonTypeOne) {
                //sim
                TextInputDialog dialog = new TextInputDialog("");
                dialog.setTitle("Filename Message");
                dialog.setContentText("Please enter filename:");
                
                //pergunta o nome do ficheiro a guardar
                Optional<String> filename = dialog.showAndWait();
                if (result.isPresent()) {
                    dao.saveGraph(graph, filename.get());
                }
                
            } else if (result.get() == buttonTypeTwo) {
                //nao
                primaryStage.close();
            } else {
                //cancel
                event.consume();
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //TESTES USADOS NUMA FASE INICIAL, PARA TESTAR OS PADRÕES E FUNCIONALIDADES
        
//        dao = new DAOFile();
//        graph = dao.loadGraph("grafo");
//        System.out.println(graph);
//        
//        MainMenu mm = new MainMenu(graph);
//        graphHandling = new GraphHandling(graph, mm);
//        
//        DAOOneJson daojson = new DAOOneJson();
//        daojson.saveGraph(graph, "");
//        MyGraph jsonGraph = daojson.loadGraph("");
//        System.out.println(jsonGraph.toString());
//        
//        dao.saveGraph(graph, "graph");
//        graph = dao.loadGraph("graph");
//        
//        graphHandling = new GraphHandling(graph);
//        
//        //Memento
//        GraphCareTaker caretaker = new GraphCareTaker();
//        System.out.println("vertice num " + graphHandling.getGaph().numVertices()+ "\n");
//        
//        caretaker.save(graphHandling.getGaph());
//        
//        VertexData va = new VertexData("A");
//        graphHandling.getGaph().insertVertex(va);
//        System.out.println("vertice num " + graphHandling.getGaph().numVertices()+ "\n");
//        
//        caretaker.restore(graphHandling.getGaph());
//        System.out.println("vertice num " + graphHandling.getGaph().numVertices()+ "\n");
//        
//        //dijkstra
//        List<VertexData> path = new ArrayList<>();
//        
//        System.out.println("min cost A-B " + graphHandling.minimumCostPath(graphHandling.getVertex("A").element(), graphHandling.getVertex("B").element(), path) + "\n");
//        System.out.println(graphHandling);
//        
//        //Percorrer
//        Iterable<VertexData> list = graphHandling.DFS(graphHandling.getVertex("A"));
//        System.out.println("DFS");
//        for(VertexData v : list){
//            System.out.print(v.getString() + ", ");
//        }
//        System.out.println();
//        System.out.println("BFS");
//        list = graphHandling.BFS(graphHandling.getVertex("A"));
//        for(VertexData v : list){
//            System.out.print(v.getString() + ", ");
//        }
        
        //View
        launch(args);
    }
    
    //MVC
    private static MainMenu createApp(){
        dao = new DAOFile();
        
        //pergunta o nome do ficheiro para fazer load do grafo
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Load Graph");
        dialog.setContentText("Please enter filename:");
        
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            graph = dao.loadGraph(result.get());
            //enquato não conseguir ler o ficheiro com o nome inserido, pergunta pelo nome
            while(graph == null){
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Error On Load");
                alert.setHeaderText(null);
                alert.setContentText("File Doesnt Exist!");

                alert.showAndWait();
                
                result = dialog.showAndWait();
                graph = dao.loadGraph(result.get());
            }
            MainMenu mm = new MainMenu(graph);
            graphHandling = new GraphHandling(graph, mm);
            return mm;
        }
        Platform.exit();
        System.exit(0);
        return null;
        
    }
    
}
