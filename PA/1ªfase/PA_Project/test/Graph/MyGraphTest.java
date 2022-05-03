package Graph;
import DiGraph.Vertex;
import DiGraph.MyGraph;
import DiGraph.Edge;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pa_project.Path;
import pa_project.Place;

public class MyGraphTest {
    
    private MyGraph<Place, Path> testGraph;
    
    private Vertex<Place> p1;
    private Vertex<Place> p2;
    private Vertex<Place> p3;
    
    private Edge<Path, Place> e1;
    private Edge<Path, Place> e2;
    private Edge<Path, Place> e3;
    
    public MyGraphTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        testGraph = new MyGraph<>();
        p1 = testGraph.insertVertex(new Place(1, "P1"));
        p2 = testGraph.insertVertex(new Place(2, "P2"));
        p3 = testGraph.insertVertex(new Place(3, "P3"));
        
        
        
        e1 = testGraph.insertEdge(p1, p2, new Path(1, "caminho", "E1", 1, 2, true, 3, 0));
        e2 = testGraph.insertEdge(p2, p3, new Path(2, "caminho", "E2", 2, 3, true, 2, 0));
        e3 = testGraph.insertEdge(p1, p3, new Path(3, "ponte", "E3", 1, 3, true, 1, 0));
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of numVertices method, of class MyGraph.
     */
    @Test
    public void testNumVertices() {
        System.out.println("numVertices");
        int expResult = 3;
        int result = testGraph.numVertices();
        assertEquals(expResult, result);
    }

    /**
     * Test of numEdges method, of class MyGraph.
     */
    @Test
    public void testNumEdges() {
        System.out.println("numEdges");
        int expResult = 3;
        int result = testGraph.numEdges();
        assertEquals(expResult, result);
    }

    /**
     * Test of vertices method, of class MyGraph.
     */
    @Test
    public void testVertices() {
        System.out.println("vertices");
        
        HashSet<Vertex<Place>> expResult = new HashSet<>();
        expResult.add(p1);
        expResult.add(p2);
        expResult.add(p3);
        
        Collection<Vertex<Place>> result = (Collection<Vertex<Place>>) testGraph.vertices();
        
        assertEquals(true, result.containsAll(expResult));
    }

    /**
     * Test of edges method, of class MyGraph.
     */
    @Test
    public void testEdges() {
        System.out.println("edges");
        
        List<Edge<Path, Place>> expResult = new ArrayList<>();
        expResult.add(e1);
        expResult.add(e2);
        expResult.add(e3);
        
        Collection<Edge<Path, Place>> result = (Collection<Edge<Path, Place>>) testGraph.edges();
        
        assertEquals(true, result.containsAll(expResult));
    }

    /**
     * Test of incidentEdges method, of class MyGraph.
     */
    @Test
    public void testIncidentEdges() {
        System.out.println("incidentEdges");
        
        List<Edge<Path, Place>> expResult = new ArrayList<>();
        expResult.add(e1);
        expResult.add(e3);
        
        Iterable<Edge<Path, Place>> result = testGraph.incidentEdges(p1);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of opposite method, of class MyGraph.
     */
    @Test
    public void testOpposite() {
        System.out.println("opposite");
        
        Vertex expResult = p3;
        
        Vertex result = testGraph.opposite(p2, e2);
        assertEquals(expResult, result);
    }

    /**
     * Test of areAdjacent method, of class MyGraph.
     */
    @Test
    public void testAreAdjacent() {
        System.out.println("areAdjacent");
        
        Vertex<Place> p4 = testGraph.insertVertex(new Place(4, "P4"));
        
        boolean expResultT = true;
        boolean expResultF = false;
        
        boolean resultTrue = testGraph.areAdjacent(p2, p3);
        boolean resultFalse = testGraph.areAdjacent(p2, p4);
        
        assertEquals(resultTrue, expResultT);
        assertEquals(resultFalse, expResultF);
    }

    /**
     * Test of insertVertex method, of class MyGraph.
     */
    @Test
    public void testInsertVertex() {
        System.out.println("insertVertex");
        
        Place testP = new Place(100, "test");

        Vertex result = testGraph.insertVertex(testP);
        Vertex expResult = null;
        
        Iterator<Vertex<Place>> it = testGraph.vertices().iterator();
        while(it.hasNext()){
            Vertex<Place> testV = it.next();
            if(testV.element() == testP){
                expResult = testV;
            }
        }
        
        assertEquals(expResult, result);
    }

    /**
     * Test of insertEdge method, of class MyGraph.
     */
    @Test
    public void testInsertEdge() {
        System.out.println("insertEdge");
        
        Vertex<Place> p5 = testGraph.insertVertex(new Place(5, "P5"));
        Vertex<Place> p6 = testGraph.insertVertex(new Place(6, "P6"));
        
        Place elem1 = p5.element();
        Place elem2 = p6.element();
        Path o = new Path(4, "ponte", "E4", 5, 6, true, 1, 2);

        Edge result = testGraph.insertEdge(elem1, elem2, o);
        Edge expResult = null;
        
        Iterator<Edge<Path,Place>> it = testGraph.edges().iterator();
        while(it.hasNext()){
            Edge<Path,Place> testE = it.next();
            if(testE.element() == o){
                expResult = testE;
            }
        }
        
        assertEquals(expResult, result);
    }

    /**
     * Test of removeVertex method, of class MyGraph.
     */
    @Test
    public void testRemoveVertex() {
        System.out.println("removeVertex");
        
        Vertex<Place> p7 = testGraph.insertVertex(new Place(7, "P7"));
        
        Place expResult = p7.element();
        
        Place result = testGraph.removeVertex(p7);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of removeEdge method, of class MyGraph.
     */
    @Test
    public void testRemoveEdge() {
        System.out.println("removeEdge");
        
        Object expResult = e1.element();
        Object result = testGraph.removeEdge(e1);
        
        assertEquals(expResult, result);
    }
}
