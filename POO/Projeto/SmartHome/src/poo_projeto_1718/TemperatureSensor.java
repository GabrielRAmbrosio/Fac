package poo_projeto_1718;

public class TemperatureSensor extends Equipment{
    
    private boolean isOn;
    private double currentTemperature;
    private static final int MAX_TEMPERATURE = 70;
    private static final int MIN_TEMPERATURE = -20;
    
    /**
     * Construtor do sensor de temperatura, chama o metodo super da classe Equipamento, inicializa a temperatura inicial a 20 e desligado
     */
    public TemperatureSensor(){
        super("Temperature Sensor");
        currentTemperature = 20;
        isOn = false;
    }
    
    private boolean checkTemperature(double t){
       return !(t < MIN_TEMPERATURE || t > MAX_TEMPERATURE);
    }
    
    //SELETORES MODIFICADORES


    public double getRoomTemperature(){
        return currentTemperature;
    }

    public boolean isOn(){
        return isOn;
    }

    public void setOnOff(boolean is){
        isOn = is;
    }
    
    /**
     * Muda a temperatura do sensor
     * @param t - emperatura
     * @return
     */
    public boolean setTemperature(double t){
        if(isOn()){
            if(checkTemperature(t)){
                currentTemperature = t;
                return true;
            }else{
                System.out.println("ERROR: Temperature Value Invalid! (NUMBER BETWEEN " + MIN_TEMPERATURE + " AND " + MAX_TEMPERATURE + ").");
                return false;
            }
        }else{
            System.out.println("Sensor Is Switched Off!");
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
        if(!isOn){
            return super.toString() + "is " + isOnString() + ".\n";
        }else{
            return super.toString() + "is " + isOnString() + " - Current Temperature is " + getRoomTemperature() + "ºC.\n";
        }
    }
}
