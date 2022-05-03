package pa_project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Gabriel
 */
public class Statistics {
    
    private ArrayList<Place> totalVisited;
    private HashMap<Place, Integer> mostVisited;
    private double totalPrice;
    private int ticketsOnFoot, ticketsOnBike;
    
    public Statistics(){
        totalVisited = new ArrayList<>();
        mostVisited = new HashMap<>();
        totalPrice = 0;
        ticketsOnBike = 0;
        ticketsOnFoot = 0;
    }

    public void addPrice(double value){
        totalPrice += value;
    }
    
    public void addTicketsOnFoot(){
        ticketsOnFoot++;
    }
    
    public void addTicketsOnBike(){
        ticketsOnBike++;
    }
    
    public void addPlace(Place place){
        totalVisited.add(place);
    }
    
    public double getMeanPrice(){
        return totalPrice/(ticketsOnFoot + ticketsOnBike);
    }
    
    public HashMap getMostVisited(){
        
        for(Place p : totalVisited){
            if (!mostVisited.containsKey(p)) {
                mostVisited.put(p, 1);
            } else {
                mostVisited.put(p, mostVisited.get(p) + 1);
            }
        }
        
        mostVisited = sortMap();
        
        int i = 1;
        for (Map.Entry<Place, Integer> entry : mostVisited.entrySet()) {
            if (i > 10) {
                mostVisited.remove(entry.getKey());
            }
            i++;
        }
        
        return mostVisited;
    }
    
    private HashMap<Place, Integer> sortMap()
    {

        List<Entry<Place, Integer>> list = new LinkedList<>(mostVisited.entrySet());
        
        Collections.sort(list, (Entry<Place, Integer> o1, Entry<Place, Integer> o2) -> {
            return o2.getValue().compareTo(o1.getValue());
        });
        
        HashMap<Place, Integer> sortedMap = new LinkedHashMap<>();
        for (Entry<Place, Integer> entry : list){
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        
        return sortedMap;
    }
    
    public double getPercentageOnFoot(){
        if(ticketsOnFoot + ticketsOnBike == 0) return 0;
        return (ticketsOnFoot*100)/(ticketsOnFoot + ticketsOnBike);
    }
    public double getPercentageOnBike(){
        if(ticketsOnFoot + ticketsOnBike == 0) return 0;
        return (ticketsOnBike*100)/(ticketsOnFoot + ticketsOnBike);
    }

    public int getTicketsOnFoot() {
        return ticketsOnFoot;
    }

    public int getTicketsOnBike() {
        return ticketsOnBike;
    }
    
    public void print(Map<Place,Integer> a){
        for (Place place : a.keySet()) {

            String key = place.getName();
            String value = a.get(place).toString();
            System.out.println(key + " " + value);
        }
    }
    
    @Override
    public String toString() {
        return "Statistics{" + "mostVisited = " + getMostVisited() + ", meanPrice = " + getMeanPrice() + ", ticketsOnFoot = " + getPercentageOnFoot() + ", ticketsOnBike = " + getPercentageOnBike() + "}";
    }
}
