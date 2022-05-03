package poo_projeto_1718;

import java.io.Serializable;

/**
 *
 * @author Gabriel
 */
public class Equipment implements Serializable{
    
    private static int numberOf = 1;
    private int num;
    private final String name;
    private Wifi wifi;
    
    /**
     * Classe super de todos os atuadores e sensores, recebe o nome do mesmo e adiciona um numero a frente para indentificação,
     * este numero aumenta sempre que e criado um novo equipamento
     * @param name
     */
    public Equipment(String name){
        this.name = name;
        num = numberOf;
        numberOf++;
    }
    
    //SELETORES MODIFICADORES

    public String getFullName(){
        return name + " " + num;
    }

    @Override
    public String toString(){
        return getFullName() + " ";
    }
}