package poo_projeto_1718;

public class LightSensor extends Equipment{
    private double currentLight;
    private final int MAX_LIGHT = 100;
    private final int MIN_LIGHT = 0;
    
    /**
     * Construtor do sensor de luz chama o metodo super da classe Equipamento
     */
    public LightSensor() {
        super("Light Sensor");
        currentLight = 50;
    }
    
    private boolean checkLight(double l){
       return !(l < MIN_LIGHT || l > MAX_LIGHT);
    }
    
    //SELETORES MODIFICADORES

    public double getRoomLight(){
        return currentLight;
    }

    /**
     * Muda a luz do sensor
     * @param l
     * @return
     */
    public boolean setLight(double l){
        if(checkLight(l)){
            currentLight = l;
            return true;
        }else{
            System.out.println("ERROR: Light Value Invalid! (NUMBER BETWEEN " + MIN_LIGHT + " AND " + MAX_LIGHT + ").");
            return false;
        }
    }
    
    @Override
    public String toString(){
        return super.toString() + "- Ligth Intensity: " + String.format("%.2f", getRoomLight()) + "%.\n";
    }
}
