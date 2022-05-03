/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.ipoo.pkg1718;

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
public class FillingStationTest {
    
    public FillingStationTest() {
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
     * Test of addParked method, of class FillingStation.
     */
    @Test
    public void testAddParked() {
        System.out.println("addParked");
        Simulator s = new Simulator();
        Barrack b = new Barrack(0, 0, "", 100, 800, s);
        Vehicle vehicle = new Vehicle(0, b, s);
        FillingStation fs = new FillingStation("", 1, -1, true);
        assertEquals(true, fs.addParked(vehicle));

    }

    /**
     * Test of addFilling method, of class FillingStation.
     */
    @Test
    public void testAddFilling() {
        System.out.println("addFilling");
        Simulator s = new Simulator();
        Barrack b = new Barrack(0, 0, "", 100, 800, s);
        Vehicle vehicle = new Vehicle(0, b, s);
        FillingStation fs = new FillingStation("", 1, -1, true);
        assertEquals(true, fs.addFilling(vehicle));
    }

    /**
     * Test of removeFilling method, of class FillingStation.
     */
    @Test
    public void testRemoveFilling() {
        System.out.println("removeFilling");
        Simulator s = new Simulator();
        Barrack b = new Barrack(0, 0, "", 100, 800, s);
        Vehicle vehicle = new Vehicle(0, b, s);
        FillingStation fs = new FillingStation("", 1, -1, true);
        fs.addFilling(vehicle);
        assertEquals(true, fs.removeFilling(vehicle));
    }

    /**
     * Test of removeParked method, of class FillingStation.
     */
    @Test
    public void testRemoveParked() {
        System.out.println("removeParked");
        Simulator s = new Simulator();
        Barrack b = new Barrack(0, 0, "", 100, 800, s);
        Vehicle vehicle = new Vehicle(0, b, s);
        FillingStation fs = new FillingStation("", 1, -1, true);
        fs.addParked(vehicle);
        assertEquals(vehicle, fs.removeParked());
    }

    /**
     * Test of fill method, of class FillingStation.
     */
    @Test
    public void testFill() {
        System.out.println("fill");
        Simulator s = new Simulator();
        Barrack b = new Barrack(0, 0, "", 100, 800, s);
        Vehicle vehicle = new Vehicle(0, b, s);
        FillingStation fs = new FillingStation("", 1, -1, true);
        fs.addFilling(vehicle);
        fs.fill();
        assertEquals(500, vehicle.getCurrentCapacity());
    }
}
