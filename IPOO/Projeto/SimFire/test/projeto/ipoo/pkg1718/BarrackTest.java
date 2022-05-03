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
public class BarrackTest {
    
    public BarrackTest() {
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
     * Test of addVehicle method, of class Barrack.
     */
    @Test
    public void testAddVehicle() {
        System.out.println("addVehicle");
        Simulator s = new Simulator();
        Barrack b = new Barrack(0, 0, "", 100, 800, s);
        Vehicle vehicle = new Vehicle(0, b, s);
        assertEquals(true, b.addVehicle(vehicle));
    }

    /**
     * Test of removeVehicle method, of class Barrack.
     */
    @Test
    public void testRemoveVehicle() {
        System.out.println("removeVehicle");
        Simulator s = new Simulator();
        Barrack b = new Barrack(0, 0, "", 100, 800, s);
        Vehicle vehicle = new Vehicle(0, b, s);
        b.addVehicle(vehicle);
        assertEquals(true, b.removeVehicle(vehicle));
    }


}
