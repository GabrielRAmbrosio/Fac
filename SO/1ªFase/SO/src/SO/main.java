package recursoso;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main{
    
    //example commands to use
    //ex23 ajkpa 30
    //ex23 ajkpbfa 30
    
    public static String filename, algorithm = "";
    public static int seconds;
    public static List<Product> allProducts;

    public static void main(String[] args) throws IOException{
        List<Product> products = new ArrayList<>();
        List<Product> allProducts = new ArrayList<>();
        
        Reader reader = new Reader();
        Writer writer = new Writer();
        
        while(products.isEmpty() && (!algorithm.equals("ajkpa") && !algorithm.equals("ajkpbfa"))){//while commands are not valid, keeps asking for them
            try{
                getCommands();
                products = reader.readFile(filename);
                allProducts = reader.readFile(filename);
            }catch(FileNotFoundException e){

            }
        }
        
        double bestValue;
        double maxWeight;
        bestValue = reader.getBestValue();
        maxWeight = reader.getMaxWeight();
        System.out.println("Best Value Possible: " + bestValue + "\nMax Weight: " + maxWeight + "\n");
        
        if(algorithm.equals("ajkpa")){//ajkpa
            
            System.out.println("\n\tAJKPA RUNNING\n");
            AJKPA ajkpa = new AJKPA(products, maxWeight, seconds, bestValue);
            Knapsack knapsack = ajkpa.runAlgorithm();//run algorithm

            System.out.println(knapsack.toString());

            //get elapsed time of the algorithm
            long milli = ajkpa.getElapsed() % 1000;
            long sec = (ajkpa.getElapsed() / 1000) % 60;
            String time = String.format("%2d.%d", sec, milli);
            System.out.println("Best Value Found: " + knapsack.getValue() + "\nKnapsack Weight: " + knapsack.getWeight() + "\nElapsed Time: " + time + " seconds\n");
            
            int aux[] = getBits(allProducts, knapsack);
            String line = "" + filename + "\t\t" + algorithm + "\t" + time + "\t" + ajkpa.getIterations() + "\t" + bitToString(aux, aux.length);
            
            //writes to file all the info
            writer.writeToFile(algorithm, line);
            
        }else{//ajkpbfa
            
            System.out.println("\n\tAJKPBFA RUNNING\n");

            AJKPBFA ajkpbfa = new AJKPBFA(products, maxWeight, seconds, bestValue);
            Knapsack knap = ajkpbfa.runAlgorithm();//run algorithm

            System.out.println(knap.toString());

            
            //get elapsed time of the algorithm
            long milli = ajkpbfa.getElapsed() % 1000;
            long sec = (ajkpbfa.getElapsed() / 1000) % 60;
            String time = String.format("%2d.%d", sec, milli);
            System.out.println("Best Value Found: " + knap.getValue() + "\nKnapsack Weight: " + knap.getWeight() + "\nElapsed Time: " + time + " seconds\n");
            
            int aux[] = getBits(allProducts, knap);
            String line = "" + filename + "\t\t" + algorithm + "\t" + time + "\t" + ajkpbfa.getIterations() + "\t" + bitToString(aux, aux.length);
            
            writer.writeToFile(algorithm, line);
        }
    }
    
    //get the commands from the user
    public static void getCommands(){
        Scanner read = new Scanner(System.in);
        read.useDelimiter(",|\\n");
        System.out.println("Enter command (<filename algorithm seconds> e.g. ex04 ajkpa 10)\n");
        
        String[] splited = read.next().split(" ");
        
        filename = splited[0];
        //threads = Integer.parseInt(splited[1].trim()); not implemented
        algorithm = splited[1];
        seconds = Integer.parseInt(splited[2].trim());
    }
    
    //get the bits from a list, 0 if the product is not in the knap, and 1 if it is
    public static int[] getBits(List<Product> all, Knapsack best){
        int bits[] = new int[all.size()];
        int size = 0;
        for(int i = 0; i < all.size(); i++){
            double value = all.get(i).getValue();
            double weight = all.get(i).getWeight();
            boolean exists = false;
            
            for(Product p : best.getProducts()){
                if(p.getValue() == value && p.getWeight() == weight){
                    exists = true;
                }
            }
            if(exists){
                bits[size] = 1;
                size++;
            }else{
                bits[size] = 0;
                size++;
            }
        }
        return bits;
    }
    
    public static String bitToString(int[] a, int size){
        String aux ="";
        
        for(int i = 0; i < size; i++){
            aux += " " + a[i];
        }
        
        return aux;
    }
}