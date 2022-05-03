package Data;   

/**
 * Classe usada para guardar a informação necessária a mostrar na table view (chart) no MainMenu
 * @author Gabriel Ambrósio - 160221013
 */
public class DijkstraData {
    
    private String start, path, end, cost;
    
    /**
     *Construtor da classe DijkstraData, recebe os valores e inicializa os atributos
     * @param s - Vertex (start), é usado apenas o seu nome, através do metodo getString() de VertexData
     * @param p - Path, string que contêm o caminho completo de s (start) a e (end)
     * @param e - Vertex (end), é usado apenas o seu nome, através do metodo getString() de VertexData
     * @param c - Cost, inteiro que armazena o custo do path recebido (é armazenado como string)
     */
    public DijkstraData(VertexData s, String p, VertexData e, int c){
        start = s.getString();
        path = p;
        end = e.getString();
        cost = ""+c;
    }
    
    //metodos usados para validar os atributos passados no construtor ou nos setters
    private String checkString(String str){
        if(str.isEmpty() || str.equals("")) return "N/A";
        return str;
    }
    
    /**
     *Metodo para alterar o nome no vertice start
     * @param s- vertexdata
     */
    public void setStart(VertexData s){
        start = s.getString();
    }

    /**
     *Metodo para alterar o path
     * @param p - string
     */
    public void setPath(String p){
        path = checkString(p);
    }

    /**
     *Metodo para obter o nome do vertice start
     * @return
     */
    public String getStart(){
        return start;
    }

    /**
     *Metodo para receber o path
     * @return
     */
    public String getPath(){
        return path;
    }

    /**
     *Metodo para alterar o nome no vertice end
     * @param e - vertex data
     */
    public void setEnd(VertexData e){
        end = e.getString();
    }

    /**
     *Metodo para obter o nome do vertice end
     * @return
     */
    public String getEnd(){
        return end;
    }
    
    /**
     *metodo para alterar o valor do custo (recebe um inteiro, armazena como string)
     * @param c - cost
     */
    public void setCost(int c){
        cost = "" + c;
    }

    /**
     *Metodo para obter o nome do custo
     * @return
     */
    public String getCost(){
        return cost;
    }
}
