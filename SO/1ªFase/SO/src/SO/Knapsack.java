package recursoso;

import java.util.ArrayList;
import java.util.List;

public class Knapsack {
    private double maxWeight;
    private List<Product> products, allProducts;
    private int level;
    
    public Knapsack(double maxWeight, List<Product> allProducts){
        if(checkMaxWeight(maxWeight)) this.maxWeight = maxWeight;
        else this.maxWeight = 0;
        products = new ArrayList<>();
        this.allProducts = new ArrayList<>(allProducts);
        level = 0;
    }
    
    public void setMaxWeight(double maxWeight){
        if(checkMaxWeight(maxWeight)) this.maxWeight = maxWeight;
        else this.maxWeight = 0;
    }
    
    public void addProduct(Product product){
        products.add(product);
    }
    public void removeProduct(Product product){
        products.remove(product);
    }
    
    //remove by index, for random remove
    public Product removeIndex(int i){
        return products.remove(i);
    }
    
    //add product if added weight does not exceed maxWeight
    public boolean addProductCheck(Product product){
        if((product.getWeight() + getWeight()) <= getMaxWeight()){
            products.add(product);
            return true;
        }else{
            return false;
        }
    }
    
    private boolean checkMaxWeight(double maxWeight){
        return maxWeight > 0;
    }
    
    //clone the kanpsack and returns it
    public Knapsack clone(){
        Knapsack sack = new Knapsack(maxWeight, allProducts);
        products.forEach((p) -> {
            sack.addProduct(p);
        });
        sack.level = this.level;
        return sack;
    }
    
    // Returns the knapsack children
    public ArrayList<Knapsack> getChildren(){
        ArrayList<Knapsack> res = new ArrayList<>();
        
        if(this.level < allProducts.size()){
            // Child with 0
            Knapsack v0 = clone();
            v0.level++;
            res.add(v0);
            
            // Child with 1
            Knapsack v1 = clone();
            v1.addProduct(allProducts.get(level));
            v1.level++;
            res.add(v1);
        }
        
        return res;
    }
    
    public double getMaxWeight(){
        return maxWeight;
    }
    
    public double getWeight(){
        double weight = 0;
        for(Product p : products){
            weight += p.getWeight();
        }
        return weight;
    }
    public double getValue(){
        double value = 0;
        for(Product p : products){
            value += p.getValue();
        }
        return value;
    }
    public List<Product> getProducts(){
        List<Product> clone = new ArrayList<>(products);
        return clone;
    }

    @Override
    public String toString(){
        String tostring = "";
        tostring = products.stream().map((p) -> p.toString() + "\n").reduce(tostring, String::concat);
        return tostring;
    }
}
