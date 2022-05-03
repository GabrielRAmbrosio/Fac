package pa_project;

import java.util.Objects;

/**
 * @author Gabriel Ambrósio 160221013 e Hugo Ferreira 160221039
 */
public class Place {
    private int id;
    private String name;

    /**
     *Construtor da classe Place
     * @param id - id do place
     * @param name - nome do place
     */
    public Place(int id, String name) {
        this.id = id;
        this.name = checkString(name);
    }
    
    private String checkString(String str){
        if(str.isEmpty() || str.equals("")) return "N/A";
        return str;
    }
    
    /**
     *Retorna o id da instancia
     * @return id
     */
    public int getId(){
        return id;
    }

    /**
     *Retorna o nome da instancia
     * @return name
     */
    public String getName(){
        return name;
    }
    
    @Override
    public String toString(){
        return "Place\nid: " + id + "\nname: " + name + "\n";
    }
    
    /**
     * equals
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
        final Place other = (Place) obj;
        return this.name.compareToIgnoreCase(other.name) == 0;
    }

    /**
     * hashcode
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.name);
        return hash;
    }
}
