package JavaFX;

import Data.DijkstraData;
import Data.EdgeData;
import Data.VertexData;
import Graph.Edge;
import Graph.GraphHandling;
import Graph.InvalidVertexException;
import Graph.MyGraph;
import Graph.Vertex;
import JavaFX.GraphView.CircularSortedPlacementStrategy;
import JavaFX.GraphView.GraphPanel;
import JavaFX.GraphView.VertexPlacementStrategy;
import Logger.Logger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * Classe MainMenu onde é criado tudo o que é UI (JavaFX), esta classe é usada como a View do padrao MVC
 * @author Gabriel Ambrósio - 160221013
 */
public class MainMenu extends BorderPane implements Observer{
    
    private final int ROW_HEIGHT = 300;
    
    private MyGraph<VertexData, EdgeData> gp;
    private VertexPlacementStrategy strategy = new CircularSortedPlacementStrategy();
    
    //Roots
    private Stage chartStage, dijkstraStage;
    private Scene scene, chartScene, dijkstraScene;
    private BorderPane bp;
    
    private GraphPanel<VertexData, EdgeData> graphView;
    private VBox vbox, listVBox, runGraph, leftVbox,rigthVbox, dijkstraVbox;
    private HBox hbox, graphBox;
    
    //Nodes
    private ObservableList<Vertex> list;
    private ListView<Vertex> viewList;
    private HashSet<Vertex> vertices;
    
    private Button createButton, deleteVertexButton, backButton, deleteEdgeButton, chartInfo, startSearch, startDijkstra;
    private TextField tf1, tf2, tf3, start, startDij, tf4;
    private Label verticesNumber, edgesNumber, isolatedVertices, vertexDegree, searchDFS, searchBFS, searchLabel, dijkstraLabel, chartLabel;
    
    //Charts
    private CategoryAxis xAxis;
    private NumberAxis yAxis;
    private BarChart<String, Number> bc;
    
    private TableView<DijkstraData> table;
    private TableColumn<DijkstraData, String> vertexCol, pathCol, endCol, costCol;
    private ObservableList<DijkstraData> listofList;
    
    //Logger
    private Logger logger = Logger.getInstance();
    
    public MainMenu(MyGraph<VertexData, EdgeData> gp){
        super();
        this.gp = gp;
        initialize();
        scene = new Scene(start(), 1200, 800);
        plot();
    }
    
    //inicializa todos os atibutos necessários para começar a aplicação
    private void initialize(){
        //é usada uma border pane
        
        //center
        graphBox = new HBox();
        graphBox.setMinHeight(600);
        graphBox.setMinWidth(600);
        graphBox.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        
        graphView = new GraphPanel<>(gp, strategy);
        graphView.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        
        //posx
        graphView.setTranslateX(25 - graphView.getLayoutBounds().getMinX());
        //posy
        graphView.setTranslateY(50 - graphView.getLayoutBounds().getMinY());
        
        
        //rigth
        vbox = new VBox();
        vbox.setMinHeight(350);
        vbox.setMinWidth(250);
        vbox.setMaxHeight(Double.MAX_VALUE);
        vbox.setMaxWidth(Double.MAX_VALUE);
        vbox.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        vbox.setPadding(new Insets(10)); 
        vbox.setSpacing(10);
        
            //rigthtop
        
        rigthVbox = new VBox();
        rigthVbox.setMinHeight(170);
        rigthVbox.setMinWidth(150);
        rigthVbox.setMaxHeight(Double.MAX_VALUE);
        rigthVbox.setMaxWidth(Double.MAX_VALUE);
        rigthVbox.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        rigthVbox.setPadding(new Insets(10)); 
        rigthVbox.setSpacing(10);
        createButton = new Button("Add Vertex");
        
        tf1 = new TextField();
        tf2 = new TextField();
        tf3 = new TextField();
        tf1.setPromptText("Vertex");
        tf2.setPromptText("Vertex");
        tf3.setPromptText("Value");
        
        //posy
        createButton.setTranslateY(10 - createButton.getLayoutBounds().getMinY());
        
        tf1.setTranslateY(10 - tf1.getLayoutBounds().getMinY());
        tf2.setTranslateY(10 - tf2.getLayoutBounds().getMinY());
        tf3.setTranslateY(10 - tf3.getLayoutBounds().getMinY());
        
            //rigthbot
        
        listVBox = new VBox();
        listVBox.setMaxHeight(Control.USE_PREF_SIZE);
        listVBox.setMaxWidth(Control.USE_PREF_SIZE);
        listVBox.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        listVBox.setPadding(new Insets(5)); 
        listVBox.setSpacing(10);
        
        vertices = copyIterable(gp.vertices());
        list = FXCollections.observableArrayList(this.vertices);
        viewList = new ListView<>(list);
        viewList.getSelectionModel().select(0);
        viewList.setPrefHeight(ROW_HEIGHT);
        
        //atualiza a cor dos vertices e edges e o grau, dependendo do vertice selecionado nesta listview
        viewList.setOnMouseClicked(e -> {
            graphView.resetColorsToDefault();
            Vertex<VertexData> vertex = viewList.getSelectionModel().getSelectedItem();
            graphView.setVertexColor(vertex, Color.GOLD, Color.BROWN);
            colorEdges(gp.getEdgesOfVertex(vertex));
            vertexDegree.setText("Vertex Degree: " + gp.vertexDegree(vertex));
        });
        
        deleteVertexButton = new Button("Delete Vertex");
        
        tf4 = new TextField();
        tf4.setPromptText("Edge");
        
        deleteEdgeButton = new Button("Delete Edge");
        
        //posy
        viewList.setTranslateY(-10 - viewList.getLayoutBounds().getMinY());
        
        tf4.setTranslateY(20 - tf4.getLayoutBounds().getMinY());
        deleteEdgeButton.setTranslateY(25 - deleteEdgeButton.getLayoutBounds().getMinY());
        
        
        //left
        
        leftVbox = new VBox();
        leftVbox.setMinHeight(200);
        leftVbox.setMinWidth(250);
        leftVbox.setMaxHeight(Double.MAX_VALUE);
        leftVbox.setMaxWidth(Double.MAX_VALUE);
        leftVbox.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        leftVbox.setPadding(new Insets(10)); 
        leftVbox.setSpacing(10);
        
            //lefttop
        runGraph = new VBox();
        runGraph.setMinHeight(100);
        runGraph.setMinWidth(250);
        runGraph.setMaxHeight(Double.MAX_VALUE);
        runGraph.setMaxWidth(Double.MAX_VALUE);
        runGraph.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        runGraph.setPadding(new Insets(10)); 
        runGraph.setSpacing(10);
        
        searchDFS = new Label();
        searchDFS.setText("DFS: ");
        
        searchBFS = new Label();
        searchBFS.setText("BFS: ");
        
        start = new TextField();
        start.setPromptText("Start Vertex");
        
        startSearch = new Button("Start Course");
        
        searchLabel = new Label("Search");
        searchLabel.setFont(new Font("Arial", 25));
        searchLabel.setTextFill(Color.BLACK);
        
        //posy
        searchDFS.setTranslateY(10 - searchDFS.getLayoutBounds().getMinY());
        searchBFS.setTranslateY(15 - searchBFS.getLayoutBounds().getMinY());
        
            //leftbot
        dijkstraVbox = new VBox();
        dijkstraVbox.setMinHeight(200);
        dijkstraVbox.setMinWidth(250);
        dijkstraVbox.setMaxHeight(Double.MAX_VALUE);
        dijkstraVbox.setMaxWidth(Double.MAX_VALUE);
        dijkstraVbox.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        dijkstraVbox.setPadding(new Insets(10)); 
        dijkstraVbox.setSpacing(10);
        
        dijkstraLabel = new Label("Dijkstra");
        dijkstraLabel.setFont(new Font("Arial", 25));
        dijkstraLabel.setTextFill(Color.BLACK);
        
        startDijkstra = new Button("Start Dijkstra");
        
        startDij = new TextField();
        startDij.setPromptText("Start Vextex");
        
        chartLabel = new Label("Chart");
        chartLabel.setFont(new Font("Arial", 25));
        chartLabel.setTextFill(Color.BLACK);
        
        chartInfo = new Button("Chart Information");
        
        backButton = new Button("Back");
        
        //posy
        dijkstraLabel.setTranslateY(50 - dijkstraLabel.getLayoutBounds().getMinY());
        startDijkstra.setTranslateY(50 - startDijkstra.getLayoutBounds().getMinY());
        startDij.setTranslateY(50 - startDij.getLayoutBounds().getMinY());
        chartLabel.setTranslateY(130 - chartLabel.getLayoutBounds().getMinY());
        chartInfo.setTranslateY(130 - chartInfo.getLayoutBounds().getMinY());
        backButton.setTranslateY(250 - backButton.getLayoutBounds().getMinY());
        
        
        //top
        hbox = new HBox();
        hbox.setMinHeight(100);
        hbox.setMaxWidth(Double.MAX_VALUE);
        hbox.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        hbox.setPadding(new Insets(10)); 
        hbox.setSpacing(10);
        
        verticesNumber = new Label();
        edgesNumber = new Label();
        isolatedVertices = new Label();
        vertexDegree = new Label();
        
        verticesNumber.setText("Vertices: " + gp.numVertices());
        edgesNumber.setText("Edges: " + gp.numEdges());
        isolatedVertices.setText("Isolated Vertices: " + gp.isolatedVertices());
        Vertex<VertexData> vertex = viewList.getSelectionModel().getSelectedItem();
        vertexDegree.setText("Vertex Degree: " + gp.vertexDegree(vertex));
        
        //font
        verticesNumber.setFont(new Font("Arial", 17));
        edgesNumber.setFont(new Font("Arial", 17));
        isolatedVertices.setFont(new Font("Arial", 17));
        vertexDegree.setFont(new Font("Arial", 17));
        
        //color
        verticesNumber.setTextFill(Color.BLACK);
        edgesNumber.setTextFill(Color.BLACK);
        isolatedVertices.setTextFill(Color.BLACK);
        vertexDegree.setTextFill(Color.BLACK);
        
        //posx
        verticesNumber.setTranslateX(350 - verticesNumber.getLayoutBounds().getMinX());
        edgesNumber.setTranslateX(350 - verticesNumber.getLayoutBounds().getMinX());
        isolatedVertices.setTranslateX(350 - verticesNumber.getLayoutBounds().getMinX());
        vertexDegree.setTranslateX(350 - verticesNumber.getLayoutBounds().getMinX());
        
        //posy
        verticesNumber.setTranslateY(25 - verticesNumber.getLayoutBounds().getMinY());
        edgesNumber.setTranslateY(25 - verticesNumber.getLayoutBounds().getMinY());
        isolatedVertices.setTranslateY(25 - verticesNumber.getLayoutBounds().getMinY());
        vertexDegree.setTranslateY(25 - verticesNumber.getLayoutBounds().getMinY());
    }
    
    /*
    cria a pagina inicial, é usada uma borderpane
    no centro é mostrado o grafo
    no topo a informação do grafo
    na direita uma lista dos vertices, e onde se pode remover/adicionar vertices edges
    na esquerda o djkstra os search e o chart
    */
    private BorderPane start(){
        bp = new BorderPane();
        
        bp.setCenter(createPanel());
        bp.setTop(createHBox());
        bp.setRight(createViewListVBox());
        bp.setLeft(createRunVBox());
        
        return bp;
    }
    
    //center
    private HBox createPanel(){
        graphBox.getChildren().add(graphView);
        return graphBox;
    }
    //top
    private HBox createHBox(){
        hbox.getChildren().addAll(verticesNumber, edgesNumber, isolatedVertices, vertexDegree);
        return hbox;
    }
    //rigth
    private VBox createViewListVBox(){
        listVBox.getChildren().addAll(viewList, deleteVertexButton, tf4, deleteEdgeButton);
        rigthVbox.getChildren().addAll(createButton, tf1, tf2, tf3);
        vbox.getChildren().addAll(rigthVbox, listVBox);
        return vbox;
    }
    //left
    private VBox createRunVBox(){
        runGraph.getChildren().addAll(searchLabel, start,startSearch, searchDFS, searchBFS);
        dijkstraVbox.getChildren().addAll(dijkstraLabel, startDij, startDijkstra, chartLabel, chartInfo, backButton);
        leftVbox.getChildren().addAll(runGraph, dijkstraVbox);
        return leftVbox;
    }
    
    //cria uma nova janela com o bar chart
    private void createChartWindow(){
        HBox graphPane = new HBox();

        graphPane.getChildren().add(createBarChart());
        chartScene = new Scene(graphPane, 1000, 800);

        chartStage = new Stage();

        chartStage.setTitle("Chart");
        chartStage.setScene(chartScene);

        chartStage.setX(scene.getWindow().getX() + 200);
        chartStage.setY(scene.getWindow().getY() + 100);

        chartStage.setResizable(false);
        chartStage.setIconified(false);
        chartStage.initModality(Modality.APPLICATION_MODAL);
        chartStage.show();
    }
    
    //cria o bar chart
    private BarChart<String, Number> createBarChart(){
        xAxis = new CategoryAxis();
        yAxis = new NumberAxis();
        bc = new BarChart<>(xAxis, yAxis);
        bc.setTitle("Graph Chart");
        xAxis.setLabel("Vertex");
        yAxis.setLabel("Degree");
        
        XYChart.Series series1 = new XYChart.Series();
        //por cada vertice adiciona uma nova barra com o seu nome e o seu grau
        for(Vertex<VertexData> v : vertices){
            series1.getData().add(new XYChart.Data(v.element().getString(), gp.vertexDegree(v)));
        }
        
        bc.getData().add(series1);
        bc.setPrefHeight(800);
        bc.setPrefWidth(1000);
        
        bc.setLegendVisible(false);
        
        return bc;
    }
    
    //cria uma nova janela para a tableview
    private void createDijkstraWindow(){
        HBox dijkstraPane = new HBox();
        dijkstraPane.getChildren().addAll(createTableView());
        
        dijkstraScene = new Scene(dijkstraPane, 500, 300);

        dijkstraStage = new Stage();

        dijkstraStage.setTitle("Dijkstra");
        dijkstraStage.setScene(dijkstraScene);

        dijkstraStage.setX(scene.getWindow().getX() + 200);
        dijkstraStage.setY(scene.getWindow().getY() + 100);

        dijkstraStage.setResizable(false);
        dijkstraStage.setIconified(false);
        dijkstraStage.initModality(Modality.WINDOW_MODAL);
        dijkstraStage.show();
    }
    
    //cria a tableview
    private TableView createTableView(){
        table = new TableView();
        table.setEditable(false);
        
        table.setMinHeight(300);
        table.setMinWidth(510);
        //primeira coluna guarada o nome do vertice escolhido como start
        vertexCol = new TableColumn("Start");
        vertexCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        vertexCol.setMaxWidth(100);
        vertexCol.setMinWidth(100);
        vertexCol.setEditable(false);
        
        //segunda coluna guarda o caminho
        pathCol = new TableColumn("Path");
        pathCol.setMinWidth(210);
        pathCol.setMaxWidth(210);
        pathCol.setCellValueFactory(new PropertyValueFactory<>("path"));
        
        //terceira coluna guarada o nome do vertice escolhido como end
        endCol = new TableColumn("End");
        endCol.setMinWidth(100);
        endCol.setMaxWidth(100);
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        
        //ultima guarda o custo do path de start a end
        costCol = new TableColumn("Cost");
        costCol.setMinWidth(100);
        costCol.setMaxWidth(100);
        costCol.setCellValueFactory(new PropertyValueFactory<>("cost"));
        
        table.setItems(listofList);
        
        table.getColumns().addAll(vertexCol, pathCol, endCol, costCol);
        return table;
    }
    
    private void plot(){
        graphView.plotGraph();
    }
    
    public Scene getCreatedScene(){
        return scene;
    }
    
    private HashSet<Vertex> copyIterable(Iterable<Vertex<VertexData>> iterable) {
        Iterator<Vertex<VertexData>> iter = iterable.iterator();
        HashSet<Vertex> newSet = new HashSet<Vertex>();
        while (iter.hasNext()) {
            newSet.add(iter.next());
        }
        return newSet;
    }
    
    //MVC
    public void setTriggers(GraphHandling gh) {
        //cria a ação de criar um novo vertice/edge
        createButton.setOnAction(e -> {
            //se um dos dois primeiros textfields não estao vazios (usados para inserir o nome do vertice a adicionar) e o ultimo esta (custo)
            //vamos assumir que o utilizador que adicionar apenas um vertice isolado (em vez de adicionar dois e um edge)
            if((!tf1.getText().isEmpty() || !tf2.getText().isEmpty()) && tf3.getText().isEmpty()){
                //se for o primeiro que não está vazio
                if(!tf1.getText().isEmpty() && tf2.getText().isEmpty()){
                    VertexData vd1 = new VertexData(tf1.getText().trim());
                    try {
                        gh.checkVertexData(vd1);
                        showAlert("Vertex Already Exists!");
                    } catch (InvalidVertexException i) {//não existe adiciona
                        gh.addVertexData(vd1);
                        setPrompt();
                    }
                }else if(tf1.getText().isEmpty() && !tf2.getText().isEmpty()){
                    //se for o segundo
                    VertexData vd1 = new VertexData(tf2.getText().trim());
                    try {
                        gh.checkVertexData(vd1);
                        showAlert("Vertex Already Exists!");
                    } catch (InvalidVertexException i) {//não existe adiciona
                        gh.addVertexData(vd1);
                        setPrompt();
                    }
                } else {
                    //se os dois textfield estiverem preenchidos mas o custo não, assumimos que o utilizador que adicionar
                    //dois vertices e um edge logo da erro a pedir o custo
                    showAlert("Cost Of Edge Missing!");
                }
            }else if (!tf1.getText().isEmpty() && !tf2.getText().isEmpty() && !tf3.getText().isEmpty()) {
                //se todos os textfields estao preenchidos
                if (!tf1.getText().trim().equals(tf2.getText().trim())) {
                    //se os dois primeiro não forem iguais
                    try {
                        Vertex<VertexData> v1, v2;
                        Edge<EdgeData, VertexData> ed1;

                        VertexData vd1 = new VertexData(tf1.getText().trim());
                        VertexData vd2 = new VertexData(tf2.getText().trim());
                        EdgeData ed = new EdgeData(tf1.getText() + tf2.getText(), Integer.parseInt(tf3.getText()));

                        //se vd1 e vd2 já existem adiciona apenas o edge entre os dois
                        try {
                            gh.checkVertexData(vd1);
                            gh.checkVertexData(vd2);

                            ed1 = gh.addEdgeData(vd1, vd2, ed);
                            
                            setPrompt();
                        } catch (InvalidVertexException i) {
                            //apenas se vd1 já existir, adiciona o vd2 e liga-os
                            try {
                                gh.checkVertexData(vd1);
                                v2 = gh.addVertexData(vd2);

                                ed1 = gh.addEdgeData(vd1, vd2, ed);

                                list.add(v2);
                                
                                setPrompt();
                            } catch (InvalidVertexException ii) {
                                //apenas se vd2 já existir, adiciona o vd1 e liga-os
                                try {
                                    gh.checkVertexData(vd2);
                                    v1 = gh.addVertexData(vd1);

                                    ed1 = gh.addEdgeData(vd1, vd2, ed);

                                    list.add(v1);
                                    
                                    setPrompt();
                                } catch (InvalidVertexException iii) {
                                    //se nenhum deles existir, adiciona-os vertices e liga-os
                                    gh.addVertexData(vd1);
                                    gh.addVertexData(vd2);

                                    ed1 = gh.addEdgeData(vd1, vd2, ed);
                                    
                                    setPrompt();
                                }
                            }
                        }
                    } catch (NumberFormatException ne) {
                        showAlert("Some Information is Wrong!");
                    }
                } else {
                    //tentar adicionar vertices com o memso nome
                    showAlert("Duplicate Information!");
                }
            } else {
                showAlert("Some Information Missing!");
            }
        });

        //ação de apagar vertice
        deleteVertexButton.setOnAction(e -> {
            //vai buscar o veetice selecionado na view list
            Vertex<VertexData> vertex = viewList.getSelectionModel().getSelectedItem();

            try {
                if (vertex != null) {
                    //remove, volta a selecionar o primeiro da lista por default e atualiza o grau
                    gh.removeVertexData(vertex.element());
                    viewList.getSelectionModel().selectFirst();
                    vertex = viewList.getSelectionModel().getSelectedItem();
                    vertexDegree.setText("Vertex Degree: " + gp.vertexDegree(vertex));
                }
            } catch (InvalidVertexException n) {

            }
        });
        
        //ação de apagar edge
        deleteEdgeButton.setOnAction(e -> {
            if (tf4.getText().isEmpty()) {
                showAlert("Some Information Missing!");
            } else {
                try {
                    Edge<EdgeData, VertexData> ed = gh.getEdge(tf4.getText());
                    gh.removeEdgeData(ed.element());
                    tf4.clear();
                } catch (NullPointerException ie) {
                    showAlert("Edge Doesnt Exist!");
                }
            }
        });

        //botao do memento
        backButton.setOnAction(e -> {
            //restaura o graph e atualiza a informação necessária
            gh.restoreGraph();
            viewList.getSelectionModel().selectFirst();
            Vertex<VertexData> vertex = viewList.getSelectionModel().getSelectedItem();
            vertexDegree.setText("Vertex Degree: " + gp.vertexDegree(vertex));
        });

        //ação de abrir o chart
        chartInfo.setOnAction(e -> {
            try {
                chartStage.close();
                createChartWindow();
                logger.saveLog("Chart Info Opened.");

            } catch (NullPointerException n) {
                createChartWindow();
                logger.saveLog("Chart Info Opened.");
            }
        });

        //ação de pedir o caminho em dfs ou bfs
        startSearch.setOnAction(e -> {
            if (!start.getText().isEmpty()) {
                try {
                    VertexData vd1 = new VertexData(start.getText().trim());
                    Vertex<VertexData> v1 = gh.checkVertexData(vd1);
                    searchDFS.setText("DFS: " + gh.DFS(v1).toString());
                    searchBFS.setText("BFS: " + gh.BFS(v1).toString());
                    logger.saveLog("Search Executed. Started on " + v1.element().getString() + ".");
                    start.clear();
                } catch (InvalidVertexException i) {
                    start.clear();
                    searchDFS.setText("DFS: ");
                    searchBFS.setText("BFS: ");
                    showAlert("Invalid Vertex");
                }
            } else {
                showAlert("Some Information Missing!");
            }
        });

        /*
        primeira implementação do dijkstra que mostrava logo na pagina principal apenas o caminho e ocusto de startDij a destinyDij
        depois atualizada pela que esta em baixo
        
        startDijkstra.setOnAction(e -> {
            if(!startDij.getText().isEmpty() && !destinyDij.getText().isEmpty()){
                try{
                    List<VertexData> path = new ArrayList<>();
                    
                    VertexData vd1 = new VertexData(startDij.getText().trim());
                    VertexData vd2 = new VertexData(destinyDij.getText().trim());
                    
                    Vertex<VertexData> v1 = gh.checkVertexData(vd1);
                    Vertex<VertexData> v2 = gh.checkVertexData(vd2);
                    
                    double cost = gh.minimumCostPath(v1.element(), v2.element(), path);
                    
                    dijkstra.setText("Dijkstra: " + path.toString());
                    dijkstraCost.setText("Cost: " + cost);
                }catch(InvalidVertexException i){
                    dijkstra.setText("Invalid Vertex");
                    dijkstraCost.setText("Cost: " + 0);
                }catch(NullPointerException n){
                    dijkstraCost.setText("Cost: " + 0);
                }
            }else{
                showAlert("Some Information Missing!");
            }
        });
         */
        
        //dijstra button
        startDijkstra.setOnAction(e -> {
            if (!startDij.getText().isEmpty()) {
                //if stage is already open
                try {
                    dijkstraStage.close();

                    useDijkstra(gh);
                } catch (InvalidVertexException i) {
                    startDij.clear();
                    showAlert("Invalid Vertex!");
                } catch (NullPointerException n) {
                    try{
                        //if its not open
                        useDijkstra(gh);
                    }catch(InvalidVertexException i){
                        startDij.clear();
                        showAlert("Invalid Vertex!");
                    }
                    
                }
            } else {
                showAlert("Some Information Missing!");
            }
        });
    }
    
    //update do padrao MVC atualiza alguns nodes quando são feitas alterações
    @Override
    public void update(Observable o, Object o1){
        MyGraph<VertexData, EdgeData> graph = (MyGraph<VertexData, EdgeData>) o;
        
        //update info on the top
        verticesNumber.setText("Vertices: " + graph.numVertices());
        edgesNumber.setText("Edges: " + graph.numEdges());
        isolatedVertices.setText("Isolated Vertices: " + graph.isolatedVertices());
        
        //update graph in the middle
        graphBox.getChildren().remove(0);
        graphView = new GraphPanel<>(gp, strategy);
        graphView.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        graphBox.getChildren().add(graphView);
        graphView.setTranslateY(50 - graphView.getLayoutBounds().getMinY());
        graphView.setTranslateX(25 - graphView.getLayoutBounds().getMinX());
        plot();
        
        //update list view on the rigth
        listVBox.getChildren().remove(0, 4);
        
        vertices = copyIterable(graph.vertices());
        list = FXCollections.observableArrayList(vertices);
        viewList = new ListView<>(list);
        viewList.setPrefHeight(ROW_HEIGHT);

        viewList.setOnMouseClicked(e -> {
            try {
                graphView.resetColorsToDefault();
                Vertex<VertexData> vertex = viewList.getSelectionModel().getSelectedItem();
                graphView.setVertexColor(vertex, Color.GOLD, Color.BROWN);
                colorEdges(gp.getEdgesOfVertex(vertex));
                vertexDegree.setText("Vertex Degree: " + gp.vertexDegree(vertex));
            } catch (InvalidVertexException i) {

            }
        });

        listVBox.getChildren().addAll(viewList, deleteVertexButton, tf4, deleteEdgeButton);
        viewList.setTranslateY(-10 - viewList.getLayoutBounds().getMinY());
    }
    
    //metodo que mostra um alerta contumizavel no ecra
    private void showAlert(String msg){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("INPUT ERROR");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
    
    //muda a cor de todos os edges num set
    private void colorEdges(HashSet<Edge<EdgeData, VertexData>> edges){
        for(Edge<EdgeData, VertexData> ed : edges){
            graphView.setEdgeColor(ed, Color.CYAN, 0.8);
        }
    }
    
    //metodo para evitar repetição de código no uso do botao startDijkstra
    private void useDijkstra(GraphHandling gh){
        VertexData vd1 = new VertexData(startDij.getText().trim());
        Vertex<VertexData> v1 = gh.checkVertexData(vd1);

        getPaths(gh, v1);

        createDijkstraWindow();
        logger.saveLog("Dijkstra Executed. Started on " + v1.element().getString() + ".");
        startDij.clear();
    }
    
    /**
     * Metodo que adiciona numa lista todos os caminhos de v1 para os restantes
     * usado para a table view e dijkstra
     * @param gh - GraphHandling
     * @param v1 - start
     */
    public void getPaths(GraphHandling gh, Vertex<VertexData> v1) {
        List<VertexData> path = new ArrayList<>();

        listofList = FXCollections.observableArrayList();

        //get the path and cost from the vertex v1 to all others
        for (Vertex<VertexData> vrt : vertices) {
            if (v1.element().getString().equals(vrt.element().getString())) {
                continue;
            }

            int cost = (int) gh.minimumCostPath(v1.element(), vrt.element(), path);

            DijkstraData dd;

            if (path.isEmpty()) {
                dd = new DijkstraData(v1.element(), "IMPOSSIBLE", vrt.element(), cost);
            } else {
                dd = new DijkstraData(v1.element(), path.toString(), vrt.element(), cost);
            }

            listofList.add(dd);

            path = new ArrayList<>();
        }
    }
    
    //faz reset aos textfield depois de serem usados
    private void setPrompt(){
        tf1.clear();
        tf2.clear();
        tf3.clear();
        viewList.getSelectionModel().select(0);
    }
}