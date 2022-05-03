package Memento;

import Graph.MyGraph;
import java.util.Stack;

/**
 * Classe CareTaker do padrão Memento
 * @author Gabriel Ambrósio - 160221013
 */
public class GraphCareTaker {
    private Stack<Memento> objMementos = new Stack<Memento>();
    
    /**
     * Metodo que recebe um graph e armazena o memento do mesmo num stack
     * @param graph - MyGraph a guadar
     */
    public void save(MyGraph graph){
        objMementos.push(graph.createMemento());
    }
    
    /**
     * Metodo que restaura o graph ao seu anterior estado
     * @param graph - graph a restaurar
     * @return
     */
    public boolean restore(MyGraph graph){
        if(objMementos.isEmpty()) return false;
        Memento memento = objMementos.pop();
        graph.setMemento(memento);
        return true;
    }
}
