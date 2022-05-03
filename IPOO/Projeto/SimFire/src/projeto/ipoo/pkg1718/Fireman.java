package projeto.ipoo.pkg1718;

import java.util.Random;

public class Fireman{
    
    private final int id;
    private static int increment;
    private int workingHours;
    private double energy;//100 = 18h
    private final boolean drivingAllowed;
    private boolean working, resting, inVehicle;
    private Barrack barrack;
    
    public Fireman(Barrack b, boolean drivingAllowed){
        id = increment;
        increment++;
        workingHours = 0;
        energy = 100;
        this.drivingAllowed = drivingAllowed;
        resting = false;
        working = false;
        inVehicle = false;
        b.addFireMan(this);//adiciona logo o fireman a uma Barrack atraves da sua posição
        barrack = b;
    }
    private boolean checkName(String name){
        return !(name == null || name.equals(""));
    }
    
    /**
     * Uses the move() method from Vehicle to change the vehicle position
     * @param vehicle - Vehicle to move
     * @param position - position to move the vehicle to
    */
    public void driveVehicle(Vehicle vehicle, Position position) {
        if(vehicle.firemanInVehicle(this)){//se este fireman está no vehicle
            if(vehicle.compareDriver(this)){//se este fireman é o condutor
                vehicle.move(position);//move para position
            }else{
                System.out.println("ERROR: Fireman " + id + " Isn't In The Driver Seat\n");
            }
        }else{
            System.out.println("ERROR: Fireman " + id + " Isn't In The Vehicle\n");
        }
    }
    
    /**
     * Removes energy from the fireman, depending on what he is doing, working removes 100.0 / 18, driving removes 200.0 / 18, if it ends, he is sent to rest()
     * @param hours - hours to rest
     * @return - the success of the method
    */
    public boolean useEnergy(double hours){
        if(resting){
            System.out.println("ERROR: FireMan Is Resting\n");
            return false;
        }else{
            if(working){
                energy -= (100 * hours) / 18;//a trabalhar 1h = 100/18
                workingHours += hours;
                return true;
            }else{
                energy -= (100 * hours) / 36;//a nao trabalhar 1h = 100/36 (metade)
                return true;
            }
        }
    }
    
    /**
     * Adds energy, depending on the hours the fireman rests, 100 / 18 per hours
     * @return - success
    */
    public boolean rest(){
        Random r = new Random();
        int hours = r.nextInt(18) + 6;//numero random entre 6 e 18
        if(resting){
            System.out.println("ERROR: FireMan Already Resting\n");
            return false;
        }else{
            if(working){
                System.out.println("ERROR: Fireman At Fire\n");
                return false;
            }else{
                if(energy >= 100){
                    System.out.println("Energy Already Full\n");
                    return true;
                }else if(workingHours >= 18){
                    if(hours < 6){
                        System.out.println("Fireman Needs To Rest Atleast 6h\n");
                        return false;
                    }else{
                        energy += (100 * hours) / 18;
                        if(energy > 100){
                            energy = 100;
                        }else if(energy < 0){
                            energy = 0;
                        }
                        return true;
                    }
                }else{
                    if(hours < 0){
                        System.out.println("Fireman Needs To Rest Atleast 1h\n");
                        return false;
                    }else{
                        energy += (100 * hours) / 18;
                        if(energy > 100){
                            energy = 100;
                        }else if(energy < 0){
                            energy = 0;
                        }
                        return true;
                    }
                }
            }
        }
    }
    
    public void showInfo(){
        System.out.println("Fireman\nID: " + id + "\nEnergy: " + energy + "\nWorking: " +  working + "\nResting: " + resting + "\n");
    }
    
    //SELETORES
    public int getId(){
        return id;
    }
    public Barrack getBarrack(){
        return barrack;
    }
    public double getEnergy(){
        return energy;
    }
    public boolean isdrivingAllowed(){
        return drivingAllowed;
    }
    public boolean isWorking(){
        return working;
    }
    public boolean isResting(){
        return resting;
    }
    public boolean isInVehicle(){
        return inVehicle;
    }
    
    //MODIFICADORES
    public void setWorking(){
        working = !working;
    }
    public void setResting(){
        resting = !resting;
    }
    public void setInVehicle(){
        inVehicle = !inVehicle;
    }
}