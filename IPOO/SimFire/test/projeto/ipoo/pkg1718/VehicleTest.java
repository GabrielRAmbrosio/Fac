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
public class VehicleTest {

    public VehicleTest() {
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
     * Test of move method, of class Vehicle.
     */
    @Test
    public void testMove() {
        System.out.println("move");
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
        vehicle.move(position);
        assertEquals(position, vehicle.getPosition());
    }

    /**
     * Test of moveToFire method, of class Vehicle.
     */
    @Test
    public void testMoveToFire(){
        System.out.println("move");
        Simulator s = new Simulator();
        Barrack b = new Barrack(3, 3, "", 100, 800, s);
        Weather w = new Weather(0, 0, 0);
        Vehicle vehicle = new Vehicle(1000, b, s);
        Fire f = new Fire(100, 0, 0, w);
        Fireman instance = new Fireman(b, true);
        Fireman instance1 = new Fireman(b, true);
        Fireman instance2 = new Fireman(b, true);
        vehicle.addDriver(instance);
        vehicle.addFireman(instance1);
        vehicle.addFireman(instance2);
        vehicle.moveToFire(f);
        assertEquals(f.getPosition(), vehicle.getPosition());
    }

    /**
     * Test of fill method, of class Vehicle.
     */
    @Test
    public void testFill() {
        System.out.println("fill");
        Simulator s = new Simulator();
        Barrack b = new Barrack(3, 3, "", 100, 800, s);
        Vehicle vehicle = new Vehicle(1000, b, s);
        vehicle.fill(10);
        assertEquals(10,vehicle.getCurrentCapacity());
    }

    /**
     * Test of extinguish method, of class Vehicle.
     */
    @Test
    public void testExtinguish() {
        System.out.println("extinguish");
        Simulator s = new Simulator();
        Barrack b = new Barrack(3, 3, "", 100, 800, s);
        Vehicle vehicle = new Vehicle(1000, b, s);
        vehicle.fill(2000);
        vehicle.extinguish();
        assertEquals(1500, vehicle.getCurrentCapacity());
    }

    /**
     * Test of addFireman method, of class Vehicle.
     */
    @Test
    public void testAddFireman() {
        System.out.println("addFireman");
        Simulator s = new Simulator();
        Barrack b = new Barrack(3, 3, "", 100, 800, s);
        Vehicle vehicle = new Vehicle(1000, b, s);
        Fireman f = new Fireman(b, true);
        vehicle.addFireman(f);
        assertEquals(1, vehicle.getFiremen().size());
    }

    /**
     * Test of removeFireman method, of class Vehicle.
     */
    @Test
    public void testRemoveFireman() {
        System.out.println("removeFireman");
        Simulator s = new Simulator();
        Barrack b = new Barrack(3, 3, "", 100, 800, s);
        Vehicle vehicle = new Vehicle(1000, b, s);
        Fireman f = new Fireman(b, true);
        vehicle.addFireman(f);
        vehicle.removeFireman(f);
        assertEquals(0, vehicle.getFiremen().size());
    }

    /**
     * Test of removeFiremanByPosition method, of class Vehicle.
     */
    @Test
    public void testRemoveFiremanByPosition() {
        System.out.println("removeFiremanByPosition");
        Simulator s = new Simulator();
        Barrack b = new Barrack(3, 3, "", 100, 800, s);
        Vehicle vehicle = new Vehicle(1000, b, s);
        Fireman f = new Fireman(b, true);
        vehicle.addFireman(f);
        vehicle.removeFiremanByPosition(0);
        assertEquals(0, vehicle.getFiremen().size());
    }

    /**
     * Test of compareDriver method, of class Vehicle.
     */
    @Test
    public void testCompareDriver() {
        System.out.println("compareDriver");
        Simulator s = new Simulator();
        Barrack b = new Barrack(3, 3, "", 100, 800, s);
        Vehicle vehicle = new Vehicle(1000, b, s);
        Fireman f = new Fireman(b, true);
        vehicle.addDriver(f);
        assertEquals(f, vehicle.getDriver());
    }

    /**
     * Test of getCurrentCapacity method, of class Vehicle.
     */
    @Test
    public void testGetCurrentCapacity() {
        System.out.println("getCurrentCapacity");
        Simulator s = new Simulator();
        Barrack b = new Barrack(3, 3, "", 100, 800, s);
        Vehicle vehicle = new Vehicle(1000, b, s);
        
        assertEquals(0, vehicle.getCurrentCapacity());
    }

    /**
     * Test of getPosition method, of class Vehicle.
     */
    @Test
    public void testGetPosition() {
        System.out.println("getPosition");
        Simulator s = new Simulator();
        Barrack b = new Barrack(3, 3, "", 100, 800, s);
        Vehicle vehicle = new Vehicle(1000, b, s);
        assertEquals(b.getPosition(), vehicle.getPosition());
    }

    /**
     * Test of getCurrentFiremen method, of class Vehicle.
     */
    @Test
    public void testGetCurrentFiremen() {
        System.out.println("getCurrentFiremen");
        Simulator s = new Simulator();
        Barrack b = new Barrack(3, 3, "", 100, 800, s);
        Vehicle vehicle = new Vehicle(1000, b, s);
        Fireman f = new Fireman(b, true);
        Fireman f2 = new Fireman(b, true);
        vehicle.addFireman(f);
        vehicle.addFireman(f2);
        assertEquals(2, vehicle.getCurrentFiremen());
    }
}
