package Data;

/**
 * Classe usada para guardar a informação a guardar nos Vertices
 * @author Gabriel Ambrósio - 160221013
 */
public class VertexData {
    
    private String str;
    
    /**
     * Constutor da classe VertexData, inicializa os atributos
     * @param str - string
     */
    public VertexData(String str){
        this.str = checkString(str);
    }   
    
    //metodos usados para validar os atributos passados no construtor ou nos setters
    private String checkString(String str){
        if(str.isEmpty() || str.equals("")) return "N/A";
        return str;
    }
    
    /**
     * Metodo que retorna o nome em String
     * @return
     */
    public String getString(){
        return str;
    }
    
    /**
     * Metodo para alterar o valor do nome
     * @param srt - string
     */
    public void setString(String srt){
        this.str = checkString(str);
    }
    
    /**
     *toString
     * @return
     */
    @Override
    public String toString(){
        return str;
    }
}
