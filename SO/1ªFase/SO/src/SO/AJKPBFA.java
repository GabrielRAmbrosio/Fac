package recursoso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class AJKPBFA {
    private List<Product> allProducts;
    private double maxWeight, bestPossible;
    private long elapsedTime;
    private int iterations, seconds;
    
    public AJKPBFA(List<Product> allProducts, double maxWeight, int seconds, double bestPossible){
        this.allProducts = new ArrayList<>(allProducts);
        this.maxWeight = maxWeight;
        this.seconds = seconds;
        this.bestPossible = bestPossible;
    }
    
    public Knapsack runAlgorithm(){
        List<Knapsack> allKnaps = new ArrayList<>();
        Knapsack first = new Knapsack(maxWeight, allProducts);
        allKnaps.add(first);
        Random random = new Random();
        
        iterations = 0;
        long startTime = System.currentTimeMillis();
        elapsedTime = 0L;
        
        Knapsack bestKnapsackSoFar = null;
        double bestValueSoFar = 0;
        
        while (elapsedTime < seconds * 1000){//run for n seconds
            if(allKnaps.size() == 0){//if there is no more items to put in the knap leave
                elapsedTime = (new Date()).getTime() - startTime;
                break;
            }
            int n = random.nextInt(allKnaps.size());
            Knapsack aux = allKnaps.get(n);
            allKnaps.remove(n);
            
            double weight = aux.getWeight();
            double value = aux.getValue();
            
            if(weight <= maxWeight){//if the weight is less than the max
                if(!aux.getChildren().isEmpty()){
                    if (aux.getChildren().get(0).getWeight() <= maxWeight) {
                        allKnaps.add(aux.getChildren().get(0));//add left children
                    }
                    if (aux.getChildren().get(1).getWeight() <= maxWeight) {
                        allKnaps.add(aux.getChildren().get(1));//and right children
                    }
                }
            }
            if(value > bestValueSoFar){
                bestKnapsackSoFar = aux;
                bestValueSoFar = value;
            }
            if(value == bestPossible){
                elapsedTime = (new Date()).getTime() - startTime;
                iterations++;
                return aux;
            }
            
            elapsedTime = (new Date()).getTime() - startTime;
            iterations++;
        }
        
        return bestKnapsackSoFar;
    }
    
    public long getElapsed(){
        return elapsedTime;
    }
    public int getIterations(){
        return iterations;
    }
}
