package recursoso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class AJKPA extends Thread{
    private List<Product> products, allProducts;
    private double maxWeight, bestPossible;
    private long elapsedTime;
    private int iterations, seconds;
    
    public AJKPA(List<Product> allProducts, double maxWeight, int seconds, double bestPossible){
        products = new ArrayList<>(allProducts);
        this.maxWeight = maxWeight;
        this.seconds = seconds;
        this.bestPossible = bestPossible;
        this.allProducts = allProducts;
    }
    
    
    public Knapsack runAlgorithm(){
        Knapsack knapsack = new Knapsack(maxWeight, allProducts);
        Random random = new Random();
        
        double bestFound = 0;
        Knapsack bestKnapsackSoFar = null;
        iterations = 0;
        
        long startTime = System.currentTimeMillis();
        elapsedTime = 0L;
        
        while (elapsedTime < seconds * 1000){//run for n seconds

            if(random.nextBoolean()){//random to remove or add product to kanpsack
                if(!products.isEmpty()){
                    int n = random.nextInt(products.size());
                    if(knapsack.addProductCheck(products.get(n))){//adds to knapsack and removes from list of all products
                        products.remove(n);
                        if(knapsack.getValue() > bestFound){
                            bestFound = knapsack.getValue();
                            bestKnapsackSoFar = knapsack.clone();
                        }
                    }
                    if(bestFound == bestPossible){//if found best outcome leaves
                        elapsedTime = (new Date()).getTime() - startTime;
                        iterations++;
                        break;
                    }
                }else{//if its empty, it means that even with all the products available, its impossible to reach goal
                    elapsedTime = (new Date()).getTime() - startTime;
                    break;
                }
            }else{
                if(!knapsack.getProducts().isEmpty()){
                    int n = random.nextInt(knapsack.getProducts().size());
                    products.add(knapsack.removeIndex(n));//removes from knapsack and adds it to list of all products
                    if(knapsack.getValue() > bestFound){
                            bestFound = knapsack.getValue();
                            bestKnapsackSoFar = knapsack.clone();
                        }
                    if(bestFound == bestPossible){//if found best outcome leaves ex200_1 ajkpa 10
                        bestKnapsackSoFar = knapsack.clone();
                        elapsedTime = (new Date()).getTime() - startTime;
                        iterations++;
                        break;
                    }
                }
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
