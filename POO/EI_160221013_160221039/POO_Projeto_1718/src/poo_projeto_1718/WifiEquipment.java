package poo_projeto_1718;

import java.util.UUID;

/**
 *
 * @author Gabriel
 */
public class WifiEquipment extends Equipment {
    private final UUID id;
    private UUID pairingId;
    private boolean paired;
    private String password;
    
    /**
     * Classe super para equipamentos com wifi (sensor de movimento e tomada), chama metodo super da classe equipent para incializar o nome
     * cria um id random usando o UUID, inicializa sem estar ligadoa  outro e com uma password 0000 que pode ser mais tarde mudada
     * @param name
     */
    public WifiEquipment(String name){
        super(name);
        id = UUID.randomUUID();
        pairingId = null;
        paired = false;
        password = "0000";
    }
    
    //SELETORES MODIFICADORES


    public UUID getId(){
        return id;
    }

    public UUID getPairingId(){
        return pairingId;
    }

    public boolean isPaired(){
        return paired;
    }

    public void setPaired(boolean pair){
        paired = pair;
    }

    /**
     * Muda o id do equipamento ligado a este
     * @param pair
     */
    public void setPairingId(UUID pair){
        if(pair != null){
            pairingId = pair;
        }
    }

    /**
     * Muda password
     * @param pass
     */
    public void setPassword(String pass){
        if(!pass.equals("")){
            password = pass;
        }else{
            password = "0000";
        }
    }
    
    public String getPassword(){
        return password;
    }
    //OUTROS
    
    @Override
    public String toString(){
        String str = super.toString() + "\n";
        str += "UUID: " + getId();
        if(getPairingId() != null){
            str += "\nPairId: " + getPairingId() + "\n";
        }else{
            str += "\nPairId: Not Connected\n";
        }
        return str;
    }
}