package pa_project;

import DiGraph.Vertex;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * @author Gabriel Ambrósio 160221013 e Hugo Ferreira 160221039
 */
public class main{
    
    public static void main(String[] args) throws FileNotFoundException{
        GestorPercurso gp = new GestorPercurso();
        
        gp.readGraph("mapa1.txt");
        
        ArrayList<Vertex<Place>> points = new ArrayList<>();
        
        points.add(gp.getVertex(7));//mapa1 - Quinta Pedagogica | mapa2 - Parque    | mapa3 - Pavilhão
        points.add(gp.getVertex(3));//mapa1 - Pomar             | mapa2 - Areia     | mapa3 - Refeitorio
        points.add(gp.getVertex(5));//mapa1 - Moinho            | mapa2 - Diversões | mapa3 - Floresta
        
        gp.startRoute(points, Criteria.COST);
    }
    
}