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
public class FiremanTest {

    public FiremanTest() {

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
    public void tearDown(){
    }

    /**
     * Test of driveVehicle method, of class Fireman.
     */
    @Test
    public void testDriveVehicle() {
        System.out.println("driveVehicle");
        Simulator s = new Simulator();
        Barrack b = new Barrack(0, 0, "", 100, 800, s);
        Vehicle vehicle = new Vehicle(1000, b, s);
        Fireman instance = new Fireman(b, true);
        Fireman instance1 = new Fireman(b, true);
        Fireman instance2 = new Fireman(b, true);
        Position position = new Position(10, 10);
        vehicle.addDriver(instance);
        vehicle.addFireman(instance1);
        vehicle.addFireman(instance2);
        instance.driveVehicle(vehicle, position);
        assertEquals(position, vehicle.getPosition());
    }

    /**
     * Test of useEnergy method, of class Fireman.
     */
    @Test
    public void testUseEnergy() {
        System.out.println("useEnergy");
        Simulator s = new Simulator();
        Barrack b = new Barrack(0, 0, "", 100, 800, s);
        double hours = 1;
        Fireman instance = new Fireman(b, true);
        assertEquals(true, instance.useEnergy(hours));
    }

    /**
     * Test of rest method, of class Fireman.
     */
    @Test
    public void testRest() {
        System.out.println("rest");
        Simulator s = new Simulator();
        Barrack b = new Barrack(0, 0, "", 100, 800, s);
        Fireman instance = new Fireman(b, true);
        instance.useEnergy(1);
        instance.rest();
        assertEquals(100, instance.getEnergy(),0);
    }

    /**
     * Test of getId method, of class Fireman.
     */
    @Test
    public void testGetId(){
        System.out.println("getId");
        Simulator s = new Simulator();
        Barrack b = new Barrack(0, 0, "", 100, 800, s);
        Fireman instance3 = new Fireman(b, true);
        assertEquals(3, instance3.getId());
    }

    /**
     * Test of getBarrack method, of class Fireman.
     */
    @Test
    public void testGetBarrack() {
        Simulator s = new Simulator();
        Barrack b = new Barrack(0, 0, "", 100, 800, s);
        Fireman instance = new Fireman(b, true);
        assertEquals(b, instance.getBarrack());

    }

    /**
     * Test of getEnergy method, of class Fireman.
     */
    @Test
    public void testGetEnergy() {
        System.out.println("getEnergy");
        Simulator s = new Simulator();
        Barrack b = new Barrack(0, 0, "", 100, 800, s);
        Fireman instance = new Fireman(b, true);
        assertEquals(100, instance.getEnergy(), 0);

    }

    /**
     * Test of isdrivingAllowed method, of class Fireman.
     */
    @Test
    public void testIsdrivingAllowed() {
        System.out.println("isdrivingAllowed");
        Simulator s = new Simulator();
        Barrack b = new Barrack(0, 0, "", 100, 800, s);
        Fireman instance = new Fireman(b, true);
        assertEquals(true, instance.isdrivingAllowed());
    }

    /**
     * Test of isInVehicle method, of class Fireman.
     */
    @Test
    public void testIsInVehicle() {
        System.out.println("isInVehicle");
        Simulator s = new Simulator();
        Barrack b = new Barrack(0, 0, "", 100, 800, s);
        Vehicle v = new Vehicle(1, b, s);
        Fireman instance = new Fireman(b, true);
        v.addDriver(instance);
        assertEquals(true, instance.isInVehicle());
    }
    
}
