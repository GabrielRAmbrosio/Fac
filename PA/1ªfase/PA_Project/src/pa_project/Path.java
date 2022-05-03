package pa_project;

import java.util.Objects;

/**
 * @author Gabriel Ambrósio 160221013 e Hugo Ferreira 160221039
 */
public class Path {
    private final int id;
    private String type, name;
    private int placeAId, placeBId;
    private boolean navigability;
    private int cost, distance;
    
    /**
     *
     * @param id - id do path
     * @param type - tipo do path
     * @param name - nome do path
     * @param pA - id do placeA
     * @param pB - id do placeB
     * @param navigability - navigability do path
     * @param cost - custo do path
     * @param distance - distancia do path
     */
    public Path(int id, String type, String name, int pA, int pB, boolean navigability, int cost, int distance){
        this.id = id;
        this.type = checkString(type);
        this.name = checkString(name);
        this.placeAId = pA;
        this.placeBId = pB;
        this.navigability = navigability;
        this.cost = checkSInteger(cost);
        this.distance = checkSInteger(distance);
    }
    
    private String checkString(String str){
        if(str.isEmpty() || str.equals("")) return "N/A";
        return str;
    }
    private int checkSInteger(int num){
        if(num < 0) return 0;
        return num;
    }
    
    /**
     *Retorna o id da instancia
     * @return
     */
    public int getId(){
        return id;
    }

    /**
     *Retorna o nome da instancia
     * @return
     */
    public String getName(){
        return name;
    }

    /**
     *Retorna o tipo da instancia
     * @return
     */
    public String getType(){
        return type;
    }

    /**
     *Retorna o custo da instancia
     * @return
     */
    public int getCost(){
        return cost;
    }

    /**
     *Retorna a distancia da instancia
     * @return
     */
    public int getDistance(){
        return distance;
    }
    
    @Override
    public String toString(){
        return "\n\tPath\n\tid: " + id + "\n\tname: " + name + "\n\ttype: " + type + "\n\tplaces id: " + placeAId + "-" + placeBId+ "\n\tnavigability: " + navigability 
                + "\n\tcost: " + cost + "\n\tdistance: " + distance + "\n";
    }
    
    /**
     *equals
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Path other = (Path) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }

    /**
     *hashcode
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + this.id;
        return hash;
    }
}
