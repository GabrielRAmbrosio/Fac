package poo_projeto_1718;


public class Siren extends Equipment{

    public int intensity;
    public boolean isOn;
    static final int MAX_INTENSITY = 10;
    static final int MIN_INTENSITY = 0;
    
    /**
     * Construtor da sirene chama metodo super e inicializa a desligada e com uma intensidade de 5
     */
    public Siren() {
        super("Siren");
        isOn = false;
        intensity = 5;
    }
    
    private boolean checkIntensity(int i){
       return !(i < 0 || i > 10);
    }
    
    //SELETORES MODIFICADORES

    public void setOnOff(boolean is){
        isOn = is;
    }

    public int getIntensity(){
        return intensity;
    }

    public boolean isOn(){
        return isOn;
    }

    /**
     * Muda intensidade da sirene
     * @param i
     * @return
     */
    public boolean setIntensity(int i){
        if(checkIntensity(i)){
            intensity = i;
            return true;
        }else {
            System.out.println("ERROR: Intensity Value For Siren Invalid! (NUMBER BETWEEN " + MIN_INTENSITY + " AND " + MAX_INTENSITY + ").");
            return false;
        }
    }

    public String isOnString(){
        if(isOn){
            return "On. With " + intensity + " Intensity.";
        }else{
            return "Off";
        }
    }
    
    @Override
    public String toString(){
        return super.toString() + "- Siren is " + isOnString() + "\n";
    }
}