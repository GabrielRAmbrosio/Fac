package poo_projeto_1718;

public class DoorSensor extends Equipment{ 
    private boolean isOpen;
    
    /**
     * Construtor do sensor de porta, chama o metodo super da classe Equipment e inicializa o desligado
     */
    public DoorSensor() {
        super("Door Sensor");
        isOpen = false;
    }
    
    //SELETORES MODIFICADORES

    public boolean isOpen(){
        return isOpen;
    }
    
    public void setOpenClose(boolean is){
        isOpen = is;
    }
    
    public String isOnOpenString(){
        if(isOpen){
            return "Open";
        }else{
            return "Closed";
        }
    }
    
    @Override
    public String toString(){
        return super.toString() + "- Door is " + isOnOpenString() + ".\n";
    }
}
