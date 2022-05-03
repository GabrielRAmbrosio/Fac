package Data;

/**
 * Classe usada para guardar a informação a guardar nos Edges
 * @author Gabriel Ambrósio - 160221013
 */
public class EdgeData {
    private String str;
    private int cost;
    
    /**
     *Construtor da classe EdgeData, inicializa os atributos
     * @param str - nome
     * @param cost - custo
     */
    public EdgeData(String str, int cost){
        this.str = checkString(str);
        this.cost = checkSInteger(cost);
    }
    
    //metodos usados para validar os atributos passados no construtor ou nos setters
    private String checkString(String str){
        if(str.isEmpty() || str.equals("")) return "N/A";
        return str;
    }
    private int checkSInteger(int num){
        if(num < 0) return 0;
        return num;
    }
    
    /**
     *Metodo que retorna o nome em String
     * @return
     */
    public String getString(){
        return str;
    }

    /**
     *Metodo que retorna o custo em inteiro
     * @return
     */
    public int getCost(){
        return cost;
    }
    
    /**
     *Metodo para alterar o valor do nome
     * @param str - string
     */
    public void setString(String str){
        this.str = checkString(str);
    }
    
    /**
     *Metodo para alterar o valor do custo
     * @param cost - cost
     */
    public void setCost(int cost){
        this.cost = checkSInteger(cost);
    }
    
    /**
     *toString
     * @return
     */
    @Override
    public String toString(){
        return str + " Cost: " + cost;
    }
}
