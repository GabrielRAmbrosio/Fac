package pa_project;

import DiGraph.MyGraph;
import DiGraph.Edge;
import DiGraph.Vertex;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author Gabriel Ambrósio 160221013 e Hugo Ferreira 160221039
 */
public class GestorPercurso{
    
    private MyGraph<Place, Path> fileGraph;
    
    /**
     *Construtor da classe GestorPercurso.
     * Inicializa a instancia fileGraph.
     */
    public GestorPercurso(){
        fileGraph = new MyGraph<>();
    }
    
    /**
     *Le um ficheiro na pasta Input e cria um graph com os dados do mesmo.
     * Guarda-o na instancia fleGraph
     * @param filename - nome do ficheiro a usar
     * @return fileGraph - graph gerado pelo ficheiro
     * @throws FileNotFoundException - se o ficheiro não for encontrado
     */
    public MyGraph<Place, Path> readGraph(String filename) throws FileNotFoundException{
        
        String path = System.getProperty("user.dir") + "\\src\\Input\\" + filename;
        
        File ficheiro = new File(path);
        
        try (Scanner read = new Scanner(ficheiro)) {//faz scan ao ficheiro
            fileGraph = new MyGraph<>();
            int numberOfPlaces, numberOfPaths;
            read.useDelimiter(",|\\n");//usa a virgula (,) e um enter (\n) como delimitador, porque sao usados no ficheiro mapa1 disponibilizado no moodle
            
            //argumentos a ler do ficheiro
            int placeId, pathId;
            String type, placeName, pathName;
            int placeAId, placeBId, cost, distance;
            boolean navigability;
            
            numberOfPlaces =  Integer.parseInt(read.next().trim());//numero de linhas a ler que contem places
            int i = 0;
            while (read.hasNext() && i < numberOfPlaces) {
                placeId = Integer.parseInt(read.next().trim());
                placeName = read.next();
                
                //CRIAR PLACE E ADICIONAR AO GRAPH
                fileGraph.insertVertex(new Place(placeId, placeName));
                
                i++;
            }
            
            numberOfPaths = Integer.parseInt(read.next().trim());//numero de linhas a ler que contem paths
            i = 0;
            while (read.hasNext() && i < numberOfPaths){
                pathId = Integer.parseInt(read.next().trim());
                type = read.next().trim();
                pathName = read.next();
                placeAId = Integer.parseInt(read.next().trim());
                placeBId = Integer.parseInt(read.next().trim());
                navigability = Boolean.valueOf(read.next().trim());
                cost = Integer.parseInt(read.next().trim());
                distance = Integer.parseInt(read.next().trim());
                
                Iterable<Vertex<Place>> vertices = fileGraph.vertices();
                Vertex<Place> verticeA = null;
                Vertex<Place> verticeB = null;
                Iterator<Vertex<Place>> iterator = vertices.iterator();
                
                while(iterator.hasNext()){//encontrar os vertices já adicionados para ligar o edge
                    Vertex<Place> aux = iterator.next();
                    if(aux.element().getId() == placeAId){
                        verticeA = aux;
                    }
                    if(aux.element().getId() == placeBId){
                        verticeB = aux;
                    }
                }
                
                //CRIAR PATHS E ADICIONAR AO GRAPH
                fileGraph.insertEdge(verticeA, verticeB, new Path(pathId, type, pathName, placeAId, placeBId, navigability, cost, distance));
                
                i++;
            }
        }
        return fileGraph;
    }
    
    private Vertex<Place> minCost(Set<Vertex<Place>> unvisited, Map<Vertex<Place>, Double> distances){
        double min = Double.MAX_VALUE;
        Vertex<Place> minCostVertex = null;
        
        for(Vertex<Place> vertex : unvisited){
            if(distances.get(vertex) <= min){
                minCostVertex = vertex;
                min = distances.get(vertex);
            }
        }
        
        return minCostVertex;
    }
    
    private void dijkstra(Criteria criteria, Vertex<Place> orig, Map<Vertex<Place>, Double> distances, Map<Vertex<Place>, Vertex<Place>> parents, MyGraph<Place, Path> graph){

            Set<Vertex<Place>> unvisited = new HashSet<>();
            
            for(Vertex<Place> vertex : graph.vertices()){
                unvisited.add(vertex);
                distances.put(vertex, Double.MAX_VALUE);
                parents.put(vertex, null);
            }
            
            distances.put(orig, 0.0);
            while(!unvisited.isEmpty()){
                Vertex<Place> lowCostVert = minCost(unvisited, distances);
                unvisited.remove(lowCostVert);
                for(Edge<Path, Place> edge : graph.incidentEdges(lowCostVert)) {
//                    if(edge.element().getType().equals("ponte") && edge.vertices()[1] == lowCostVert){//se for uma ponte no sentido oposto
//                        continue;//nao adiciona
//                    }
                    Vertex<Place> opposite = graph.opposite(lowCostVert, edge);
                    if(unvisited.contains(opposite)){
                        double dist = getCriteria(criteria, edge) + distances.get(lowCostVert);
                        if(distances.get(opposite) > dist) {
                            distances.put(opposite, dist);
                            parents.put(opposite, lowCostVert);
                        }
                    }
                }
            }
        }
   
    
    /**
     *Calcula o custo minimo dos pontos orig a dst.
     * @param criteria - criterio a usar (cost/distance)
     * @param orig - ponto de origem
     * @param dst - destino
     * @param places - lista de places por onde passar
     * @param graph - graph a usar
     * @return cost - custo da viagem de orig a dst
     */
    public double minimumCostPath(Criteria criteria, Vertex<Place> orig, Vertex<Place> dst, List<Vertex<Place>> places, MyGraph<Place, Path> graph) {
            HashMap<Vertex<Place>, Vertex<Place>> parents = new HashMap<>();
            HashMap<Vertex<Place>, Double> distances = new HashMap<>();
            places.clear();
            
            dijkstra(criteria,orig, distances, parents, graph);
            
            double cost = distances.get(dst);
            Vertex<Place> vertex = dst;
            do{
                places.add(0,vertex);
                vertex = parents.get(vertex);
            }while(vertex != orig);
            return cost;
    }
    
    /**
     *Recebe um array de pontos de interesse e cria o percurso mais curto ou mais barato dependendo do criterio.
     * @param points - pontos de interesse
     * @param criteria - criterio a usar
     */
    public void startRoute(ArrayList<Vertex<Place>> points, Criteria criteria){
        
        List<Vertex<Place>> fullRoute = new ArrayList<>();//rota completa começando na entrada (vertex com id == 1) e acabando na entrada, passando pelos pontos de interesse (points)
        
        ArrayList<Vertex<Place>> aux = new ArrayList<>();//array auxiliar para percorrer os pontos de interesse
        points.forEach((p) ->{
            aux.add(p);
        });
        
        double fullDist = 0;//distancia total a percorrer
        
        Vertex<Place> beggining = null;//entrada (vertex com id == 1)
        for(Vertex<Place> auxV : fileGraph.vertices()){
            if(auxV.element().getId() == 1 ){
                beggining = auxV;
            }
        }
        
        Vertex<Place> start = beggining;
        Vertex<Place> current = start;
        Vertex<Place> end = points.get(aux.size()-1);
        
        List<Vertex<Place>> path = new ArrayList<>();
        
        aux.remove(start);
        
        Iterator<Vertex<Place>> it = aux.iterator();
        
        while(it.hasNext()){
            Vertex<Place> next = it.next();
            
            double dist = minimumCostPath(criteria, current, next, path, fileGraph);
            
            path.forEach((p) ->{//remove os pontos de interesse ja visitados do aux
                if(p == start){
                    
                }else{
                    if(aux.contains(p)){
                        aux.remove(p);
                    }
                }
            });
            
            it = aux.iterator();//atualiza o iterador sem os pontos ja visitados
            
            fullRoute.addAll(path);
            fullDist += dist;

            current = next;//continua
        }
        
        
        
        fullDist += minimumCostPath(criteria, current, beggining, path, fileGraph);
        fullRoute.addAll(path);
            
        String str = "";
        String str2 = start.element().getId() + ", ";
        
        //string com os pontos de interesse
        for(int i = 0; i < points.size(); i++){
            if(i == points.size()-1){
                str += "" + points.get(i).element().getId();
                break;
            }
            str += points.get(i).element().getId() + ", ";
        }
        
        //string com o caminho completo
        for(int i = 0; i < fullRoute.size(); i++){
            if(i == fullRoute.size()-1){
                str2 += "" + fullRoute.get(i).element().getId();
                break;
            }
            str2 += "" + fullRoute.get(i).element().getId() + ", ";
        }
        
        if(criteria == Criteria.COST){
            System.out.println("Custo minimo com os pontos de interesse " + str + " é " + fullDist + " € indo por " + str2 + ".\n");
        }else{
            System.out.println("Custo minimo com os pontos de interesse " + str + " é " + fullDist + " Miles indo por " + str2 + ".\n");
        }
    }
    
    /**
     *Retorna o custo de um edge dependendo do criterio.
     * @param e - criterio
     * @param ed - aresta
     * @return
     */
    public double getCriteria(Criteria e, Edge<Path, Place> ed){
        switch(e){
            case COST:
                return ed.element().getCost();
            case DISTANCE:
                return ed.element().getDistance();
        }
        return 0;
    }
    
    /**
     *Retorna um Vertex com um determinado id
     * @param id - id do vertex pretendido
     * @return vertex pretendido
     */
    public Vertex<Place> getVertex(int id){
        ArrayList<Vertex<Place>> points = new ArrayList<>();
        
        Iterator<Vertex<Place>> it = fileGraph.vertices().iterator();
        
        Vertex<Place> vertex = null;
        
        while(it.hasNext()){
            Vertex<Place> aux = it.next();
            if(aux.element().getId() == id){
                vertex = aux;
                break;
            }
        }
        return vertex;
    }
}