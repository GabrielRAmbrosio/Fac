package poo_projeto_1718;

public class MotionSensor extends WifiEquipment implements Wifi{
    
    private boolean isOn;
    private boolean motion;

    /**
     * Construtor do sensor de movimento chama o metodo super da classe Equipamento, e inicializa o desligado
     */
    public MotionSensor(){
        super("Motion Sensor");
        isOn = false;

    }
    //SELETORES MODIFICADORES

    public boolean hasMotion(){
        return motion;
    }

    /**
     * Muda a existencia de movimento
     * @param m
     */
    public void setMotion(boolean m){
        motion = m;
    }   

    public boolean isOn(){
        return isOn;
    }

    public void setOnOff(boolean is){
        isOn = is;
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
        return super.toString() + "Motion Sensor is " + isOnString() + ".\n";
    }

    /**
     * Como o sensor de movimento e um equipamento que possui wifi, implementa uma interface Wifi, 
     * que permite usar este meodo, que liga este equipamento a outro recebido por parametro
     * @param equipment - equipamento a ligar
     * @param password - password
     */
    @Override
    public void pairDevice(WifiEquipment equipment, String password){
        if(equipment != null){
            if(equipment.isPaired() && equipment.getPairingId() == this.getId()){
                System.out.println("Equipment Already Paired With This One!");
                return;
            }
            if(!equipment.isPaired()){
                if(equipment.getPassword().equals(password)){
                    this.setPairingId(equipment.getId());
                    equipment.setPairingId(this.getId());
                    this.setPaired(true);
                    equipment.setPaired(true);
                    System.out.println("Equipment Paired!");
                }else{
                    System.out.println("Wrog Password!");
                }
            }else{
                System.out.println("Equipment Already Paired With Another One!");
            }
        }else{
            System.out.println("Equipment Invalid!");
        }
    }

    /**
     * Como o sensor de movimento e um equipamento que possui wifi, implementa uma interface Wifi, 
     * que permite usar este metodo, que desliga este equipamento de outro recebido por parametro
     * @param equipment - equipamento a desligar
     */
    @Override
    public void unpairDevice(WifiEquipment equipment){
        if(equipment != null){
            if(equipment.isPaired() && equipment.getPairingId() == this.getId()){
                equipment.setPairingId(null);
                this.setPairingId(null);
                equipment.setPaired(false);
                this.setPaired(false);
                System.out.println("Equipment Unpaired!");
            }else{
                System.out.println("Equipment Not Paired With This One!");
            }
        }else{
            System.out.println("Equipment Invalid!");
        }
    }
}
