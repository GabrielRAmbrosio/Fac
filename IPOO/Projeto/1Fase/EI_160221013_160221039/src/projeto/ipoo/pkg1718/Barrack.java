package projeto.ipoo.pkg1718;

import java.util.ArrayList;
import java.util.Random;

public class Barrack {
    
    private ArrayList<Vehicle> vehicles;
    private ArrayList<Fireman> firemen;
    private final String name;
    private final Position position;
    private static int increment;
    private int totalSeats;
    private Fire closestFire; //fogo mais prox
    private final Simulator sim;

    public Barrack(double lon, double lat, String name, int maxVehicles, int maxFireman, Simulator s) {
        Random r = new Random();
        increment++;
        if(checkPos(lat, lon))position = new Position(lat,lon);
        else position = new Position(-9.5+(r.nextDouble()*(-6.2 + 9.5)),36.8+(r.nextDouble()*(42.2-36.8)));//cria uma posição aleatoria, se a fornecida nao e valida
        firemen = new ArrayList<>();
        vehicles = new ArrayList<>();
        if (checkName(name))this.name = name;
        else this.name = "Barrack " + increment;
        closestFire = null;
        sim = s;
    }

    private boolean checkName(String name){
        return !(name == null || name.equals(""));
    }
    private boolean checkPos(double lat, double lon){
        return lat > -9.5 && lat <-6.2 && lon > 36.8 && lon < 42.2;
    }
    
    /**
     * Adds a vehicle to the Barrack
     * @param vehicle - Vehicle to add
     * @return 
    */
    public boolean addVehicle(Vehicle vehicle){
        if(vehicle != null){
            vehicles.add(vehicle);
            totalSeats += vehicle.getCurrentFiremen();//adiciona o numero de seats que o veiculo adicionado tem
            System.out.println("Vehicle " + vehicle.getId() + " Added To Barrack (" + getName() + ")\n");
            return true;
        }else{
            System.out.println("Not Able To Add Vehicle To Barrack\n");
            return false;
        }
    }
    /**
     * Removes a vehicle from the barrack
     * @param vehicle - Vehicle to remove
     * @return 
    */
    public boolean removeVehicle(Vehicle vehicle){
        if(vehicles.remove(vehicle)){
            totalSeats -= vehicle.getCurrentFiremen();
            System.out.println("Vehicle " + vehicle.getId() + " Removed From Barrack (" + getName() + ")\n");
            return true;
        }else{
            System.out.println("Not Able To Remove Vehicle From Barrack\n");
            return false;
        }
    }
    
    /**
     * Adds a fireman to the Barrack
     * @param fireman - Fireman
     * @return 
    */
    public boolean addFireMan(Fireman fireman){
        if(fireman != null){
            firemen.add(fireman);
            System.out.println("Fireman " + fireman.getId() + " Added To Barrack (" + getName() + ")\n");
            return true;
        }else{
            System.out.println("Not Able To Add Fireman To Barrack\n");
            return false;
        }
    }
    
    /**
     * Removes a fireman from the Barrack
     * @param fireman - Fireman
     * @return 
    */
    public boolean removeFireMan(Fireman fireman){
        if(firemen.remove(fireman)){
            System.out.println("Fireman " + fireman.getId() + " Removed From Barrack (" + getName() + ")\n");
            return true;
        }else{
            System.out.println("Not Able To Remove Fireman From Barrack\n");
            return false;
        }
    }
    
    /**
     * Counts the number of fireman in the barrack
     * @return number of firemen
    */
    public int countFireman(){
        return firemen.size();
    }
    
    /**
     * Counts the number of vehicles in the barrack
     * @return number of vehicles
    */
    public int countVehicles(){
        return vehicles.size();
    }
    
    /**
     * Uses the addFireman() method from Vehicle to add a fireman to the vehicle on the barrack
     * @param v - Vehicle
     * @param f - Fireman
     * @return 
    */
    public boolean addFiremanToVehicle(Vehicle v, Fireman f){
        if(v.getBarrack() == f.getBarrack()){
            if(!v.firemanInVehicle(f)){
                v.addFireman(f);
                return true;
            }else{
                System.out.println("ERROR: Fireman Already In Vehicle\n");
                return false;
            }
        }else{
            System.out.println("ERROR: Fireman And Vehicle Are Not In The Same Barrack\n");
            return false;
        }
    }
    public boolean removeFiremanFromVehicle(Vehicle v, Fireman f){
        if(v.getBarrack() == f.getBarrack()){
            if(v.firemanInVehicle(f)){
                v.removeFireman(f);
                return true;
            }else{
                System.out.println("ERROR: Fireman Not In Vehicle\n");
                return false;
            }
        }else{
            System.out.println("ERROR: Fireman And Vehicle Are Not In The Same Barrack\n");
            return false;
        }
    }
    
    /**
     * Uses the addVehicle() method from Fire to add a vehicle to the vehicle array of Fire
     * @param v - Vehicle
     * @param f - Fire
    */
    public void addVehicleToFire(Fire f, Vehicle v){
        if(!f.checkVehicle(v)){
            removeVehicle(v);
            f.addVehicle(v);
        }else{
            System.out.println("ERROR: Vehicle Already At The Fire\n");
        }
    }
    
    public void checkVehicles(){
        for(Vehicle v : vehicles){
            if(v.isReady()){
                if(v.getCurrentCapacity() <= 0){
                    v.moveToFillingStation(v.getClostestFillingStation());
                }
            }
        }
    }

    public void act(boolean first){
        if(first){
            for(Vehicle vvv : vehicles){
            if(vvv.getDriver() == null){
                for(Fireman f : firemen){
                    if(f.isdrivingAllowed()){
                        if(!f.isInVehicle()){
                            vvv.addDriver(f);
                            f.setInVehicle();
                        }
                    }
                }
            }
            for(Vehicle v : vehicles){
                while(v.getCurrentFiremen() < 3){//adiciona bombeiros
                    for(Fireman f : firemen){
                        if(!f.isInVehicle()){
                            v.addFireman(f);
                            f.setInVehicle();
                        }
                    }
                }
                if(v.getCurrentFiremen() >= 3){
                    if(!v.isReady()){
                        v.setIsReady();
                    }
                }
            }
            for(Vehicle vv : vehicles){//remove bombeiros de veiculos que nao estejam prontos
                    if(!vv.isReady()){
                        vv.getFiremen().clear();
                    }
                }
            }
            
            int a = 0;
            for(Fireman f : firemen){
                if(!f.isInVehicle()){
                    a++;
                }
            }
            
            for(Vehicle v : vehicles){
                while(v.getCurrentFiremen() < v.getMaxSeats() && a >= 0){
                    for(Fireman f : firemen){
                            if(!f.isInVehicle()){
                                v.addFireman(f);
                                f.setInVehicle();
                            }
                        }
                }
            }
            checkVehicles();
        }else{
            closestFire = sim.closestFireTo(position.getLatitude(), position.getLongitude());
            for(Vehicle v : vehicles){
                v.act(first);
            }
        }
        
        
        
        //FALTA EXECUTAR O ACT DO VEHICLE
        
        
        /*CÓDIGO ANTIGO PARA ORGANIZAR OS VEICULOS
        if(countFireman() <= totalSeats){//organiza todos os bombeiros pelos totalSeats disponiveis
            int p = 0;
            for (int i = 0; i < vehicles.size(); i++) {
                int z = 0;
                while (z < vehicles.get(i).getCurrentFiremen()) {
                    if (p < firemen.size()) {
                        if (vehicles.get(i).getCurrentFiremen() <= vehicles.get(i).getFiremen().size()) {
                            vehicles.get(i).addFireman(firemen.get(p));
                            p++;
                        }
                    } else {
                        break;
                    }
                    z++;
                }
            }
        }else{
            if(countFireman() <= countVehicles() * 3){
                int p = 0;
                for (Vehicle vehicle : vehicles) {
                    int z = 0;
                    while (z < 3) {
                        if (p < firemen.size()) {
                            if (vehicle.getCurrentFiremen() <= vehicle.getFiremen().size()) {
                                vehicle.addFireman(firemen.get(p));
                                p++;
                            }
                        } else {
                            break;
                        }
                        z++;
                    }
                }
            }
        }
        */
    }

    public void showInfo(){
        System.out.println("Barrack\nName: "+ name + "\nNumber Of Fireman: " + countFireman() + "\nNumber Of Vehicles: " + countVehicles() + "\n" + position.toString() + "\n");
    }
    //SELETORES
    
    public Position getPosition() {
        return position;
    }
    public String getName() {
        return name;
    }
    public Fire getClosestFire() {
        return closestFire;
    }
    public ArrayList getVehicles(){
        return vehicles;
    }
    public ArrayList getFiremen(){
        return firemen;
    }
}