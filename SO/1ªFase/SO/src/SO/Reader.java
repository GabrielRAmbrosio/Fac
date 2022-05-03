package recursoso;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {
    private final String path = System.getProperty("user.dir") + "\\src\\Input\\";
    private double maxWeight;
    private int bestValue;
    
    //reads a file and creates the products, and returns a list with all of them
    public List<Product> readFile(String filename) throws FileNotFoundException{
        List<Product> allProducts = new ArrayList<>();
        
        Scanner read = new Scanner(new File(path + filename + ".txt"));
        read.useDelimiter(",|\\n");
        
        int numberOfItems = -1;
        
        numberOfItems = Integer.parseInt(read.next().trim());
        maxWeight = Integer.parseInt(read.next().trim());
        
        for(int i = 0; i < numberOfItems; i++){
            String[] splited = read.next().split(" ");
            allProducts.add(new Product(Double.parseDouble(splited[0].trim()), Double.parseDouble(splited[1].trim())));
        }
        
        bestValue = Integer.parseInt(read.next().trim());
        
        return allProducts;
    }
    
    public double getBestValue(){
        return bestValue;
    }
    public double getMaxWeight(){
        return maxWeight;
    }
}