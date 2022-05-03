package poo_projeto_1718;

public class Lamp extends Equipment{
    private int intensity;
    private static final int MAX_LIGHT = 20;
    private static final int MIN_LIGHT = 0;
    
    /**
     * Construtor da lampada, chama o metodo super da classe Equipment e inicializa a desligada
     */
    public Lamp() {
        super("Lamp");
        intensity = 0;
    }
    
    private boolean checkIntensity(int i){
       return !(i < MIN_LIGHT || i > MAX_LIGHT);
   }

//SELETORES MODIFICADORES

   public int getIntensity(){
       return intensity;
   }

    /**
     * Muda valor de intensidade da lampada
     * @param i - intensidade
     * @return
     */
    public boolean setIntensity(int i){
       if(checkIntensity(i)){
           intensity = i;
           return true;
       }else{
           System.out.println("ERROR: Lamp Intensity Value Invalid! (NUMBER BETWEEN " + MIN_LIGHT + " AND " + MAX_LIGHT + ").");
           return false;
       }
   }

    @Override
    public String toString(){
        return super.toString() + "- Lamp Is At " + intensity + " Intensity.\n";
    }
}
