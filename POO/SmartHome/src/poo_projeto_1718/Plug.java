package poo_projeto_1718;

//import java.util.Timer;
import java.util.ArrayList;
import java.util.List;
//import java.util.TimerTask;


public class Plug extends WifiEquipment implements Wifi{
    //private Timer timer;
    private List<Equipment> equipments;  

    /**
     * Construtor da tomada chama metodo super e inicializa o array de equipamentos
     */
    public Plug() {
        super("Plug");
        equipments = new ArrayList<>();
        //timer = new Timer();
    }

    public List<Equipment> getEquipment(){
        return equipments;
    }
    /*
    public void setOffTimer(long time){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //do something
            }
        }, time);//tempo
    }
    
    public void setOnTimer(long time){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //do something
            }
        }, time);//tempo
    }
    public Timer getTimer(){
        return timer;
    }
    */

    /**
     * Mostra equipamentos ligados a tomada
     */
    public void showEqupments(){
        System.out.println("Equipments:\n");
        equipments.forEach((e) -> {
            e.getFullName();
        });
    }

    /**
     * Como esta classe e um equipamento que possui wifi, implementa uma interface Wifi, 
     * que permite usar este metodo, que liga este equipamento a outro recebido por parametro
     * @param equipment - equipamento
     * @param password - password
     */
    @Override
    public void pairDevice(WifiEquipment equipment, String password) {
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
     *Como esta classe e um equipamento que possui wifi, implementa uma interface Wifi, 
     * que permite usar este metodo, que desliga este equipamento de outro recebido por parametro
     * @param equipment - equipamento
     */
    @Override
    public void unpairDevice(WifiEquipment equipment) {
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
    
    @Override
    public String toString(){
        return super.toString(); //+ connection.toString();
    }
}