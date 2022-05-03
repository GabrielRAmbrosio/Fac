package pa_project;

import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class Percurso {
    private ArrayList<Place> places;
    private ArrayList<Path> paths;
    private double cost, distance;
    private static String FILE = "c:/temp/FirstPdf.pdf";
    
    public Percurso(){
        
    }
    
    public void printTicket(){
        
    }
    
    public void setPlaces(ArrayList<Place> places) {
        this.places = places;
    }

    public void setPaths(ArrayList<Path> paths) {
        this.paths = paths;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public ArrayList<Place> getPlaces() {
        return places;
    }

    public ArrayList<Path> getPaths() {
        return paths;
    }

    public double getCost() {
        return cost;
    }

    public double getDistance() {
        return distance;
    }
}
