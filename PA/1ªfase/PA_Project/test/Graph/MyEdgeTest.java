package Graph;

import DiGraph.Vertex;
import DiGraph.MyVertex;
import DiGraph.MyEdge;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pa_project.Place;

public class MyEdgeTest {
    
    private Vertex<Place> v1;
    private Vertex<Place> v2;
    
    public MyEdgeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        v1 = new MyVertex("");
        v2 = new MyVertex("");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of element method, of class MyEdge.
     */
    @Test
    public void testElement() {
        System.out.println("element");
        
        MyEdge instance = new MyEdge("test", v1, v2);
        Object expResult = "test";
        Object result = instance.element();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of vertices method, of class MyEdge.
     */
    @Test
    public void testVertices() {
        System.out.println("vertices");
        
        MyEdge instance = new MyEdge("test", v1, v2);
        Vertex[] expResult = {v1,v2};
        Vertex[] result = instance.vertices();
        
        assertArrayEquals(expResult, result);
    }
    
}
