package pa_project;

import DiGraph.Edge;
import DiGraph.MyGraph;
import DiGraph.Vertex;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GestorPercursoTest {
    
    private final String filename = "mapa1.txt";
    
    
    public GestorPercursoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws FileNotFoundException {
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of readGraph method, of class GestorPercurso.
     * @throws java.lang.Exception
     */
    @Test
    public void testReadGraph() throws Exception {
        System.out.println("readGraph");
        GestorPercurso instance = new GestorPercurso();
        int expResult = 8;
        MyGraph<Place, Path> result = instance.readGraph(filename);
        assertEquals(expResult, result.numVertices());
    }

    /**
     * Test of minimumCostPath method, of class GestorPercurso.
     * @throws java.io.FileNotFoundException
     */
    @Test
    public void testMinimumCostPath() throws FileNotFoundException {
        System.out.println("minimumCostPath");
        
        GestorPercurso instance = new GestorPercurso();
        MyGraph<Place, Path> graph = instance.readGraph(filename);
        Criteria criteria = Criteria.COST;
        
        Iterator<Vertex<Place>> aa = graph.vertices().iterator();
        Vertex<Place> orig = null;
        Vertex<Place> dst = null;
        
        while(aa.hasNext()){
            Vertex<Place> c = aa.next();
            if(c.element().getId() == 2){
                orig = c;
            }
            if(c.element().getId() == 1){
                dst = c;
            }
        }
        
        List<Vertex<Place>> places = new ArrayList<>();
        
        double expResult = 17.0;
        double result = instance.minimumCostPath(criteria, orig, dst, places, graph);
        
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getCriteria method, of class GestorPercurso.
     * @throws java.io.FileNotFoundException
     */
    @Test

    public void testGetCriteria() throws FileNotFoundException {
        System.out.println("getCriteria");
        
        GestorPercurso instance = new GestorPercurso();
        MyGraph<Place, Path> graph = instance.readGraph(filename);
        
        Vertex<Place> p1 = graph.insertVertex(new Place(1, "1"));
        Vertex<Place> p2 = graph.insertVertex(new Place(2, "2"));
        
        Edge<Path, Place> e1 = graph.insertEdge(p1, p2, new Path(1, "caminho", "", 1, 2, true, 10, 10));
        
        Criteria e = Criteria.COST;
        
        double expResult = e1.element().getCost();
        double result = instance.getCriteria(e, e1);
        
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getVertex method, of class GestorPercurso.
     * @throws java.io.FileNotFoundException
     */
    @Test
    public void testGetVertex() throws FileNotFoundException {
        System.out.println("getVertex");
        
        int id = 100;
        
        GestorPercurso instance = new GestorPercurso();
        MyGraph<Place, Path> graph = instance.readGraph(filename);
        Vertex<Place> expResult = graph.insertVertex(new Place(100, "getVertexTest"));
        
        Vertex<Place> result = instance.getVertex(id);
        
        assertEquals(expResult, result);
        
    }
    
}
