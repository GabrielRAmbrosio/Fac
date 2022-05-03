/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.ipoo.pkg1718;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gabriel
 */
public class FireTest {
    
    public FireTest() {
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
     * Test of ignite method, of class Fire.
     */
    @Test
    public void testIgnite() {
        System.out.println("ignite");
        Weather w = new Weather(0, 0, 0);
        Fire f = new Fire(10, 2, 3, w);
        f.ignite();
    }

    /**
     * Test of extinguish method, of class Fire.
     */
    @Test
    public void testExtinguish() {
        System.out.println("extinguish");
        System.out.println("ignite");
        Weather w = new Weather(0, 0, 0);
        Fire f = new Fire(1, 2, 3, w);
        Simulator s = new Simulator();
        Barrack b = new Barrack(0, 0, "", 100, 800, s);
        Vehicle vehicle = new Vehicle(0, b, s);
        f.addVehicle(vehicle);
        f.extinguish();
        assertEquals(0, f.getBurnedArea(), 0);
    }


    /**
     * Test of getBurnedArea method, of class Fire.
     */
    @Test
    public void testGetBurnedArea() {
        System.out.println("getBurnedArea");
        Weather w = new Weather(0, 0, 0);
        Fire f = new Fire(1, 2, 3, w);
        assertEquals(1 , f.getBurnedArea(), 0);
    }
}
