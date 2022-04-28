package poo_projeto_1718;

import java.io.Serializable;

public class TemperatureModule implements Serializable{
    private double temperature, variation;
    
    /**
     *Construtor do modulo da temperatura, inicializa a temperatura pretendida pelo utilizador e a variaçao max
     */
    public TemperatureModule(){
        temperature = 20;
        variation = 2;
    }
    
    /**
     *Liga ou desliga todos os sensores de temperatura de uma determinada divisao
     * @param room - divisao pretendida
     * @param onOff - ligar ou desligar
     * @return <code>true</code> se ligar ou desligar
     *        <code>false</code> se nao
     */
    public boolean setSensorsTo(Room room, boolean onOff){
        if(room != null){
            room.getTempSensors().forEach((ts) -> {
                ts.setOnOff(onOff);
            });
            System.out.print("All Room Sensors Are");
            if(onOff){
                System.out.println(" On!\n");
            }else{
                System.out.println(" Off!\n");
            }
            return true;
        }
        System.out.println("Room Invalid!\n");
        return false;
    }
    
    /**
     *Liga ou desliga todos os ar condicionados de uma determinada divisao
     * @param room - divisao
     * @param onOff - ligar ou desligar 
     * @return <code>true</code> se ligar ou desligar
     *        <code>false</code> se nao
     */
    public boolean setAcsTo(Room room, boolean onOff){
        if(room != null){
            room.getAcs().forEach((ac) -> {
                ac.setOnOff(onOff);
            });
            System.out.print("All Room Air Conditioners Are ");
            if(onOff){
                System.out.println("On!\n");
            }else{
                System.out.println("Off!\n");
            }
            return true;
        }
        System.out.println("Room Invalid!\n");
        return false;
    }
    
    /**
     * Automatiza os arcondicionados.
     * Ativa os ar condicionados de uma divisão se a temperatura da mesma nao estiver dentro do pretendido
     * @param room - divisao a automatizar
     * @return <code>true</code> se automatizar
     *        <code>false</code> se nao
     */
    public boolean autoAirConditioners(Room room){
        if(room != null){
            if(room.getTempSensors().isEmpty()){
                System.out.println("There Are No Temperature Sensors In This Room!\n");
                return false;
            }
            if(room.getAcs().isEmpty()){
                System.out.println("There Are No Acs In This Room!\n");
                return false;
            }
            setSensorsTo(room, true);
            for (TemperatureSensor ts : room.getTempSensors()){
                       if(ts.getRoomTemperature() > temperature + variation || ts.getRoomTemperature() < temperature - variation){
                           System.out.println("Temperature Out Of Interval (" + ts.getRoomTemperature() + ")! AC's Turned On At " + temperature + "ºC\n");
                           setAcsTo(room, true);
                           room.getAcs().stream().map((ac) -> {
                               ac.setTemperature(temperature);
                               return ac;
                           }).forEachOrdered((_item) -> {
                               room.getTempSensors().forEach((tss) -> {
                                   tss.setTemperature(temperature);
                               });
                           });
                           return true;
                       }
            }
            System.out.println("Temperature Already In Interval!\n");
            return true;
        }else{
             System.out.println("Room Invalid!\n");
             return false;
        }
    }
    
    /**
     * Muda a temperatura pretendida pelo utilizador
     * @param val - temperatura
     * @return <code>true</code> se mudar
     *        <code>false</code> se nao
     */
    public boolean setTemperature(double val){
        if(val > 10 && val < 30){
            temperature = val;
            return true;
        }else{
            temperature = 25;
            System.out.println("Temperature Value Invalid! (-20<value<70) set to 25.");
            return false;
        }
    }

    /**
     * Muda a variação de temperatura pretendida pelo utilizador
     * @param val - variação
     * @return <code>true</code> se mudar
     *        <code>false</code> se nao
     */
    public boolean setVariation(double val){
        if(val > 0){
            variation = val;
            return true;
        }else{
            System.out.println("Variation Value Invalid! (value>0)");
            return false;
        }
    }
    
    @Override
    public String toString(){
        return "Temperature Module\nTemperature: " + temperature + "\nVariation: " + variation + "\n";
    }
    
}