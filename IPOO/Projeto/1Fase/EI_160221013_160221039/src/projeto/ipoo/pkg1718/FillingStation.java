package projeto.ipoo.pkg1718;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class FillingStation{
    
    private final String name;
    private final int id;
    private static int increment;
    private final boolean useful;
    private final Position position;
    private ArrayList<Vehicle> filling, parked;
    
    public FillingStation(String name, double lat, double lon, boolean useful){
        Random r = new Random();
        this.useful = useful;
        id = increment;
        increment++;
        if(checkName(name))this.name = name;
        else this.name = "Station " + id;
        if(checkPos(lat, lon))position = new Position(lat,lon);
        else position = new Position(-9.5+(r.nextDouble()*(-6.2 + 9.5)),36.8+(r.nextDouble()*(42.2-36.8)));//se a posição dada no construtor nao for valida, é gerada uma posição valida
        filling = new ArrayList<>();
        parked = new ArrayList<>();
    }
    
    private boolean checkName(String name){
        return !(name == null || name.equals(""));
    }
    private boolean checkPos(double lat, double lon){
        return lat > -9.5 && lat <-6.2 && lon > 36.8 && lon < 42.2;
    }
    
    /**
     * Organizes the perked array
     * Adds a vehicle to the parked array (waiting to fill)
     * @param vehicle - Vehicle
     * @return 
    */
    public boolean addParked(Vehicle vehicle){
        //organizeParked();
        if(vehicle != null){
            parked.add(vehicle);
            System.out.println("Vehicle " + vehicle.getId() + " Added To Park On "+ getName() + "\n");
            return true;
        }else{
            System.out.println("Not Able To Add Vehicle To Park\n");
            return false;
        }
    }

    
    /**
     * Uses the removeParked() method to remove a vehicle from the parked array
     * Adds it to the filling station
     * @param vehicle
     * @return
    * 
    */
    public boolean addFilling(Vehicle vehicle){
        //organizeFilling();
        if(vehicle != null){
            if(filling.size() >= 10){
                addParked(vehicle);
                System.out.println("Vehicle " + vehicle.getId() + " Added To Park (Filling Full) On " + getName() + "\n");
                return true;
            }else{
                filling.add(vehicle);
                System.out.println("Vehicle " + vehicle.getId() + " Added To Filling On " + getName() + "\n");
                return true;
            }
        }else{
            System.out.println("Not Able To Add Vehicle To Filling\n");
            return false;
        }
    }
    /**
     * Verifica se existe espaço na filling e existe alguem na parked a espera, se sim move o veiculo do parked para o filling
     */
    public void organizeLines(){
        if(filling.size() < 10){
            if(parked.size() >= 1){
                filling.add(removeParked());
            }
        }
    }
    /**
     * @param vehicle veiculo a remover
     * @return 
     */
    public boolean removeFilling(Vehicle vehicle){
        //organizeFilling();
        if(filling.remove(vehicle)){
            System.out.println("Vehicle " + vehicle.getId() + " Removed From Barrack (" + getName() + ")\n");
            if(filling.size() < 10){
                if(parked.size() > 0){
                    if(parked.get(0) != null){
                    filling.add(removeParked());
                    System.out.println("Vehicle " + vehicle.getId() + " Added To Filling On " + getName() + "\n");
                }  
                }
                      
            }
            return true;
        }else{
            System.out.println("Not Able To Remove Vehicle From Barrack\n");
            return false;
        }
    }
    
    /**
     * Removes the first vehicle from the parked array
     * @return v - Removed Vehicle
    */
    public Vehicle removeParked(){
        //organizeParked();
        System.out.println("Vehicle " + parked.get(0).getId() + " Removed From Park\n");
        Vehicle v = parked.get(0);
        parked.remove(v);
        return v;
    }
    
    /**
     * Fill the tank of a vehicle
    */
    public void fill(){
        if(useful){
            filling.forEach((f) -> {
                if(!f.isFull()){
                    f.fill(500);
                }
            });
        }else{
            System.out.println("ERROR: FillingStation " + name +" Not Working");
        }
    }
    
    /**
     * Shows all the vehicles in the filling array
    */
    public void showFilling(){
        System.out.println("Vehicle Filling:\n");
        filling.forEach((f) -> {
                System.out.println("Vehicle " + f.getId() + "\n");
            });
    }
    
    /**
     * Shows all vehicles in the parked array
    */
    public void showParked(){
        System.out.println("Vehicle Parked:\n");
        parked.forEach((p) -> {
                System.out.println("Vehicle " + p.getId() + "\n");
            });
    }
    
    public void act(){
        organizeLines();
        if(filling.size() >= 1){
            for(int i = 0; i< filling.size(); i++){
                filling.get(i).fill(500);
                if(filling.size() > i){
                    if(filling.get(i) != null){
                    if(filling.get(i).isFull()){
                        removeFilling(filling.get(i));
                        filling.get(i).moveToBarrack(filling.get(i).getBarrack());
                    }
                }
                }
                
                
            }
        }
        /* ENCONTRAR A BARRACA MAIS PROX
                        Barrack[] b;
                        double nearest = 999999999;
                        Barrack nearestb = null;
                        for(int a = 0; a < b.length; a++){//procuraa  barrack mais perto e retorna-o para la
                            if(b[a].getPosition().getKilometersTo(this.getPosition().getLatitude(), this.getPosition().getLongitude()) < nearest){
                                nearest = b[a].getPosition().getKilometersTo(this.getPosition().getLatitude(), this.getPosition().getLongitude());
                                nearestb = b[a];
                            }
                        }
                        filling[i].moveToBarrack(nearestb);
        */
    }

    public void showInfo(){
        System.out.println("FillingStation\nName: " + name + "\n" + position.toString() + "\n");
    }
    //SELETORES
    public String getName(){
        return name;
    }
    public boolean isUseful(){
        return useful;
    }
    public Position getPosition(){
        return position;
    }
    
}