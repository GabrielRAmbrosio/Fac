package Graph;

import DiGraph.MyVertex;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MyVertexTest {
    
    public MyVertexTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of element method, of class MyVertex.
     */
    @Test
    public void testElement() {
        System.out.println("element");
        
        MyVertex instance = new MyVertex("test");
        
        Object expResult = "test";
        Object result = instance.element();
        
        assertEquals(expResult, result);
    }
    
}
