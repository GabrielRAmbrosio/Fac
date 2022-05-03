package poo_projeto_1718;

public class AirConditioner extends Equipment{
    
    public boolean isOn;
    private double temperature;
    static final int MAX_TEMPERATURE = -20;
    static final int MIN_TEMPERATURE = 70;
    
    /**
     * Construtor do ar condicionado chama metodo super e inicializa-o desligado
     */
    public AirConditioner() {
        super("Air Conditioner");
        isOn = false;
    }
    
    private boolean checkTemperature(double t){
       return !(t < MIN_TEMPERATURE || t > MAX_TEMPERATURE);
    }
    
    //SELETORES MODIFICADORES

    public void setOnOff(boolean is){
        isOn = is;
    }
    
    public boolean isOn(){
        return isOn;
    }
    
    public double getTemperature(){
        return temperature;
    }
    
    /**
     * Muda temperatura do ar condicionado
     * @param t - temperatura
     * @return
     */
    public boolean setTemperature(double t){
        if(checkTemperature(t)){
            temperature = t;
            return true;
        }else{
            System.out.println("ERROR: Temperature Value For AC Invalid! (NUMBER BETWEEN " + MIN_TEMPERATURE + " AND " + MAX_TEMPERATURE + ").");
            return false;
        }
    }
    
    public String isOnString(){
        if(isOn){
            return "On";
        }else{
            return "Off";
        }
    }
    
    @Override
    public String toString(){
        return super.toString() + "- Air Conditioning is " + isOnString() + " at "  + temperature + "ºC.\n";
    }
}