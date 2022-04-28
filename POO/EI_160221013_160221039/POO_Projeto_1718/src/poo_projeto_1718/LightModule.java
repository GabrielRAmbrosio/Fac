package poo_projeto_1718;

import java.io.Serializable;

public class LightModule implements Serializable{
    private int maxIntensity, minIntensity;
    
    /**
     *Construtor do modulo de luz, inicializa a intensidade max e min
     */
    public LightModule(){
        maxIntensity = 100;
        minIntensity = 70;
    }
    
    /**
     *Liga ou desliga todas as lampadads de uma determinada divisao
     * @param room - divisao
     * @param intensity - intensidade da lampada
     * @return <code>true</code> se ligar ou desligar
     *        <code>false</code> se nao
     */
    public boolean setLampsTo(Room room, int intensity){
        if(room != null){
            for(Lamp lamp : room.getLamps()){
                if(intensity > lamp.getIntensity()){
                    room.getLightSensors().forEach((ls) -> {
                        ls.setLight(ls.getRoomLight()+ ((intensity * (100))/20));
                    });
                    lamp.setIntensity(intensity);
                }else if(intensity < lamp.getIntensity()){
                    room.getLightSensors().forEach((ls) -> {
                        ls.setLight(ls.getRoomLight() / (1.5));
                    });
                    lamp.setIntensity(intensity);
                }
            }
            return true;
        }
        System.out.println("Room Invalid!\n");
        return false;
    }
    
    /**
     * Automatiza as lampadas.
     * Ativa as lampadas de uma divisão se a luz da mesma nao estiver dentro do pretendido
     * @param room - divisao
     * @return <code>true</code> se automatizar
     *        <code>false</code> se nao
     */
    public boolean autoLamps(Room room){
        if(room != null){
            if(room.getLightSensors().isEmpty()){
                System.out.println("There Are No Light Sensors In This Room!\n");
                return false;
            }
            if(room.getLamps().isEmpty()){
                System.out.println("There Are No Lamps In This Room\n");
                return false;
            }
            for (LightSensor ls : room.getLightSensors()){
                       if(ls.getRoomLight() > maxIntensity){
                           setLampsTo(room, 0);
                           System.out.println("Light Over Limit! Lamps Turned Off!\n");
                           return true;
                       }else if(ls.getRoomLight() < minIntensity / 3){
                           setLampsTo(room, 20);
                           System.out.println("Lamps Turned On Up To " + 20 + "!\n");
                           return true;
                       }else if((ls.getRoomLight() < minIntensity / 2)){
                           setLampsTo(room, 15);
                           System.out.println("Lamps Turned On Up To " + 15 + "!\n");
                           return true;
                       }else{
                           setLampsTo(room, 10);
                           System.out.println("Lamps Turned On Up To " + 10 + "!\n");
                           return true;
                       }
            }
            System.out.println("Room Light Already Stabilized!\n");
            return true;
        }else{
             System.out.println("Room Invalid!\n");
             return false;
        }
    }
    
    /**
     * Liga ou desliga uma determinada lampada de uma determinada divisao
     * @param room - divisao
     * @param lamp - lampada
     * @param intensity
     * @return <code>true</code> se ligar ou desligar
     *        <code>false</code> se nao
     */
    public boolean setSingleLampTo(Room room, Lamp lamp, int intensity){
        if(room != null){
            if(room.getLamps().isEmpty()){
                System.out.println("There Are No Lamps In This Room\n");
                return false;
            }
            for(Lamp l : room.getLamps()){
                if(l == lamp){
                    if(intensity > lamp.getIntensity()){
                        room.getLightSensors().forEach((ls) -> {
                            ls.setLight(ls.getRoomLight()+ intensity * (2));
                        });
                    l.setIntensity(intensity);
                    System.out.println(lamp.getFullName()+ " Set to " + intensity + "!\n");
                    return true;
                }else{
                        room.getLightSensors().forEach((ls) -> {
                            ls.setLight(ls.getRoomLight() / (1.3));
                        });
                    l.setIntensity(intensity);
                    System.out.println(lamp.getFullName()+ " Set to " + intensity + "!\n");
                    return true;
                }
                }
            }
            System.out.println("Lamp Does Not Exist In This Room!\n");
            return false;
        }else{
            System.out.println("Room Invalid!\n");
            return false;
        }
    }
    
    /**
     * Muda a intensidade max
     * @param val - intensidade
     * @return <code>true</code> se mudar
     *        <code>false</code> se nao
     */
    public boolean setMaxIntensitye(int val){
        if(val > minIntensity && val <= 100){
            maxIntensity = val;
            System.out.println("Max Intensity Updated To " + val + "!\n");
            return true;
        }else{
            System.out.println("Light Intensity Value Invalid! (" + minIntensity + "<value<100)\n");
            return false;
        }
    }

    /**
     * Muda a intensidade min
     * @param val - intensidade
     * @return <code>true</code> se mudar
     *        <code>false</code> se nao
     */
    public boolean setMinIntensitye(int val){
        if(val >= 0 && val < maxIntensity){
            minIntensity = val;
            System.out.println("Min Intensity Updated To " + val + "!\n");
            return true;
        }else{
            System.out.println("Min Light Intensity Value Invalid! (0<value<" + maxIntensity + ")\n");
            return false;
        }
    }

    @Override
    public String toString(){
        return "Light Module\nMax Intensity: " + maxIntensity + "\nMin Intensity: " + minIntensity + "\n";
    }
}
