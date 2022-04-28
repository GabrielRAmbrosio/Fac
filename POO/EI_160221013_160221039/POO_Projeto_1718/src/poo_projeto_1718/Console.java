package poo_projeto_1718;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Console implements Wifi, Serializable{
    private final UUID uuid;
    
    private  String name;
    private  String address;
    private final int id;
    private static int n = 1;
    private String password;
    
    private final List<Room> rooms;
    private final List<WifiEquipment> pairedEquipment;
    
    private final TemperatureModule tempModule;
    private final LightModule lightModule;
    private final AlarmModule alarmModule;
    
    /**
     *Construtor da Consola recebe toda a ainformação do cliente e as passwords que ele pretende. Inicializa todos os atributos
     * as divisões, os equipamentos ligados a consola e os modulos
     * @param name - nome
     * @param address - morada
     * @param password - wifi pass
     * @param pin - alarme pin
     */
    public Console(String name, String address, String password, String pin){
        uuid = UUID.randomUUID();
        
        if(checkString(name)){
            this.name = name;
        }
        if(checkString(address)){
            this.address = address;
        }
        if(checkString(address)){
            this.password = password;
        }
        
        id = n;
        n++;
        
        rooms = new ArrayList<>();
        pairedEquipment = new ArrayList<>();
        
        tempModule = new TemperatureModule();
        lightModule = new LightModule();
        alarmModule = new AlarmModule(pin);
    }
    
    /**
     *Verifica se uma string introduzida pelo utilizador é utilizavel
     *@return <code>true</code> se for utilizavel
     *        <code>false</code> se nao for
     */
    private boolean checkString(String str){
        return !str.isEmpty();
    }
    
    //SELETORES MODIFICADORES//OUTROS

    /**
     *Retorna o ID do cliente
     * @return - id
     */
    public int getClientId(){
        return id;
    }

    /**
     *Retorna o nome do cliente
     * @return - name
     */
    public String getClientName(){
        return name;
    }

    /**
     *Retorna a morada do cliente
     * @return - address
     */
    public String getClientAddress(){
        return address;
    }

    /**
     *Retorna as divisoes da consola
     * @return - rooms
     */
    public List<Room> getRooms(){
        return rooms;
    }

    /**
     *Retorna o modulo da temperatura
     * @return - tempModule
     */
    public TemperatureModule getTemperatureModule(){
        return tempModule;
    }

    /**
     *Retorna o modulo da luz
     * @return - lightModule
     */
    public LightModule getLightModule(){
        return lightModule;
    }

    /**
     *Retorna o modulo do alarme
     * @return - alarmModule
     */
    public AlarmModule getAlarmModule(){
        return alarmModule;
    }

    /**
     *Permite mudar o nome do cliente
     * @param str - nome
     */
    public void setName(String str){
        if(checkString(str)){
            name = str;
        }
    }

    /**
     *Permite mudar a morada do cliente
     * @param str - morada
     */
    public void setAddress(String str){
        if(checkString(str)){
            address = str;
        }
    }

    /**
     *Permite mudar a password do cliente
     * @param str - password
     */
    public void setPassword(String str){
        if(checkString(str)){
            password = str;
        }
    }
    //OUTROS
    
    /**
     *Conectar um equipamento a consola atraves da password
     * @param equipment - equipamento a ligara a consola
     * @param password - pass da consola
     */
    @Override
    public void pairDevice(WifiEquipment equipment, String password) {
        if(equipment != null){
            if(pairedEquipment.contains(equipment)){
                System.out.println("Equipment Already Paired To The Console!");
                return;
            }if(equipment.isPaired()){
                System.out.println("Equipment Already Paired!");
            }else{
                if(this.password.equals(password)){
                    pairedEquipment.add(equipment);
                    equipment.setPaired(true);
                    System.out.println("Equipment Paired!");
                }else{
                    System.out.println("Wrong Password!");
                }
            }
        }
    }
    
    /**
     *Desconecta um equipamento a consola
     * @param equipment - equipamento a desligar
     */
    @Override
    public void unpairDevice(WifiEquipment equipment) {
        if(equipment != null){
            if(pairedEquipment.contains(equipment)){
                pairedEquipment.remove(equipment);
                equipment.setPaired(false);
                System.out.println("Equipment Unpaired!");
            }else{
                System.out.println("Equipment Not Paired!");
            }
        }else{
            System.out.println("Invalid Equipment!");
        }
    }
    
    /**
     *Adicionar uma divisao a consola
     * @param room - divisao a adicionar
     */
    public void addRoom(Room room){
        rooms.add(room);
    }

    /**
     *remover uma divisao da consola
     * @param room - divisao a remover
     */
    public void removeRoom(Room room){
        rooms.remove(room);
    }

    /**
     *Mostra as divisoes que estao adicionadas a consola
     */
    public void showRooms(){
        rooms.forEach((r) -> {
            System.out.println(r.toString());
        });
    }

    @Override
    public String toString(){
        return "Name: " + getClientName() + "\nAddress: " + getClientAddress() + "\nID: " + getClientId() + "\n";
    }
}
