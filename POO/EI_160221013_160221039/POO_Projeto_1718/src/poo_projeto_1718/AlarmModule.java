package poo_projeto_1718;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class AlarmModule implements Serializable{
    private String pin;
    private boolean activated;
    private int method; //1- foto 2- video 3- sirene
    private int intruderNumber;
    
    /**
     * Construtor do modulo de alarme,  inicializa o pin
     * @param pin
     */
    public AlarmModule(String pin){
        setPin(pin);
        activated = false;
        method = 0;
        intruderNumber = 0;
    }
    
    /**
     * Ativa o alarme de uma determinada divisao, liga os sensores e fechas as portas
     * @param room - divisao
     * @return
     */
    public boolean activateAlarms(Room room){
        if(room != null){
            if(room.getMotionSensors().isEmpty() && room.getDoorSensors().isEmpty()){
                System.out.println("There Are No Alarm Sensors In This Room (Motion Or Door Sensor)!\n");
                return false;
            }
            if(activated){
                System.out.println("Alarm Already Activated!\n");
                return false;
            }else{
                room.getMotionSensors().stream().map((ms) -> {
                    ms.setOnOff(true);
                    return ms;
                }).forEachOrdered((ms) -> {
                    ms.setMotion(false);
                });
                room.getDoorSensors().forEach((ds) -> {
                    ds.setOpenClose(false);
                });
                activated = true;
                System.out.println("Alarm Activated! All Sensors On And Doors Closed!\n");
                return true;
            }
        }else{
            System.out.println("Room Invalid!\n");
            return false;
        }
        
    }
    
    /**
     * Desativa o alarme se o pin introduzido for correto
     * @param room - divisao
     * @param pin - pin
     * @return
     */
    public boolean deactivateAlarm(Room room, String pin){
        if(room != null){
            if(activated){
                if(this.pin.equals(pin)){
                    room.getMotionSensors().stream().map((ms) -> {
                        ms.setOnOff(false);
                        return ms;
                    }).forEachOrdered((ms) -> {
                        ms.setMotion(false);
                    });
                    room.getSiren().setOnOff(false);
                    activated = false;
                    System.out.println("Alarm Deactivated! All Sensors Off And Siren Off!\n");
                    return true;
                }else{
                    System.out.println("Wrong Pin!\n");
                    return false;
                }
            }else{
                System.out.println("Alarm Not Activated!");
                return false;
            }
            
        }else{
            System.out.println("Room Invalid!\n");
            return false;
        }
    }
    
    /**
     * Ve se existe algum movimento ou porta aberta numa divisao que tenha o alarme ativado
     * @param room
     * @return
     */
    public boolean intruderDetection(Room room){
        if(activated){
            for(MotionSensor ms : room.getMotionSensors()){
                if(ms.hasMotion()){
                    System.out.println("MOTION!\n");
                    intruderAction(room);
                    return true;
                }
            }
            for(DoorSensor ds : room.getDoorSensors()){
                if(ds.isOpen()){
                    System.out.println("DOOR OPEN!\n");
                    intruderAction(room);
                    return true;
                }
            }
            System.out.println("No Intruder!\n");
            return false;
        }else{
            System.out.println("Alarms Not Activated!\n");
            return false;
        }
    }
    
    /**
     * Se existir movimento ou porta aberta e acionado o alrame dependendo do metodo escolhido pelo utilizador 1 - foto 2 - video 3 - sirene
     * @param room
     */
    private void intruderAction(Room room){
        switch (method) {
            case 1:
                for(Camera c : room.getCameras()){
                    if(c.getPhotoFormats().contains(c.getCurrentFormat())){
                        c.take(room.getRoomType().name() + intruderNumber);
                        System.out.println("INTRUDER! Camera Took a Photo!\n");
                        intruderNumber++;
                    }else{
                        c.changeCurrentFormat(".jpeg");
                        c.take(room.getRoomType().name() + intruderNumber);
                        intruderNumber++;
                        System.out.println("INTRUDER! Camera Took a Photo!\n");
                    }
                }
                break;
            case 2:
                for(Camera c : room.getCameras()){
                    if(c.getVideoFormats().contains(c.getCurrentFormat())){
                        c.take(room.getRoomType().name() + intruderNumber);
                        intruderNumber++;
                        System.out.println("INTRUDER! Camera Took a Video!\n");
                    }else{
                        c.changeCurrentFormat(".flv");
                        c.take(room.getRoomType().name() + intruderNumber);
                        intruderNumber++;
                        System.out.println("INTRUDER! Camera Took a Video!\n");
                    }
                }
                break;
            case 3:
                room.getSiren().setOnOff(true);
                System.out.println("INTRUDER! Siren On!\n");
                break;
            default:
                room.getSiren().setOnOff(true);
                System.out.println("INTRUDER! Siren On!\n");
                break;
        }
    }
    /**
     * permite mudar o pin, verifica se e valido (4 digitos)
     * @param room
     */
    private boolean setPin(String pin){
        List<Integer> numbers;
        numbers = Arrays.asList(0,1,2,3,4,5,6,7,8,9);
        if(pin.length() == 4){
            for(int i = 0; i < 4; i++){
                int num = Character.getNumericValue(pin.charAt(i));
                if(!numbers.contains(num)){
                    return false;
                }
            }
            this.pin = pin;
            return true;
        }else{
            return false;
        }
    }
    
    public boolean isActivated(){
        return activated;
    }
    
    @Override
    public String toString(){
        String str = "Alarm Module\n" + "Activated: ";
        if(activated){
            str += "Yes";
        }else{
            str += "No";
        }
        str += "\nAlarm Method: ";
        switch (method) {
            case 1:
                str += "Photos";
                break;
            case 2:
                str += "Videos";
                break;
            case 3:
                str += "Siren";
                break;
            default:
                str += "Siren";
                break;
        }
        return str + "\n";
    }
}