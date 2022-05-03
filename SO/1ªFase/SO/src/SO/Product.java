package recursoso;

public class Product {
    
    private double value, weight;
    
    public Product(double value, double weight){
        if(checkDouble(value)) this.value = value;
        else this.value = 0;
        if(checkDouble(weight)) this.weight = weight;
        else this.weight = 0;
    }
    
    public Product(){
        value = 0;
        weight = 0;
    }
    
    public void setValue(double value){
        if(checkDouble(value)) this.value = value;
    }
    public void setWeight(double weight){
        if(checkDouble(weight)) this.weight = weight;
    }
    
    private boolean checkDouble(double n){
        return n > 0;
    }
    
    public double getValue(){
        return value;
    }
    public double getWeight(){
        return weight;
    }
    
    @Override
    public String toString(){
        return "Value: " + value + "\nWeight: " + weight + "\n";
    }
}
