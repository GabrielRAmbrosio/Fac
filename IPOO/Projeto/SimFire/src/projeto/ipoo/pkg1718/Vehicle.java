package projeto.ipoo.pkg1718;

import java.util.ArrayList;
import java.util.Random;

public class Vehicle {

    private static int increment;
    private final int id, maxCapacity, maxSeats;
    private int currentCapacity;
    private Fireman driver;
    private ArrayList<Fireman> firemen;
    private Position position;
    private final int MAXMAXCAPACITY = 20000;
    private final int MINMAXCAPACITY = 1500;
    private boolean inBarrack, atFire, inFilling, isFull, isReady;
    private Barrack barrack;
    private FillingStation closestFillingStation;
    private Simulator sim;

    public Vehicle(int maxCapacity, Barrack b, Simulator s){
        Random r = new Random();
        maxSeats = r.nextInt(5) + 3;
        id = increment;
        increment++;//incrementa o id ao criar um objeto novo
        firemen = new ArrayList<>();
        driver = null;
        if(b != null){
            position = b.getPosition();
            b.addVehicle(this);//adiciona logo o veiculo a uma Barrack
            barrack = b;
            inBarrack = true;
        }
        inFilling = false;
        atFire = false;
        isFull = false;
        isReady = false;
        currentCapacity = 0;
        if(checkMaxCapacity(maxCapacity))this.maxCapacity = maxCapacity;
        else this.maxCapacity = MINMAXCAPACITY;//se a maxCapacity dada nao estiver entre os valores finais estabelecidos (MAXMAXCAPACITY e MINMAXCAPACITY) e o resto da divisão com 500 não for zero, iguala a á MINMAXCAPACITY
        sim = s;
        closestFillingStation = null;
    }

    private boolean checkMaxCapacity(int maximum){
        if(maximum > MINMAXCAPACITY && maximum < MAXMAXCAPACITY){
            if(maximum % 500 == 0){
                return true;
            }
        }
        return false;
    }
    
    private boolean checkSeatNumber(int s){
        return s <= 8 && s >=3;
    }
    
    /**
     * Changes the current position of the vehicle to the newPosition if valid
     * @param newPosition - position to move to
     * @return the success
    */
    public boolean move(Position newPosition){
        if(newPosition != null){
            if(getCurrentFiremen() >= 3){//no minimo 3 pessoas no veiculo
                if(driver.isdrivingAllowed()){//condutor pode conduzir
                    if(driver.isWorking()){//tira os do trabalho
                            driver.setWorking();
                        }
                    for(Fireman f : firemen){
                        if(f.isWorking()){
                            f.setWorking();
                        }
                    }
                    double t = position.getTimeFromTo(position, newPosition);
                    useEnergyAll(t);
                    position = newPosition;//muda a posição
                    checkEnergy();
                    System.out.println("Vehicle " + getId()+" Drove To " + newPosition.toString() + "\n");
                    return true;
                }else{
                    System.out.println("ERROR: Driver (Fireman "+firemen.get(0).getId()+") Not Allowed To Drive\n");
                    return false;
                }
            }else{
                System.out.println("ERROR: Not Enough FireMen In The Vehicle\n");
                return false;
            }
        }else{
            System.out.println("ERROR: Invalid Position\n");
            return false;
        }
    }
    /**
     * Uses Energy From All Fireman In Vehicle
     * @param number hours do use
    */
    private void useEnergyAll(double number){
        driver.useEnergy(number);
        firemen.forEach((f) -> {
            f.useEnergy(number);
        });
    }
    /**
     * Checks If Any Fireman From Vehicle Needs Rest
     */
    private void checkEnergy(){
        int n = 0;
        if(driver.getEnergy() <= 0){
            if(!driver.isResting()){
                    driver.setResting();
                    driver.rest();
                }
        }
        for(Fireman f : firemen){
            if(f.getEnergy() <= 0){
                if(!f.isResting()){
                    f.setResting();
                    f.rest();
                }
            }
        }
    }
    /**
     * Uses the move() method above, but its uses a Fire position
     * @param fire - Fire
     * @return 
    */
    public boolean moveToFire(Fire fire){
        if(getPosition() != fire.getPosition()){
            if(move(fire.getPosition())){
                if(getCurrentCapacity() <= 0){
                    moveToFillingStation(sim.closestFillingTo(position.getLatitude(), position.getLongitude()));
                    inFilling = true;
                    inBarrack = false;
                    atFire = false;
                    return false;
                }
                for(Fireman firemen1 : firemen){
                    if(!firemen1.isWorking()){
                        firemen1.setWorking();
                    }if(firemen1.isResting()){
                        firemen1.setResting();
                    }
                    fire.addFireman(firemen1);  //adiciona os firemen do veiculo aos firemen fire
                }
                System.out.println("Vehicle " + id + " Moved To Fire " + fire.getPosition().toString() + "\n");
                fire.addVehicle(this);//adiciona este veiculo ao array de veiculos do fire
                atFire = true;
                inFilling = false;
                inBarrack = false;
                return true;
            }else{
                System.out.println("ERROR: No Able To Move To Fire\n");
                return false;
            }
        }else{
            System.out.println("Vehicle " + id + " Already At The Fire " + fire.getPosition().toString() + "\n");
            return false;
        }
    }
    
    /**
     * Uses the move() method, but its uses a Barrack position
     * @param b - Barrack
    */
    public void moveToBarrack(Barrack b){
        if(getPosition() != b.getPosition()){
            if(move(b.getPosition())){
                for (Fireman firemen1 : firemen){
                    if(firemen1.isWorking()){
                        firemen1.setWorking();
                    }if(firemen1.isResting()){
                        firemen1.setResting();
                    }
                    b.addFireMan(firemen1);
                }
                inFilling = false;
                atFire = false;
                inBarrack = true;
                b.addVehicle(this);
                barrack = b;
                System.out.println("Vehicle " + id + " Moved To Barrack " + b.getPosition().toString() + "\n");
            }
        }else{
            System.out.println("Vehicle " + id + " Already In Barrack " + b.getPosition().toString() + "\n");
        }
    }
    
    /**
     * Uses move(), but its uses a FillingStation position
     * @param fs - FillingStation
    */
    public void moveToFillingStation(FillingStation fs){
        if(getPosition() != fs.getPosition()){
            if(move(fs.getPosition())){
                for (Fireman firemen1 : firemen) {

                    if(firemen1.isWorking()){
                        firemen1.setWorking();
                    }
                    if(firemen1.isResting()){
                        firemen1.setResting();
                    }
                    fs.addParked(this);//adiciona o veiculo ao array parked da fillingstation
                }
                inFilling = true;
                atFire = false;
                inBarrack = false;
                System.out.println("Vehicle " + id + " Moved To FillingStation " + fs.getPosition().toString() + "\n");
            }
        }else{
            System.out.println("Vehicle " + id + " Already In FillingStation " + fs.getPosition().toString() + "\n");
        }
    }
    
    /**
     * Adds water from the FillingStation to the vehicle tank (2500 per 5min)
     * @param a
    */
    public void fill(double a){
        currentCapacity += a;
        if(currentCapacity > maxCapacity){
            currentCapacity = maxCapacity;
            isFull = true;
        }
    }
        
    /**
     * Sums the area removed by one vehicle (water used) and the area removed by extra fireman that left the vehicle (minutes spent)
     * @return area - area to remove from fire
    */
    public int extinguish(){
        int area = 0;
        int a = 0;
        if(getCurrentFiremen() >= 3){
            area += firemen.size() - 3;//adiciona 1 metro quadrado por bombeiro (exepto por 3 deles)
            if(atFire){
                if(currentCapacity >= 1000){
                    currentCapacity -= 1000;
                    isFull = false;
                    area += 1000 / 2;//adiciona 500m^2 por 1000 litros
                }
            }else{
                System.err.println("Vehicle " + getId() + " Not At The Fire\n");
            }
        }
        return area;//retorna o valor que um veiculo apaga no incendio (depende da agua usada e dos bombeiros a mais)
    }
    
    /**
     * Adds a fireman to the vehicle array of Fireman
     * @param fireman - fireman to add
     * @return 
    */
    public boolean addFireman(Fireman fireman){
        if(fireman != null){
            if(fireman.getBarrack() == getBarrack()){
                if(getCurrentFiremen() >= maxSeats){
                    System.out.println("Vehicle " + getId() + " Is Full\n");
                    return false;
                }
                firemen.add(fireman);
                if(!fireman.isInVehicle()){
                    fireman.setInVehicle();
                }
                System.out.println("Fireman " + fireman.getId() + " Added To Vehicle " + id + "\n");
                return true;
            }else{
                System.out.println("Fireman " + fireman.getId() + ") Not In The Same Barrack As The Vehicle (Vehicle " + getId() + ")\n");
                return false;
            }
        }
        return false;
    }
    /**
     * Add a driver to the vehicle
     * @param fireman 
     * @return success
     */
    public boolean addDriver(Fireman fireman){
        if(fireman != null){
            if(fireman.getBarrack() == getBarrack()){
                if(fireman.isdrivingAllowed()){
                    driver = fireman;
                    if(!fireman.isInVehicle()){
                        fireman.setInVehicle();
                    }
                    System.out.println("Fireman " + fireman.getId() + " Added To Vehicle" + id + " As A Driver\n");
                    return true;
                }else{
                    System.out.println("Fireman " + fireman.getId() + " Not Allowed To Drive\n");
                    return false;
                }
            }else{
                System.out.println("Fireman " + fireman.getId() + ") Not In The Same Barrack As The Vehicle (Vehicle " + getId() + ")\n");
                return false;
            }
        }
        return false;
    }
    
    /**
     * Removes a fireman from the vehicle array of Fireman
     * @param fireman - fireman to remove
     * @return 
    */
    public boolean removeFireman(Fireman fireman){
        if(firemen.remove(fireman)){
            if(fireman.isInVehicle()){
                fireman.setInVehicle();
            }
            System.out.println("Fireman " + fireman.getId() + " Removed From Vehicle\n");
            return true;
        }else{
            if(compareDriver(fireman)){
                driver = null;
                if(fireman.isInVehicle()){
                    fireman.setInVehicle();
                }
                return true;
            }
            System.out.println("ERROR: FireMan Not In The Vehicle\n");
            return false;
        }
    }
    /**
     * Removes a fireman from the vehicle array of Fireman by the position
     * @param i -  Position of a Fireman
     * @return 
    */
    public Fireman removeFiremanByPosition(int i){
        Fireman fireman = firemen.get(i);
        if(firemen.remove(firemen.get(i))){
            System.out.println("Fireman " + fireman.getId() + " Removed From Vehicle\n");
            if(fireman.isInVehicle()){
                fireman.setInVehicle();
            }
            return fireman;
        }else{
            System.out.println("ERROR: FireMan Not In The Vehicle\n");
            return null;
        }
    }
    /**
     * Compares the first Fireman of the array firemen[0] to the fireman recieved in the parameters
     * @param f - Fireman
     * @return
    */
    public boolean compareDriver(Fireman f){
        return driver == f;
    }
    
    /**
     * Runs the fireman array, and checks if f is in it
     * @param f - Fireman
     * @return 
    */
    public boolean firemanInVehicle(Fireman f){
        if(compareDriver(f)){
            return true;
        }
        for(Fireman firemen1 : firemen){
            if (firemen1 == f) {
                return true;
            }
        }
        return false;
    }
    
    public void act(boolean first){
        if(first){
            closestFillingStation = sim.closestFillingTo(position.getLatitude(), position.getLongitude());
        }else{
            if(isReady){
                if(inBarrack && !inFilling){
                    if(barrack.getClosestFire() != null || barrack.getClosestFire().getBurnedArea() > 0){
                        moveToFire(barrack.getClosestFire());
                        barrack.getClosestFire().addVehicle(this);
                    }
                }
            }
        }
    }
    
    public void showInfo(){
        System.out.println("Vehicle\nVehicle " + id +"\nNumber Of Fireman " + getCurrentFiremen()+ "\n" + position.toString() + "\nCurrent Capacity: " + currentCapacity + "\nBarrack: " + barrack.getName() + "\n");
    }
    
    //SELETORES
    public int getId(){
        return id;
    }
    public boolean isInBarrack(){
        return inBarrack;
    }
    public boolean isInFilling(){
        return inFilling;
    }
    public boolean isFull(){
        return isFull;
    }
    public Barrack getBarrack(){
        return barrack;
    }
    public int getCurrentCapacity(){
        return currentCapacity;
    }
    public int getMaxCapacity(){
        return maxCapacity;
    }
    public Position getPosition(){
        return position;
    }
    public int getCurrentFiremen(){
        if(driver == null){
            return firemen.size();
        }else{   
        return firemen.size() + 1;
        }
    }
    public ArrayList getFiremen(){
        return firemen;
    }
    public Fireman getDriver(){
        return driver;
    }
    public int getMaxSeats(){
        return maxSeats;
    }
    public boolean isReady(){
        return isReady;
    }
    public FillingStation getClostestFillingStation(){
        return closestFillingStation;
    }
    public void showFiremenInVehicle(){
        firemen.forEach((fireman1) -> {
            System.out.println(fireman1);
        });
    }
    
    //MODIFICADORES
    public void MaxCurrentCapacity(){
        currentCapacity = maxCapacity;
    }
    public void setIsReady(){
        isReady = !isReady;
    }
}