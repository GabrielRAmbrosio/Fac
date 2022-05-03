package projeto.ipoo.pkg1718;
    
import java.util.Random;

    
public class Simulator {
    private Barrack[] barracks;
    private Vehicle[] vehicles;
    private Fireman[] firemen;
    private FillingStation[] fillingStations;
    private Fire[] fires;
    private Weather weather;
    
    
    public void create(){
        
        Random r = new Random();
        
        barracks = new Barrack[r.nextInt(70) + 30];
        for(int i = 0; i < barracks.length; i++){
            barracks[i] = new Barrack(-9.5+(r.nextDouble()*(-6.2 + 9.5)),36.8+(r.nextDouble()*(42.2-36.8)), "", r.nextInt(300) + 700, r.nextInt(400) + 800, this);
        }
        vehicles = new Vehicle[r.nextInt(300) + 700];
        for(int i = 0; i < vehicles.length; i++){
            vehicles[i] = new Vehicle(r.nextInt(20000-1500) + 1500,barracks[r.nextInt(barracks.length)], this);
        }
        firemen = new Fireman[r.nextInt(400) + 800];
        for(int i = 0; i < firemen.length; i++){
            firemen[i] = new Fireman(barracks[r.nextInt(barracks.length)], true);
        }
        fillingStations = new FillingStation[r.nextInt(30) + 20];
        for(int i = 0; i < fillingStations.length; i++){
            fillingStations[i] = new FillingStation("",-9.5+(r.nextDouble()*(-6.2 + 9.5)),36.8+(r.nextDouble()*(42.2-36.8)), r.nextBoolean());
        }
        
        weather = new Weather(r.nextInt(45),r.nextInt(100),r.nextInt(100));
        
        fires = new Fire[r.nextInt(50) + 50];
        for(int i = 0; i < fires.length; i++){
            fires[i] = new Fire(r.nextInt(1000)+ 1000,-9.5+(r.nextDouble()*(-6.2 + 9.5)),36.8+(r.nextDouble()*(42.2-36.8)), weather);
        }
        double initialArea = 0;
        for (Fire f : fires) {
            initialArea += f.getinitialArea();
        }
        
        for (int e = 0; e < vehicles.length; e++) {
                vehicles[e].act(true);
        }
        for (int i = 0; i < barracks.length; i++) {
            barracks[i].checkVehicles();
        }
        int z = 0;
        double a = 0.00000000000000001;
        while(a > 0){
            for (int i = 0; i < fillingStations.length; i++){
                fillingStations[i].act();
            }
            for (int i = 0; i < barracks.length; i++) {
                barracks[i].act(true);
            }
            for(Fire f: fires){
               z += f.getFiremen().size();
            }
            for (int i = 0; i < barracks.length; i++) {
                barracks[i].checkVehicles();
            }
            for (int i = 0; i < barracks.length; i++) {
                barracks[i].act(false);
            }
            for (int i = 0; i < fires.length; i++){
                fires[i].act();
            }
            for(Fire f : fires){
                a = 0;
                a += f.getBurnedArea();
            }
            
        }
        double totalWater = 0;
        for (Fire f : fires) {
            totalWater += f.waterUsed();
        }
        
        System.out.println("END OF SIMULATION:\nNumber Of Fires: " + fires.length + 
             "\nNumber Of Vehicles: " + vehicles.length + "\nNumber Of Firemen: " + firemen.length + 
                "\nTotal Burned Area: " + initialArea + "\nAverage Firemen Used: "+ z/fires.length + "\nTotal Water Used: " + totalWater + "\nAverage Water Used: " +
                totalWater/fires.length + "");
    }
    
    public Fire closestFireTo(double x,double y){
       double minDistance = 9999999; 
       Fire closestFire = null; 
       for(Fire f : fires){
           double dist = f.getPosition().getKilometersTo(x, y);
           if(minDistance > dist){
               closestFire = f;
               minDistance = dist;
           }
       }
       return closestFire;
    }
    public FillingStation closestFillingTo(double x,double y){
       double minDistance = 9999999; 
       FillingStation closestFillingStation = null; 
       for(FillingStation f : fillingStations){
           double dist = f.getPosition().getKilometersTo(x, y);
           if(minDistance > dist){
               closestFillingStation = f;
               minDistance = dist;
           }
       }
       return closestFillingStation;
    }
}
