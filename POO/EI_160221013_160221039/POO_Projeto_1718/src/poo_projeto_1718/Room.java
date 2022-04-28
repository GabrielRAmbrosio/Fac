package poo_projeto_1718;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class Room implements Serializable{
    private final Rooms roomType;
    private static int n = 1;
    private int nn;
    private final List<TemperatureSensor> temperatureSensors;
    private final List<LightSensor> lightSensors;
    private final List<MotionSensor> motionSensors;
    private final List<DoorSensor> doorSensors;
    
    private final List<AirConditioner> acs;
    private final List<Plug> plugs;
    private final List<Camera> cameras;
    private final List<Lamp> lamps;
    
    private final Siren siren;

    /**
     * Construtor de uma divisao inicializa todas listas de equipamentos
     * @param room
     */
    public Room(Rooms room){
        this.roomType = room;
        
        temperatureSensors = new ArrayList<>();
        lightSensors = new ArrayList<>();
        motionSensors = new ArrayList<>();
        doorSensors = new ArrayList<>();
        
        acs = new ArrayList<>();
        plugs = new ArrayList<>();
        cameras = new ArrayList<>();
        lamps = new ArrayList<>();
        
        siren = new Siren();
        nn = n;
        n++;
    }
    
    public void addTemperatureSensor(TemperatureSensor eq){
        temperatureSensors.add(eq);
    }
    public void removeTemperatureSensor(TemperatureSensor eq){
        temperatureSensors.remove(eq);
    }
    
    public void addLightSensor(LightSensor eq){
        lightSensors.add(eq);
    }
    public void removeLightSensor(LightSensor eq){
        lightSensors.remove(eq);
    }
    
    public void addMotionSensor(MotionSensor eq){
        motionSensors.add(eq);
    }
    public void removeMotionSensor(MotionSensor eq){
        motionSensors.remove(eq);
    }
    
    public void addDoorSensor(DoorSensor eq){
        doorSensors.add(eq);
    }
    public void removeDoorSensor(DoorSensor eq){
        doorSensors.remove(eq);
    }
    
    public void addAirConditioner(AirConditioner eq){
        acs.add(eq);
    }
    public void removeAirConditioner(AirConditioner eq){
        acs.remove(eq);
    }
    
    public void addPlug(Plug eq){
        plugs.add(eq);
    }
    public void removePlug(Plug eq){
        plugs.remove(eq);
    }
    
    public void addCamera(Camera eq){
        cameras.add(eq);
    }
    public void removeCamera(Camera eq){
        cameras.remove(eq);
    }

    public void addLamp(Lamp eq){
        lamps.add(eq);
    }
    public void removeLamp(Lamp eq){
        lamps.remove(eq);
    }
    
    public List<TemperatureSensor> getTempSensors(){
        return temperatureSensors;
    }

    public List<LightSensor> getLightSensors(){
        return lightSensors;
    }

    public List<MotionSensor> getMotionSensors(){
        return motionSensors;
    }

    public List<DoorSensor> getDoorSensors(){
        return doorSensors;
    }

    public List<AirConditioner> getAcs(){
        return acs;
    }

    public List<Plug> getPlugs(){
        return plugs;
    }

    public List<Camera> getCameras(){
        return cameras;
    }

    public List<Lamp> getLamps(){
        return lamps;
    }

    public Siren getSiren(){
        return siren;
    }
    
    public Rooms getRoomType(){
        return roomType;
    }
    
    private void printEqupments(){
        String str = "Equipments:\n";
        str = temperatureSensors.stream().map((ts) -> ts.getFullName() + " ").reduce(str, String::concat);
        str += "\n";
        str = lightSensors.stream().map((ls) -> ls.getFullName() + " ").reduce(str, String::concat);
        str += "\n";
        str = motionSensors.stream().map((ms) -> ms.getFullName() + " ").reduce(str, String::concat);
        str += "\n";
        str = doorSensors.stream().map((ds) -> ds.getFullName() + " ").reduce(str, String::concat);
        str += "\n";
        str = acs.stream().map((ac) -> ac.getFullName() + " ").reduce(str, String::concat);
        str += "\n";
        str = plugs.stream().map((p) -> p.getFullName() + " ").reduce(str, String::concat);
        str += "\n";
        str = cameras.stream().map((c) -> c.getFullName() + " ").reduce(str, String::concat);
        str += "\n";
        str = lamps.stream().map((l) -> l.getFullName() + " ").reduce(str, String::concat);
        str += "\n";
        System.out.println(str);
    }
    
    @Override
    public String toString(){
        String str = "Room: " + roomType.toString() + " " + nn;
        str += "\n Temp Sensors: " + temperatureSensors.size() + "\n Light Sensors: " + lightSensors.size() +
                "\n Motion Sensors: " + motionSensors.size()+ "\n Door Sensors: " + doorSensors.size()+ "\n AC's: " + acs.size() +
                "\n Plugs: " + plugs.size() + "\n Cameras: " + cameras.size() + "\n Lamps: " + 
                lamps.size() + "\n";
        return str;
    }
}