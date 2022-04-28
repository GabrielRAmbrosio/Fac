
package projeto.ipoo.pkg1718;

import java.time.*;
import java.util.ArrayList;
import java.util.Random;

public class Fire{
    
    private final Position position;
    private double burnedArea, initialArea, waterUsed = 0;//area a arder
    private ArrayList<Vehicle> vehicles;
    private ArrayList<Fireman> firemen;
    private final LocalDateTime startHour;
    private LocalDateTime endHour;
    private int usedWater;
    private final Weather w;
    private int totalSeats;
    
    public Fire(double burnedArea, double lat, double lon, Weather w){
        Random r = new Random();
        this.w = w;
        initialArea = burnedArea;
        if(checkBurnedArea(burnedArea))this.burnedArea = burnedArea;
        else this.burnedArea = (500000000) * r.nextDouble();
        if(checkPos(lat, lon))position = new Position(lat,lon);
        else position = new Position(-9.5+(r.nextDouble()*(-6.2 + 9.5)),36.8+(r.nextDouble()*(42.2-36.8)));
        vehicles = new ArrayList<>();
        firemen = new ArrayList<>();
        startHour = LocalDateTime.now();
    }
    private boolean checkBurnedArea(double area){
        return (area > 0 && area < 500000000);
    }
    private boolean checkPos(double lat, double lon){
        return lat > -9.5 && lat <-6.2 && lon > 36.8 && lon < 42.2;
    }
    
    /**
     * Uses the weather factors to add area to the fire
    */
    public void ignite(){//tendo em conta o tempo, gera os fatores
        double humFactor = 0,tempFactor = 0,windFactor = 0;
        if(w.getHum() == 45){
            humFactor = 1;
        }else if(w.getHum() < 45){
            humFactor = (-3/45)*w.getHum()+4;
        }else if(w.getHum() <= 65 && w.getHum() > 45){
            humFactor = (-1/20)*w.getHum()+3.25;
        }else if(w.getHum() > 65 && w.getHum() <=100){
            humFactor = (-3.5/35)*w.getHum()+6.5;
        }if(w.getTemp() == 20){
            tempFactor = 0;
        }else{
            tempFactor += (w.getTemp() - 20) * 0.1;
        }if(w.getWind() < 5){
            windFactor = 0;
        }else{
            if(w.getTemp() > 25 && w.getHum() < 30){
                windFactor += (w.getWind()*0.2)/5;
            }
        }
        burnedArea += (humFactor + tempFactor + windFactor) * Math.sqrt(burnedArea);//area a aumentar segundo os fatores
    }
    
    /**
     * Removes area from the fire, depending of the water and fireman used in the fire
    */
    public void extinguish(){
        for(Vehicle v : vehicles){
            burnedArea -= v.extinguish();
            waterUsed += 1000;
        }
        if(burnedArea <= 0){
            burnedArea = 0;
            endHour = LocalDateTime.now();
        }
    }
    /**
     * Counts the number of fireman in the Fire
     * @return a - number of firemen in the fire
    */
        public int countFireman(){
        return firemen.size();
    }
    
    /**
     * Counts the number of vehicles in the Fire
     * @return a - number of vehicles in the Fire
    */
    public int countVehicles(){
        return vehicles.size();
    }
    
    /**
     * Adds a vehicle to the fire
     * @param vehicle - Vehicle
     * @return 
    */
    public boolean addVehicle(Vehicle vehicle){
        if(vehicle != null){
            vehicles.add(vehicle);
            totalSeats += vehicle.getCurrentFiremen();//adiciona o numero de seats que o veiculo adicionado tem
            System.out.println("Vehicle " + vehicle.getId() + " Added To Fire\n");
            return true;
        }else{
            System.out.println("Not Able To Add Vehicle To Fire\n");
            return false;
        }
    }
    
    /**
     * Removes a vehicle from the fire
     * @param vehicle - Vehicle
     * @return 
    */
    public boolean removeVehicle(Vehicle vehicle){
        
        if(vehicles.remove(vehicle)){
            totalSeats -= vehicle.getCurrentFiremen();
            System.out.println("Vehicle " + vehicle.getId() + " Removed From Fire\n");
            return true;
        }else{
            System.out.println("Not Able To Remove Vehicle From Fire\n");
            return false;
        }
    }
    
    /**
     * Adds a fireman to the fire
     * @param fireman - Fireman
     * @return 
    */
    public boolean addFireman(Fireman fireman) {
        
        if(fireman != null){
                firemen.add(fireman);
                System.out.println("Fireman " + fireman.getId() + " Added To Fire\n");
                return true;
        }
        return false;
    }
    
    /**
     * Removes a fireman from the fire
     * @param fireman - Fireman
     * @return 
    */
    public boolean removeFireman(Fireman fireman){
        
        if(firemen.remove(fireman)){
            System.out.println("Fireman " + fireman.getId() + " Removed From Fire\n");
            return true;
        }else{
            System.out.println("ERROR: FireMan Not In The Fire\n");
            return false;
        }
    }
    
    /**
     * Removes extra fireman from vehicles on the fire (if vehicle has more than 3 firemen in it)
     * Adds them to the firemen array on the fire 
    */
    public void removeExcessOfFireman(){ //retira os bombeiros a mais dos veiculos e adiciona-os ao array firemen
        vehicles.stream().filter((v) -> (v.getCurrentFiremen() > 3)).forEachOrdered((v) -> {
            for(int z = 4; z < v.getCurrentFiremen(); z++){
                addFireman(v.removeFiremanByPosition(z));
            }
        });
    }
    
    /**
     * Checks if a vehicle is in the vehcile array
     * @param v - Vehicle
     * @return 
    */
    public boolean checkVehicle(Vehicle v){
        return vehicles.contains(v);
    }
    
    public void act(){
        while(burnedArea > 0){
            ignite();
            extinguish();
            showInfo();
        }
        if(burnedArea <= 0){//volta a colocar os bombeiros nos carros, distribuindo-os dependendo dos lugares que existes(totalSteats)
            if(countFireman() <= totalSeats){
                int p = 0;
                for (Vehicle v : vehicles){
                    int z = 0;
                    while(z < v.getCurrentFiremen()){
                        if(p < firemen.size()){
                            if(v.getCurrentFiremen() <= v.getFiremen().size()){
                                v.addFireman(firemen.get(p));
                                p++;
                            }
                        }else{
                            break;
                        }
                        z++;
                    }
                }
            }else{
                if(countFireman() <= countVehicles() * 3){
                    int p = 0;
                    for(Vehicle v : vehicles){
                        int z = 0;
                        while(z < 3){
                            if(p < firemen.size()){
                                if(v.getCurrentFiremen() <= v.getFiremen().size()){
                                    v.addFireman(firemen.get(p));
                                    p++;
                                }
                            }else{
                                break;
                            }
                            z++;
                        }
                    }
                }
                for(Vehicle v : vehicles){
                    if(v != null){
                        v.moveToBarrack(v.getBarrack());
                        vehicles.remove(v);
                    }
                }
            }
        }
    }
    
    public void showInfo(){
        System.out.println("Fire\nBurned Area: "+ burnedArea + "\n" + position.toString() + "\n");
    }
    //SELETORES
    public Position getPosition() {
        return position;
    }
    public ArrayList getFiremen(){
        return firemen;
    }
    public double getBurnedArea(){
        return burnedArea;
    }
    public double getinitialArea(){
        return initialArea;
    }
    public double waterUsed(){
        return waterUsed;
    }
}